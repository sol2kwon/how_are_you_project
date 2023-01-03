package how_are_you_project.how_are_you.member.controller;

import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/joinView")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if (result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setName(form.getUserName());
        member.setName(form.getBirth());
        member.setName(form.getEmail());
        member.setName(form.getBirth());
        member.setName(form.getPassword());

        memberService.join(member);
        return "redirect:/";
    }



}
