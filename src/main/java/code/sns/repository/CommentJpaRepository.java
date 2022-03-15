package code.sns.repository;


import code.sns.domain.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Transactional
    public void createComment(Comment comment) {
        em.persist(comment);
    }
}
