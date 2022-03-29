package code.sns.repository.like;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static code.sns.domain.QPostLike.postLike;


@RequiredArgsConstructor
public class PostLikeCustomRepositoryImpl implements PostLikeCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean IsFollowList(Long userId, Long postId) {

            return Boolean.TRUE.equals(queryFactory.select(
                            postLike.isNotNull()
                    )
                    .from(postLike)
                    .where(postLike.user.id.eq(userId))
                    .where(postLike.post.id.eq(postId))
                    .fetchOne());




    }
}
