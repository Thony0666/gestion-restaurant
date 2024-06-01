package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.MenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MenuResponse;
import com.anthonyo.kfc.kfc.entities.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    MenuResponse create (MenuRequest toCreate);
    List<Menu> getAll();
    Menu deleteMenu(Integer menuId);
    Optional<Menu> updateById(Integer menuId , String name);
}
