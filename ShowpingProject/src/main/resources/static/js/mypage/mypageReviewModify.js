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

document.getElementById("submitReview_1").addEventListener("click", function () {
    var reviewContent_1 = document.getElementById("reviewContent_1").value;
    if (reviewContent_1.trim() === "") {
        // 후기 내용이 비어 있는 경우 경고 메시지를 표시하거나 아무 작업을 하지 않을 수 있습니다.
    } else {
        // 후기를 서버로 보내는 코드를 추가하세요.
        // 이동할 페이지로 리다이렉트
        window.location.href = "Review_List_MyPage.html";
    }
});

document.getElementById("cancelReview_1").addEventListener("click", function () {
    // 입력된 내용을 지우고 MyPage_Review_List 페이지로 이동
    window.location.href = "Review_List_MyPage.html";
});