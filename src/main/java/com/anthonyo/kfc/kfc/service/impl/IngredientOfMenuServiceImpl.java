package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOfMenuRequest;
import com.anthonyo.kfc.kfc.dtos.requests.IngredientOneOfMenu;
import com.anthonyo.kfc.kfc.dtos.requests.UpdateQteIOFRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IOMResponse;
import com.anthonyo.kfc.kfc.dtos.responses.IngredientOfMenuResponse;
import com.anthonyo.kfc.kfc.entities.*;
import com.anthonyo.kfc.kfc.enums.ActionStockType;
import com.anthonyo.kfc.kfc.exceptions.BadRequestException;
import com.anthonyo.kfc.kfc.exceptions.NotFoundException;
import com.anthonyo.kfc.kfc.mappers.IngredientOfMenuMappers;
import com.anthonyo.kfc.kfc.repository.*;
import com.anthonyo.kfc.kfc.service.IngredientOfMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientOfMenuServiceImpl implements IngredientOfMenuService {
    private final IngredientOfMenuRepository ingredientOfMenuRepository;
    private final MenuRepository menuRepository;
    private final IngredientRepository  ingredientRepository;
    private final RestaurantRepository restaurantRepository;
    private final SaleRepository saleRepository;

    @Override
    public void create(IngredientOfMenuRequest toCreate) {
        for (var q: toCreate.getIngredientList()){
            ingredientRepository.findById(q.getIngredientId()).orElseThrow(()->new NotFoundException("ingredient not found"));
            IngredientOfMenu.builder()
                    .ingredient(Ingredient.builder().id(q.getIngredientId()).build()).build();
        }
        var  createMenu = menuRepository.create(Menu.builder()
                .name(toCreate.getMenuName())
                .price(toCreate.getMenuPrice())
                .build());
        for (var q: toCreate.getIngredientList()){
            var ingredientOfMenu = IngredientOfMenu.builder()
                     .ingredient(Ingredient.builder().id(q.getIngredientId()).build())
                     .quantity(q.getQuantity())
                     .menu(Menu.builder().id(createMenu.getId()).build())
                     .build();
             ingredientOfMenuRepository.create(ingredientOfMenu);
        }
        var restaurants = restaurantRepository.findAll();
        for (var q : restaurants) {
            var createSale = Sale.builder()
                    .restaurant(Restaurant.builder().id(q.getId()).build())
                    .menu(Menu.builder().id(createMenu.getId()).build())
                    .price(0)
                    .saleDate(Instant.now())
                    .quantity(0)
                    .build();
            saleRepository.creatSale(createSale);
        }
    }

    @Override
    public IngredientOneOfMenu createOneToOne(IngredientOneOfMenu toCreate) {
        return ingredientOfMenuRepository.createOneToOne(toCreate);
    }

    @Override
    public void updateQte(UpdateQteIOFRequest updateQteIOFRequest) {
         ingredientOfMenuRepository.findByManyID(updateQteIOFRequest.getMenuId(), updateQteIOFRequest.getIngredientId()).orElseThrow(()->new NotFoundException("selected not found"));
         ingredientOfMenuRepository.update(updateQteIOFRequest);
    }

    @Override
    public List<IOMResponse> getByMenuId(Long menuId) {
        return ingredientOfMenuRepository.findAllByMenuId(menuId);
    }

    @Override
    public Optional<IOMResponse> deleteByManyId(Integer menuId, Integer ingredientId) {
        return ingredientOfMenuRepository.deleteByManyId(menuId,ingredientId);
    }
}