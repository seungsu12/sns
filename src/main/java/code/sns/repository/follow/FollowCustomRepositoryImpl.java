package code.sns.repository.follow;

import code.sns.domain.QFollow;
import code.sns.domain.QPost;
import code.sns.domain.QUser;
import code.sns.domain.dto.response.FollowResponseDto;
import code.sns.domain.dto.response.QFollowResponseDto;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static code.sns.domain.QFollow.follow;
import static code.sns.domain.QPost.post;
import static code.sns.domain.QUser.user;

@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<FollowResponseDto> getUnFollowList(Long userId, PageRequest pageRequest) {

        List<FollowResponseDto> result = queryFactory.select(new QFollowResponseDto(
                        user.id,
                        user.nickname,
                        user.username,
                        user.profile_img,
                        user.job
                )).from(user)
                .where(user.id.notIn(
                        JPAExpressions
                                .select(follow.toFollow.id)
                                .from(follow)
                                .where(follow.fromFollow.id.eq(userId))

                )).where(user.id.ne(userId))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();


        return result;

    }

    @Override
    public List<FollowResponseDto> getBasicList() {
        return  queryFactory.select(new QFollowResponseDto(
                user.id,
                user.nickname,
                user.username,
                user.profile_img,
                user.job
                )).from(user)
                .fetch();
    }

    @Override
    public List<FollowResponseDto> getFollowList(Long id) {
        return queryFactory.select(new QFollowResponseDto(
                        user.id,
                        user.nickname,
                        user.username,
                        user.profile_img,
                        user.job
                )).from(user)
                .where(user.id.in(
                        JPAExpressions
                                .select(follow.toFollow.id)
                                .from(follow)
                                .where(follow.fromFollow.id.eq(id))

                )).fetch();
    }


}
