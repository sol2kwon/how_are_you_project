package how_are_you_project.how_are_you.member.service;

import how_are_you_project.how_are_you.cipher.Aes128;
import how_are_you_project.how_are_you.dto.MemberJoinDto;
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
    private final Aes128 aes128;

    @Transactional
    public void memberJoin(MemberJoinDto memberJoinDto){
        validateDuplicateMember(memberJoinDto);
        Member joinMember = Member.builder()
                .loginId(memberJoinDto.getLoginId())
                .loginPassword(aes128.encrypt(memberJoinDto.getLoginPassword())) // TODO 암호화 해야함
                .birth(memberJoinDto.getBirth())
                .email(memberJoinDto.getEmail())
                .name(memberJoinDto.getName())
                .build();
        memberRepository.save(joinMember);


//
//        String encrypted = aes128.encrypt("hello");
//        System.out.println(encrypted);
//
//        String decrypted = aes128.decrypt(encrypted);
//        System.out.println(decrypted);
    }
    private void validateDuplicateMember(MemberJoinDto memberJoinDto){
        List<Member> findMembers = memberRepository.findByName(memberJoinDto.getLoginId());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
