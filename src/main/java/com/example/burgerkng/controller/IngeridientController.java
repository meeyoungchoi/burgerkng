package com.example.burgerkng.controller;

import com.example.burgerkng.dto.IngredientForm;
import com.example.burgerkng.repostiory.IngredientRepository;
import com.example.burgerkng.vo.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class IngeridientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/ingredients")
    public String index(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients",ingredients);
        return "ingredients/index";
    }

    @GetMapping("/ingredients/new")
    public String newFrom() {
        return "ingredients/new";
    }

    @PostMapping("/ingredients")
    public String create(IngredientForm form) {
        log.info(form.toString());

        Ingredient ingredient = form.toEntity();
        log.info(ingredient.toString());

        Ingredient saved = ingredientRepository.save(ingredient);
        log.info(saved.toString());
        return "redirect:/ingredients";
    }

    @GetMapping("/ingredients/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
         Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
         model.addAttribute("ingredient", ingredient);
         return "ingredients/edit";
    }

    @PostMapping("/ingredients/{id}")
    public String update(IngredientForm form) {
        log.info(form.toString());

        Ingredient ingredient = form.toEntity();
        log.info(ingredient.toString());

        Ingredient saved = ingredientRepository.save(ingredient);
        log.info(saved.toString());

        return "redirect:/ingredients";
    }

    @GetMapping("/ingredients/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ingredientRepository.deleteById(id);
        log.info(id + "번 삭제완료");
        return "redirect:/ingredients";
    }
}
