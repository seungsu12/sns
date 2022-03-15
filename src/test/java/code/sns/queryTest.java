package code.sns;

import code.sns.domain.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static code.sns.domain.QComment.comment;
import static code.sns.domain.QPost.post;
import static code.sns.domain.QUser.user;

@SpringBootTest
@Transactional
public class queryTest {

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void test() {
        User user1 = User.JoinUser("11@","111","user1",LocalDate.now() ,"@user1", Gender.Male);
        User user2 = User.JoinUser("22@","222","user2",LocalDate.now() ,"@user2", Gender.Male);
        em.persist(user1);
        em.persist(user2);
        Post post1 = Post.createPost("좋아좋아용",null,user1);
        Post post2 = Post.createPost("행복하다구",null,user1);
        em.persist(post1);
        em.persist(post2);
        Comment c1 = new Comment("댓글댓글1",post1,user1);
        Comment c2 = new Comment("댓글댓글2",post1,user2);
        Comment c3 = new Comment("댓글댓글3",post1,user2);
        Comment c4 = new Comment("댓글댓글4",post1,user2);

    }


    @Test
    void join() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Post> 좋아좋아용 = queryFactory.select(post)
                .from(post)
                .leftJoin(post.user, user).on(post.context.eq("좋아좋아용"))
                .fetch();

        for (Post post1 : 좋아좋아용) {
            System.out.println(post1);
        }

    }
}
