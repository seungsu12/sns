package code.sns.service;

import code.sns.domain.User;
import code.sns.domain.dto.UserRequestDto;
import code.sns.exception.NotFoundObjectException;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void join(UserRequestDto requestDto) {


       userRepository.save(User.JoinUser(requestDto.getEmail(),
               requestDto.getPassword(),
                requestDto.getUsername(),
               requestDto.getBirth(),
               requestDto.getUserLink(),
               requestDto.getGender()
       ));
    }

    public User findById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundObjectException("해당하는 유저는 없습니다."));
    }
}
