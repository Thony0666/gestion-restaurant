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
    public List<MostUsedIngredientResponse> findMostUsedIngredient(MostUsedIngredientRequest mostUsedIngredientRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var dateStart = mostUsedIngredientRequest.getStartDate();
        var dateEnd = mostUsedIngredientRequest.getEndDate();
        LocalDate parsedDateStart = LocalDate.parse(dateStart, formatter);
        LocalDate parsedDateEnd = LocalDate.parse(dateEnd, formatter);
        Instant startInstant = parsedDateStart.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endInstant = parsedDateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return qteSortieRepository.findMostUsedIngredient(startInstant,endInstant,mostUsedIngredientRequest.getRestaurantId(),mostUsedIngredientRequest.getLimit());
    }
}
