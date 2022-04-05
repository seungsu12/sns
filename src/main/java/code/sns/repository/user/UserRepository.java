package code.sns.repository.user;

import code.sns.domain.User;
import code.sns.domain.dto.response.UserBirthDto;
import code.sns.domain.dto.response.UserProfileDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User,Long>,UserCustomRepository {

     Optional<User> findByEmail(String email);


}
