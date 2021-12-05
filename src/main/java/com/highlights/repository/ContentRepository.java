package com.highlights.repository;

import com.highlights.common.entity.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author curkudkar on 12/5/21
 */
public interface ContentRepository extends MongoRepository<Content,String> {

}
