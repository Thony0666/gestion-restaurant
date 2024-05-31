package com.anthonyo.kfc.kfc.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailMenu {
    String menu;
    Double sumQte;
    Integer totalPrice;
}
