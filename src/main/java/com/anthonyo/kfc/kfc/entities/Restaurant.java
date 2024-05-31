package com.anthonyo.kfc.kfc.entities;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    private Integer id;
    private String place;
    private List<Stock> stocks = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();
}
