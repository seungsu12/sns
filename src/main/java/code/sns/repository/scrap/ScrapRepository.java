package code.sns.repository.scrap;

import code.sns.domain.Post;
import code.sns.domain.Scrap;
import code.sns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap,Long> {

    void deleteByUserAndPost(User user, Post post);

    Boolean existsByUserAndPost(User user, Post post);
}
