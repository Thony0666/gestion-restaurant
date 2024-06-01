package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.requests.IngredientOneOfMenu;
import com.anthonyo.kfc.kfc.dtos.requests.UpdateQteIOFRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IOMResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;
import com.anthonyo.kfc.kfc.service.IngredientOfMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PostMapping("/create/one-to-one")
    public IngredientOneOfMenu ingredientOneOfMenu(@RequestBody IngredientOneOfMenu ingredientOneOfMenu) {
        return ingredientOfMenuService.createOneToOne(ingredientOneOfMenu);
    }
    @PutMapping("/update")
    public void ingredientOfMenu(@RequestBody UpdateQteIOFRequest updateQteIOFRequest){
         ingredientOfMenuService.updateQte(updateQteIOFRequest);
    }
    @GetMapping("/{menuId}")
    public List<IOMResponse> getByMenuId(@PathVariable Long menuId){
       return ingredientOfMenuService.getByMenuId(menuId);
    }
    @DeleteMapping("/delete/{menuId}/{ingredientId}")
    public Optional<IOMResponse> getByMenuId(@PathVariable Integer menuId , @PathVariable Integer ingredientId){
       return ingredientOfMenuService.deleteByManyId(menuId,ingredientId);
    }
}
