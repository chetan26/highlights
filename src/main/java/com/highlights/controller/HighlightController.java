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
        return null;
    }

    @GetMapping(value = "/highlight", produces = MediaType.APPLICATION_JSON_VALUE)
    public Highlight getHighlight(@RequestBody String highlightId)  {
        Highlight h = new Highlight(); h.setContentId("test");
        return h;
    }

    @PutMapping(value = "/me/config")
    public Highlight saveMyConfig()  {
        return null;
    }

    @GetMapping(value = "/content/{contentId}/highlights",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Highlight> getContentHighlights(@PathVariable(value="contentId")String contentId)  {
        return null;
    }

    @PostMapping(value = "/content/highlight")
    public void saveContentHighlight(@RequestBody Highlight highlight)  {
        highlightService.saveHighlights(highlight);
    }

}