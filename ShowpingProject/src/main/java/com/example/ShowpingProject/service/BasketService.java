package com.example.ShowpingProject.service;

import com.example.ShowpingProject.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class BasketService {

    @Autowired
    BasketRepository basketRepository;



}
