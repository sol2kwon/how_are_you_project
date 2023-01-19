$(document).ready(function() {
    selectMember()
});


/**
 * 사용자 정보 조회
 * */
function selectMember(){
    axios.get(`/members/myPage/${getLoginMemberNo()}`)
        .then(function (response) {
            settingMyPageMember(response);
        })
        .catch(function (error) {
            alert(error);
        });
}

/**
 * 사용자 정보 세팅
 * */

function settingMyPageMember(response){
    console.log(response.data.email)
    const email =response.data.email.split('@')

    $('#loginId').val(response.data.loginId)
    $('#name').val(response.data.name)
    $('#birth').val(response.data.birth)
    $('#email').val(email[0]) // 이메일 형식 지정 및 @ 끊기
    $('#email2').val('@'+email[1]) // 이메일 형식 지정 및 @ 끊기
}


/**
 * 사용자 정보 업데이트
 * 이메일 변경
 * */
function updateEmail(){
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId
    const email = $('#email').val() + $('#email2').val();

    axios.put("/members/myPage/put-email", {
        memberId: memberId,
        email: email,
    }) .then(function (response) {
        alert("수정이 완료되었습니다.")

        // settingMyPageMember(response);

    })
        .catch(function (error) {
            alert(error);
        });
}
/**
 * 사용자 정보 업데이트
 * 비밀번호 변경
 * */
function updatePassword(){
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId
    const loginPassword = $('#loginPassword').val();
    const checkPassword = $('#checkPassword').val();


    axios.put("/members/myPage/put-password", {
        memberId: memberId,
        loginPassword: loginPassword,
        checkPassword: checkPassword
    }) .then(function (response) {
        alert("수정이 완료되었습니다. 다시 로그인해주세요.")
        logout();
    })
        .catch(function (error) {
            alert(error);
        });

}