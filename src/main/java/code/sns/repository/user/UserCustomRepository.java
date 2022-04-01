package code.sns.repository.user;

import code.sns.domain.dto.response.UserBirthDto;
import code.sns.domain.dto.response.UserProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserCustomRepository {

    UserProfileDto getProfile(Long id);

    List<String> getToFollowImg(Long id);

    List<String> getFromFollowImg(Long id);

    Page<UserBirthDto> getBirthPeople(PageRequest pageRequest);
}
