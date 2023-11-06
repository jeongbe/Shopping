// DOM 요소 가져오기
const check1 = document.querySelector('.check-wap'); // 첫 번째 체크박스 컨테이너
const check2 = document.querySelector('.check-wap2'); // 두 번째 체크박스 컨테이너
const check3 = document.querySelector('.check-wap3'); // 세 번째 체크박스 컨테이너
const check4 = document.querySelector('.check-wap4'); // 세 번째 체크박스 컨테이너
const allch = document.querySelector('.Allcheck-wap');
const item1 = document.querySelector('.item1'); // 첫 번째 체크박스 레이블
const item2 = document.querySelector('.item2'); // 두 번째 체크박스 레이블
const item3 = document.querySelector('.item3'); // 두 번째 체크박스 레이블
const item4 = document.querySelector('.item4'); // 두 번째 체크박스 레이블
const btn = document.querySelector('.btn-submit'); // "다음" 버튼
const Allcheck = document.querySelector('.allitems'); //전체 체크박스 컨테이너

var x = false; // 첫 번째 체크박스 상태
var y = false; // 두 번째 체크박스 상태
var j = false;
var z = false;
var i = false;

Allcheck.addEventListener('click', () => {
    i = !i;
    x = !x;
    y = !y;
    j = !j;
    z = !z;

    updateCheck1Content5();
    updateCheck1Content();
    updateCheck1Content2();
    updateCheck1Content3();
    updateCheck1Content4();
    c_Check();
});

// 첫 번째 체크박스 클릭 시 처리
item1.addEventListener('click', () => {
    x = !x; // 상태 반전
    updateCheck1Content();
    c_Check(); // 버튼 활성화 여부 확인
});

// 두 번째 체크박스 클릭 시 처리
item2.addEventListener('click', () => {
    y = !y; // 상태 반전
    updateCheck1Content2();
    c_Check(); // 버튼 활성화 여부 확인
});

// 세 번째 체크박스 클릭 시 처리
item3.addEventListener('click', () => {
    j = !j; // 상태 반전
    updateCheck1Content3();
    c_Check(); // 버튼 활성화 여부 확인
});

// 네 번째 체크박스 클릭 시 처리
item4.addEventListener('click', () => {
    z = !z; // 상태 반전
    updateCheck1Content4();
    c_Check(); // 버튼 활성화 여부 확인
});

// 첫 번째 체크박스 상태에 따른 UI 업데이트
function updateCheck1Content() {
    if (x) {
        onclickitem1();
    } else {
        Notclickitem1();
    }
}

// 두 번째 체크박스 상태에 따른 UI 업데이트
function updateCheck1Content2() {
    if (y) {
        onclickitem2();
    } else {
        Notclickitem2();
    }
}

// 세 번째 체크박스 상태에 따른 UI 업데이트
function updateCheck1Content3() {
    if (j) {
        onclickitem3();
    } else {
        Notclickitem3();
    }
}

// 네 번째 체크박스 상태에 따른 UI 업데이트
function updateCheck1Content4() {
    if (z) {
        onclickitem4();
    } else {
        Notclickitem4();
    }
}

function updateCheck1Content5() {
    if (i) {
        onclickitem5();
    } else {
        Notclickitem5();
    }
}

// 첫 번째 체크박스 클릭 시 UI 업데이트
function onclickitem1() {
    check1.classList.add('check-desc2'); // 체크박스 컨테이너에 스타일 추가
}

// 첫 번째 체크박스 클릭 해제 시 UI 업데이트
function Notclickitem1() {
    check1.classList.remove('check-desc2'); // 체크박스 컨테이너 스타일 제거
}

// 두 번째 체크박스 클릭 시 UI 업데이트
function onclickitem2() {
    check2.classList.add('check-desc2'); // 체크박스 컨테이너에 스타일 추가
}

// 두 번째 체크박스 클릭 해제 시 UI 업데이트
function Notclickitem2() {
    check2.classList.remove('check-desc2'); // 체크박스 컨테이너 스타일 제거
}

// 세 번째 체크박스 클릭 시 UI 업데이트
function onclickitem3() {
    check3.classList.add('check-desc3'); // 체크박스 컨테이너에 스타일 추가
}

// 세 번째 체크박스 클릭 해제 시 UI 업데이트
function Notclickitem3() {
    check3.classList.remove('check-desc3'); // 체크박스 컨테이너 스타일 제거
}

// 네 번째 체크박스 클릭 시 UI 업데이트
function onclickitem4() {
    check4.classList.add('check-desc4'); // 체크박스 컨테이너에 스타일 추가
}

// 네 번째 체크박스 클릭 해제 시 UI 업데이트
function Notclickitem4() {
    check4.classList.remove('check-desc4'); // 체크박스 컨테이너 스타일 제거
}

function onclickitem5() {
    allch.classList.add('check-desc5'); // 체크박스 컨테이너에 스타일 추가
}

function Notclickitem5() {
    allch.classList.remove('check-desc5'); // 체크박스 컨테이너 스타일 제거
}

// 체크박스 상태에 따라 버튼 활성화 여부 결정
function c_Check() {
    if (x == true && y == true && j == true) {
        btn.disabled = false; // 버튼 활성화
    } else {
        btn.disabled = true; // 버튼 비활성화
    }
}

// "다음" 버튼 클릭 시 페이지 이동
btn.onclick = () => {
    location.href = '/Pro1/join.html';
};
