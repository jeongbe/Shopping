package com.example.ShowpingProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MypageSellerform {
    //폼데이터에 날짜형식으로 데이터 넣어주기
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date DateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date DateEnd;

    String Dayfilter;

//    public MypageSellerform(Date dateStart, Date dateEnd) {
//        DateStart = dateStart;
//        DateEnd = dateEnd;
//    }
//
//    public void setDateStart(Date dateStart) {
//        DateStart = dateStart;
//    }
//
//    public void setDateEnd(Date dateEnd) {
//        DateEnd = dateEnd;
//    }
//
//    public Date getDateStart() {
//        return DateStart;
//    }
//
//    public Date getDateEnd() {
//        return DateEnd;
//    }
}