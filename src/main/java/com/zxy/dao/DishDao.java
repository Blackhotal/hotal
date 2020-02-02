package com.zxy.dao;

import com.zxy.entity.Dish;

import java.util.List;

/**
 * DishDao
 * @author black
 */
public interface DishDao {
    List<Dish> findAll();
}
