package how_are_you_project.how_are_you.member.repository;

import how_are_you_project.how_are_you.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member>  findByUsername(String username);
    // 000. 사용자의 비밀번호와 이메일을 수정하시오

    @Repository
    public interface UserRepository extends JpaRepository<Member,Long>{
        Optional<Member> findByUsername(String username);

    }
}

