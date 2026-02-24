package org.lessons.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(int id) {
        return ingredientRepository.findById(id).get();
    }

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void delete(int id) {
        Ingredient ingredient = findById(id);
        for (Pizza pizza : ingredient.getPizzas()) {
            pizza.getIngredients().remove(ingredient);
        }
        ingredientRepository.delete(ingredient);
    }
}