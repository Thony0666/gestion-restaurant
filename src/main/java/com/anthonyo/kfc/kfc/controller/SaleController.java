package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.SaleRequest;
import com.anthonyo.kfc.kfc.dtos.responses.SaleResponse;
import com.anthonyo.kfc.kfc.entities.Sale;
import com.anthonyo.kfc.kfc.service.SaleService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/create")
    public SaleResponse createSale(@RequestBody SaleRequest sale){
        return saleService.creatSale(sale);
    }
}
