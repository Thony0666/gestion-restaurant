package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.service.MenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @PostMapping("/create")
    public Menu create(@RequestBody Menu menu) {
        return menuService.create(menu);
    }


}
