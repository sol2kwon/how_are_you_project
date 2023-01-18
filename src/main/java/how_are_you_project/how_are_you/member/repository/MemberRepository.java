package how_are_you_project.how_are_you.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import how_are_you_project.how_are_you.dto.member.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static how_are_you_project.how_are_you.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    /**
     * 회원정보 저장
     * */
    public void save(Member member) {
        em.persist(member);
    }

    public List <Member> findByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId",loginId).getResultList();

    }

    /**
     * 로그인 아이디를 가지고
     * 해당 멤버정보 조회
     * */
    public Member findByPassword(String loginId){
        return queryFactory
                .selectFrom(member)
                .where(loginIdEq(loginId))
                .fetchOne();

    }

    /**
     * 멤버 아이디를 가지고
     * 해당 멤버정보 조회
     * */
    public Member findByMember(Long memberId){
        return queryFactory
                .selectFrom(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

    }

    private BooleanExpression loginIdEq(String loginId){
        return StringUtils.hasText(loginId) ? member.loginId.eq(loginId) : null;
    }

    private BooleanExpression loginPasswordEq(String loginPassword){
        return StringUtils.hasText(loginPassword) ? member.loginPassword.eq(loginPassword) : null;
    }

    public Member myPageMember(Long memberId){
        return queryFactory
                .selectFrom(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

    }

    public void updateEmail(MyPageMemberResponseDto updateParam) {
        em.flush();
        em.clear();

       queryFactory
           .update(member)
           .set(member.email,updateParam.getEmail())
           .where(member.memberId.eq(updateParam.getMemberId()))
           .execute();
    }

    public void updatePassword(MyPageMemberResponseDto updateParam) {
        em.flush();
        em.clear();

        queryFactory
                .update(member)
                .set(member.loginPassword,updateParam.getCheckPassword())
                .where(member.memberId.eq(updateParam.getMemberId()))
                .execute();
    }


}
