package org.lessons.java.spring_la_mia_pizzeria_webapi.controller;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ingredient", new Ingredient());

        return "ingredients/form-ingredient";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/form-ingredient";
        }

        ingredientService.save(formIngredient);

        return "redirect:/ingredient";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("ingredient", ingredientService.findById(id));
        model.addAttribute("edit", true);

        return "ingredients/form-ingredient";
    }

    @PostMapping("edit/{id}")
    public String upate(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/form-ingredient";
        }

        ingredientService.save(formIngredient);

        return "redirect:/ingredient";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
       
        ingredientService.delete(id);

        return "redirect:/ingredient";
    }

}
