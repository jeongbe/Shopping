package com.example.ShowpingProject.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


public class sellerform {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date DateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date DateEnd;

    public sellerform(Date dateStart, Date dateEnd) {
        DateStart = dateStart;
        DateEnd = dateEnd;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }
}