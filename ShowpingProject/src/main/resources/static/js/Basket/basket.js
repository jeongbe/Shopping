//alert("gd");
// const checkAll = document.getElementById('Allc');
//    const checkboxes = document.querySelectorAll('.chk');
//    const deleteButtons = document.querySelectorAll('.Ximg');
//    const products = document.querySelectorAll('.product');
//    const allDeleteButton = document.querySelector('.Alldelete');
//    const productsContainer = document.querySelector('.products');
//    const clickDeleteButton = document.querySelector('.clickdelete');
//
//
//    // "전체 선택" 체크박스를 클릭할 때의 이벤트 핸들러입니다.
//    checkAll.onclick = () => {
//        // "전체 선택" 체크박스의 상태를 가져와 is_checked 변수에 저장합니다.
//        is_checked = checkAll.checked;
//
//        // 각 상품의 체크박스에 대해 is_checked 값으로 상태를 설정하여 모두 선택 또는 해제합니다.
//        checkboxes.forEach((checkbox) => {
//            checkbox.checked = is_checked;
//        });
//    };
//
//    // 각 상품의 체크박스에 대한 이벤트 핸들러를 등록합니다.
//    checkboxes.forEach((checkbox) => {
//        // 개별 체크박스를 클릭할 때의 이벤트 핸들러입니다.
//        checkbox.onclick = () => {
//            // 모든 체크박스가 선택되었는지 확인하는 변수를 선언합니다.
//            const allChecked = Array.from(checkboxes).every((chk) => chk.checked);
//
//            // 만약 모든 체크박스가 선택되었다면 "전체 선택" 체크박스를 활성화합니다.
//            checkAll.checked = allChecked;
//        };
//    });
//
//    //X이미지 클릭시 해당 상품 삭제
//    deleteButtons.forEach((button, index) => {
//        button.onclick = () => {
//            // 해당 버튼이 속한 product 요소를 삭제
//            products[index].remove();
//        };
//    });
//
//        // 제품들의 정보를 페이지에 업데이트하는 함수
//        function updateProductElements() {
//            // 모든 .product 요소들을 순회하며 각 제품의 정보를 업데이트
//            productElements.forEach(function (productElement, index) {
//                //상품명
//                var productNameElement = productElement.querySelector(".product_name");
//                //상품 사이즈
//                var productSizeElement = productElement.querySelector(".product_size");
//                //상품 가격
//                var productPriceElement = productElement.querySelector(".product_pirce");
//                //상품 개수
//                var quantityElement = productElement.querySelector(".page-item.disabled a");
//
//                //이름 넣어주기
//                productNameElement.textContent = products[index].name;
//                //사이즈 넣어주기
//                productSizeElement.textContent = products[index].size;
//
//                //개수만 큼 곱해서 가격 넣어주기
//                var totalPrice = products[index].price * products[index].quantity;
//                productPriceElement.textContent = totalPrice.toLocaleString() + "원";
//
//                //상품 개수
//                quantityElement.textContent = products[index].quantity;
//            });
//        }
//
//        // + 버튼 및 - 버튼 이벤트 처리
//        productElements.forEach(function (productElement, index) {
//            var plusButton = productElement.querySelector(".page-item:nth-child(3) a");
//            var minusButton = productElement.querySelector(".page-item:nth-child(1) a");
//
//            plusButton.addEventListener("click", function (event) {
//                event.preventDefault();
//                products[index].quantity++;
//                updateProductElements();
//            });
//
//            minusButton.addEventListener("click", function (event) {
//                event.preventDefault();
//                if (products[index].quantity > 1) {
//                    products[index].quantity--;
//                    updateProductElements();
//                }
//            });
//        });
//
//        // 초기 제품 정보 업데이트
//        updateProductElements();
//
//        var allCheckbox = document.getElementById("Allc");
//        var checkboxes = document.querySelectorAll(".chk");
//        var totalCount = 0;
//        var allClickText = 0;
//        var checkedCount = 0;
//
//         // "선택 삭제" 버튼 클릭 시 이벤트 처리
//        clickDeleteButton.onclick = () => {
//            const checkedProductIndices = [];
//
//
//            checkboxes.forEach((checkbox, index) => {
//                if (checkbox.checked) {
//                    checkedProductIndices.push(index);
//                }
//            });
//
//            // 선택된 상품을 역순으로 정렬하여 뒤에서부터 삭제해야 인덱스 문제를 피할 수 있습니다.
//            checkedProductIndices.reverse();
//
//            checkedProductIndices.forEach(index => {
//                // DOM에서 해당 상품 요소 제거
//                productElements[index].remove();
//                // products 배열에서 해당 상품 제거
//                products.splice(index, 1);
//            });
//
//            // 체크된 상품의 개수를 계산하여 totalCount 업데이트
//
//            totalCount--;
//            checkedCount--;
//            if(checkedCount < 0){
//                checkedCount = 0;
//            }
//            allClickText = "(" + checkedCount + "/" + totalCount + ")";
//            document.querySelector(".checkbox-count").textContent = allClickText;
//        };
//
//
//        let count = 0;
//        // 체크박스의 상태가 변경될 때마다 호출되는 함수
//        function updateCheckboxCount() {
//
//            if(count % 2 === 0){
//
//                checkboxes.forEach(function (checkbox) {
//                    if (checkbox.checked) {
//                        checkedCount++;
//                    }
//                });
//
//                totalCount = checkboxes.length;
//                allClickText = "(" + checkedCount + "/" + totalCount + ")";
//                document.querySelector(".checkbox-count").textContent = allClickText;
//            }else if(count % 2 === 1){
//                totalCount = checkboxes.length;
//                checkedCount = 0;
//                allClickText = "(" + checkedCount + "/" + totalCount + ")";
//                document.querySelector(".checkbox-count").textContent = allClickText;
//            }
//            count++;
//        }
//
//        //전체 삭제
//        allDeleteButton.onclick = () => {
//            // productsContainer 내의 모든 product 요소를 삭제
//            productsContainer.innerHTML = '';
//
//            // 모든 체크박스를 해제
//            checkboxes.forEach(function (checkbox) {
//                checkbox.checked = false;
//            });
//
//            // totalCount를 0으로 업데이트하고 화면에 출력
//            totalCount = 0;
//            allClickText = "(" + checkedCount + "/" + totalCount + ")";
//            document.querySelector(".checkbox-count").textContent = allClickText;
//        };
//
//        // 각 상품 체크박스에 이벤트 리스너 추가
//        checkboxes.forEach(function (checkbox) {
//            checkbox.addEventListener("change", updateCheckboxCount);
//        });
//
//        // 전체 선택 체크박스에 이벤트 리스너 추가
//        allCheckbox.addEventListener("change", function () {
//            // 전체 선택 체크박스(allCheckbox)의 상태가 변경될 때마다 실행되는 함수입니다.
//
//            checkboxes.forEach(function (checkbox) {
//                // checkboxes 배열에 포함된 각각의 개별 상품 체크박스에 대해 반복합니다.
//
//                checkbox.checked = allCheckbox.checked;
//                // 개별 상품 체크박스의 checked 속성을 전체 선택 체크박스의 상태(allCheckbox.checked)로 설정합니다.
//                // 이렇게 함으로써 전체 선택 체크박스의 상태에 따라 모든 개별 상품 체크박스가 선택 또는 해제됩니다.
//            });
//
//            updateCheckboxCount();
//            // 전체 선택 체크박스의 상태가 변경되었으므로, 선택된 상품의 개수를 다시 계산하고 페이지에 업데이트하는 함수(updateCheckboxCount)를 호출합니다.
//        });
//    });



