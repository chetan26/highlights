package com.highlights.service;

import com.highlights.common.entity.Content;
import com.highlights.common.entity.Context;
import com.highlights.common.entity.Highlight;
import com.highlights.repository.HighlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;


/**
 * @author curkudkar on 12/5/21
 */
@Service
public class HighlightService {

    @Autowired
    private HighLightServiceFactory highLightFactory;

    @Autowired
    private HighlightsRepository highlightsRepository;

    public void saveHighlights(Highlight highlightData){
        highLightFactory.getHighlightProvider(highlightData.getType());
        //after the highlight factory has done their custom implementation
        //create a highlight entry in mongo for the content. ui should send a uniqueId for every highlight.
        //the highlight will be persisted in mongo for that content as well as the content repository and will be the unique identifier in case of fetch
        Highlight highlight=highlightsRepository.findByIdAndContentId(highlightData.getId(),highlightData.getContentId());
        if(highlight!=null){
            Context newContext=highlight.getContext();
            if(newContext!=null){
                highlight.setContext(newContext);
            }
            highlight.setUpdatedOn(Instant.now().toString());
        }else{
            highlightsRepository.save(highlight);
        }
    }


}
