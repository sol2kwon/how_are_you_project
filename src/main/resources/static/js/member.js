$(document).ready(function() {
    inputError();
    settingNavbar();

    if (urlCheck()){
        selectMember()
    };

    questionRandom();

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
        let button = '';
        button += '<button onclick="question();" id="question" class="w3-button w3-green w3-xlarge" style="height: 56px;margin: 30px;">이야기 작성하기</button>'

        $("#question").remove();
        $("#header").append(button);

        html +=
            '<a href="#about" class="w3-bar-item w3-button">About</a>' +
            '<a href="#menu" class="w3-bar-item w3-button">Menu</a>' +
            '<a href="/members/question" class="w3-bar-item w3-button">Contact</a>' +
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
    console.log(memberId)
    console.log(email)


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
/**
 * url확인
 * */

function urlCheck(){
    const myPage = location.host+'/members/myPage'
    const nowPage = document.location.href
    let check = Boolean(false);

    if (myPage === nowPage){
        check = true;
    }
    return check;
}

function question(){
    location.href = "/members/question"

}
/************************************ Question *********************************/
/**
 * 오늘의 질문 호출
 * */
function questionRandom(){
    let questionId = Math.floor(Math.random() * 100) + 1;
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId

    axios.get("/question/"+ memberId+"/"+questionId)
        .then(function (response) {
            console.log(response)
        })
        .catch(function (error) {
            alert(error);
        });


    //*알고리즘 시나리오
    // questionId값 = 1~100까지의 숫자를 무작위로 뽑는다.
    // member id값,questionId값 넘겨준다
    // member question 테이블에  where memberid 리스트 조회
    // 중복된 값이 없으면 question 테이블에서 questionId에 맞는 값을 뽑아온다.

    // 중복된 값이 있으면? member question 테이블에 저장된 id [] 출력
    // 1~100개의 숫자중에 [] 과 중복된 값을 제외하고 new [] 생성
    // new [] 에서 [0]번째 값을 가지고 question 테이블 조회
    // 화면에 반환

}
//확인
// 비밀번호 변경 후 기존 비번으로 접속가능하나 데이터 볼 수 없음..




