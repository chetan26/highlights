package com.highlights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author curkudkar on 12/5/21
 */
@Component
public class HighLightServiceFactory {

    @Autowired
    List<HighlightDataService> highlightDataServices;

    public HighlightDataService getHighlightProvider(String type){
        HighlightDataService service=getHighlightService(type);
        return service;
    }

    private HighlightDataService getHighlightService(String type){
        HighlightDataService dataService=null;
        for(HighlightDataService service:highlightDataServices){
            if (service.canHandle(type)) {
                dataService=service;
            }
        }
        return dataService;
    }

}
