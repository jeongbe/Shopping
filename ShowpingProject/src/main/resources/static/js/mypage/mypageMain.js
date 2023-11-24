
let totalPage = document.querySelector(".totalPage").textContent;
console.log(totalPage);

let usercode = document.querySelector(".u").textContent;

let userCode = parseInt(usercode);
console.log(userCode);

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
