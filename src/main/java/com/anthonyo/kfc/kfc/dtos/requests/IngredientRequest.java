package com.anthonyo.kfc.kfc.dtos.requests;

import com.anthonyo.kfc.kfc.entities.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientRequest {
    Integer id;
    String name;
    Integer price;
    Integer unit;
}
