document.addEventListener('DOMContentLoaded', function () {
    let countValues = document.querySelectorAll('.countValue');
    console.log()
    // NodeList를 배열로 변환
    let countValuesArray = Array.from(countValues);
    console.log(countValuesArray.length);



    // 배열의 각 요소에 대해 출력
    countValuesArray.forEach(function (countValue) {
        console.log(countValue.textContent);
    });

    let text = document.querySelectorAll('.orderDetailCount');
    let array2 = Array.from(text);
    array2.forEach(function (Value) {
        console.log(Value.textContent);
    });

    for(let i = 0; i < countValuesArray.length; i++){
        array2[i].textContent = "총 "+countValuesArray[i].textContent +"건";
    }

    let TotalPriceEL = document.querySelectorAll('.totalPrice_');

    TotalPriceEL.forEach(function(TPElement){
            let price = parseInt(TPElement.textContent.replace(/₩|,/g, ''));
            TPElement.textContent = "₩" + price.toLocaleString() + "원";
        })
});

// Rest of your existing code


let totalPage = document.querySelector(".totalPage").textContent;
//console.log(totalPage);

let usercode = document.querySelector(".u").textContent;

let userCode = parseInt(usercode);
//console.log(userCode);

 // 부모 요소
var paginationContainer = document.querySelector(".pagination");

if (paginationContainer) {
 // 이전 버튼
    var prevButton = document.createElement("li");
    prevButton.classList.add("page-item");
    prevButton.innerHTML = `<a class="page-link"  href="/mypage/main/${userCode}?page=0" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>`;
    paginationContainer.appendChild(prevButton);

 // 페이지 번호 생성
    for (var i = 1; i <= totalPage; i++) {
        var pageItem = document.createElement("li");
        pageItem.classList.add("page-item");
        pageItem.innerHTML = `<a class="page-link" href="/mypage/main/${userCode}?page=${i-1}">${i}</a>`;
        paginationContainer.appendChild(pageItem);
    }

    // 다음 버튼
    var nextButton = document.createElement("li");
    nextButton.classList.add("page-item");
    nextButton.innerHTML = `<a class="page-link" href="/mypage/main/${userCode}?page=${totalPage-1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>`;
    paginationContainer.appendChild(nextButton);
}



