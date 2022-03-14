package code.sns.repository;


import code.sns.domain.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PostJpaRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }
}
