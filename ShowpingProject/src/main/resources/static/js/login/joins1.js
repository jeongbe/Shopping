let id_count =0;
let password_count =0;
let password_check_count =0;
let name_count =0;
let birthday_count =0;
let phone_count =0;
let address_count =0;
let address_detail_count =0;
let sum_count =0;

//아이디
const userid_warning = document.querySelector('.userid');   //사용자가 입력한 아이디
const text_userid_warning = document.querySelector('.warning_id');  // 오류문구
const chk_id_btn = document.querySelector('.chk_btn');      //중복확인 버튼
const UsernameMessage = document.querySelector('.usernameMessage'); //텍스트

userid_warning.addEventListener('blur', function() {

     if(userid_warning.value !=""){
     userid_warning.style.borderColor = "black";
     text_userid_warning.style.display = "none";
     }
     else{
      userid_warning.style.borderColor = "red";
      text_userid_warning.style.display = "block";
     }
});

//아이디 중복 확인
document.addEventListener('DOMContentLoaded', function() {
    const chkIdBtn = document.querySelector('.chk_btn');
    const userIdInput = document.getElementById('userid');
    const usernameMessage = document.querySelector('.usernameMessage');

    chkIdBtn.addEventListener('click', function() {
        const userid = userIdInput.value;

        fetch('/joins/idcheck', {
            method: 'POST',
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
           },
           body: 'userid=' + encodeURIComponent(userid)

        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok.');
            }
            return response.json();
        })
        .then(data => {
            if (data.isAvailable) {
                usernameMessage.textContent = '사용 가능한 아이디입니다.';
//                id_count=1;
            } else {
                usernameMessage.textContent = '이미 존재하는 아이디입니다.';
//                id_count=0;
            }
        })
        .catch(error => {
            usernameMessage.textContent = '오류가 발생했습니다: ' + error.message;
        });

        if(usernameMessage.textContent == '사용 가능한 아이디입니다.'){
        id_count=1;
        }
        else {
        id_count=0;
        }
    });
});



//비밀번호
const password_warning = document.querySelector('.pasw');
const text_password_warning = document.querySelector('.warning_password');

password_warning.addEventListener('blur', function() {
          const password = password_warning.value;

          if(password.length >= 8 && password.length <= 16){
          password_warning.style.borderColor = "black";
          text_password_warning.style.display = "none";
          password_count =1;
          }
          else{
           password_warning.style.borderColor = "red";
           text_password_warning.style.display = "block";
           password_count =0;
          }
});

//비밀번호 체크
const passwordcheck_warning = document.querySelector('.chk_pasw');
const text_passwordcheck_warning = document.querySelector('.warning_passwordcheck');
passwordcheck_warning.addEventListener('blur', function() {
        if(password_warning.value === passwordcheck_warning.value){
            passwordcheck_warning.style.borderColor = "black";
            text_passwordcheck_warning.style.display = "none";
            password_check_count =1;
        }
        else{
            passwordcheck_warning.style.borderColor = "red";
            text_passwordcheck_warning.style.display = "block";
            password_check_count =0;
        }
});

//이름
const username_warning = document.querySelector('.username');
const text_username_warning = document.querySelector('.warning_name');
username_warning.addEventListener('blur', function() {
          if(username_warning.value !=""){
          username_warning.style.borderColor = "black";
          text_username_warning.style.display = "none";
          name_count =1;
          }
          else{
           username_warning.style.borderColor = "red";
           text_username_warning.style.display = "block";
           name_count =0;
          }
});

//생년월일
const userbirthday_warning = document.querySelector('.userbirthday');
const text_userbirthday_warning = document.querySelector('.warning_birthday');
userbirthday_warning.addEventListener('blur', function() {
          if(userbirthday_warning.value !=""){
          userbirthday_warning.style.borderColor = "black";
          text_userbirthday_warning.style.display = "none";
          birthday_count =1;
          }
          else{
           userbirthday_warning.style.borderColor = "red";
           text_userbirthday_warning.style.display = "block";
           birthday_count =0;
          }
});

//휴대전화번호
const userphone_warning = document.querySelector('.userphone');
const text_userphone_warning = document.querySelector('.warning_phone');
userphone_warning.addEventListener('blur', function() {
          if(userphone_warning.value !=""){
          userphone_warning.style.borderColor = "black";
          text_userphone_warning.style.display = "none";
          phone_count =1;
          }
          else{
           userphone_warning.style.borderColor = "red";
           text_userphone_warning.style.display = "block";
           phone_count =0;
          }
});

//주소
const useraddr_warning = document.querySelector('.useraddr');
const text_useraddr_warning = document.querySelector('.warning_address');
useraddr_warning.addEventListener('blur', function() {
       if(useraddr_warning.value !=""){
       useraddr_warning.style.borderColor = "black";
       text_useraddr_warning.style.display = "none";
       address_count =1;
       }
       else{
        useraddr_warning.style.borderColor = "red";
        text_useraddr_warning.style.display = "block";
        address_count =0;
       }
});

//상세주소
const userdetailaddr_warning = document.querySelector('.userdetailaddr');
const text_userdetailaddr = document.querySelector('.warning_detailaddress');
userdetailaddr_warning.addEventListener('blur', function() {
        if(userdetailaddr_warning.value !=""){
        userdetailaddr_warning.style.borderColor = "black";
        text_userdetailaddr.style.display = "none";
        address_detail_count =1;
        }
        else{
         userdetailaddr_warning.style.borderColor = "red";
         text_userdetailaddr.style.display = "block";
         address_detail_count =0;
        }
});



//가입하기 버튼 클릭시
document.addEventListener('DOMContentLoaded', function() {
    const joinForm = document.querySelector('.form_container');
    joinForm.addEventListener('submit', function(event) {
        let sum_count = id_count + password_count + password_check_count + name_count +
                        birthday_count + phone_count + address_count + address_detail_count;

        if(sum_count != 8) {
            event.preventDefault(); // sum_count = 8 이 아닐때 폼 제출을 막음
            alert('모든 필드를 올바르게 입력해주세요.');
        } else {
            // 유효성 검사에 성공했을 때는 추가적인 작업을 수행하지 않고 폼이 자동으로 제출

        }
    });
});