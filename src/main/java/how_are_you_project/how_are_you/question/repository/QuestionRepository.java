package how_are_you_project.how_are_you.question.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.question.domain.MemberQuestion;
import how_are_you_project.how_are_you.question.domain.QQuestion;
import how_are_you_project.how_are_you.question.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static how_are_you_project.how_are_you.question.domain.QMemberQuestion.memberQuestion;
import static how_are_you_project.how_are_you.question.domain.QQuestion.question;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    /**
     * 답변 저장
     * */
    public void saveQuestion(MemberQuestion memberQuestion) {
        em.persist(memberQuestion);
    }


    /**
     * memberId를 가지고 [questionId] 조회
     */
    public List<Long> findByMemberQuestionIdList(Long memberId) {
        return queryFactory
                .select(memberQuestion.question.questionId)
                .from(memberQuestion)
                .where(memberQuestion.member.memberId.eq(memberId))
                .fetch();

    }

    public List<Long> findByQuestionIdList() {
        return queryFactory
                .select(question.questionId)
                .from(question)
                .fetch();
    }

    public Question findByQuestionList(Long noneMatchQuestionId) {
        return queryFactory
                .select(question)
                .from(question)
                .where(question.questionId.eq(noneMatchQuestionId))
                .fetchOne();
    }

    public Long findByQuestionCount() {
        return queryFactory
                .select(question.count())
                .from(question)
                .fetchOne();


    }
}
