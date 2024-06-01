package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.MostUsedIngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MostUsedIngredientResponse;
import com.anthonyo.kfc.kfc.repository.QteSortieRepository;
import com.anthonyo.kfc.kfc.service.MostUsedIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MostUsedIngredientServiceImpl implements MostUsedIngredientService {
    private  final QteSortieRepository qteSortieRepository;

    @Override
    public List<MostUsedIngredientResponse> findMostUsedIngredient(String startDate, String endDate ,Integer limit,Integer restaurantId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDateStart = LocalDate.parse(startDate, formatter);
        LocalDate parsedDateEnd = LocalDate.parse(endDate, formatter);
        Instant startInstant = parsedDateStart.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endInstant = parsedDateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return qteSortieRepository.findMostUsedIngredient(startInstant,endInstant,restaurantId,limit);
    }
}
