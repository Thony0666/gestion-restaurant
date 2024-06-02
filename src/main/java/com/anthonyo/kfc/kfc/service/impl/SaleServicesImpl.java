package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.SaleRequest;
import com.anthonyo.kfc.kfc.dtos.responses.SaleResponse;
import com.anthonyo.kfc.kfc.entities.*;
import com.anthonyo.kfc.kfc.enums.ActionStockType;
import com.anthonyo.kfc.kfc.exceptions.BadRequestException;
import com.anthonyo.kfc.kfc.exceptions.NotFoundException;
import com.anthonyo.kfc.kfc.mappers.SaleMapper;
import com.anthonyo.kfc.kfc.repository.*;
import com.anthonyo.kfc.kfc.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SaleServicesImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final  MenuRepository menuRepository;
    private final SaleMapper saleMapper;
    private final IngredientOfMenuRepository ingredientOfMenuRepository;
    private final StockRepository stockRepository;
    private final ActionStockRepository actionStockRepository;
    private final QteSortieRepository qteSortieRepository;


    @Override
    public SaleResponse creatSale(SaleRequest toCreate) {
        var menu = menuRepository.findById(toCreate.getMenu()).orElseThrow(()-> new NotFoundException("menu not found"));
        var insertPrice = SaleRequest.builder()
                .price(menu.getPrice() *toCreate.getQuantity())
                .quantity(toCreate.getQuantity())
                .restaurant(toCreate.getRestaurant())
                .menu(toCreate.getMenu())
                .build();
        var sale = saleMapper.toEntity(insertPrice);
        var createSale = saleRepository.creatSale(sale);
        var menuWithIngredient = ingredientOfMenuRepository.findByIdMenu(toCreate.getMenu());
        for (var q : menuWithIngredient) {
            var stocks = stockRepository.findByIngredientIdSale(q.getIngredient().getId(), toCreate.getRestaurant()).orElseThrow(() -> new NotFoundException("stock not found"));
            if (stocks.getQuantity() < q.getQuantity() * toCreate.getQuantity()) {
                throw new BadRequestException("quantity ingredient is insufficient");
            }
        }
        for (var q : menuWithIngredient) {
            var stocks = stockRepository.findByIngredientIdSale(q.getIngredient().getId(), toCreate.getRestaurant()).orElseThrow(() -> new NotFoundException("stock not found"));
            stockRepository.update(Stock.builder()
                    .ingredient(stocks.getIngredient())
                    .restaurant(stocks.getRestaurant())
                    .quantity(stocks.getQuantity() - (q.getQuantity() * toCreate.getQuantity()))
                    .build());
            actionStockRepository.create(ActionStock.builder()
                    .quantity(q.getQuantity() * toCreate.getQuantity())
                    .type(ActionStockType.SORTIE)
                    .dateTime(Instant.now())
                    .ingredient(q.getIngredient())
                    .stock(Stock.builder()
                            .id(stocks.getId())
                            .build())
                    .build());
                qteSortieRepository.create(
                        QteSortie.builder()
                                .quantity((q.getQuantity() * toCreate.getQuantity()))
                                .ingredient(stocks.getIngredient().getId())
                                .menu(Menu.builder().id(toCreate.getMenu()).build())
                                .restaurant(Restaurant.builder().id(toCreate.getRestaurant()).build())
                                .sortieDate(Instant.now())
                                .build());

        }
        return saleMapper.toResponse(createSale);
    }
}
