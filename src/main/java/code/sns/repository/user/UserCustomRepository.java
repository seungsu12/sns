package code.sns.repository.user;

import code.sns.domain.dto.response.UserProfileDto;

import java.util.List;

public interface UserCustomRepository {

    UserProfileDto getProfile(Long id);

    List<String> getToFollowImg(Long id);

    List<String> getFromFollowImg(Long id);
}
