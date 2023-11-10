document.addEventListener('DOMContentLoaded', function() {
    let UserCodeE = document.querySelector('.UserCode');
    console.log(UserCodeE);

    let UserCode = "";
    if (UserCodeE) {
        UserCode = UserCodeE.textContent;
        console.log(UserCode);

    } else {
        console.log('.UserCode 요소를 찾을 수 없습니다.');
    }

    let ProdCodeE = document.querySelector(".prodCode");
    let prodCode = ProdCodeE.textContent;
    console.log(prodCode);

    let prodNameE = document.querySelector(".prodName");
    let ProdName = prodNameE.textContent;
    console.log(ProdName)

    let prodPriceE = document.querySelector(".prodPrice");
    let prodPrice = prodPriceE.textContent;
    console.log(prodPrice);

    var prodSize = "";
    let ProdSizeE = document.getElementById('prodSize');
        ProdSizeE.addEventListener('change', function() {
            prodSize = ProdSizeE.value;
            console.log(prodSize);
        });


    let ProdCnt = "1";
    console.log(ProdCnt);

    let basketBtn = document.querySelector(".basket-button");

    basketBtn.onclick = () => {
        console.log("장바구니버튼 클릭");
        console.log(UserCode);


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

//        location.href="/shopping/cartbox/" + UserCode;
    }

});
