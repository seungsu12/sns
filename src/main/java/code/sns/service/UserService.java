package code.sns.service;

import code.sns.domain.dto.UserRequestDto;
import code.sns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public void join(UserRequestDto requestDto) {
    }
}
