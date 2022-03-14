package code.sns;

import code.sns.domain.Post;
import code.sns.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@SpringBootTest
@Transactional
public class queryTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void test() {
        User user1 = User.JoinUser("123@","123","user1", LocalDate.now(),"@user1");
        User user2 = User.JoinUser("12345@","123","user2", LocalDate.now(),"@user2");
        em.persist(user1);
        em.persist(user2);
        Post post1 = Post.createPost("좋아좋아용","1234",user1);
        Post post2 = Post.createPost("행복하다구","12345",user1);
        em.persist(post1);
        em.persist(post2);

        User user = em.find(User.class, 1L);
        System.out.println(user);
        Post post = em.find(Post.class, 3L);
        System.out.println(post.getContext());

    }
}
