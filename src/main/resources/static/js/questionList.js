$(document).ready(function() {
});


function questionListAll(){
    const memberId = JSON.parse(localStorage.getItem("loginInfo")).memberId
    const startDate = $('#startDate').val();
    const endDate = $('#endDate').val();
    const text = $('#text').val();

    const params = {"memberId":memberId,
                    "startDate":startDate,
                    "endDate":endDate,
                    "text":text
                    };

    // axios.get("/question/questionList?memberId="+memberId+"&startDate="+startDate+"&endDate="+endDate+"&text="+text)
    axios.get("/question/questionList",{params})
        .then(function (response) {
            console.log(response)
            let html = '';

            if (response.data.length !==0){
                for(let i = 0; i<response.data.length; i++){
                    const today = new Date();
                    const dataDate = new Date(response.data[i].memberQuestionDate);
                    const threeMonthAgo = today.setMonth(today.getMonth() - 3) //현재 날짜에서 3개월전
                    // const disabled = dataDate - threeMonthAgo > 0? '' : 'disabled';
                    html += '<tr class="w3-hover-green tableSolid">\n' +
                        '<input type="hidden" id="memberQuestionId" value="'+response.data[i].memberQuestionId+'"/>\n' +
                        '<input type="hidden" id="questionId" value="'+response.data[i].questionId+'"/>\n' +
                        '<td id="memberQuestionDate" style="text-align: center">'+response.data[i].memberQuestionDate+'</td>\n' +
                        '<td style="text-align: center"><a onclick="memberQuestionOne();">'+response.data[i].title+'</a></td>\n'
                    if (dataDate - threeMonthAgo > 0){
                        html += '<td style="text-align: center">정상</td>'
                    }else {
                        html += '<td style="text-align: center"><button class="w3-button w3-black">결제</button></td>'
                    }
                    html += '</tr>'
                }
            }else {
                html += '<tr class="w3-hover-green tableSolid" >\n' +
                    '<td colspan="3" style="text-align: center" > 검색조건에 맞는 내용이 없습니다.</td>\n' +
                    '<td style="text-align: center" > </td>\n' +
                    '<td style="text-align: center" > </td>\n' +
                    '</tr>'
            }
            $('#questionTbody').empty();
            $('#questionTbody').append(html)
        })
        .catch(function (error) {
            alert(error);
        });
}
function memberQuestionOne(){

}