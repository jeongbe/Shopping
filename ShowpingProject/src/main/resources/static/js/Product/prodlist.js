// HTML 요소들을 가져옵니다.
     var categoryLinks = document.querySelectorAll('.category-form h3');
     var detail1 = document.querySelector('.detail-category1 p');
     var detail2 = document.querySelector('.detail-category2 p');
     var detail3 = document.querySelector('.detail-category3 p');
     var detail4 = document.querySelector('.detail-category4 p');
     var detail5 = document.querySelector('.detail-category5 p');

      // 카테고리들에 클릭 이벤트 리스너를 추가합니다.
      categoryLinks.forEach((category) => {
         category.addEventListener('click', handleCategoryClick);
     });


     // 카테고리를 클릭할 때 실행할 함수를 정의합니다.
     function handleCategoryClick(event) {
       decateclick1();
         // 클릭한 카테고리의 텍스트를 가져옵니다.
         var selectedCategory = event.target.textContent;
          // "nav" 내의 h3 요소 업데이트
         document.querySelector('.head-category h3').textContent = selectedCategory;
         var hr = document.querySelector('.hr');
         hr.style.display = 'block';

       // 상단 카테고리 클릭시 세부카테고리가 변경되도록
          if(selectedCategory === "상의"){
             detail1.textContent = "긴팔";
             detail2.textContent = "반팔";
             detail3.textContent = "후드";
             detail4.textContent = "맨투맨";
             detail5.textContent = "니트";

         }
         else if(selectedCategory === "하의"){
             detail1.textContent = "데님팬츠";
             detail2.textContent = "코튼팬츠";
             detail3.textContent = "카고팬츠";
             detail4.textContent = "조거팬츠";
             detail5.textContent = "슬렉스";
         }
         else if(selectedCategory === "아우터"){
             detail1.textContent = "후드집업";
             detail2.textContent = "가디건";
             detail3.textContent = "무스탕";
             detail4.textContent = "패딩";
             detail5.textContent = "코트";
         }
         else if(selectedCategory === "신발"){
             detail1.textContent = "운동화";
             detail2.textContent = "구두";
             detail3.textContent = "로퍼";
             detail4.textContent = "샌들";
             detail5.textContent ="";
         }

     }

     // JavaScript 코드는 DOM 요소를 다루기 위한 코드입니다.
     // 해당 DOM 요소들을 가져오기 위해선 적절한 선택자를 사용하실 필요가 있습니다.
     // 아래 코드는 가상의 상황을 가정하고 작성한 예제입니다.

     // 세부 카테고리를 클릭했을 때 실행할 함수
     function decateclick1() {
         // 모든 상품 요소를 숨깁니다.
         hideAllProducts();
           //세부디테일 요소 전부 가져오기
           var decate1 = document.querySelector('.cusor1');
            //세부 디테일의 세부정보 가져오기
           var decateinfo1 = decate1.textContent;
            // 클릭한 세부 카테고리에 해당하는 상품 요소를 보여줍니다.
          showProductsForCategory(decateinfo1);
           }
           function decateclick2() {
                    // 모든 상품 요소를 숨깁니다.
                    hideAllProducts();
                      //세부디테일 요소 전부 가져오기
                      var decate2 = document.querySelector('.cusor2');
                       //세부 디테일의 세부정보 가져오기
                      var decateinfo2 = decate2.textContent;
                       // 클릭한 세부 카테고리에 해당하는 상품 요소를 보여줍니다.
                     showProductsForCategory(decateinfo2);
                      }
                      function decateclick3() {
                               // 모든 상품 요소를 숨깁니다.
                               hideAllProducts();
                                 //세부디테일 요소 전부 가져오기
                                 var decate3 = document.querySelector('.cusor3');
                                  //세부 디테일의 세부정보 가져오기
                                 var decateinfo3 = decate3.textContent;
                                  // 클릭한 세부 카테고리에 해당하는 상품 요소를 보여줍니다.
                                showProductsForCategory(decateinfo3);
                                 }
                                 function decateclick4() {
                                          // 모든 상품 요소를 숨깁니다.
                                          hideAllProducts();
                                            //세부디테일 요소 전부 가져오기
                                            var decate4 = document.querySelector('.cusor4');
                                             //세부 디테일의 세부정보 가져오기
                                            var decateinfo4 = decate4.textContent;
                                             // 클릭한 세부 카테고리에 해당하는 상품 요소를 보여줍니다.
                                           showProductsForCategory(decateinfo4);
                                            }
                                            function decateclick5() {
                                                     // 모든 상품 요소를 숨깁니다.
                                                     hideAllProducts();
                                                       //세부디테일 요소 전부 가져오기
                                                       var decate5 = document.querySelector('.cusor5');
                                                        //세부 디테일의 세부정보 가져오기
                                                       var decateinfo5 = decate5.textContent;
                                                        // 클릭한 세부 카테고리에 해당하는 상품 요소를 보여줍니다.
                                                      showProductsForCategory(decateinfo5);
                                                       }


     // 모든 상품을 숨기는 함수
     function hideAllProducts() {
         var productElements = document.querySelectorAll('.prod-list1-text');
         for (var i = 0; i < productElements.length; i++) {
             productElements[i].style.display = 'none';
         }
     }

     // 특정 카테고리의 상품을 보여주는 함수
     function showProductsForCategory(categoryClassName) {
         var productElements = document.querySelectorAll('.prod_decate');
         for (var i = 0; i < productElements.length; i++) {
             var productCategory = productElements[i].textContent.trim();
             if (productCategory.trim() === categoryClassName.trim()) {
                 var productElement = productElements[i].parentNode;
                 productElement.style.display = 'block';
             }
         }
     }



