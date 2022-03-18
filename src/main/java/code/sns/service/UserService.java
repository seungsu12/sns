package code.sns.service;

import code.sns.domain.User;
import code.sns.domain.dto.request.UserRequestDto;
import code.sns.exception.NotFoundObjectException;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void join(UserRequestDto requestDto) {


       userRepository.save(User.JoinUser(requestDto.getEmail(),
               requestDto.getPassword(),
                requestDto.getUsername(),
               requestDto.getBirth(),
               requestDto.getNickname(),
               requestDto.getGender()
       ));
    }

    public User findById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundObjectException(String.format("해당하는 유저아이디 [%s]는 없습니다.",id)));
    }

    public User updateUser(UserRequestDto requestDto) {

        return userRepository.findByEmail(requestDto.getEmail());
    }
}
