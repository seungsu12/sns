package code.sns.service;

import code.sns.domain.User;
import code.sns.domain.dto.UserRequestDto;
import code.sns.exception.NotFoundObjectException;
import code.sns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public void join(UserRequestDto requestDto) {

       userJpaRepository.join(User.JoinUser(requestDto.getEmail(), requestDto.getPassword(),
                requestDto.getUsername(), requestDto.getBirth(),requestDto.getUserLink()));
    }

    public User findById(Long id) {
        return userJpaRepository.findById(id).
                orElseThrow(() -> new NotFoundObjectException("해당하는 유저는 없습니다."));
    }
}
