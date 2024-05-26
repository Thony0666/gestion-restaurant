package com.anthonyo.kfc.kfc.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Ingredient {
    private Integer id;
    private String name;
    private Integer price;
    private Unit unit;
    private ActionStock actionStock;
    private List<ActionStock> actionStocks = new ArrayList<>();
    private List<IngredientOfMenu> ingredientsOfMenu = new ArrayList<>();
}
