 // 카테고리와 세부 카테고리 배열 정의
        var scate1 = ["긴팔", "반팔", "후드", "니트"];
        var scate2 = ["데님팬츠", "코튼팬츠", "카고팬츠", "조거팬츠", "슬렉스"];
        var scate3 = ["후드집업", "가디건", "무스탕", "패딩", "코트"];
        var scate4 = ["운동화", "구두", "로퍼", "샌들"];

        var categorySelect = document.getElementById("categorySelect");
        var subcategorySelect = document.getElementById("subcategorySelect");

        // 카테고리 선택 시 세부 카테고리 동적으로 업데이트
        categorySelect.addEventListener("change", function () {
            subcategorySelect.innerHTML = ""; // 기존 옵션 제거
            var selectedCategory = categorySelect.value;
            var subcategories = [];

            switch (selectedCategory) {
                case "1":
                    subcategories = scate1;
                    break;
                case "2":
                    subcategories = scate2;
                    break;
                case "3":
                    subcategories = scate3;
                    break;
                case "4":
                    subcategories = scate4;
                    break;
                default:
                    subcategories = ["세부카테고리"];
                    break;
            }

            // 동적으로 세부 카테고리 옵션 추가
            subcategories.forEach(function (subcategory) {
                var option = document.createElement("option");
                option.text = subcategory;
                subcategorySelect.add(option);
            });
        });

        // 할인율 입력란과 체크박스 요소 가져오기
                var discountInput = document.querySelector(".product-discountprice-input input[type='text']");
                var discountCheckbox = document.querySelector(".product-discount-input input[type='checkbox']");
                var sellPriceInput = document.querySelector(".product-sellprice-input input[type='text']");
                var actualPriceInput = document.querySelector(".product-sellprice-input input[readonly]");


                // 할인 체크박스의 변경 이벤트 처리
                discountCheckbox.addEventListener("change", function () {
                    toggleDiscountRateInput();
                });
                //적용하기 버튼을 누르면 할인여부에 따라 바로 실제 가격을 나타내주는 온클릭 이벤트
                function apply() {
                    updateActualPrice();
                }

                // 판매 가격 및 할인 여부를 기반으로 실제 가격 업데이트
                function updateActualPrice() {
                    var sellPrice = parseFloat(sellPriceInput.value);
                    var discountRate = parseFloat(discountInput.value);
                    var isDiscounted = discountCheckbox.checked;

                    if (isDiscounted && !isNaN(sellPrice) && !isNaN(discountRate)) {
                        var actualPrice = sellPrice - (sellPrice * (discountRate / 100));
                        actualPriceInput.value = actualPrice.toFixed(2) + "원";
                    } else {
                        // 할인 체크박스가 체크되지 않았거나 판매 가격이나 할인율이 유효하지 않은 경우
                        // 판매 가격 그대로 표시
                        actualPriceInput.value = sellPriceInput.value;
                    }
                }

                // 할인율 입력란의 가시성을 토글
                function toggleDiscountRateInput() {
                    var isDiscounted = discountCheckbox.checked;
                    discountInput.style.display = isDiscounted ? "block" : "none";
                    updateActualPrice(); // 가시성 변경 후 실제 가격 업데이트
                }

                // 페이지 로드 시 할인율 입력란 초기 가시성 설정
                toggleDiscountRateInput();

                //HTML 요소 가져오기
                var notice = document.getElementById("notice");
                var materialInput = document.querySelectorAll(".materialInput")
                //notice 체크박스 모니터링
                notice.addEventListener("change", function(){
                    //체크가 된다면 상품상세 참조
                    if(notice.checked){
                        materialInput.forEach((input) =>{
                            input.value="상품상세 참조";
                        })
                    }
                    //체크가 안되면 초기값 복구
                    else{
                        materialInput.forEach((input) =>{
                            input.value="";
                        })
                    }
                });

                 //정보가져오기
                        var deimage = document.querySelector(".default-image");
                        var addimage1 = document.querySelector(".add-image1");
                        var addimage2 = document.querySelector(".add-image2");
                        var addimage3 = document.querySelector(".add-image3");
                        var addimage4 = document.querySelector(".add-image4");
                        var addimage5 = document.querySelector(".add-image5");
                        var addimage6 = document.querySelector(".add-image6");
                        var addimage7 = document.querySelector(".add-image7");
                        var addimage8 = document.querySelector(".add-image8");
                        //몇개의 추가가 이루어졌는지 확인하기 위한 변수
                        var cnt = 0;

                        //온클릭 이벤트 클릭시 하나씩 순차적으로 보이게
                        function addimage(){
                            if(cnt===0)
                            {
                                addimage1.style.display= "block";
                                cnt++;
                            }
                            else if (cnt === 1){
                                addimage2.style.display="block";
                                cnt++;
                            }
                            else if (cnt === 2){
                                addimage3.style.display="block";
                                cnt++;
                            }
                            else if (cnt === 3){
                                addimage4.style.display="block";
                                cnt++;
                            }
                            else if (cnt === 4){
                                addimage5.style.display="block";
                                cnt++;
                            }
                            else if (cnt === 5){
                                addimage6.style.display="block";
                                cnt++;
                            }
                            else if (cnt === 6){
                                addimage7.style.display="block";
                                cnt++;
                            }
                            else if (cnt === 8){
                                addimage8.style.display="block";
                            }
                        }
