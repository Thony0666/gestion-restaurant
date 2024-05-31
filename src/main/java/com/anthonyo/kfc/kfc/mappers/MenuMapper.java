package com.anthonyo.kfc.kfc.mappers;

import com.anthonyo.kfc.kfc.dtos.requests.MenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MenuResponse;
import com.anthonyo.kfc.kfc.entities.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {
    public Menu toEntity(MenuRequest request){
        return Menu.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }

    public MenuResponse toResponse(Menu menu){
        return MenuResponse.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
    }
}
