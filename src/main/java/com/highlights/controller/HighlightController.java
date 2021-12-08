package com.highlights.controller;

import com.highlights.common.entity.Content;
import com.highlights.common.entity.Highlight;
import com.highlights.configuration.HighlightsApplicationContext;
import com.highlights.service.ContentCreationService;
import com.highlights.service.HighlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * @author curkudkar on 12/5/21
 */
@RestController
@RequestMapping("/highlights")
public class HighlightController {


    @Autowired
    private HighlightService highlightService;

    @Autowired
    private ContentCreationService contentCreationService;

    @GetMapping(value = "/create-contents")
    public void createSeedData()  {
        contentCreationService.createOrUpdateContent();
    }

    @GetMapping(value = "/available-contents")
    public List<Content> getAvailableContent()  {
        return highlightService.getAvailableContent();
    }

    @GetMapping(value = "/content/{contentId}")
    public Content getAvailableContent(@PathVariable(value="contentId")String contentId)  {
        return highlightService.getContentById(contentId).get();
    }

    @GetMapping(value = "highlight", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Highlight> getAllHighlight()  {
        return highlightService.getAllHighlights();
    }

    @GetMapping(value = "/highlight/{highlightId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Highlight getHighlightById(@PathVariable(value="highlightId")String highlightId)  {
        return highlightService.getHighlightById(highlightId);
    }

    @GetMapping(value = "{userId}/content/{contentId}/highlights", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Highlight> getHighlightByUserIdandContentId(@PathVariable(value="contentId")String contentId, @PathVariable(value="userId")String userId)  {
        return highlightService.getHighlightByUserIdandContentId(userId,contentId);
    }

    @PutMapping(value = "/me/config")
    public Highlight saveMyConfig()  {
        return null;
    }

    @DeleteMapping(value = "/{highlightId}")
    public void deleteHighlightById(@PathVariable(value="highlightId")String highlightId)  {
        highlightService.deleteHighlightById(highlightId);
    }
    @GetMapping(value = "/content/{contentId}/highlights",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Highlight> getContentHighlights(@PathVariable(value="contentId")String contentId)  {
        return highlightService.getContentHighlights(contentId);
    }

    @PostMapping(value = "/content/highlight")
    public void saveContentHighlight(@RequestBody Highlight highlight)  {
        highlightService.saveHighlights(highlight);
    }

    @GetMapping(value = "/oneHighlight")
    public Highlight getNextHighlight()  {
        Highlight highlight = highlightService.getNextHighlight();
        if(highlight == null)
            return highlight;

        Optional<Content> maybeContent = highlightService.getContentById(highlight.getContentId());
        maybeContent.ifPresent(content -> {
            highlight.setContentTitle(content.getTitle());
            //TODO: fix this url in the db scripts
            highlight.setContentImgUrl(content.getId()+"/"+ content.getImgUrl());
            if("video".equals(highlight.getType())) {
                highlight.setContentLaunchUrl("/highlights/"+ highlight.getId() + ".mp4");
            } else {
                highlight.setContentLaunchUrl(content.getLaunchUrl());
            }
        });

        return highlight;
    }

    @GetMapping(value = "/me")
    public List<Highlight> getUserHighlights()  {
        return highlightService.getAllUserHighlights();
    }

    @GetMapping(value = "/html/{contentId}",produces = MediaType.TEXT_PLAIN_VALUE)
    public String getContent(@PathVariable(value="contentId")String contentId){
        return highlightService.getContent(contentId,"html");
    }



}