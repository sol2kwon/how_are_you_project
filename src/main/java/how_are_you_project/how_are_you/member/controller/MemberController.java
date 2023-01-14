package how_are_you_project.how_are_you.member.controller;

import how_are_you_project.how_are_you.dto.member.JoinMemberDto;
import how_are_you_project.how_are_you.dto.member.LoginMemberDto;
import how_are_you_project.how_are_you.dto.member.LoginMemberResponse;
import how_are_you_project.how_are_you.dto.member.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/join")
    public void joinMember(@Valid @RequestBody JoinMemberDto joinMemberDto) {
        memberService.joinMember(joinMemberDto);
    }

    @PostMapping("/login")
    public LoginMemberResponse loginMember(@Valid @RequestBody LoginMemberDto loinMemberDto) {
        return memberService.loginMember(loinMemberDto);
    }

    @GetMapping("/myPage/{memberId}")
    public MyPageMemberResponseDto myPageMember(@PathVariable("memberId") Long memberId ) {
        return memberService.myPageMember(memberId);
    }

    @PutMapping("/myPage/put-email")
    public void updateEmail(@Valid @RequestBody MyPageMemberResponseDto updateParam ) {
        log.info("put email --->  {}",updateParam);
        memberService.updateEmail(updateParam);
    }
    @PutMapping("/myPage/put-password")
        public void updatePassword(@Valid @RequestBody MyPageMemberResponseDto updateParam ) {
         memberService.updatePassword(updateParam);
    }







}
