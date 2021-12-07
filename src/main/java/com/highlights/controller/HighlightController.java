package com.highlights.controller;

import com.highlights.common.entity.Content;
import com.highlights.common.entity.Highlight;
import com.highlights.service.ContentCreationService;
import com.highlights.service.HighlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;



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

    @GetMapping(value = "/highlight")
    public Highlight getNextHighlight()  {
        return highlightService.getNextHighlight();
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