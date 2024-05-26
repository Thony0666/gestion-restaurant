package com.anthonyo.kfc.kfc.dtos.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class RestaurantRequest {
     Integer id;
     String name;
     String place;

}
