$(document).ready(function() {
    inputError();
    settingNavbar();
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
        button += '<button id="question" class="w3-button w3-green w3-xlarge questionButton" style="height: 56px;margin: 30px;">이야기 작성하기</button>'

        $("#question").remove();
        $("#header").append(button);

        html +=
            '<a href="/#about" class="w3-bar-item w3-button">프로젝트 소개</a>' +
            '<a href="#menu" class="w3-bar-item w3-button">아이템 상점</a>' +
            '<a href="/question" class="w3-bar-item w3-button questionButton" >오늘의 질문</a>' +
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

function getLoginInfo() {
    return JSON.parse(localStorage.getItem('loginInfo'));
}

function getLoginMemberNo() {
    return getLoginInfo()['memberId'];
}

/**
 * 로그아웃시 로컬스토리지 사용자 정보 삭제
 * */
function logout() {
    localStorage.removeItem('loginInfo');
    location.href = '/';
}


/**
 * url확인
 * */

function urlCheck(){
    const myPage = location.host+'/members/myPage'
    const nowPage = document.location.href
    let check = Boolean(false);

    if (myPage === nowPage ){
        check = true;
    }
    return check;
}

/**
 * 사이더바 열기, 닫기
 * */

function sideOpenClose() {
    const stateCheck = $("#mySidebar").children('button').attr('name');
    let stateNow = 'open'

    if (stateCheck === stateNow){
        $("#mySidebar").css('width', '10px');
        stateNow = 'close'
        $("#buttonState").prop('name', stateNow);
    }else {
        $("#mySidebar").css("width", "200px");
        stateNow = 'open'
        $("#buttonState").prop('name', stateNow);
    }
}
function settingDate() {
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    const today = year + '-' + month + '-' + day
    $('#today').text(today);
}



//확인
// 비밀번호 변경 후 기존 비번으로 접속가능하나 데이터 볼 수 없음..




