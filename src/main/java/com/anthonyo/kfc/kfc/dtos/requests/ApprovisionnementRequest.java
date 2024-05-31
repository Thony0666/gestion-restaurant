package com.anthonyo.kfc.kfc.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ApprovisionnementRequest {
    private Integer restaurantId;
    private Integer ingredientId;
    private String ingredientName;
    private Instant approvisionnementDate;
    private Double qte;
}
