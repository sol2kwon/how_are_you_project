package how_are_you_project.how_are_you.member.repository;

import how_are_you_project.how_are_you.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor

public class MemberRepository {

    private final EntityManager em;
    public void save(Member member) {
        em.persist(member);
    }

    public List <Member> findByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId",loginId).getResultList();

    }
    public List <Member> findByLoginMember(String loginId, String loginPassword){
        return em.createQuery("select m.loginId, m.loginPassword from Member m where m.loginId = :loginId ", Member.class)
                .setParameter("loginId",loginId).getResultList();
    };







}
