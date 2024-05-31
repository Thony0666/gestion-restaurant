package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.MenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MenuResponse;
import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @PostMapping("/create")
    public MenuResponse create(@RequestBody MenuRequest menuRequest) {
        System.out.println(menuRequest);
        return menuService.create(menuRequest);
    }
    @GetMapping
    public List<Menu> getAll() {
        return menuService.getAll();
    }
}
