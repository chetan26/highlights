package com.highlights.service;

import com.highlights.common.entity.Highlight;
import com.highlights.configuration.HighlightsApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author curkudkar on 12/5/21
 */
@Service
public class VideoHighlightService implements HighlightDataService {
    @Override
    public void persistHighlightData(Highlight highlight) {
        //fetch the details of the video from contentId;
        //fetch the original video from the contentId
        //use ffmpeg to trim the video and save the highlight to content base directory with the contentId;
        HighlightsApplicationContext.getApplicationContext().getEnvironment().getProperty("com.highlights.content.basePath","/resources");

    }

    @Override
    public boolean canHandle(String type) {
        return "video".equals(type);
    }
}
