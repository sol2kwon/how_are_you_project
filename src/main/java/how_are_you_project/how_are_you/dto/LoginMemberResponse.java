package how_are_you_project.how_are_you.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
@Getter
@NoArgsConstructor
@ToString

public class LoginMemberResponse {
    private boolean success;
    private String loginId;

    private String token;

    private String message;

    public static LoginMemberResponse test() {
        return new LoginMemberResponse();
    }

    public static LoginMemberResponse success(String loginId, String token) {
        LoginMemberResponse response = new LoginMemberResponse();
        response.success = true;
        response.loginId = loginId;
        response.token = token;
        response.message = "성공";
        return response;
    }

    public static LoginMemberResponse fail(String failReason) {
        LoginMemberResponse response = new LoginMemberResponse();
        response.success = false;
        response.loginId = null;
        response.token = null;
        response.message = failReason;
        return response;
    }

}
