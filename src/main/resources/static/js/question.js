$(document).ready(function() {
    questionRandom();
});


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
            const date = '<h3>'+response.data.memberQuestionDate+'</h3>'

            $('#title').children('p').eq(0).text(title)
            $('#title').children('p').eq(1).text(content)
            $('#questionId').val(questionId)
            $('#today').append(date)

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


