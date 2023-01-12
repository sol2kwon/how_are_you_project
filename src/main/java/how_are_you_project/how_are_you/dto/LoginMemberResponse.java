package how_are_you_project.how_are_you.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
@Getter
@NoArgsConstructor
@ToString

public class LoginMemberResponse {
    private String loginId;

    private String loginPassword;

    private String name;

    private String birth;

    private String email;

}
