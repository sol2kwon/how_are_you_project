package how_are_you_project.how_are_you.member.service;

import how_are_you_project.how_are_you.dto.LoginMemberDto;
import how_are_you_project.how_are_you.dto.LoginMemberResponse;
import how_are_you_project.how_are_you.security.cipher.Aes128;
import how_are_you_project.how_are_you.dto.JoinMemberDto;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public void joinMember(JoinMemberDto joinMemberDto) {
        validateDuplicateMember(joinMemberDto);
        Member joinMember = Member.builder()
                .loginId(joinMemberDto.getLoginId())
                .loginPassword(aes128.encrypt(joinMemberDto.getLoginPassword())) // TODO 암호화 해야함
                .birth(joinMemberDto.getBirth())
                .email(joinMemberDto.getEmail())
                .name(joinMemberDto.getName())
                .build();
        memberRepository.save(joinMember);


//
//        String encrypted = aes128.encrypt("hello");
//        System.out.println(encrypted);
//
//        String decrypted = aes128.decrypt(encrypted);
//        System.out.println(decrypted);
    }

    private void validateDuplicateMember(JoinMemberDto joinMemberDto) {
        List<Member> findMembers = memberRepository.findByLoginId(joinMemberDto.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public LoginMemberResponse loginMember(LoginMemberDto loinMemberDto) {
        try {
            Member member = memberRepository.findByPassword(loinMemberDto.getLoginId());
            LoginMemberResponse loginMemberResponse = new LoginMemberResponse();
            ModelMapper modelMapper = new ModelMapper();
            if (member.getLoginId()!= null){
                boolean checkPassword = aes128.decrypt(member.getLoginPassword()).matches(loinMemberDto.getLoginPassword());
                boolean checkId = member.getLoginId().matches(loinMemberDto.getLoginId());

                if (checkPassword & checkId){
                    loginMemberResponse = modelMapper.map(member,LoginMemberResponse.class);

                    System.out.println("로그인 성공");

                    //로그인 성공 상태 넣어줄지 생각
                    //비번도 반환해줘야하는지?
                } else {
                    System.out.println("로그인 실패");
                    //로그인 실패 상태 넣어줄지 생각
                }
            }
            return loginMemberResponse;


        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }
}

