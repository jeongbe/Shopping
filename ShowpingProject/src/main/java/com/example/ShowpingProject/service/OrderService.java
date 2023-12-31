package com.example.ShowpingProject.service;

import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.OrderDetail;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.repository.BasketRepository;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    //장바구니에 담은 상품 주문하기 했을때 장바구니 상품들 디테일 테이블에 담기
    public void saveOrderDetail(int userCode, Long orderID){
        List<Baskets> basketList = basketRepository.findUsercart(userCode);
        log.info(basketList.toString());

        for(Baskets baskets : basketList){
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setProd_code(baskets.getProd_code());
            orderDetail.setUser_code(baskets.getUser_code());
            orderDetail.setProd_name(baskets.getProd_name());
            orderDetail.setOrder_id(orderID);
            orderDetail.setProd_size(baskets.getProd_size());
            orderDetail.setProd_cnt(baskets.getProd_cnt());
            orderDetail.setProd_price(baskets.getProd_price());

            orderDetailRepository.save(orderDetail);
            log.info("디테일 테이블"+ orderDetail.toString());
        }

    }


}
