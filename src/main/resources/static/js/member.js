$(document).ready(function() {
    inputError();

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
        console.log(response);
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
                console.log($(this).siblings('p').html())
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

    alert("js 전송")

    axios.post("/members/login", {
        loginId: loginId,
        loginPassword: loginPassword,
    }) .then(function (response) {
        // location.href= "/";
        //뭘가지고 로그인 상태 유지할건지 생각해야함
        navHtml();
        console.log(response);
        alert("성공?")
    })
        .catch(function (error) {
            alert(error);
        });
}

/**
 * 로그인시 네비바
 * */
function navHtml() {
    let html ='';
    html +=
        '<a href="#about" class="w3-bar-item w3-button">About</a>'+
        '<a href="#menu" class="w3-bar-item w3-button">Menu</a>'+
        '<a href="#contact" class="w3-bar-item w3-button">Contact</a>'+
        '<a href="/templates/myPage.html" class="w3-bar-item w3-button">마이페이지</a>'+
        '<a href="#id01" class="w3-bar-item w3-button">로그아웃</a>'

    $("#siderBar").empty();
    $("#siderBar").html(html);

}




