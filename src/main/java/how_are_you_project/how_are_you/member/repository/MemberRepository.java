package how_are_you_project.how_are_you.member.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import how_are_you_project.how_are_you.dto.LoginMemberDto;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import java.util.Optional;

import static how_are_you_project.how_are_you.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Member member) {
        em.persist(member);
    }

    public List <Member> findByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId",loginId).getResultList();

    }
    public List <Member> findByPassword(String loginId){
        System.out.println("비밀번호 담겼을까?@@@@@@@@");

        return queryFactory
                .select(Projections.constructor(Member.class,
                        member.loginId,
                        member.loginPassword))
                .from(member)
                .where(loginIdEq(loginId))
                .fetch();

    }

    private BooleanExpression loginIdEq(String loginId){
        return StringUtils.hasText(loginId) ? member.loginId.eq(loginId) : null;
    }

    private BooleanExpression loginPasswordEq(String loginPassword){
        return StringUtils.hasText(loginPassword) ? member.loginPassword.eq(loginPassword) : null;
    }









}
