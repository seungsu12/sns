package code.sns.repository;


import code.sns.domain.Post;
import code.sns.domain.dto.PostResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostJpaRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public List<PostResponseDto> getPosts() {

//        queryFactory.select()
        return null;
    }
}
