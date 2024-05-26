package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.repository.MenuRepository;
import com.anthonyo.kfc.kfc.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    @Override
    public Menu create(Menu toCreate) {
        return menuRepository.create(toCreate);
    }
}
