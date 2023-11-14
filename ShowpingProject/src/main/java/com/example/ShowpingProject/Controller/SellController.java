package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.sellerform;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SellController {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    //날짜별로 조회해서 매출통계가져오기

    @GetMapping("/sellinfo")
    public String sellinfo(sellerform form, Model model){

        //머스태치에서 지정해준 시작날짜와 끝날짜를 파라미터로 넣어서 레파지토리의 쿼리문에 사용
        String sellinfo = orderHeaderRepository.sellinfo(form.getDateStart(),form.getDateEnd());
        if (sellinfo == null){
            sellinfo = "0";
        }
        //해당 데이터를 가져온 후 뷰에 뿌려주기
        model.addAttribute("sellinfo", sellinfo);



        return "adminpage/adminmain";
    }
    //최근 1주일 조회
    @GetMapping("/sell/7day")
    public String sell7(Model model){
        //레파지토리에서 7주일간의 매출을 조회하는 쿼리문을 가져와서 sellinfo7에 선언을 해줌
        int sellinfo7 = orderHeaderRepository.sellinfo7();
        //그 정보를 뷰에 뿌려주기위한 모델화
        model.addAttribute("sellinfo7", sellinfo7);

        return "adminpage/adminmain";
    }
    //최근 한달 조회
    @GetMapping("/sell/1Month")
    public String sell1(Model model){
        //레파지토리에서 한달간의 매출을 조회하는 쿼리문을 가져와서 sellinfo1에 선언을 해줌
        int sellinfo1 = orderHeaderRepository.sellinfo1();
        //정보를 뷰에 뿌려주기
        model.addAttribute("sellinfo1", sellinfo1);

        return "adminpage/adminmain";
    }

    //최근 3개월 조회
    @GetMapping("/sell/3Month")
    public String sell3(Model model){
        //레파지토리에서 3개월같의 매출을 조회하는 쿼리문을 가져와서 저장하기
        int sellinfo3 = orderHeaderRepository.sellinfo3();
        //정보를 뷰에 뿌려주기
        model.addAttribute("sellinfo3", sellinfo3);

        return "adminpage/adminmain";
    }

    //최근 1년 조회
    @GetMapping("/sell/1Years")
    public String sell1Y(Model model){
        //레파지토리에서 1년간의 매출을 조회하는 쿼리문을 가져와서 저장하기
        int sellinfo1Y = orderHeaderRepository.sellinfo1Y();
        //정보를 뷰에 뿌려주기
        model.addAttribute("sellinfo1Y", sellinfo1Y);

        return "adminpage/adminmain";

    }
   //카테고리별로 조회하기
    @GetMapping("/sell/decateinfo")
    public String selldecate(Model model){
        //긴팔 가져오기
        String longsleeve = orderDetailRepository.longsleeves();
        //팔린값이 없을때 0으로 받기
        if (longsleeve == null){
            longsleeve = "0";
        }
        //모델화
        model.addAttribute("longsleeve", longsleeve);

        //반팔 가져오기
        String sleeve = orderDetailRepository.sleeves();
        if (sleeve == null){
            sleeve = "0";
        }
        //모델화
        model.addAttribute("sleeve",sleeve);

        //후드
        String hoodie = orderDetailRepository.hoodie();
        if (hoodie == null){
            hoodie = "0";
        }
        //모델화
        model.addAttribute("hoodie", hoodie);

        //맨투맨
        String manto = orderDetailRepository.manto();
        if (manto == null){
            manto = "0";
        }
        //모델화
        model.addAttribute("manto", manto);

        //니트
        String knit = orderDetailRepository.knit();
        if (knit == null){
            knit = "0";
        }
        //모델화
        model.addAttribute("knit", knit);

        //데님팬츠
        String denim = orderDetailRepository.denim();
        if (denim == null){
            denim = "0";
        }
        //모델화
        model.addAttribute("denim", denim);

        //코튼팬츠
        String clothes = orderDetailRepository.clothes();
        if (clothes == null){
            clothes = "0";
        }
        //모델화
        model.addAttribute("clothes", clothes);

        //카고팬츠
        String  cago = orderDetailRepository.cago();
        if (cago == null){
            cago = "0";
        }
        //모델화
        model.addAttribute("cago", cago);

        //조거팬츠
        String jogger = orderDetailRepository.jogger();
        if (jogger == null){
            jogger = "0";
        }
        model.addAttribute("jogger", jogger);

        //슬렉스
        String slex = orderDetailRepository.slex();
        if (slex == null){
            slex = "0";
        }
        //모델화
        model.addAttribute("slex", slex);

        //후드집업
        String hoodiejip = orderDetailRepository.hoodiejib();
        if (hoodiejip == null){
            hoodiejip = "0";
        }
        //모델화
        model.addAttribute("hoodiejip", hoodiejip);

        //가디건
        String cardigan = orderDetailRepository.cardigan();
        if (cardigan == null){
            cardigan = "0";
        }
        //모델화
        model.addAttribute("cardigan", cardigan);

        //무스탕
        String mustang = orderDetailRepository.mustang();
        if (mustang == null){
            mustang = "0";
        }
        //모델화
        model.addAttribute("mustang",mustang);

        //패딩
        String padding = orderDetailRepository.padding();
        if (padding == null){
            padding = "0";
        }
        //모델화
        model.addAttribute("padding", padding);

        //코트
        String coat = orderDetailRepository.coat();
        if (coat == null){
            coat = "0";
        }
        //모델화
        model.addAttribute("coat",coat);


        return "adminpage/adminmain";
    }
}
