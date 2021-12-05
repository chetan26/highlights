package com.highlights.demo;

import org.springframework.beans.factory.annotation.Autowired;
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
  }


}
