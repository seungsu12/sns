package code.sns.repository;


import code.sns.domain.QUser;
import code.sns.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.Optional;

import static code.sns.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserJpaRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void join(User user) {
        em.persist(user);

    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne());
    }
}
