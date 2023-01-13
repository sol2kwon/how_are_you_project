package how_are_you_project.how_are_you.member.service;

import how_are_you_project.how_are_you.dto.LoginMemberDto;
import how_are_you_project.how_are_you.dto.LoginMemberResponse;
import how_are_you_project.how_are_you.dto.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.member.domain.Role;
import how_are_you_project.how_are_you.security.cipher.Aes128;
import how_are_you_project.how_are_you.dto.JoinMemberDto;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.repository.MemberRepository;
import how_are_you_project.how_are_you.utils.TimeUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true) //따로 설정한 트랜잭셔널 먼저 동작
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final Aes128 aes128;

    private final String jwtSecretKey;

    private final Long jwtSecretExpiration;

    public MemberService(MemberRepository memberRepository,
                         Aes128 aes128,
                         @Value("${security.jwt.token.security-key}")  String jwtSecretKey,
                         @Value("${security.jwt.token.expiration-length}") Long jwtSecretExpiration) {
        this.memberRepository = memberRepository;
        this.aes128 = aes128;
        this.jwtSecretKey = jwtSecretKey;
        this.jwtSecretExpiration = jwtSecretExpiration;
    }


    @Transactional
    public void joinMember(JoinMemberDto joinMemberDto) {
        List<Role> list = new ArrayList<>();
        list.add(Role.USER);

        validateDuplicateMember(joinMemberDto);

        Member joinMember = Member.builder()
                .loginId(joinMemberDto.getLoginId())
                .loginPassword(aes128.encrypt(joinMemberDto.getLoginPassword())) //암호화
                .birth(joinMemberDto.getBirth())
                .email(joinMemberDto.getEmail())
                .name(joinMemberDto.getName())
                .roles(list)
                .build();
        memberRepository.save(joinMember);
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
            // loginId로 회원이 확인될 때
            if (member != null) {
                boolean checkPassword = aes128.decrypt(member.getLoginPassword()).matches(loinMemberDto.getLoginPassword());
                boolean checkId = member.getLoginId().matches(loinMemberDto.getLoginId());

                // ID & PW 체크가 성공하면
                if (checkPassword & checkId){
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime expiration = now.plusSeconds(jwtSecretExpiration);
                    String token = Jwts.builder()
                            .setSubject(String.valueOf(member.getMemberId())) //필요 내용
                            .setIssuedAt(TimeUtils.toDate(now)) // 발행일자
                            .setExpiration(TimeUtils.toDate(expiration)) //만료일자
                            .signWith(SignatureAlgorithm.HS512,jwtSecretKey)
                            .compact();

                    return LoginMemberResponse.success(member.getMemberId(),member.getLoginId(),token);
                }
                // ID & PW 체크 실패 시
                else {
                    log.warn("로그인 실패");
                    //로그인 실패 상태 넣어줄지 생각
                    return LoginMemberResponse.fail("ID, PW를 확인하세요");
                }
            }
//            return loginMemberResponse;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        // loginId로 확인 안될때, 로그인 id로 회원이 존재하지않을때
        return LoginMemberResponse.fail("존제하지 않는 회원입니다. " + loinMemberDto.getLoginId());
    }

    public MyPageMemberResponseDto myPageMember(Long memberId) {
        Member member = memberRepository.myPageMember(memberId);

        return MyPageMemberResponseDto.myPageMember(member.getMemberId(), member.getLoginId(), member.getName(), member.getBirth(), member.getEmail());

    }


}

