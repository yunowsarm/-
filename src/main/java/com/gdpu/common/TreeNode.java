package com.gdpu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private Integer id;
    private Integer pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;//
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /*首页左边导航树的构造器*/
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }


    /*角色构造器*/
    public TreeNode(Integer id, Integer pid, String title, String icon, String href) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }
}
