package com.gdpu.vo;

import com.gdpu.bean.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider {
    private static final long serialVersionUID = 1L;
    private Integer page = 1;
    private Integer limit = 10;
}
