package how_are_you_project.how_are_you.question.controller;

import how_are_you_project.how_are_you.dto.member.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.dto.question.MemberQuestionDto;
import how_are_you_project.how_are_you.member.service.MemberService;
import how_are_you_project.how_are_you.question.domain.MemberQuestion;
import how_are_you_project.how_are_you.question.domain.Question;
import how_are_you_project.how_are_you.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @PutMapping("/put-answer")
    public void updateQuestion(@Valid @RequestBody MemberQuestionDto memberQuestionDto) {
        log.info("updateQuestion {}", memberQuestionDto);

        questionService.updateQuestion(memberQuestionDto);
    }
    @GetMapping("/get-{memberId}")
    public MemberQuestionDto randomQuestion(@PathVariable("memberId") Long memberId ) {
        log.info("randomQuestion {}", memberId);
        return questionService.randomQuestion(memberId);
    }

    // restful api >
    // GET /members/{memberNo}
    // GET /members?gender=man
    // GET /questions?memberId=1&startDate=1111

    @GetMapping("/questionList/{memberId}/{startDate}/{endDate}")
    public List<MemberQuestionDto> questionList(@PathVariable("memberId") Long memberId
    , @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        return questionService.questionList(memberId,startDate,endDate);
    }

//    @GetMapping("/memberQuestionOne/{memberId}/{memberQuestionId}/{questionId}/{memberQuestionDate}")
//    public List<MemberQuestionDto> memberQuestionOne(@PathVariable("memberId") Long memberId
//            , @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
//        return questionService.questionList(memberId,startDate,endDate);
//    }
}
