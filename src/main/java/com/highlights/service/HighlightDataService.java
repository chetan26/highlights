package com.highlights.service;

import com.highlights.common.entity.Highlight;

/**
 * @author curkudkar on 12/5/21
 */
public interface HighlightDataService {

    public void persistHighlightData(Highlight highlight,String finalContentLocation) ;

    public boolean canHandle(String type);
}
