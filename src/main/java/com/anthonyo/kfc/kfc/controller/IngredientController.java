package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientWithUnit;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/create")
    public IngredientResponse addIngredient(@RequestBody IngredientRequest ingredient) {
        return ingredientService.create(ingredient);
    }
    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
    @GetMapping("/unit")
    public List<IngredientWithUnit> getAllIngredientsWithUnit() {
        return ingredientService.findIngredientWithUnit();
    }

    @GetMapping("/unit/{ingredientId}")
    public Optional<IngredientWithUnit> getUnit(@PathVariable Integer ingredientId) {
        return ingredientService.findUnit(ingredientId);
    }
}
