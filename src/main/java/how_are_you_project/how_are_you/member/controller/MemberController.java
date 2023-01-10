package how_are_you_project.how_are_you.member.controller;

import how_are_you_project.how_are_you.dto.JoinMemberDto;
import how_are_you_project.how_are_you.dto.LoginMemberDto;
import how_are_you_project.how_are_you.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/join")
    public void joinMember(@Valid @RequestBody JoinMemberDto joinMemberDto) {
        System.out.println(joinMemberDto);
        memberService.joinMember(joinMemberDto);
    }

    @PostMapping("/login")
    public void loginMember(@Valid @RequestBody LoginMemberDto loinMemberDto) {
        System.out.println(loinMemberDto);
        System.out.println("입장 @@@@@@@@@@@@@@@@@2");
        memberService.loginMember(loinMemberDto);
    }





}
