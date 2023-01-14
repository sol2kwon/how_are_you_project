package how_are_you_project.how_are_you.question.service;

import how_are_you_project.how_are_you.question.domain.Question;
import how_are_you_project.how_are_you.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //따로 설정한 트랜잭셔널 먼저 동작
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;


    public static void randomQuestion(Long memberId, Long questionId) {
//       questionRepository.selectMemberQuestion(memberId,questionId);

        //*알고리즘 시나리오
        // questionId값 = 1~100까지의 숫자를 무작위로 뽑는다.
        // member id값,questionId값 넘겨준다
        // member question 테이블에  where memberid 리스트 조회
        // 중복된 값이 없으면 question 테이블에서 questionId에 맞는 값을 뽑아온다.

        // 중복된 값이 있으면? member question 테이블에 저장된 id [] 출력
        // 1~100개의 숫자중에 [] 과 중복된 값을 제외하고 new [] 생성
        // new [] 에서 [0]번째 값을 가지고 question 테이블 조회
        // 화면에 반환
    }
}
