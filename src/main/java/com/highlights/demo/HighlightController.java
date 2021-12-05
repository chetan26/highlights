package com.highlights.demo;

import com.highlights.common.entity.Content;
import com.highlights.common.entity.Highlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/highlights")
public class HighlightController {

    @Autowired
    HighlightService highlightService;

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
    public void saveContentHighlight()  {
    }

}