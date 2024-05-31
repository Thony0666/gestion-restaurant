package com.anthonyo.kfc.kfc.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class SaleResponse{
     Integer id;
     Integer price;
     Integer quantity;
     Integer menu;
     Integer restaurant;

}
