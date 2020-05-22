package com.example.burgerkng.repostiory;

import com.example.burgerkng.vo.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    @Override
    List<Ingredient> findAll();
}
