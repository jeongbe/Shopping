package com.example.ShowpingProject.repository.productRepository;

import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.product.productimage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdimageRepository extends CrudRepository<productimage,Long> {
    //상품 코드와 일치하는 이미지를 가져오기 위한 쿼리
    @Query(value = "SELECT image_link, image_link2, image_link3, image_link4" +
            "FROM productimage " +
            "INNER JOIN product" +
            "ON productimage.prod_code = product.prod_code" +
            "WHERE  productimage.prod_code = :prod_code",nativeQuery = true)
    List<productimage> findbyprodcode(long prod_code);

    //디테일에서 이미지 가져오기
    @Query(value = "select p.*\n" +
            "from orderdetail o\n" +
            "join productimage p\n" +
            "on o.prod_code = p.prod_code\n" +
            "where o.order_id = :orderId" , nativeQuery = true)
    List<productimage> DetailImg(String orderId);

    //주문 메인 페이지에서 이미지
    @Query(value = "select p.*\n" +
            "from orderdetail o\n" +
            "join productimage p\n" +
            "on o.prod_code = p.prod_code\n" +
            "where o.order_id = :orderId\n" +
            "limit 1", nativeQuery = true)
    List<productimage> mypageMainImg(String orderId);

    //장바구니에서 상품 이미지 불러올때
    @Query(value = "select *\n" +
            "from basket b\n" +
            "join productimage p\n" +
            "on b.prod_code = p.prod_code\n" +
            "where b.user_code = :userCode\n" +
            "and b.prod_code= :prodCode", nativeQuery = true)
    List<productimage> basketImgs(String userCode,String prodCode);

    @Query(value = "SELECT i.*\n" +
            "FROM basket b\n" +
            "JOIN product p\n" +
            "ON b.prod_code = p.prod_code\n" +
            "join users u\n" +
            "on b.user_code = u.user_code\n" +
            "join productimage i\n" +
            "on b.prod_code = i.prod_code\n" +
            "WHERE b.user_code = :userCode", nativeQuery = true)
    List<productimage> test(int userCode);

    //바로 결제 할때 주문 페이지에서 이미지 뿌려줄때
    @Query(value = "select i.image_link\n" +
            "from product p\n" +
            "join productimage i\n" +
            "on p.prod_code = i.prod_code\n" +
            "where p.prod_code = :prodCode", nativeQuery = true)
    String oneimg(String prodCode);
}
