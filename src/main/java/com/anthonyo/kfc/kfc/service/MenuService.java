package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.dtos.requests.MenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MenuResponse;
import com.anthonyo.kfc.kfc.entities.Menu;

import java.util.List;

public interface MenuService {
    MenuResponse create (MenuRequest toCreate);
    List<Menu> getAll();
}
