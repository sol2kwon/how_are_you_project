package how_are_you_project.how_are_you.question.controller;

import how_are_you_project.how_are_you.dto.member.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.dto.question.MemberQuestionDto;
import how_are_you_project.how_are_you.member.service.MemberService;
import how_are_you_project.how_are_you.question.domain.Question;
import how_are_you_project.how_are_you.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/save")
    public void saveQuestion(@Valid @RequestBody MemberQuestionDto memberQuestionDto) {
        log.info("saveQuestion {}", memberQuestionDto);
        System.out.println(memberQuestionDto);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
        questionService.saveQuestion(memberQuestionDto);
    }
    @GetMapping("/{memberId}")
    public Question randomQuestion(@PathVariable("memberId") Long memberId ) {
        return questionService.randomQuestion(memberId);
    }
}
