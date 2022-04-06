package code.sns.service;

import code.sns.domain.User;
import code.sns.domain.dto.request.UserRequestDto;
import code.sns.domain.dto.response.UserBirthDto;
import code.sns.domain.dto.response.UserProfileDto;
import code.sns.domain.enums.Role;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.exception.NotFoundObjectException;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void join(UserRequestDto requestDto) {
       userRepository.save(User.JoinUser(requestDto.getEmail(),
               encoder.encode(requestDto.getPassword()),
                requestDto.getUsername(),
               requestDto.getBirth(),
               requestDto.getNickname(),
               requestDto.getGender(),
               requestDto.getJob (),
               Role.USER
               ));
    }

    public User findById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundObjectException(String.format("해당하는 유저아이디 [%s]는 없습니다.",id)));
    }

    public User updateUser(UserRequestDto requestDto) {
        return userRepository.findByEmail(requestDto.getEmail()).orElseThrow(()->
                new CustomException(ErrorCode.NOT_FOUND_EMAIL,String.format("해당하는 이메일 [%s]는 없습니다.",requestDto.getEmail())));
    }

    public UserProfileDto getProfile(Long id) {
        return userRepository.getProfile(id);
    }

    public List<String> getToFollowImg(Long id){
        return userRepository.getToFollowImg(id);
    }

    public List<String> getFromFollowImg(Long id){
        return userRepository.getFromFollowImg(id);
    }

//    @Cacheable(value = "birth",key = "{#pageRequest.getPageNumber()}")
    public List<UserBirthDto> getBirthPeople(PageRequest pageRequest) {

     return  userRepository.getBirthPeople(pageRequest).toList();



    }

    public List<UserBirthDto> searchUser(String word) {

        return userRepository.findByUsernameContains(word);
    }
}
