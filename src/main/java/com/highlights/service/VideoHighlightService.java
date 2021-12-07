package com.highlights.service;

import com.highlights.common.entity.Highlight;
import com.highlights.configuration.HighlightsApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

           String from = "00:"+highlight.getTrim().getFrom();
           String to = "00:"+highlight.getTrim().getTo();

           DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
           Date reference = dateFormat.parse(from);
           Date date = dateFormat.parse(to);
           long seconds = (date.getTime() - reference.getTime()) / 1000L;
           System.out.println(seconds);
           String updatedTo="00:00:"+String.valueOf(seconds);
           //date conversion

           String videoPath = contentRepoLocation+ File.separator+finalContentLocation;
           String outputFileName = contentRepoLocation+"/highlights/"+highlight.getId()+".mp4";


           String[] ffmpeg = new String[]{"ffmpeg", "-ss", from, "-i", videoPath, "-to", updatedTo, "-c", "copy", outputFileName};
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
