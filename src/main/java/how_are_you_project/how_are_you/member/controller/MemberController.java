package how_are_you_project.how_are_you.member.controller;

import how_are_you_project.how_are_you.dto.JoinMemberDto;
import how_are_you_project.how_are_you.dto.LoginMemberDto;
import how_are_you_project.how_are_you.dto.LoginMemberResponse;
import how_are_you_project.how_are_you.dto.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/join")
    public void joinMember(@Valid @RequestBody JoinMemberDto joinMemberDto) {
        System.out.println(joinMemberDto);
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


//    @GetMapping("/login")
//    public LoginMemberResponse myPageMember(@Valid @RequestBody LoginMemberDto loinMemberDto) {
//        LoginMemberResponse loginMemberResponse = memberService.loginMember(loinMemberDto);
//        System.out.println(loginMemberResponse);
//        return loginMemberResponse;
//    }





}
