package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.StockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;
import com.anthonyo.kfc.kfc.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/create")
    public StockResponse create(@RequestBody StockRequest stock) {
        return stockService.createStock(stock);
    }
    @PutMapping("/update")
    public StockResponse update(@RequestBody StockRequest stock) {
        return stockService.updateStock(stock);
    }

}
