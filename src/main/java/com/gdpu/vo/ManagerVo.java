package com.gdpu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ManagerVo extends com.gdpu.bean.Manager {

    private static final Long serialVersionUID = 1L;


    private Integer page = 1;
    private Integer limit = 10;
}
