package com.anthonyo.kfc.kfc.service;

import com.anthonyo.kfc.kfc.entities.Unit;

import java.util.List;

public interface UnitService {
    Unit creat(Unit toCreate);
    List<Unit> findAll();
}
