package how_are_you_project.how_are_you.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString

public class MyPageMemberResponseDto {
    private Long memberId;

    private String loginId;

    private String loginPassword;

    private String checkPassword;

    private String name;

    private String birth;

    private String email;

    public static MyPageMemberResponseDto test() {return new MyPageMemberResponseDto();

    }

    public static MyPageMemberResponseDto myPageMember(Long memberId, String loginId, String name, String birth, String email) {
        MyPageMemberResponseDto myPageMember = new MyPageMemberResponseDto();
        myPageMember.memberId = memberId;
        myPageMember.loginId = loginId;
        myPageMember.name = name;
        myPageMember.birth = birth;
        myPageMember.email = email;
        return myPageMember;
    }
//    /**
//     * 이메일 변경
//     * */
//
//    public static MyPageMemberResponseDto updateEmail(Long memberId, String email) {
//        MyPageMemberResponseDto updateEmail = new MyPageMemberResponseDto();
//        updateEmail.memberId = memberId;
//        updateEmail.email = email;
//        return updateEmail;
//    }
    /**
     * 비밀번호 변경
     * */
    public static MyPageMemberResponseDto updatePassword(Long memberId, String loginPassword, String checkPassword) {
        MyPageMemberResponseDto updatePassword = new MyPageMemberResponseDto();
        updatePassword.memberId = memberId;
        updatePassword.loginPassword = loginPassword;
        updatePassword.checkPassword = checkPassword;
        return updatePassword;
    }
}
