package org.lessons.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.service.PizzaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/pizza")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> index(
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {

        List<Pizza> pizzas;
        if (search.isEmpty()) {
            pizzas = pizzaService.findAll();
        } else {
            pizzas = pizzaService.findByName(search);
        }

        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {

        Pizza pizza = pizzaService.findById(id);

        if(pizza == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        Pizza saved = pizzaService.save(pizza);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Integer id, @RequestBody Pizza pizza) {
        
        if(!pizzaService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        pizza.setId(id);
        return new ResponseEntity<>(pizzaService.save(pizza), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id){

        if(!pizzaService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pizzaService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
