document.addEventListener('DOMContentLoaded', function() {
    //유저코드 가져옴
    let UserCodeE = document.querySelector('.UserCode');
    console.log(UserCodeE);

    var UserCode = "";
    if (UserCodeE) {
        UserCode = UserCodeE.textContent;
        console.log(UserCode);

    } else {
        console.log('.UserCode 요소를 찾을 수 없습니다.');
    }

    //상품 코드
    let ProdCodeE = document.querySelector(".prodCode");
    let prodCode = ProdCodeE.textContent;
    console.log(prodCode);

    //상품 이름
    let prodNameE = document.querySelector(".prodName");
    let ProdName = prodNameE.textContent;
    console.log(ProdName)

    //상품 가격
    let prodPriceE = document.querySelector(".prodPrice");
    let prodPrice = prodPriceE.textContent;
    console.log(prodPrice);

    //상품 사이즈
    var prodSize = "";
    let ProdSizeE = document.getElementById('prodSize');
        ProdSizeE.addEventListener('change', function() {
            prodSize = ProdSizeE.value;
            console.log(prodSize);
        });

    //상품 수량
    let ProdCnt = "1";
    console.log(ProdCnt);

    let basketBtn = document.querySelector(".basket-button");

    //장바구니 버튼 클릭했을때
    basketBtn.onclick = () => {
        console.log("장바구니버튼 클릭");
        console.log(UserCode);

        if(prodSize === ""){
            alert("상품 사이즈를 선택해주세요");
        }else{
            //보내줄 정보들
                    let PostData = `usercode=${UserCode}&prodcode=${prodCode}&prodname=${ProdName}&prodsize=${prodSize}&prodcnt=${ProdCnt}&prodprice=${prodPrice}&totalPrice${prodPrice}`

                    $.ajax({
                        url: "/shopping/insertcartbox/" + UserCode +"/"+ prodCode,
                        data: PostData,
                        type: "POST",
                        success : function(data){
                            alert("상품이 장바구니에 담겼습니다.")
                        },
                        error : function(){
                            alert("에러")
                        }
                    })
        }



    }

    let payBtn = document.querySelector('.buy-button');



    payBtn.onclick = () => {
        console.log("바로결제");

        if(prodSize === ""){
            alert("상품 사이즈를 선택해주세요");
        }else{
            location.href = "/order/auickly/payment/" + UserCode + "/" + prodCode + "/" + prodSize;
        }


    }

});

var soldout = document.querySelectorAll('.size');

soldout.forEach(function(soldout){

    var soldoutoption = soldout.textContent;

    if(soldoutoption === '품절'){
        soldout.disabled = true;
    }else if(soldoutoption === '옵션을 선택해주세요'){
        soldout.disabled = true;
    }
});

//판매가 , 표시

var prod_price = document.querySelector('.prod-price-text1').textContent;

var price1 = parseInt(prod_price);

var price2 = price1.toLocaleString();

document.querySelector('.prod-price-text1').textContent = price2 + "원";


//할인가 , 표시

var disprod_price = document.querySelector('.prod-discount-text1').textContent;

var disprice1 = parseInt(disprod_price);

var disprice2 = disprice1.toLocaleString();

document.querySelector('.prod-discount-text1').textContent = disprice2 + "원";

//배송비

var dilprod_price = document.querySelector('.prod-diliver-text1').textContent;

var dilprice1 = parseInt(dilprod_price);

var dilprice2 = dilprice1.toLocaleString();

document.querySelector('.prod-diliver-text1').textContent = dilprice2 + "원";










