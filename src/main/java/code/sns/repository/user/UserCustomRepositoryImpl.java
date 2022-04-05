package code.sns.repository.user;


import code.sns.domain.QFollow;
import code.sns.domain.dto.response.QUserBirthDto;
import code.sns.domain.dto.response.QUserProfileDto;
import code.sns.domain.dto.response.UserBirthDto;
import code.sns.domain.dto.response.UserProfileDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static code.sns.domain.QFollow.follow;
import static code.sns.domain.QUser.user;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements  UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserProfileDto getProfile(Long id) {

        return queryFactory.select(new QUserProfileDto(
                        user.id,
                        user.username,
                        user.nickname,
                        user.profile,
                        user.profile_img,
                        user.created_at,
                        user.fromFollower.size(),
                        user.toFollower.size()
                )).from(user)
                .where(user.id.eq(id))
                .fetchOne();

    }

    @Override
    public List<String> getToFollowImg(Long id) {

        return queryFactory.select(follow.fromFollow.profile_img)
                .from(follow)
                .leftJoin(follow.toFollow, user)
                .where(user.id.eq(id))
                .limit(5)
                .offset(0)
                .fetch();

    }

    @Override
    public List<String> getFromFollowImg(Long id) {

        return queryFactory.select(follow.toFollow.profile_img)
                .from(follow)
                .leftJoin(follow.fromFollow, user)
                .where(user.id.eq(id))
                .limit(5)
                .offset(0)
                .fetch();

    }

    @Override
    public Page<UserBirthDto> getBirthPeople(PageRequest pageRequest) {
        LocalDate now = LocalDate.now();
        Integer start = now.getDayOfMonth();
        Integer end = now.lengthOfMonth();

        List<UserBirthDto> fetch = queryFactory.select(new QUserBirthDto(
                        user.id,
                        user.nickname,
                        user.username,
                        user.profile_img,
                        user.birth
                )).from(user)
                .where(user.birth.month().eq(now.getMonth().getValue()))
                .where(user.birth.dayOfMonth().between(start,end))
                .offset(0)
                .limit(pageRequest.getPageSize())
                .fetch();
        return new PageImpl<>(fetch,pageRequest,fetch.size());
    }

    @Override
    public List<UserBirthDto> findByUsernameContains(String word) {

        return queryFactory.select(new QUserBirthDto(
                user.id,
                user.nickname,
                user.username,
                user.profile_img,
                user.birth
        )).from(user)
                .where(user.username.contains(word))
                .fetch();
    }
}
