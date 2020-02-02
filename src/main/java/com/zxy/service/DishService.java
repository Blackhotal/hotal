package com.zxy.service;

import com.zxy.entity.Dish;

import java.util.List;

/**
 * DishService
 * @author black
 *
 */
public interface DishService {
    /**
     * 查询所有的菜品
     * @return
     */
    List<Dish> findAll();

}
