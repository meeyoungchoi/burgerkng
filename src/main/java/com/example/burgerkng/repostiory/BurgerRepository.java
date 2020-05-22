package com.example.burgerkng.repostiory;

import com.example.burgerkng.vo.Burger;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository extends CrudRepository<Burger, Integer> {


    @Override
    List<Burger> findAll();
}
