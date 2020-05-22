package com.example.burgerkng.dto;

import com.example.burgerkng.vo.Burger;

import com.example.burgerkng.vo.Ingredient;
import lombok.Data;
import java.util.List;

@Data
public class BurgerForm {


    private Integer id;
    private String name;
    private Integer price;

    //재료의id
    //생성자에 넣어쟈하낟
    private List<Ingredient> ingredients;//폼에서 준 이름이랑 같아야 한다


    //버거폼을 버거가 될수 있게 해주는 메소드
    public Burger toEntity() {
        return new Burger(id, name, price, ingredients);
    }
}
