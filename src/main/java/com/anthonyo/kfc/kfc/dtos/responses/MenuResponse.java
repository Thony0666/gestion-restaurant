package com.anthonyo.kfc.kfc.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class MenuResponse {
    Integer id;
    String name;
    Integer price;
}
