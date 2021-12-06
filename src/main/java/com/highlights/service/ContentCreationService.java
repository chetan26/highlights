package com.highlights.service;

import com.highlights.common.entity.Content;
import com.highlights.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author curkudkar on 12/5/21
 */
@Service
public class ContentCreationService {

    @Autowired
    private ContentRepository contentRepository;

    static List<Content> contentList=new ArrayList<>();

    static {
        Content c1=new Content("content1","How to be politically correct","html","/highlights/content1/politics.jpg","10m",0,"POLITICALLY_CORRECT",1,"");
        Content c2=new Content("content2","How to play Soccer","pdf","/highlights/content2/soccer.png","30m",0,"SOCCER",1,"");
        Content c3=new Content("content3","Astronomy","video","/highlights/assets/placeholder.jpg","1h",0,"ASTRONOMY",1,"");
        contentList.add(c1);contentList.add(c2);contentList.add(c3);
    }

    public void createOrUpdateContent(){
        System.out.println("Create/Update Content Data");
        if(!contentList.isEmpty()){
            contentList.forEach(c->{
                contentRepository.save(c);
            });

        }
    }
}
