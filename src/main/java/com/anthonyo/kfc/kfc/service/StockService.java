package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.StockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;

public interface StockService {
    StockResponse createStock(StockRequest stockRequest);
    StockResponse updateStock(StockRequest stockRequest);
}
