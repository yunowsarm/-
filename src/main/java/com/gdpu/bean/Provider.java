package com.gdpu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Provider implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 供货商id
     */
    @TableId(value = "provider_id", type = IdType.AUTO)
    private Integer providerId;

    /**
     * 供货商名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 负责人
     */
    private String manager;

    /**
     * 负责人电话
     */
    private String talePhone;


}
