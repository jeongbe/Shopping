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

document.addEventListener("DOMContentLoaded", function () {
    const addAddressButton = document.getElementById("add-address-button");
    const addressContainer = document.querySelector(".address_container");
    let addressCount = 0; // 입력된 주소 수

    addAddressButton.addEventListener("click", function () {
        if (addressCount < 3) {
            // 복제할 기존 주소 컨테이너
            const originalAddressContainer = addressContainer.cloneNode(true);

            // 새로운 주소와 상세주소 입력 칸을 addressContainer에 추가
            addressContainer.appendChild(originalAddressContainer);

            addressCount++;
        }
    });
});
