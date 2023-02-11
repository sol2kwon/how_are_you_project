$(document).ready(function() {

});

// const IMP = window.IMP; // 생략 가능
IMP.init("가맹점 식별코드"); // 예: imp00000000a IMP.init("가맹점 식별코드",test);

function requestPay() {
    IMP.request_pay({
        pg: "kcp.{상점id}",
        pay_method: "card",
        merchant_uid: "ORD20180131-0000011",   // 주문번호
        name: "노르웨이 회전 의자",
        amount: 64900,                         // 숫자 타입
        buyer_email: "gildong@gmail.com",
        buyer_name: "홍길동",
        buyer_tel: "010-4242-4242",
        buyer_addr: "서울특별시 강남구 신사동",
        buyer_postcode: "01181"
    }, function (rsp) { // callback
        if (rsp.success) {
            // 결제 성공 시 로직
        } else {
            // 결제 실패 시 로직
        }
    });
}