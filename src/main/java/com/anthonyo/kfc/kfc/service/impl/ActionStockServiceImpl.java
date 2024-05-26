package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.repository.ActionStockRepository;
import com.anthonyo.kfc.kfc.service.ActionStockService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActionStockServiceImpl implements ActionStockService {
    private final  ActionStockRepository actionStockRepository;

    public ActionStockServiceImpl(ActionStockRepository actionStockRepository) {
        this.actionStockRepository = actionStockRepository;
    }

    @Override
    public List<ActionStockResponse> findAllActionStock() {
        return actionStockRepository.findAll();
    }

    @Override
    public List<ActionStockResponse> findActionsBetweenDates(LocalDate startDate , LocalDate endDate, Integer restaurantId) {
        return actionStockRepository.findActionsBetweenDates(startDate,endDate);
    }
}
