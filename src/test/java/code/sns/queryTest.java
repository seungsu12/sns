package code.sns;

import code.sns.domain.*;
import code.sns.domain.enums.Gender;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static code.sns.domain.QPost.post;
import static code.sns.domain.QTeam.team;
import static code.sns.domain.QUser.user;

@SpringBootTest
@Transactional
public class queryTest {

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void test() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        User user1 = User.JoinUser("11@","111","user1",LocalDate.now() ,"@user1", Gender.Male);
        User user2 = User.JoinUser("22@","222","user2",LocalDate.now() ,"@user2", Gender.Male);
        User user3 = User.JoinUser("33@","333","user3",LocalDate.now() ,"@user2", Gender.Male);
        User user4 = User.JoinUser("44@","444","user4",LocalDate.now() ,"@user2", Gender.Male);

        user1.setTeam(teamA);
        user2.setTeam(teamB);
        user3.setTeam(teamA);
        user4.setTeam(teamB);

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);

        Post post1 = Post.createPost("좋아좋아용",null,user1);
        Post post2 = Post.createPost("행복하다구",null,user1);
        Post post3 = Post.createPost("즐겁다구",null,user1);

        em.persist(post1);
        em.persist(post2);
        Comment c1 = new Comment("댓글댓글1",post1);
        Comment c2 = new Comment("댓글댓글2",post1);
        Comment c3 = new Comment("댓글댓글3",post1);
        Comment c4 = new Comment("댓글댓글4",post1);
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);

    }
    @Test
    void post() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Tuple> result = queryFactory.select(post,user)
                .from(post)
                .leftJoin(post.user,user)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple : "+tuple);
        }

    }

    @Test
    void fetchjoin() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Post> fetch = queryFactory.select(post)
                .from(post)
                .join(post.user, user).fetchJoin()
                .fetch();


        for (Post fetch1 : fetch) {
            System.out.println(fetch1);
        }
    }

    @Test
    void join() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Tuple> result = queryFactory.select(user, team)
                .from(user)
                .leftJoin(user.team, team)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println(tuple);
        }

    }

    @Test
    void all() {

        Post post = em.find(Post.class, 7L);
        List<Comment> comments = post.getComments();

        for (Comment comment : comments) {
            System.out.println("comment :"+comment);
        }

    }
}
