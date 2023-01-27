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
            const memberQuestionId = response.data.memberQuestionId
            const date = '<h3>'+response.data.memberQuestionDate+'</h3>'

            $('#title').children('p').eq(0).text(title)
            $('#title').children('p').eq(1).text(content)
            $('#questionId').val(questionId)
            $('#memberQuestionId').val(memberQuestionId)
            $('#today').append(date)

        }).catch(function (error) {
        alert(error);
    });
}

/**
 * 답변 저장
 * */
function updateQuestion() {
    const answer = $('#answer').val();
    const memberQuestionId = $('#memberQuestionId').val()

    axios.put("/question/"+memberQuestionId+"/"+answer, {
        memberQuestionId: memberQuestionId,
        answer: answer
    }) .then(function (response) {
        alert('답변이 완료되었습니다.')
        location.href= "/";
    })
        .catch(function (error) {
            alert(error);
        });
}


