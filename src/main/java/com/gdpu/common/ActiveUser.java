package com.gdpu.common;

import com.gdpu.bean.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {
    private Manager manager;

    private List<String> roles;

    private List<String> permission;
}
