package com.highlights.repository;

import com.highlights.common.entity.Highlight;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author curkudkar on 12/5/21
 */
public interface HighlightsRepository extends MongoRepository<Highlight,String> {
}
