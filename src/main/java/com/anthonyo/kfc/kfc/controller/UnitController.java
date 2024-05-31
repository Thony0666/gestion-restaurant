package com.anthonyo.kfc.kfc.controller;

import com.anthonyo.kfc.kfc.entities.Unit;
import com.anthonyo.kfc.kfc.service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/unit")
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<Unit> units(){
        return unitService.findAll();
    }
    @PostMapping("/create")
    public Unit unit(Unit unit){
        return unitService.creat(unit);
    }
}
