package com.anthonyo.kfc.kfc.repository;


import com.anthonyo.kfc.kfc.entities.Unit;

import java.util.List;

public interface UnitRepository {
    Unit create(Unit toCreate);
    List<Unit> findAll ();
}
