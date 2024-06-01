package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.MenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MenuResponse;
import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.mappers.MenuMapper;
import com.anthonyo.kfc.kfc.repository.MenuRepository;
import com.anthonyo.kfc.kfc.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;


    @Override
    public MenuResponse create(MenuRequest menuRequest) {
         Menu menu = menuMapper.toEntity(menuRequest);
         return menuMapper.toResponse(menuRepository.create(menu));
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
    public Optional<Menu> updateById(Integer menuId , String name) {
        return menuRepository.updateById(menuId,name);
    }
}
