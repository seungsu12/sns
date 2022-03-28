package code.sns.repository.like;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostLikeCustomRepository {

    boolean IsFollowList(Long userId,Long postId);
}
