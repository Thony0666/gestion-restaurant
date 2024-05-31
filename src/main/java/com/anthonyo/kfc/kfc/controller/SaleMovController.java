package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.SaleMovDateRequest;
import com.anthonyo.kfc.kfc.dtos.responses.SaleMovResponse;
import com.anthonyo.kfc.kfc.service.SaleMovService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sale-mov")
@RequiredArgsConstructor
public class SaleMovController {
    private final SaleMovService saleMovService;

    @GetMapping("/between-dates")
    public ResponseEntity<List<SaleMovResponse>> getSalesBetweenDates(@RequestBody SaleMovDateRequest saleMovDateRequest) {
        List<SaleMovResponse> responses = saleMovService.findBetweenDate(saleMovDateRequest);
        return ResponseEntity.ok(responses);
    }
}
