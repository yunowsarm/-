package com.gdpu.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    public static List<TreeNode> build(List<TreeNode> treeNodes,Integer topId){

        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1: treeNodes) {    //寻找pid为1的结点，我们认为pid=1是树的根节点
            if(n1.getPid() == topId){
                nodes.add(n1);
            }
            for (TreeNode n2:treeNodes) {    //寻找pid=this.id的子结点，将他加入到树的列表中
                if(n1.getId() == n2.getPid()){
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}
