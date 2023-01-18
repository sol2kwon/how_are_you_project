package how_are_you_project.how_are_you.dto.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import how_are_you_project.how_are_you.member.domain.Member;
import how_are_you_project.how_are_you.question.domain.Question;
import lombok.*;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@ToString
public class MemberQuestionDto {

    private String answer;

    private Long memberId; //memberId

    private Long memberQuestionId ; //questionId

    private LocalDate memberQuestionDate;

    private Long questionId;

    private String title;

    public MemberQuestionDto(Long memberQuestionId, LocalDate memberQuestionDate, Long questionId, String title) {
        this.memberQuestionId = memberQuestionId;
        this.memberQuestionDate = memberQuestionDate;
        this.questionId = questionId;
        this.title = title;
    }
}
