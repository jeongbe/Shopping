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

// 가상 주문 데이터
//orders 이름의 배열을 만들어 가상 주문 데이터를 저장한다. 각 주문은 객체로 표현된다
const orders = [
    { orderNumber: 1, productName: "상품 1", orderDate: "2023-10-10" },
    { orderNumber: 2, productName: "상품 2", orderDate: "2023-10-15" },
    { orderNumber: 3, productName: "상품 3", orderDate: "2023-11-05" },
    // 다른 주문 데이터...
];

// 주문 내역을 테이블에 추가하는 함수
//renderOrderTable함수는 주문 내역을 HTML 테이블에 표시하는 역할이다.
//이 함수는 orders 배열을 받아와서 주문 데이터를 HTML 테이블의 내용으로 표시한다
//tbody 변수에 주문 데이터를 표시할 HTML 테이블의 <tbody> 요소를 나타낸다
//document.querySelector("#order-table tbody")는 HTML 문서에서 id가 order-table 인 테이블의 <tbody> 요소를 선택한다
//tbody.innerHTML =''; : tobody 요소의 내용을 지운다. 이것은 이전의 표시되었던 주문 데이터를 모두 제거하여 새로운
//데이터를 추가할 때 기존 데이터와 충돌하지 않도록 한다
//orders.forEach(order => {~~}): orders 배열 내의 각 주문 데이터에 대해 반복한다. forEach 메서드를 사용하여
//배열의 각 항목에 대한 반복을 수행한다
//const row = document.createElemeet('tr'); : 새로운 주문 데이터를 표시할 HTML <tr> 요소를 생성한다.
//이 요소는 테이블에 한 행을 나타낸다
//row.innerHTML = ~~ : row요소의 innerHTML 속성을 설정한다. 이 부분은 주문 데이터를 표시하는 HTML을
//문자열 템플릿으로 생성한다. 주문 번호 , 상품 이름, 주문 날짜를 사용하여 테이블 행을 구성한다.
//tbody.appendChild(row) : row요소를 tbody 요소에 추가한다. 이로써 새로운 주문 데이터가 테이블에 추가되고 화면에 표시된다
//즉, renderOrderTable 함수는 입력된 주문 데이터를 HTML 테이블에 추가하고 나타내는 역할을 한다
function renderOrderTable(orders) {
    const tbody = document.querySelector("#order-table tbody");
    tbody.innerHTML = '';

    orders.forEach(order => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${order.orderNumber}</td>
            <td>${order.productName}</td>
            <td>${order.orderDate}</td>
        `;
        tbody.appendChild(row);
    });
}

// 초기 데이터 표시
//초기 데이터를 화면에 표시하기 위해 renderOrderTable 함수가 호출되며, 'orders' 배열이 테이블에 렌더링된다
renderOrderTable(orders);

// 필터링 함수
//filterOrders 함수는 필터링을 수행하는 함수로, 사용자가 선택한 날짜 범위와 기간 옵션에 따라
//주문 데이터를 필터링하고 다시 표시한다
function filterOrders() {
    const startDate = document.getElementById('start-date').value;      //사용자가 선택한 시작 날짜를 가져온다
    const endDate = document.getElementById('end-date').value;          //사용자가 선택한 종료 날짜를 가져온다
    const periodFilter = document.getElementById('period-filter').value; //사용자가 선택한 기간 필터 옵션을 가져온다

    // 여기에서 필터링 로직을 구현하세요.
    // startDate, endDate, periodFilter를 이용해 orders 배열을 필터링하고
    // renderOrderTable 함수를 사용하여 결과를 다시 표시해야 합니다.

    // 예시: orders 배열을 startDate와 endDate로 필터링
    //함수 내에서 선택한 날짜 범위에 따라 주문 데이터를 필터링하는 부분이다.
    //시작 날짜(startDate)와 종료 날짜(endDate)를 사용하여 주문 데이터를 필터링 한다
    //이 부분은 주문 날짜를 날짜 개체로 변환하고, 주문 날짜가 선택한 범위 내에 있는 경우 해당 주문을 포함시킨다.
    const filteredOrders = orders.filter(order => {
        const orderDate = new Date(order.orderDate);
        const filterStartDate = new Date(startDate);
        const filterEndDate = new Date(endDate);

        return orderDate >= filterStartDate && orderDate <= filterEndDate;
    });

    // 여기에서 periodFilter를 이용해 추가적인 필터링을 수행하세요.

    // 필터된 결과를 표시
    //필터링된 결과를 화면에 표시하기 위해 renderOrderTable 함수가 필터링된 주문 데이터(filteredOrders)를 테이블에 랜더링한다
    renderOrderTable(filteredOrders);
}

// 이벤트 핸들러 등록
//"조회하기" 버튼에 클릭 이벤트 리스너를 추가한다. 이 버튼을 클릭하면 filterOrders 함수가 호출되어 필터링이 수행되고 결과가 업데이트 된다.
document.getElementById('filter-button').addEventListener('click', filterOrders);