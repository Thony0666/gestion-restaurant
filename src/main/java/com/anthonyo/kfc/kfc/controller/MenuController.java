package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.dtos.requests.MenuRequest;
import com.anthonyo.kfc.kfc.dtos.responses.MenuResponse;
import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
      return   menuService.create(menuRequest);
    }
    @GetMapping
    public List<Menu> getAll() {
        return menuService.getAll();
    }
    @DeleteMapping("/delete/{menuId}")
    public Menu deleteMenu(@PathVariable Integer menuId){
        return menuService.deleteMenu(menuId);
    }
    @PutMapping("/update/{menuId}/{name}")
    public Optional<Menu> update(@PathVariable Integer menuId , @PathVariable String name){
        return menuService.updateById(menuId,name);
    }
}
