package com.gdpu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;


    private String name;


    private Integer providerId;


    private Integer price;


    private String size;


    private String packages;

    @TableField(exist = false)
    private String providerName;

    @TableField(exist = false)
    private String address;
}
