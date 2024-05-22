package com.gdpu.vo;

import com.gdpu.bean.InputForm;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class InputFormVo extends InputForm {
    private static final long serialVersionUID = 1L;
    private Integer page = 1;
    private Integer limit = 10;
}
