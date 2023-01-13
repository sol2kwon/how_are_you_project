package how_are_you_project.how_are_you.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString

public class MyPageMemberResponseDto {
    private Long memberId;

    private String loginId;

    private String loginPassword;

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
}
