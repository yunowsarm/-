package com.gdpu.vo;

import com.gdpu.bean.Leftmenu;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class LeftMenuVo extends Leftmenu {
    private static final Long serialVersionUID=1L;
    private Integer page = 1;
    private Integer limit = 10;
}
