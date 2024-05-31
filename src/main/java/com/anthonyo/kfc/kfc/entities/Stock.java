package com.anthonyo.kfc.kfc.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    private Integer id;
    private Double quantity;
    private Ingredient ingredient;
    private Restaurant restaurant;
    private List<ActionStock> actionsStock = new ArrayList<>();
}
