package com.highlights.service;

import com.highlights.common.entity.Content;
import com.highlights.common.entity.Context;
import com.highlights.common.entity.Highlight;
import com.highlights.repository.ContentRepository;
import com.highlights.repository.ContentRepository;
import com.highlights.repository.HighlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        highLightFactory.getHighlightProvider(highlightData.getType()).persistHighlightData(highlightData);
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
            highlight.setUpdatedOn(Instant.now().toString());
            highlight.setUserId(loggedInUser);
            highlightsRepository.save(highlight);
        } else {
            Highlight high = Highlight.HighlightBuilder.aHighlight()
                    .withContentId(highlightData.getContentId())
                    .withContext(highlightData.getContext())
                    .withCreatedOn(Instant.now().toString())
                    .withId(highlightData.getId())
                    .withLocation(highlightData.getLocation())
                    .withSource(highlightData.getSource())
                    .withText(highlightData.getText())
                    .withTrim(highlightData.getTrim())
                    .withType(highlightData.getType())
                    .withUserId(loggedInUser)
                    .build();
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

    public List<Highlight> getHighlightByUserIdandContentId(String userId,String contentId){
        return highlightsRepository.findByUserIdAndContentId(userId,contentId, Sort.by(Sort.Direction.DESC, "createdOn"));
    }

    public List<Highlight> getHighlightByUserId(String userId){
        return highlightsRepository.findByUserId(userId);
    }

    public List<Highlight> getAllHighlights(){
        return highlightsRepository.findAll();
    }
    public void deleteHighlightById(String id){
        highlightsRepository.deleteById(id);
    }
}
