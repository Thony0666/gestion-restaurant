package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.MostUsedIngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MostUsedIngredientResponse;
import com.anthonyo.kfc.kfc.service.MostUsedIngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qte_expenses")
@CrossOrigin
public class QteSortieController {
    private final MostUsedIngredientService mostUsedIngredientService;

    public QteSortieController(MostUsedIngredientService mostUsedIngredientService) {
        this.mostUsedIngredientService = mostUsedIngredientService;
    }

    @GetMapping("/{restaurantId}/{startDate}/{endDate}/{limit}")
    public List<MostUsedIngredientResponse>   mostUsedIngredientRequest(@PathVariable String startDate,@PathVariable String endDate ,@PathVariable Integer limit,@PathVariable Integer restaurantId){
        return mostUsedIngredientService.findMostUsedIngredient(startDate,endDate,limit,restaurantId);
    }
}
