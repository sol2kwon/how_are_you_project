package how_are_you_project.how_are_you.member.controller;

import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
//@RestController 경로 안먹음 찾아보기
@RequiredArgsConstructor
//@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/join")
    public String createForm(Model model){
        log.info("joinView controller");
        System.out.println("입장!!!!!!!!!!!!1");
        model.addAttribute("memberForm",new MemberForm());
        return "createMemberForm" ;
    }

//    @RequestMapping("/members/joinView")
//    public String joinView(){
//        log.info("joinView controller");
//        return "createMemberForm";
//    }

    @PostMapping("/members/join")
    public String create(@Valid MemberForm form, BindingResult result){
        if (result.hasErrors()){
            return "createMemberForm";
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
