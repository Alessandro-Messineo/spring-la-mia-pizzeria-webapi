package org.lessons.java.spring_la_mia_pizzeria_webapi.controller;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.SpecialOffer;

import org.lessons.java.spring_la_mia_pizzeria_webapi.service.PizzaService;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.SpecialOfferService;
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
@RequestMapping("/special-offer")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferService specialOfferService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/create/{id}")
    public String create(@PathVariable Integer id, Model model) {

        SpecialOffer specialOffer = new SpecialOffer();

        specialOffer.setPizza(pizzaService.findById(id));

        model.addAttribute("specialOffer", specialOffer);
        return "special-offers/form-special-offer";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formOffer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "special-offers/form-special-offer";
        }

        Integer idPizza = formOffer.getPizza().getId();

        specialOfferService.save(formOffer);
        return "redirect:/pizza/" + idPizza;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("specialOffer", specialOfferService.findById(id));
        model.addAttribute("edit", true);

        return "special-offers/form-special-offer";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer formOffer, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "special-offers/form-special-offer";
        }

        Integer idPizza = formOffer.getPizza().getId();

        specialOfferService.save(formOffer);

        return "redirect:/pizza/" + idPizza;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {

        SpecialOffer offer = specialOfferService.findById(id);

        Integer idPizza = offer.getPizza().getId();

        specialOfferService.delete(id);

        return "redirect:/pizza/" + idPizza;
    }

}
