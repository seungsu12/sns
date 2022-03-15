package code.sns.repository;


import code.sns.domain.Post;
import code.sns.domain.QComment;
import code.sns.domain.QPost;
import code.sns.domain.User;
import code.sns.domain.dto.PostResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static code.sns.domain.QPost.post;
import static code.sns.domain.QComment.comment;
import static code.sns.domain.QUser.user;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostJpaRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Transactional
    public void save(Post post) {
        em.persist(post);
    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(post)
                .where(post.id.eq(id))
                .fetchOne());

    }

    public List<PostResponseDto> getPosts() {

//        List<User> fetch = queryFactory.selectFrom(comment)
//                .join().fetchJoin()
//                .fetch();
//
//        for (User fetch1 : fetch) {
//            System.out.println(fetch1);
//        }

        return null;
    }
}
