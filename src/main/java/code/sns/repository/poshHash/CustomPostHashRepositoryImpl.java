package code.sns.repository.poshHash;


import code.sns.domain.QPostHash;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static code.sns.domain.QPostHash.postHash;

@RequiredArgsConstructor
public class CustomPostHashRepositoryImpl implements CustomPostHashRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> getHashFromPost(Long postId) {


        return queryFactory.select(postHash.hashTag.tagName)
                .from(postHash)
                .where(postHash.post.id.eq(postId))
                .fetch();
    }
}
