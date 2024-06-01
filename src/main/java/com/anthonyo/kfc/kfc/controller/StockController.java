package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.ActionStockDateRangeRequest;
import com.anthonyo.kfc.kfc.dtos.requests.ApprovisionnementRequest;
import com.anthonyo.kfc.kfc.dtos.requests.StockRequest;
import com.anthonyo.kfc.kfc.dtos.requests.SupplyStockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;
import com.anthonyo.kfc.kfc.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;


    @PostMapping("/create")
    public StockResponse create(@RequestBody StockRequest stock) {
        return stockService.createStock(stock);
    }


    @PutMapping("/update")
    public StockResponse update(@RequestBody SupplyStockRequest stock) {
        return stockService.updateStock(stock);
    }
    @GetMapping("/stock-between-dates/{restaurantId}/{startDate}/{endDate}")
    public List<ActionStockResponse> getActionsBetweenDates(@PathVariable String startDate,@PathVariable String endDate ,@PathVariable Integer restaurantId) {
        return stockService.findByBetweenDate(startDate,endDate,restaurantId);
    }

}
