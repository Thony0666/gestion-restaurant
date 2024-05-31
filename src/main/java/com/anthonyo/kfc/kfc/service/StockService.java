package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.ActionStockDateRangeRequest;
import com.anthonyo.kfc.kfc.dtos.requests.StockRequest;
import com.anthonyo.kfc.kfc.dtos.requests.SupplyStockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;

import java.util.List;

public interface StockService {
    StockResponse createStock(StockRequest stockRequest);
    StockResponse updateStock(SupplyStockRequest supplyStockRequest);
    List<ActionStockResponse> findByBetweenDate(ActionStockDateRangeRequest actionStockDateRangeRequest);
}
