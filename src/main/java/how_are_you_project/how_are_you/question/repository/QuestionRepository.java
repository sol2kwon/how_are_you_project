package how_are_you_project.how_are_you.question.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    /**
     * memberId와 questionId를 가지고 member_question 테이블 조회
     *
     * */
    public void selectMemberQuestion(Long memberId, Long questionId) {

    }
}
