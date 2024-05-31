package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.MostUsedIngredientRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MostUsedIngredientResponse;

import java.util.List;

public interface MostUsedIngredientService {
    List<MostUsedIngredientResponse> findMostUsedIngredient(MostUsedIngredientRequest mostUsedIngredientRequest);
}
