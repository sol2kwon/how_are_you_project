package how_are_you_project.how_are_you.member.controller;

import how_are_you_project.how_are_you.dto.MemberJoinDto;
import how_are_you_project.how_are_you.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
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
    public void memberJoin(@Valid @RequestBody MemberJoinDto memberJoinDto) {
        System.out.println(memberJoinDto);
        memberService.memberJoin(memberJoinDto);
    }





}
