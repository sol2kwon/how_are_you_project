package how_are_you_project.how_are_you.question.controller;

import how_are_you_project.how_are_you.dto.member.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.member.service.MemberService;
import how_are_you_project.how_are_you.question.domain.Question;
import how_are_you_project.how_are_you.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{memberId}")
    public Question randomQuestion(@PathVariable("memberId") Long memberId ) {
        return questionService.randomQuestion(memberId);
    }
}
