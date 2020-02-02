package com.zxy.service.impl;

import com.zxy.dao.DishDao;
import com.zxy.entity.Dish;
import com.zxy.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DishServiceImpl
 * @author black
 */
@Service
@Transactional
public class DishServiceImpl implements DishService {
    @Autowired
    private DishDao dishDao;

    @Override
    public List<Dish> findAll() {
        return dishDao.findAll();
    }
}
