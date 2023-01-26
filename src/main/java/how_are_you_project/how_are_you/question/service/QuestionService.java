package how_are_you_project.how_are_you.question.service;
import how_are_you_project.how_are_you.dto.question.MemberQuestionDto;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.member.service.MemberService;
import how_are_you_project.how_are_you.question.domain.MemberQuestion;
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

    /** 랜덤 질문 시나리오
     * 공통 :  memberQuestion 테이블에 값이 있는지 확인한다.
     * ★★★ 신규 답변일 경우 questionId = [] 반환 // findByMemberQuestionIdList가 비어있을 경우
     * 1. Question 테이블 findId = Count 조회
     * 2. 1 ~ Count 까지 랜덤 값 생성
     * 3. findId = 생성한 랜덤 id값을 가지고 질문목록 조회
     *
     * ★★★ 이전 답변한 내용이 있을 경우 questionId = [questionId]를 반환한다.
     * 1. memberId를 넘겨준다
     * 2. memberQuestion 테이블에 memberId가 일치한 questionId 조회 []
     * 3. Question 테이블에 questionId 전체 조회
     * 4. Question 테이블과 memberQuestion 테이블에서 조회된 questionId를 가지고 중복되지 않은 noneMatchIdList 반환
     * 5. noneMatchIdList size 만큼 랜덤 인덱스 생성
     * 6. noneMatchIdList 랜덤 인덱스 값을 가지고 Question 테이블에서 질문목록을 반환한다.
     * */
//    public Question randomQuestion(Long memberId) {
//        Question resultQuestion ;
//        List<Long> findByMemberQuestionIdList = questionRepository.findByMemberQuestionIdList(memberId);
//
//        Member member = memberService.findMemberEntity(memberId);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String nowDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        if (ObjectUtils.isEmpty(findByMemberQuestionIdList)){
//            Long idCount = questionRepository.findByQuestionCount();
//            int findId = (int) Math.floor(Math.random() * idCount)+1;
//
//            resultQuestion = findQuestionEntity((long) findId);
//
//            MemberQuestion prams = MemberQuestion.builder()
//                    .member(member)
//                    .question(resultQuestion)
//                    .memberQuestionDate(LocalDate.parse(nowDate))
//                    .build();
//
//            log.info("prams {}", prams);
//
//            questionRepository.saveQuestion(prams);
//
//        }else {
//            List<Long> findByQuestionIdList = questionRepository.findByQuestionIdList();
//
//            List<Long> noneMatchIdList = findByQuestionIdList.stream().filter(o -> findByMemberQuestionIdList.stream()
//                    .noneMatch(Predicate.isEqual(o))).collect(Collectors.toList());
//
//            int Index = (int) (Math.random() * noneMatchIdList.size());
//
//            resultQuestion = findQuestionEntity(noneMatchIdList.get(Index));
//            Question saveQuestionId = new Question(resultQuestion.getQuestionId());
//
//            MemberQuestion prams = MemberQuestion.builder()
//                    .member(member)
//                    .question(saveQuestionId)
//                    .memberQuestionDate(LocalDate.parse(nowDate))
//                    .build();
//
//            questionRepository.saveQuestion(prams);
//        }
//        return resultQuestion;
//    }

    public Question randomQuestion(Long memberId){
        List<MemberQuestion> answerNullList = questionRepository.findByAnswerNullList(memberId);

        log.info("나와라 {}",answerNullList);

        Optional<MemberQuestion> todayQuestion = answerNullList.stream() // (2)
         .filter(a -> a.getMemberQuestionDate().equals(LocalDate.now())) // (2)
                .findFirst(); // (2)

        log.info("todayQuestion {}",todayQuestion);
        log.info("LocalDate {}",LocalDate.now());

//
        if (todayQuestion.isEmpty()) { // (2-2)
            List<Question> findByQuestionList = questionRepository.findByQuestionList();
            List<MemberQuestion> answerNotNullList = questionRepository.findByAnswerNullList(memberId);


            // 없으니 만들어서 save
        }


//        List<MemberQuestion> A = Collections.emptyList();  // (1)
//        Optional<MemberQuestion> todayQuestion = A.stream() // (2)
//         .filter(a -> a.getMemberQuestionDate().equals(LocalDate.now())) // (2)
//                .findFirst(); // (2)
//        if (todayQuestion.isEmpty()) { // (2-2)
//            // 없으니 만들어서 save
//        }
//        // A + 새로만든거(2-2) 중에 랜덤으로 뽑아서 리턴한다 (3)
        return null;
    }

    /***
     * 시나리오2
     * 1. 멤버의 아이디로 질문을 조회해본다.
     *    1-1. 있다? X
     *    1-2. 없는데 아직 줄 질문이 남았다? 중복되지 않는 것으로 만들어서 save
     *    - 받지 않은 질문
     *    - 답변 안한 질문
     *
     * 1. 멤버의 답변 안한 질문 (anwser = null) 목록(A)들을 다 조회한다.
     * 2. A 중에서 오늘날짜의 질문이 있나?
     *    2-1. 있다? 아무것도안함
     *    2-2. 없다? 받지않고, 답변안한 질문 중 랜덤으로 하나 만들어준다.
     * 3. 그 중에서 랜덤으로 하나를 리턴해준다.
     *
     * [Q1, Q2]
     *
     * [MQ-Q1-230119-X, MQ-Q2-230120-X, MQ-Q3-230121-X, ...., MQ-Q1-230221-O, MQ-Q1-230120-X]
     */

//

    public void updateQuestion(MemberQuestionDto memberQuestionDto) {
        log.info("서비스 데이터 {}",memberQuestionDto);

        Long memberQuestionId = questionRepository.findByMemberQuestionId(memberQuestionDto);
        questionRepository.updateMemberQuestion(memberQuestionDto,memberQuestionId);
    }

    public List<MemberQuestionDto> questionList(Long memberId,String startDate,String endDate) {
        log.info("서비스 questionList {} {} {}",memberId,startDate,endDate);

        return questionRepository.questionList(memberId,startDate,endDate);
    }

    public Question findQuestionEntity(Long id) {
        return questionRepository.findByQuestionId(id);
    }
}










