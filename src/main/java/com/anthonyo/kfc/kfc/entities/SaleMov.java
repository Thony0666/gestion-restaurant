package com.anthonyo.kfc.kfc.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class SaleMov {
    Integer restaurantId;
    String restaurantName;
    String menuName;
    Double sumQteSold;
    Integer sumPrice;
}
