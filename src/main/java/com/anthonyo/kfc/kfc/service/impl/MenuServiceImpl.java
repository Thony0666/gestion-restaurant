package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.MenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MenuResponse;
import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.entities.Sale;
import com.anthonyo.kfc.kfc.mappers.MenuMapper;
import com.anthonyo.kfc.kfc.repository.MenuRepository;
import com.anthonyo.kfc.kfc.repository.RestaurantRepository;
import com.anthonyo.kfc.kfc.repository.SaleRepository;
import com.anthonyo.kfc.kfc.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final RestaurantRepository restaurantRepository;
    private final SaleRepository saleRepository;


    @Override
    public MenuResponse create(MenuRequest menuRequest) {
        var restaurants = restaurantRepository.findAll();
        Menu menu = menuMapper.toEntity(menuRequest);
        var createMenu = menuMapper.toResponse(menuRepository.create(menu));
        for (var q : restaurants) {
            var createSale = Sale.builder()
                    .restaurant(Restaurant.builder().id(q.getId()).build())
                    .menu(Menu.builder().id(createMenu.getId()).build())
                    .price(createMenu.getPrice())
                    .saleDate(Instant.now())
                    .quantity(0)
                    .build();
            saleRepository.creatSale(createSale);
        }
        return createMenu;
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu deleteMenu(Integer menuId) {
        return menuRepository.deleteMenu(menuId);
    }

    @Override
    public Optional<Menu> updateById(Integer menuId, String name) {
        return menuRepository.updateById(menuId, name);
    }
}
