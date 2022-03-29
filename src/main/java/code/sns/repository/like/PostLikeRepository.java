package code.sns.repository.like;

import code.sns.domain.Post;
import code.sns.domain.PostLike;
import code.sns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface PostLikeRepository extends JpaRepository<PostLike,Long>,PostLikeCustomRepository {
    Optional<PostLike> findByUserAndPost(User user,Post post);

    void deleteByUserAndPost(User user, Post post);
}
