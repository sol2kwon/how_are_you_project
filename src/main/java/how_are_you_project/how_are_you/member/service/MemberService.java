package how_are_you_project.how_are_you.member.service;

import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //따로 설정한 트랜잭셔널 먼저 동작
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getUserName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);


    }
//회원정보 수정 다시 만들기
    @Transactional
    public void update(Long id, String email) {
        Member member =  memberRepository.findOne(id);
        member.setName(email);
    }
}
