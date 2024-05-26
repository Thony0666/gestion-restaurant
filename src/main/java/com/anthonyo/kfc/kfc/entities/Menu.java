package com.anthonyo.kfc.kfc.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    private Integer id;
    private String name;
    private Integer price;
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<IngredientOfMenu> ingredientsOfMenu = new ArrayList<>();
}
