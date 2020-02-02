package com.zxy.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 菜单
 * @author black
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    /**
     * 菜品ID
     * */
    private int id;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 菜品价格
     */
    private double price;

    /**
     * 菜品类型
     */
    private int type;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createPeople;
}
