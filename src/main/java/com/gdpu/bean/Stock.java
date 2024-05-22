package com.gdpu.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer goodsId;

    private Integer houseId;

    private Integer number;

    private Integer upperAlert;

    private Integer underAlert;


    //货物名
    @TableField(exist = false)
    private String name;
    //仓库名
    @TableField(exist = false)
    private String houseName;
    //仓库地址
    @TableField(exist = false)
    private String address;
    //商品价格
    @TableField(exist = false)
    private Integer price;
    //规格
    @TableField(exist = false)
    private String size;
    //包装
    @TableField(exist = false)
    private String packages;

    public Stock(Integer goodsId, Integer houseId, Integer number, Integer upperAlert, Integer underAlert) {
        this.goodsId = goodsId;
        this.houseId = houseId;
        this.number = number;
        this.upperAlert = upperAlert;
        this.underAlert = underAlert;
    }
}
