package com.anthonyo.kfc.kfc.dtos.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientQteListRequest {
    Integer ingredientId;
    Double quantity;
}
