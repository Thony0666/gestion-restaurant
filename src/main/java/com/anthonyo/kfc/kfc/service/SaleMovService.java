package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.SaleMovDateRequest;
import com.anthonyo.kfc.kfc.dtos.responses.SaleMovResponse;

import java.util.List;

public interface SaleMovService {
    List<SaleMovResponse> findBetweenDate(SaleMovDateRequest saleMovDateRequest);
}
