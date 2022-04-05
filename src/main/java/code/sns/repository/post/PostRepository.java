package code.sns.repository.post;

import code.sns.domain.Post;
import code.sns.repository.post.PostCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash
public interface PostRepository extends  JpaRepository<Post,Long>, PostCustomRepository {

}
