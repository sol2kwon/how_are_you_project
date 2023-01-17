package how_are_you_project.how_are_you.dto.question;

import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@Builder
@ToString
public class MemberQuestionDto {


    private String answer;

    private Long memberId; //memberId

    private Long questionId ; //questionId
}
