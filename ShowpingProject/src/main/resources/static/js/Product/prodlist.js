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