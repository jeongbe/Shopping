package com.example.ShowpingProject.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Getter
public class ProdSizeForm {
    //디테일 테이블에 상품 사이즈를 넣어주기 위해 따로 만듬
    
    String Prsize;

    String Prname;

    String Prprice;

}
