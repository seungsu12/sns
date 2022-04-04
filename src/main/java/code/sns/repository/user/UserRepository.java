package code.sns.repository.user;

import code.sns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User,Long>,UserCustomRepository {

     Optional<User> findByEmail(String email);


    List<User> findByUsernameContains(String word);
}
