package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.dtos.responses.SaleMovResponse;
import com.anthonyo.kfc.kfc.entities.SaleMov;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public interface SaleMovRepository {
    List<SaleMovResponse> findBetweenDate(Instant startDate, Instant endDate);
}
