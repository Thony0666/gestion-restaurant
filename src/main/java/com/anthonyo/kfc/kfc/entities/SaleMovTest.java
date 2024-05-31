package com.anthonyo.kfc.kfc.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class SaleMovTest {
    Integer restaurantId;
    String restaurantName;
    List<DetailMenu> detailMenus=new ArrayList<>();
}
