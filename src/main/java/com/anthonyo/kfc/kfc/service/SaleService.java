package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.SaleRequest;
import com.anthonyo.kfc.kfc.dtos.responses.SaleResponse;
import com.anthonyo.kfc.kfc.entities.Sale;

public interface SaleService {
    SaleResponse creatSale(SaleRequest toCreate);
}
