package com.anthonyo.kfc.kfc.dtos.responses;

import com.anthonyo.kfc.kfc.entities.DetailMenu;
import com.anthonyo.kfc.kfc.entities.Sale;
import com.anthonyo.kfc.kfc.entities.SaleMov;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleMovResponse {
    Integer restaurantId;
    String restaurantName;
    List<DetailMenu> detailMenuList = new ArrayList<>();
}
