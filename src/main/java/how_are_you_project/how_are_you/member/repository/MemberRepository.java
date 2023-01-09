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
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name).getResultList();
    }

    





}
