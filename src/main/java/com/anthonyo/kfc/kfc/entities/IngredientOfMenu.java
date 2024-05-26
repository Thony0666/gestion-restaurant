package com.anthonyo.kfc.kfc.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientOfMenu {
    Integer id;
    Double quantity;
    Menu menu;
    Ingredient ingredient;
}
