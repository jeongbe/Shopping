// 사용자 이름을 가져와 웹 페이지에 표시하는 함수
function displayUsername() {
    // 여기에서 사용자 이름을 하드코딩 또는 사용자 입력으로 설정
    const username1 = "홍길동";
    const username2 = "홍길동님 반갑습니다~!";

    // 사용자 이름을 웹 페이지에 표시
    const usernameDisplay1 = document.getElementById('username-display1');
    usernameDisplay1.textContent = username1;

    const usernameDisplay2 = document.getElementById('username-display2');
    usernameDisplay2.textContent = username2;
}

// 페이지 로드 시 사용자 이름 표시
displayUsername();

//--------------------------------------------------

// JavaScript로 전체 선택 및 개별 상품 선택 구현
const selectAllCheckbox = document.getElementById("select-all");
const itemCheckboxes = document.querySelectorAll(".item-checkbox");


selectAllCheckbox.addEventListener("change", function () {
    const isChecked = this.checked;
    itemCheckboxes.forEach((checkbox) => {
        checkbox.checked = isChecked;
    });

});

itemCheckboxes.forEach((checkbox) => {
    checkbox.addEventListener("change", function () {
        if (!this.checked) {
            // 개별 상품 체크박스 중 하나라도 체크 해제되면 "전체 선택" 체크박스도 체크 해제
            selectAllCheckbox.checked = false;
        }
    });
});