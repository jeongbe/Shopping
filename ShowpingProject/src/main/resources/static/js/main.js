var category = document.getElementById("category");
var dropdown = document.getElementById("dropdown");
var line = document.querySelector(".line");
var btnContainer = document.querySelector(".btn2");
var cards2 = document.querySelector(".cards2");
var cards3 = document.querySelector(".cards3");
var count = 0;

// category와 line 요소에 마우스 이벤트 리스너를 등록합니다.
category.addEventListener("mouseenter", function () {
    // 마우스가 category 위에 있을 때 dropdown을 보이도록 설정합니다.
    dropdown.style.display = "block";
});

line.addEventListener("mouseenter", function () {
    // 마우스가 line 위에 있을 때 dropdown을 보이도록 설정합니다.
    dropdown.style.display = "block";
});

// // 이벤트 리스너 함수: 마우스가 요소를 벗어날 때 dropdown을 감춥니다.
// function hideDropdown(event) {
//     // 마우스가 dropdown, category, line 요소 밖으로 나가면 dropdown을 감춥니다.
//     if (!dropdown.contains(event.relatedTarget) && !category.contains(event.relatedTarget) && !line.contains(event.relatedTarget)) {
//         dropdown.style.display = "none";
//     }
// }

category.addEventListener("mouseleave", function(event) {
    // 마우스가 category 요소를 벗어날 때 dropdown을 감춘다.

    //event.relatedTarget을 사용하여 마우스 커서가 카테고리 요소를 떠나 다른 요소로 이동한 경우 체크
    //if (!dropdown.contains(event.relatedTarget))는 드롭다운 요소 안에 마우스 커서가 있지 않은 경우를 체크
    if (!dropdown.contains(event.relatedTarget)) {
        dropdown.style.display = "none";
    }
});

dropdown.addEventListener("mouseleave", function(event) {
    // 마우스가 dropdown 요소를 벗어날 때 dropdown을 감춘다.
    if (!category.contains(event.relatedTarget)) {
        dropdown.style.display = "none";
    }
});

// btnContainer.onclick = () => {
//     console.log("click");
// };

btnContainer.addEventListener("click", function(event) {
    count++;

    if(count == 1) {
        cards2.style.display = "flex";
    } else if(count == 2) {
        cards3.style.display = "flex";
    }

    if (count >= 2) {
        btnContainer.disabled = true;
    }
});
