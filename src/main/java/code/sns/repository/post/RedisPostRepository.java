package code.sns.repository.post;

import code.sns.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface RedisPostRepository extends CrudRepository<Post,Long> {
    
}
