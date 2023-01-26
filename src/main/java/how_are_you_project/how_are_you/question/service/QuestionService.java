package how_are_you_project.how_are_you.question.service;
import how_are_you_project.how_are_you.dto.question.MemberQuestionDto;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.service.MemberService;
import how_are_you_project.how_are_you.question.domain.MemberQuestion;
import how_are_you_project.how_are_you.question.domain.QQuestion;
import how_are_you_project.how_are_you.question.domain.Question;
import how_are_you_project.how_are_you.question.repository.QuestionRepository;
import how_are_you_project.how_are_you.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) //따로 설정한 트랜잭셔널 먼저 동작
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final MemberService memberService;
    private final QuestionRepository questionRepository;

    public MemberQuestionDto randomQuestion(Long memberId){
        List<MemberQuestion> answerNullList = questionRepository.findByAnswerNullList(memberId);
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Optional<MemberQuestion> todayQuestion = answerNullList.stream()
         .filter(a -> a.getMemberQuestionDate().equals(LocalDate.now()))
         .findFirst();

        if (todayQuestion.isEmpty()) {
            List<Question> findByQuestionList = questionRepository.findByQuestionList();
            List<MemberQuestion> findByAnswerNotNullList = questionRepository.findByAnswerNotNullList(memberId);
            Optional<Long> result = findByQuestionList.stream().map(Question::getQuestionId).collect(Collectors.toList()).stream()
                    .filter(i -> !findByAnswerNotNullList.stream().map(e->e.getQuestion().getQuestionId()).collect(Collectors.toList()).contains(i))
                    .parallel().findAny();

            List <Question> resultQuestionList = findByQuestionList.stream()
                    .filter(e->e.getQuestionId().equals(result.get())).collect(Collectors.toList());

            MemberQuestion memberQuestion = MemberQuestion.builder()
                    .question(resultQuestionList.get(0))
                    .member(memberService.findMemberEntity(memberId))
                    .memberQuestionDate(LocalDate.parse(nowDate))
            .build();
            questionRepository.saveQuestion(memberQuestion);
        }
        MemberQuestion m = questionRepository.findByAllMemberQuestion(memberId,nowDate);
        MemberQuestionDto result = MemberQuestionDto.builder()
                .memberQuestionId(m.getMemberQuestionId())
                .memberQuestionDate(m.getMemberQuestionDate())
                .questionId(m.getQuestion().getQuestionId())
                .answer(m.getMemberAnswer())
                .title(m.getQuestion().getTitle())
                .content(m.getQuestion().getContent())
                .memberId(m.getMember().getMemberId()).build();

        return result;
    }

    public void updateQuestion(Long memberQuestionId, String answer) {
        questionRepository.updateMemberQuestion(memberQuestionId,answer);
    }

    public List<MemberQuestionDto> questionList(Long memberId,String startDate,String endDate) {
        log.info("서비스 questionList {} {} {}",memberId,startDate,endDate);

        return questionRepository.questionList(memberId,startDate,endDate);
    }

    public Question findQuestionEntity(Long id) {
        return questionRepository.findByQuestionId(id);
    }
}










