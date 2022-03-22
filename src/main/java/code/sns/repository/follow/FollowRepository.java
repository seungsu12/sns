package code.sns.repository.follow;

import code.sns.domain.Follow;
import code.sns.domain.User;
import code.sns.domain.dto.response.FollowResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long>,FollowCustomRepository {

    Optional<Follow> findByToFollowAndFromFollow(User toUser, User followUser);

    boolean existsByToFollowAndFromFollow(User toUser, User followUser);

}
