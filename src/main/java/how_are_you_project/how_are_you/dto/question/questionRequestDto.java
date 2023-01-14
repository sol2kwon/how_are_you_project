package how_are_you_project.how_are_you.dto.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString

public class questionRequestDto {
    private Long questionId;
    private Long memberId;


}
