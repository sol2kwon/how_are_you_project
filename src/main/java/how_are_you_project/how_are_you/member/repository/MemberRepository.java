package how_are_you_project.how_are_you.member.repository;

import how_are_you_project.how_are_you.dto.MemberJoinDto;
import how_are_you_project.how_are_you.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class MemberRepository {
    private final EntityManager em;
    public void save(Member member) {
        em.persist(member);
    }
    public List<Member> findByName(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId",loginId).getResultList();
    }



}
