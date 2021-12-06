package com.highlights.repository;

import com.highlights.common.entity.Content;
import com.highlights.common.entity.Highlight;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author curkudkar on 12/5/21
 */
public interface HighlightsRepository extends MongoRepository<Highlight,String> {

    public Highlight findByIdAndContentId(String id, String contentId);
}
