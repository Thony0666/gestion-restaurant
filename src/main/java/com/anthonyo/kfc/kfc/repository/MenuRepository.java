package com.anthonyo.kfc.kfc.repository;

import com.anthonyo.kfc.kfc.entities.Menu;

import java.util.Optional;

public interface MenuRepository {
    Menu create(Menu toCreate);
    Optional<Menu> findById(Integer id);
}
