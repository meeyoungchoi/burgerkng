package com.example.burgerkng.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//데이터베이스에서 아이디를 관리하게 해준다 => 데이터를 집어넣으면 첫값을 1로 만들고 다음거를 2로 이렇게 하나씩 증가가시킨다.
    private Integer id;

    private String name;


    private Integer price;


    @ManyToMany
    List<Ingredient> ingredients;
}
