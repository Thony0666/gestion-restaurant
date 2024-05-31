package com.anthonyo.kfc.kfc.mappers;

import com.anthonyo.kfc.kfc.dtos.requests.SaleRequest;
import com.anthonyo.kfc.kfc.dtos.responses.SaleResponse;
import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.entities.Sale;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {
    public Sale toEntity(SaleRequest saleRequest){
      var newSale =  Sale.builder()
              .id(saleRequest.getId())
              .price(saleRequest.getPrice())
              .quantity(saleRequest.getQuantity())
              .menu(Menu.builder()
                      .id(saleRequest.getMenu())
                      .build())
              .restaurant(Restaurant.builder()
                      .id(saleRequest.getRestaurant())
                      .build())
              .build();
      return  newSale;
    }

    public SaleResponse toResponse(Sale sale){
        var newSaleResponse = SaleResponse.builder()
                .id(sale.getId())
                .price(sale.getPrice())
                .quantity(sale.getQuantity())
                .menu(sale.getMenu().getId())
                .restaurant(sale.getRestaurant().getId())
                .build();

        return newSaleResponse;
    }
}
