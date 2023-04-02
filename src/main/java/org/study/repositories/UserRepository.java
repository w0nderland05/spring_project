package org.study.repositories;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.QUser;
import org.study.entities.User;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor {
    // 아이디로 회원 정보 조회
    User findByUserId(String userId);

    /**
     * 회원이 등록되어 있는지 아이디로 체크
     *
     * @param userId
     * @return {boolean}
     */
    default boolean isUserExists(String userId) {
        QUser user = QUser.user;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.userId.eq(userId));

        long cnt = this.count(builder);
        return cnt > 0;
    }
}
