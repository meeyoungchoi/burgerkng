package com.example.burgerkng.controller;

import com.example.burgerkng.dto.BurgerForm;
import com.example.burgerkng.repostiory.BurgerRepository;
import com.example.burgerkng.repostiory.IngredientRepository;
import com.example.burgerkng.vo.Burger;

import java.util.ArrayList;
import java.util.List;


import com.example.burgerkng.vo.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
public class BurgerController {

    @Autowired
    private BurgerRepository burgerRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/")
    public String index(Model model) {
        //데이터 수령
        List<Burger> burgers = burgerRepository.findAll();
        //데이터를 모델을 통해서 데이터를 뷰로 전달한다
        //                                 키       value
        model.addAttribute("burgers", burgers);
        return "burgers/index";
    }



    @GetMapping("/burgers/new")
    public String burgerForm(Model model) {

        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients",ingredients);
        return "burgers/new";
    }

    @PostMapping("/burgers")
    public String create(BurgerForm form) {
        log.info(form.toString());

        //버거를 생성 from form 데이터
        Burger burger = form.toEntity();
        log.info(burger.toString());

        //버거를 저장
        Burger burger1 = burgerRepository.save(burger);
        log.info(burger1.toString());

        return "redirect:/";
    }


    //테스트를 위해 버거를 미리 만들어놓는다.
    @GetMapping("/burgers/init")
    public String init() {
        //버거 만들기
        List<Burger> burgers = new ArrayList<>();
        burgers.add(new Burger(null ,"와퍼", 4900, null));
        burgers.add(new Burger(null ,"치번", 3900, null));
        burgers.add(new Burger(null ,"새우", 2900, null));
        //버거 저장
        burgerRepository.saveAll(burgers);


        //재료 저장
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(null, "치즈", 300));
        ingredients.add(new Ingredient(null, "야체", 400));
        ingredients.add(new Ingredient(null, "패티", 500));
        ingredients.add(new Ingredient(null, "소스", 200));

        ingredientRepository.saveAll(ingredients);

        //리다이렉트
        return "redirect:/";
    }

    //수정을 눌렀을떄 버거의 id값을 가져온다
    @GetMapping("/burgers/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {//url 요청에서 온 버거 아이디를 가져와서 id변수에 저장한다
        //DB검색
        Burger burger = burgerRepository.findById(id).orElse(null);//해당 id가 없으면 null

        //화면에 전달
        model.addAttribute("burger", burger);
        return "burgers/edit";
    }

    @PostMapping("/burgers/{id}")
    public String update(BurgerForm form) {//form이 아이디를 url에서 가져온다
        log.info(form.toString());//form(5900) db(4900)

        Burger burger = form.toEntity();//3이없으면 새로운 객체가 만들어지는데 있기떄문에 덮어쎠진다.(id가 있으면 업데이트 없으면 생성)
        log.info(burger.toString());
        //burger(5900) db(4900)

        Burger saved = burgerRepository.save(burger);
        log.info(saved.toString());
        //burger(5900) db(5900)

        return "redirect:/";
    }


    @GetMapping("/burgers/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Burger burger = burgerRepository.findById(id).orElse(null);
        log.info(burger.toString());
        model.addAttribute("burger", burger);

        return "burgers/show";
    }

}
