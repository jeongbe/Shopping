package com.example.ShowpingProject.service;

import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PageService {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    //기본 전체 주문 내역 가져올때
    public Page<OrderHeader> getList(int page,int userCode){
        
        //한페이지에 일단 5개 해놓음 나중에 10으로 수정해야함
        Pageable pageable = PageRequest.of(page,5);
        return this.orderHeaderRepository.find(pageable,userCode);
    }

    //직접 주문 날짜 조회할때
    public Page<OrderHeader> getSellList(int page, Date DateStart, Date DateEnd){

        Pageable pageable = PageRequest.of(page,5);

        return this.orderHeaderRepository.SellOrderHeader(pageable,DateStart,DateEnd);
    }

    //주문내역 최근 일주일 조회
    public Page<OrderHeader> getOneWeek(int page){

        Pageable pageable = PageRequest.of(page,5);
        return this.orderHeaderRepository.OneWeek(pageable);
    }
    
    //주문 내역 최근 한달
    public Page<OrderHeader> getOneMonth(int page){

        Pageable pageable = PageRequest.of(page, 5);
        return this.orderHeaderRepository.OneMonth(pageable);
    }
    
    //주문 내역 최근 3달
    public Page<OrderHeader> getThreeMonth(int page){

        Pageable pageable = PageRequest.of(page, 5);
        return this.orderHeaderRepository.ThreeMonth(pageable);
    }
    
    //주문 내역 최근 1년
    public Page<OrderHeader> getOneYear(int page){

        Pageable pageable = PageRequest.of(page, 5);
        return this.orderHeaderRepository.OneYear(pageable);
    }

}
