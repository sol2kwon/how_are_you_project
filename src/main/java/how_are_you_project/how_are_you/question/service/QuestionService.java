package how_are_you_project.how_are_you.question.service;
import how_are_you_project.how_are_you.dto.question.MemberQuestionDto;
import how_are_you_project.how_are_you.member.domain.Member;
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
    public Question randomQuestion(Long memberId) {
        Question resultQuestion ;
        List<Long> findByMemberQuestionIdList = questionRepository.findByMemberQuestionIdList(memberId);
        Member saveMemberId = new Member(memberId);
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (ObjectUtils.isEmpty(findByMemberQuestionIdList)){
            Long idCount = questionRepository.findByQuestionCount();
            int findId = (int) Math.floor(Math.random() * idCount)+1;
            Question saveQuestionId = new Question((long) findId);

            resultQuestion = questionRepository.findByQuestionList((long) findId);

            MemberQuestion prams = MemberQuestion.builder()
                    .member(saveMemberId)
                    .question(saveQuestionId)
                    .memberQuestionDate(LocalDate.parse(nowDate))
                    .build();

            questionRepository.saveQuestion(prams);

        }else {
            List<Long> findByQuestionIdList = questionRepository.findByQuestionIdList();

            List<Long> noneMatchIdList = findByQuestionIdList.stream().filter(o -> findByMemberQuestionIdList.stream()
                    .noneMatch(Predicate.isEqual(o))).collect(Collectors.toList());

            int Index = (int) (Math.random() * noneMatchIdList.size());

            resultQuestion = questionRepository.findByQuestionList(noneMatchIdList.get(Index));
            Question saveQuestionId = new Question(resultQuestion.getQuestionId());

            MemberQuestion prams = MemberQuestion.builder()
                    .member(saveMemberId)
                    .question(saveQuestionId)
                    .memberQuestionDate(LocalDate.parse(nowDate))
                    .build();

            questionRepository.saveQuestion(prams);
        }
        return resultQuestion;
    }

    public void updateQuestion(MemberQuestionDto memberQuestionDto) {

        Long memberQuestionId = questionRepository.findByMemberQuestionId(memberQuestionDto);
        questionRepository.updateMemberQuestion(memberQuestionDto,memberQuestionId);
    }
}










