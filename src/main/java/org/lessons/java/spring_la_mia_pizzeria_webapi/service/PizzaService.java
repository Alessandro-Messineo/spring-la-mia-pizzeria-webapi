package org.lessons.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> findByName(String search) {
        return pizzaRepository.findByNameContainingIgnoreCase(search);
    }

    public Pizza findById(Integer id) {
        return pizzaRepository.findById(id).get();
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Integer id) {
        Pizza pizza = findById(id);
        for (SpecialOffer offer : pizza.getSpecialOffers()) {
            specialOfferRepository.delete(offer);
        }
        pizzaRepository.delete(pizza);
    }
}
