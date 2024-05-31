package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.entities.Unit;
import com.anthonyo.kfc.kfc.repository.UnitRepository;
import com.anthonyo.kfc.kfc.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;

    @Override
    public Unit creat(Unit toCreate) {
        return unitRepository.create(toCreate);
    }

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }
}
