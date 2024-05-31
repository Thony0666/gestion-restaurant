package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.ActionStockDateRangeRequest;
import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.service.ActionStockService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/action")
public class ActionStockController {
    private  final ActionStockService actionStockService;

    public ActionStockController(ActionStockService actionStockService) {
        this.actionStockService = actionStockService;
    }

    @GetMapping
    public List<ActionStockResponse> getActionStock() {
        return actionStockService.findAllActionStock();
    }

}
