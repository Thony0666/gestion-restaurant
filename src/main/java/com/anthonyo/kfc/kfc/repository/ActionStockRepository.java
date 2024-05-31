package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.entities.ActionStock;

import java.time.LocalDate;
import java.util.List;

public interface ActionStockRepository {
    ActionStock create(ActionStock toCreate);
    List<ActionStockResponse> findAll();
    List<ActionStockResponse> findActionsBetweenDates(LocalDate fromDate, LocalDate toDate);
}
