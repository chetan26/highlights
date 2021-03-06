package com.highlights.service;

import com.highlights.common.entity.Content;
import com.highlights.common.entity.Context;
import com.highlights.common.entity.Highlight;
import com.highlights.configuration.HighlightsApplicationContext;
import com.highlights.repository.ContentRepository;
import com.highlights.repository.HighlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.nio.file.Files;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author curkudkar on 12/5/21
 */
@Service
public class HighlightService {

    @Autowired
    private HighLightServiceFactory highLightFactory;

    @Autowired
    private HighlightsRepository highlightsRepository;

    @Autowired
    private ContentRepository contentRepository;

    public void saveHighlights(Highlight highlightData) {
        String loggedInUser=(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String contentRepoLocation = HighlightsApplicationContext.getApplicationContext().getEnvironment().getProperty("com.highlights.content.basePath", "/resources");
        //get actual content location and send it ahead
        Optional<Content> content=contentRepository.findById(highlightData.getContentId());
        String finalContentLocation="";
        if(content.isPresent()){
             finalContentLocation=content.get().getLaunchUrl();
        }



        highLightFactory.getHighlightProvider(highlightData.getType()).persistHighlightData(highlightData,finalContentLocation);
        //after the highlight factory has done their custom implementation
        //create a highlight entry in mongo for the content. ui should send a uniqueId for every highlight.
        //the highlight will be persisted in mongo for that content as well as the content repository and will be the unique identifier in case of fetch
        //application.properties has the property for content rep
        //save highlights for the logged in user
        Highlight highlight = highlightsRepository.findByIdAndContentIdAndUserId(highlightData.getId(), highlightData.getContentId(),loggedInUser);
        if (highlight != null) {
            Context newContext = highlight.getContext();
            if (newContext != null) {
                highlight.setContext(newContext);
            }
            highlight.setUpdatedOn(LocalDateTime.now());
            highlight.setUserId(loggedInUser);
            highlight.setAccessed(false);
            highlightsRepository.save(highlight);
        } else {
            Highlight high = Highlight.HighlightBuilder.aHighlight()
                    .withContentId(highlightData.getContentId())
                    .withContext(highlightData.getContext())
                    .withCreatedOn(LocalDateTime.now())
                    .withId(highlightData.getId())
                    .withLocation(highlightData.getLocation())
                    .withSource(highlightData.getSource())
                    .withText(highlightData.getText())
                    .withTrim(highlightData.getTrim())
                    .withType(highlightData.getType())
                    .withUserId(loggedInUser)
                    .build();
            high.setAccessed(false);
            highlightsRepository.save(high);
        }
    }

    public Highlight getHighlightById(String highlightId){
        Optional<Highlight> highlight = highlightsRepository.findById(highlightId);
        return highlight.isPresent()?highlight.get():null;
    }

    public List<Content> getAvailableContent(){
        return contentRepository.findAll();
    }
    public List<Highlight> getContentHighlights(String contentId){
        return highlightsRepository.findByContentId(contentId);
    }

    public Optional<Content> getContentById(String contentId){
        return contentRepository.findById(contentId);

    }
    //used only for html content
    public String getContent(String contentId,String type){
        String output="";
        try {
            if ("html".equals(type)) {
                Optional<Content> content = contentRepository.findById(contentId);
                if (content.isPresent()) {
                    Content content1 = content.get();
                    String launchUrl = content1.getLaunchUrl();

                    String contentRepoLocation = HighlightsApplicationContext.getApplicationContext().getEnvironment().getProperty("com.highlights.content.basePath", "/resources");
                    File f = new File(contentRepoLocation+ File.separator + launchUrl);
                    output=new String ( Files.readAllBytes(f.toPath()));
                }
            }
        }catch (Exception e){
            System.out.println("Error occurred "+ e);
        }

        return output;
    }

    public List<Highlight> getHighlightByUserIdandContentId(String userId,String contentId){
        return highlightsRepository.findByUserIdAndContentId(userId,contentId, Sort.by(Sort.Direction.DESC, "createdOn"));
    }

    public List<Highlight> getHighlightByUserId(String userId){
        return highlightsRepository.findByUserId(userId,Sort.by(Sort.Direction.ASC, "createdOn"));
    }

    public List<Highlight> getAllHighlights(){
        return highlightsRepository.findAll();
    }
    public void deleteHighlightById(String id){
        highlightsRepository.deleteById(id);
    }

    public Highlight getNextHighlight(){
        String loggedInUser=(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<List<Highlight>> highlights = highlightsRepository.findByUserIdAndAccessedFalse(loggedInUser,Sort.by(Sort.Direction.ASC, "createdOn"));
        if(highlights.isPresent() && !CollectionUtils.isEmpty(highlights.get())){
            Highlight highlight = highlights.get().get(0);
            highlight.setUpdatedOn(LocalDateTime.now());
            highlight.setAccessed(true);
            highlightsRepository.save(highlight);
            return highlight;
        }
        //when all highlights are accessed, refresh access status and return oldest highlight
        else{
            List<Highlight> all = highlightsRepository.findByUserId(loggedInUser,Sort.by(Sort.Direction.ASC, "createdOn"));
            if(!CollectionUtils.isEmpty(all)){
                all.forEach(highlight -> {
                    highlight.setAccessed(false);
                });
                highlightsRepository.saveAll(all);
                Highlight oldest = all.get(0);
                oldest.setAccessed(true);
                highlightsRepository.save(oldest);
                return oldest;
            }
            return null;
        }
    }

    public List<Highlight> getAllUserHighlights(){
        String loggedInUser=(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Highlight> highlights = highlightsRepository.findByUserId(loggedInUser,Sort.by(Sort.Direction.ASC, "createdOn"));

        if(!CollectionUtils.isEmpty(highlights)){
            highlights.forEach(highlight -> {
                Optional<Content> maybeContent = getContentById(highlight.getContentId());
                maybeContent.ifPresent(content -> {
                    highlight.setContentTitle(content.getTitle());
                    //TODO: fix this url in the db scripts
                    highlight.setContentImgUrl(content.getId()+"/"+ content.getImgUrl());
                    highlight.setContentLaunchUrl(content.getLaunchUrl());
                });
            });
            return highlights;
        }
        return null;
    }
}
