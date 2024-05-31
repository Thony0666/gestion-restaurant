package com.anthonyo.kfc.kfc.dtos.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientOfMenuRequest {
    Integer id;
    String menuName;
    Integer menuPrice;
    List<IngredientQteListRequest> ingredientList =new ArrayList<>();
}
