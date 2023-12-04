let totalPrice = 0;

// DOMContentLoaded 이벤트가 발생했을 때 실행할 코드를 정의합니다.
document.addEventListener("DOMContentLoaded", function() {
    var AllPriceElement = document.querySelector('.All_price');
    var LastAllPrice = document.querySelector('.last_All_price');

    let prodPrice = document.querySelectorAll(".price_");



    // find 함수 정의
    function find(prod) {

        console.log("dsfsdf");

        // 상품 가격이 표시된 요소들
        let priceElements = prod.querySelectorAll(".price_");

        // 각 요소에서 텍스트를 추출하여 가격을 합산
        priceElements.forEach(function (priceElement) {
            let priceText = priceElement.textContent.replace("원", "").trim();
            let price = parseInt(priceText.replace(",", ""), 10);
            totalPrice += price;
        });

        // 총 가격을 콘솔에 출력
        console.log("총 가격: " + totalPrice + "원");

        // All_price 요소의 텍스트를 변경
        AllPriceElement.textContent = "₩" + totalPrice.toLocaleString() + "원";


        //배달비까지 더한 최종 가격
        let a = parseInt(totalPrice) + 3000;
        LastAllPrice.textContent = "₩" + a.toLocaleString() + "원";
    }

    // 페이지 로드 시 자동으로 find 함수 호출
    find(document);
    prodPrice.forEach(function(PElement){
        let price = parseInt(PElement.textContent.replace(/₩|,/g, ''));
        PElement.textContent = "₩" + price.toLocaleString() + "원";
    })
});

//유저 코드 가져옴
var userCodeE = document.querySelector(".userCode");
var userCode = parseInt(userCodeE.textContent);
console.log(userCode);

//유저 이름 가져옴
var userName = document.querySelector(".userName").textContent;
console.log(userName);

let PayMentBtn = document.querySelector(".paymentBtn");

PayMentBtn.onclick = () => {
    alert("주문완료");
    totalPrice = totalPrice + 3000;
    //데이터를 총가격,유저코드,유저이름을 보내준다.
    let dataToSend2 = `totalPrice=${totalPrice}&userCode=${userCode}&userName=${userName}`;
    console.log(userCode);
    //보낼데이터는 상품 총 가격 , user_code
    $.ajax({
        url: "/order/" + userCode,
        data: dataToSend2,
        type: "POST",
        success: function (data) {
//            alert("성공");

        },
        error: function () {
            alert("에러");
        }
    });
    location.href = "/shopping/mainlogin";
}



