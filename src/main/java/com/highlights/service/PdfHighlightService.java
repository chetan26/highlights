package com.highlights.service;

import com.highlights.common.entity.Highlight;
import org.springframework.stereotype.Service;

/**
 * @author curkudkar on 12/5/21
 */
@Service
public class PdfHighlightService implements HighlightDataService {
    @Override
    public void persistHighlightData(Highlight highlight) {

    }

    @Override
    public boolean canHandle(String type) {
        return "pdf".equals(type);
    }
}
