package code.sns.repository.follow;

import code.sns.domain.dto.response.FollowResponseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowCustomRepository {

    List<FollowResponseDto> getUnFollowList(Long userId, PageRequest pageRequest);

    List<FollowResponseDto> getBasicList();

    List<FollowResponseDto> getFollowList(Long id);

}
