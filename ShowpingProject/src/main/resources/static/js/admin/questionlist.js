    var qustate = document.querySelector('.question-state');
    var questionforms = document.querySelectorAll('.question-list-form');

    qustate.addEventListener('change', function () {
        qustatevalue = qustate.value;

        questionforms.forEach(function (form) {
            var statecollum = form.querySelector('.question-list-collum7');
            var vquestionform = statecollum.textContent.trim();

            if (qustatevalue === "0") {
            form.style.display = "block";
            form.style.display = "flex";
        }
        else if (qustatevalue === "1") {
            if (vquestionform === "완료") {
                form.style.display = "block";
                form.style.display = "flex";
            }
            else {
                form.style.display = "none";
            }
        }
        else if (qustatevalue === "2") {
            if (vquestionform === "미완료") {
                form.style.display = "block";
                form.style.display = "flex";
            }
            else {
                form.style.display = "none";
            };

        }

        });
     });

        //데이터를 날짜형식으로 변환 해주기

        var dateStringElement = document.querySelector('.question-list-collum6');
        var dateString = dateStringElement.textContent;
        var dateObject = new Date(dateString);


        //조회하기 클릭시 해당 날짜에 해당하는 문의만 보여주기
        var search = document.querySelector('.in-button');
        search.addEventListener('click', function(){
        var start = new Date(document.querySelector('.seller-start').value);
        var end = new Date(document.querySelector('.seller-end').value);
        var quform = document.querySelectorAll('.question-list-form');

       //문의 목록을 전부 조회하며 기간에 해당하는 값만 보이도록 하기
       quform.forEach(function(quform){
       var quDate = new Date(quform.querySelector('.question-list-collum6').textContent)
       if(quDate >= start && quDate <= end){
       quform.style.display = "block";
       quform.style.display = "flex";
       }
       else{
       quform.style.display = "none";
       }
        });
        });

//답변 완료, 미완료 여부
let Q = document.querySelectorAll('.question-list-collum7');

Q.forEach(function(target){
    if(target.innerText === '답변완료'){
        target.style.color = "blue";
        target.style.fontWeight  = "bold";
    }else{
        target.style.color = "red";
        target.style.fontWeight = "bold";
    }
});


