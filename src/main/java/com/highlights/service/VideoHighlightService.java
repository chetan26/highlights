package com.highlights.service;

import com.highlights.common.entity.Highlight;
import com.highlights.configuration.HighlightsApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author curkudkar on 12/5/21
 */
@Service
public class VideoHighlightService implements HighlightDataService {

    @Override
    public void persistHighlightData(Highlight highlight,String finalContentLocation) {
        //fetch the details of the video from contentId;
        //fetch the original video from the contentId
        //use ffmpeg to trim the video and save the highlight to content base directory with the contentId;
       try {
           String contentRepoLocation = HighlightsApplicationContext.getApplicationContext().getEnvironment().getProperty("com.highlights.content.basePath", "/resources");

           String from = highlight.getTrim().getFrom();
           String to = highlight.getTrim().getTo();
           String videoPath = contentRepoLocation+ File.separator+finalContentLocation;
           String outputFileName = contentRepoLocation+File.separator+"highlights"+File.separator+highlight.getId()+".mp4";


           String[] ffmpeg = new String[]{"ffmpeg", "-ss", from, "-i", videoPath, "-to", to, "-c", "copy", outputFileName};
           System.out.println("trim video");
           Process p = Runtime.getRuntime().exec(ffmpeg);
       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }

    @Override
    public boolean canHandle(String type) {
        return "video".equals(type);
    }
}
