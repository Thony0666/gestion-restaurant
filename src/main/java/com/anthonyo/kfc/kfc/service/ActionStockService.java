package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;

import java.time.LocalDate;
import java.util.List;

public interface ActionStockService {
    List<ActionStockResponse> findAllActionStock();
    List<ActionStockResponse> findActionsBetweenDates(LocalDate startDate, LocalDate endDate, Integer restaurantId);
}
