const count = 0;

$(document).ready(function() {
    inputError();
    settingNavbar();

    if (urlCheck()){
        selectMember()
    };



});

/**
 * 회원가입
 * */
function joinMember() {
    const email = $('#email').val();
    const loginId = $('#loginId').val();
    const loginPassword = $('#loginPassword').val();
    const name = $('#name').val();
    const birth = $('#birth').val();

    axios.post("/members/join", {
        email: email,
        loginId: loginId,
        loginPassword: loginPassword,
        name: name,
        birth: birth,
    }) .then(function (response) {
        location.href= "/";
    })
        .catch(function (error) {
            alert(error);
        });
}
/**
 * 회원가입시 INPUT 공백이면 에러 스타일 적용
 * */

function inputError(){
    let newValue;
    $('.form-control').on("propertychange change keyup paste input", function() {
        newValue = $(this).val();
            if (newValue == null || newValue === ""){
                $(this).siblings('p').text('값을 입력해 주세요')
                $(this).siblings('p').removeClass('fontBlue')
                $(this).siblings('p').addClass('fontError')

            }else {
                $(this).siblings('p').text('사용할 수 있습니다.')
                $(this).siblings('p').removeClass('fontError')
                $(this).siblings('p').addClass('fontBlue')
            }
    });
}

/**
 * 로그인
 * */
function loginMember() {
    const loginId = $('#loginId').val();
    const loginPassword = $('#loginPassword').val();

    axios.post("/members/login", {
        loginId: loginId,
        loginPassword: loginPassword,
    }).then(function (response) {
        login(response);
        location.href= "/";
    }).catch(function (error) {
        alert(error);
    });
}

/**
 * 로그인시 네비바
 * */
function settingNavbar() {
    if (loginCheck() ) {
        let html ='';

        html +=
            '<a href="#about" class="w3-bar-item w3-button">About</a>' +
            '<a href="#menu" class="w3-bar-item w3-button">Menu</a>' +
            '<a href="#contact" class="w3-bar-item w3-button">Contact</a>' +
            '<a href="/members/myPage" class="w3-bar-item w3-button">마이페이지</a>' +
            '<a href="/" class="w3-bar-item w3-button" onclick="logout();">로그아웃</a>'

        $("#siderBar").empty();
        $("#siderBar").html(html);
    }
}

/**
 * 로그인 여부 판단
 * */
function loginCheck() {
    let check = Boolean(false);
    if (localStorage.getItem('loginInfo') != null){
        console.log('로그인 중인 사용자');
        check = true;
    } else {
        console.log('로그인 중이 아닌 사용자');
    }
    return check;
}

/**
 * 로컬스토리지 사용자 정보 저장
 * */
function login(loginResponse) {
    const loginInfo = {
        loginId: loginResponse.data.loginId,
        token: loginResponse.data.token,
        memberId: loginResponse.data.memberId
    };

    localStorage.setItem('loginInfo', JSON.stringify(loginInfo));
}

/**
 * 로그아웃시 로컬스토리지 사용자 정보 삭제
 * */
function logout() {
    localStorage.removeItem('loginInfo');
    location.href = '/';
}

/**
 * 사용자 정보 조회
 * */
function selectMember(){
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId

        axios.get("/members/myPage/"+ memberId)
            .then(function (response) {
                settingMyPageMember(response);
            })
            .catch(function (error) {
                alert(error);
            });







}

function settingMyPageMember(response){
    const myPageMemberInfo = {
        memberId: response.data.memberId,
        loginId: response.data.loginId,
        name: response.data.name,
        birth: response.data.birth,
        email: response.data.email
    };
    // const myPageMember = JSON.parse(myPageMemberInfo);

    $('#loginId').val(response.data.loginId)
    $('#name').val(response.data.name)
    $('#birth').val(response.data.birth)
    // $('#email').val(response.data.email)

}

/**
 * 사용자 정보 업데이트
 * */
function updateMember(){

    axios.put("/members/myPage/")
        .then(function (response) {
            settingMyPageMember(response);
        })
        .catch(function (error) {
            alert(error);
        });

}

function urlCheck(){
    const myPage = 'http://localhost:8080/members/myPage'
    const nowPage = document.location.href
    let check = Boolean(false);

    if (myPage === nowPage){
        check = true;
    }
    return check;


}

// 로그인 응답에 loginId, success, message, token만 있음 -완료
// 여기에 memberNo를 추가 -완료
// 프론트의 loginInfo 추가 -완료

// members/{2} -완료

//마이페이지
// 회원정보 가져오기, 수정/저장
//


