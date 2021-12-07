package com.highlights.repository;

import com.highlights.common.entity.Highlight;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

/**
 * @author curkudkar on 12/5/21
 */
public interface HighlightsRepository extends MongoRepository<Highlight,String> {

    //method to find highlight
    public Highlight findByIdAndContentIdAndUserId(String id, String contentId,String userId);
    public List<Highlight> findAll();
    public List<Highlight> findByContentId(String contentId);
    public List<Highlight> findByUserId(String userId,Sort sort);
    public List<Highlight> findByUserIdAndContentId(String userId, String contentId, Sort sort);
    public Optional<Highlight> findById(String id);
    public Optional<List<Highlight>> findByUserIdAndAccessedFalse(String userId,Sort sort);
}
