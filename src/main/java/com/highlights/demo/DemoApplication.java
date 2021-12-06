package com.highlights.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.highlights")
@EnableSwagger2
@EnableMongoRepositories("com.highlights.repository")
public class DemoApplication {


  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
    /*try {
      String from = "00:00:10";
      String to = "00:00:20";
      String videoPath = "/Users/curkudkar/Desktop/test.mp4";
      String outputFileName = "/Users/curkudkar/Desktop/output.mp4";


      String[] ffmpeg = new String[]{"ffmpeg", "-ss", from, "-i", videoPath, "-to", to, "-c", "copy", outputFileName};
      System.out.println("trim video");
      Process p = Runtime.getRuntime().exec(ffmpeg);
    }catch (Exception e){

    }*/

  }


}
