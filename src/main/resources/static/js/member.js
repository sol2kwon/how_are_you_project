
$(document).ready(function() {
    inputError();
    settingNavbar();

    if (urlCheck()){
        selectMember()
    };


    settingDate();

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

    if (myPage === nowPage ){
        check = true;
    }
    return check;
}

function urlCheck1(){
    const myPage = location.host+'/question'
    const nowPage = document.location.href
    let check = Boolean(false);

    if (myPage === nowPage ){
        check = true;
    }
    return check;
}

/************************************ Question *********************************/
/**
 * 오늘의 질문 호출
 * */
function questionRandom(){
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId
    axios.get("/question/get-"+memberId)
        .then(function (response) {
            const title = response.data.title
            const content = response.data.content
            const questionId = response.data.questionId

            $('#title').children('p').eq(0).text(title)
            $('#title').children('p').eq(1).text(content)
            $('#questionId').val(questionId)

        }).catch(function (error) {
            alert(error);
        });
}

/**
 * 답변 저장
 * */
function updateQuestion() {
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId
    const questionId = $('#questionId').val()
    const answer = $('#answer').val();
    const nowDate = new Date();

    axios.put("/question/put-answer", {
        memberId: memberId,
        answer: answer,
        questionId : questionId,
        nowDate : nowDate
    }) .then(function (response) {
        console.log(response)
        alert('답변이 완료되었습니다.')
        //오늘 작성한 날짜의 게시글만 불러오기
        location.href= "/";
    })
        .catch(function (error) {
            alert(error);
        });
}


/************************************ Question *********************************/
function questionListAll(){
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId
    const startDate = $('#startDate').val();
    const endDate = $('#endDate').val();

    axios.get("/question/questionList/"+memberId+"/"+startDate+"/"+endDate)
        .then(function (response) {
            console.log(response)
            let html = '';

            if (response.data.length !==0){
                for(let i = 0; i<response.data.length; i++){
                    const today = new Date();
                    const dataDate = new Date(response.data[i].memberQuestionDate);
                    const threeMonthAgo = today.setMonth(today.getMonth() - 3) //현재 날짜에서 3개월전
                    // const disabled = dataDate - threeMonthAgo > 0? '' : 'disabled';
                    html += '<tr class="w3-hover-green">\n' +
                            '<td style="text-align: center">'+response.data[i].memberQuestionDate+'</td>\n' +
                            '<td style="text-align: center"><a href="">'+response.data[i].title+'</a></td>\n'
                    if (dataDate - threeMonthAgo > 0){
                       html += '<td style="text-align: center">정상</td>'
                    }else {
                        html += '<td style="text-align: center"><button class="w3-button w3-black">결제</button></td>'
                    }
                     html += '</tr>'
                }
            }else {
                html += '<tr class="w3-hover-green">\n' +
                        '<td colspan="3" style="text-align: center"> 검색조건에 맞는 내용이 없습니다.</td>\n' +
                        '<td style="text-align: center"> </td>\n' +
                        '<td style="text-align: center"> </td>\n' +
                        '</tr>'
            }
            $('#questionTbody').empty();
            $('#questionTbody').append(html)
        })
        .catch(function (error) {
            alert(error);
        });
}


/************************************ Common *********************************/
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




