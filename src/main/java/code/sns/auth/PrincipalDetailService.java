package code.sns.auth;

import code.sns.domain.User;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User principal = userRepository.findByEmail(email)
                .orElseThrow(() ->{
            return new CustomException(ErrorCode.NOT_FOUND_EMAIL,String.format("[%s] 이메일을 찾을 수 없습니다.",email));
        });
        log.info("User datails user {}",principal);

        return new PrincipalDetail(principal);
    }
}
