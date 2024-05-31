package com.anthonyo.kfc.kfc.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sale {
    Integer id;
    Integer price;
    Instant saleDate;
    Integer quantity;
    Menu menu;
    Restaurant restaurant;
}
