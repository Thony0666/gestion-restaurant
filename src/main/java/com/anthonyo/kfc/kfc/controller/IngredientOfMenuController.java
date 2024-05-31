package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.requests.UpdateQteIOFRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IOMResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;
import com.anthonyo.kfc.kfc.service.IngredientOfMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredient-of-menu")
public class IngredientOfMenuController {
    private final IngredientOfMenuService ingredientOfMenuService;
    public IngredientOfMenuController(IngredientOfMenuService ingredientOfMenuService) {
            this.ingredientOfMenuService = ingredientOfMenuService;
    }
    @PostMapping("/create")
    public void createIngredientOfMenu(@RequestBody IngredientOfMenuRequest ingredientOfMenu) {
        ingredientOfMenuService.create(ingredientOfMenu);
    }
    @PutMapping("/update")
    public void ingredientOfMenu(@RequestBody UpdateQteIOFRequest updateQteIOFRequest){
         ingredientOfMenuService.updateQte(updateQteIOFRequest);
    }
    @GetMapping("/{menuId}")
    public List<IOMResponse> getByMenuId(@PathVariable Long menuId){
       return ingredientOfMenuService.getByMenuId(menuId);
    }
}
