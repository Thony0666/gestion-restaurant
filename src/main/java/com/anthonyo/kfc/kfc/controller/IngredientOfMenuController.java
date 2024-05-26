package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;
import com.anthonyo.kfc.kfc.service.IngredientOfMenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/IngredientOfMenu")
public class IngredientOfMenuController {
    private final IngredientOfMenuService ingredientOfMenuService;
    public IngredientOfMenuController(IngredientOfMenuService ingredientOfMenuService) {
            this.ingredientOfMenuService = ingredientOfMenuService;
    }
    @PostMapping("/create")
    public IngredientOfMenuResponse createIngredientOfMenu(@RequestBody IngredientOfMenuRequest ingredientOfMenu) {
        return ingredientOfMenuService.create(ingredientOfMenu);
    }
}
