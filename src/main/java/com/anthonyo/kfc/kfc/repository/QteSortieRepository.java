package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.dtos.requests.MostUsedIngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MostUsedIngredientResponse;
import com.anthonyo.kfc.kfc.entities.QteSortie;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QteSortieRepository {
    QteSortie create(QteSortie qteSortie);
    void updateByManyId(Double quantity, Integer menuId, Integer restaurantId , Integer ingredient);
    Optional<QteSortie> findByManyId(Integer menu,Integer restaurant ,Integer ingredient);
    List<QteSortie> findAll();
    public List<MostUsedIngredientResponse> findMostUsedIngredient(Instant startDate , Instant endDate, Integer restaurantId, Integer limit) ;
}
