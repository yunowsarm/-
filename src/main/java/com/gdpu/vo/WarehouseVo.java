package com.gdpu.vo;

import com.gdpu.bean.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseVo extends Warehouse {
    private static final long serialVersionUID = 1L;
    private Integer page = 1;
    private Integer limit = 10;
}
