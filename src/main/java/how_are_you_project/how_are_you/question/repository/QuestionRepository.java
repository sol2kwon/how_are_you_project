package how_are_you_project.how_are_you.question.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import how_are_you_project.how_are_you.dto.member.MyPageMemberResponseDto;
import how_are_you_project.how_are_you.dto.question.MemberQuestionDto;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.question.domain.MemberQuestion;
import how_are_you_project.how_are_you.question.domain.QMemberQuestion;
import how_are_you_project.how_are_you.question.domain.QQuestion;
import how_are_you_project.how_are_you.question.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static how_are_you_project.how_are_you.member.domain.QMember.member;
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
    public void saveQuestion(MemberQuestion prams) {
        em.persist(prams);
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

    public List<Question> findByQuestionList() {
        return queryFactory
                .selectFrom(question)
                .fetch();
    }

    // findByMemberId()
    // findAllByGenderAndName()

    public Question findByQuestionId(Long id) {
        return queryFactory
                .select(question)
                .from(question)
                .where(question.questionId.eq(id))
                .fetchOne();
    }

    public Long findByQuestionCount() {
        return queryFactory
                .select(question.count())
                .from(question)
                .fetchOne();


    }
    /**
     * memberId,nowDate를 가지고
     * MemberQuestion테이블의 MemberQuestionId 조회
     * */
    public Long findByMemberQuestionId (MemberQuestionDto memberQuestionDto){
        log.info("데이터 확인 {}",memberQuestionDto);
       return queryFactory
                .select(memberQuestion.memberQuestionId)
                .from(memberQuestion)
                .where(memberQuestion.member.memberId.eq(memberQuestionDto.getMemberId())
                        ,memberQuestion.memberQuestionDate.eq(memberQuestionDto.getMemberQuestionDate()))
                .fetchOne();
    }

    /**
     * memberQuestionDto 와 MemberQuestionId를 가지고
     * MemberQuestion테이블의 memberAnswer 업데이트
     * */
    public void updateMemberQuestion(MemberQuestionDto memberQuestionParams,Long memberQuestionId){
        em.flush();
        em.clear();

        queryFactory
                .update(memberQuestion)
                .set(memberQuestion.memberAnswer,memberQuestionParams.getAnswer())
                .where(memberQuestion.memberQuestionId.eq(memberQuestionId))
                .execute();
    }

    public void findByQuestionMemberList(MemberQuestion memberQuestion) {
    }

    public void questionMember(MemberQuestion memberQuestion) {
                em.persist(memberQuestion);
    }

    public List<MemberQuestionDto> questionList(Long memberId, String startDate, String endDate) {
         List<MemberQuestionDto> result = queryFactory
                .select(Projections.constructor(MemberQuestionDto.class,
                        memberQuestion.memberQuestionId,
                        memberQuestion.memberQuestionDate,
                        memberQuestion.question.questionId,
                        memberQuestion.question.title
                        ))
                 .from(memberQuestion)
                 .where(memberIdEq(memberId)
                         ,startDateEq(startDate)
                            ,endDateEq(endDate))
                .fetch();
        return result;
    }

    private BooleanExpression memberIdEq(Long memberId){
        return StringUtils.hasText(String.valueOf(memberId)) ? memberQuestion.member.memberId.eq(memberId) : null;
    }
    private BooleanExpression startDateEq(String startDate){
        return StringUtils.hasText(startDate) ? memberQuestion.memberQuestionDate.goe(LocalDate.parse(startDate)) : null;
    }
    private BooleanExpression endDateEq(String endDate){
        return StringUtils.hasText(endDate) ? memberQuestion.memberQuestionDate.loe(LocalDate.parse(endDate)) : null;
    }
/**
 * null인 목록 반환
 * */
    public List<MemberQuestion> findByAnswerNullList(Long Id) {
        return queryFactory
                .select(memberQuestion)
                .from(memberQuestion)
                .where(memberQuestion.memberAnswer.isNull())
                .fetch();
    }

    public List<MemberQuestion> findByAnswerNotNullList(Long Id) {
        return queryFactory
                .select(memberQuestion)
                .from(memberQuestion)
                .where(memberQuestion.memberAnswer.isNotNull())
                .fetch();
    }

//    private BooleanExpression answerNullEq(Long memberId) {	// (4)
//        return QMEM. != null ? member.age.goe(ageGoe) : null;
//    }

}
