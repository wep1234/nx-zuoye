package com.nx.day01;

import java.util.Stack;

/**
 * 二叉搜索树
 */
public class BST {

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    TreeNode root;

    public void addNode(int val){
        root = addNode(root,val);
    }

    public TreeNode addNode(TreeNode node,int val){
        if(node == null){
            node = new TreeNode(val);
        }else{
            if(val < node.val){
                //插入值小于根节点，插入左子树
                node.left = addNode(node.left,val);
            }else {
                //插入值大于根节点，插入右子树
                node.right = addNode(node.right,val);
            }
        }
        return node;
    }

    /**
     * 前序遍历: 先输出父节点，再遍历左子树和右子树
     * 中序遍历: 先遍历左子树，再输出父节点，再遍历右子树，中序遍历的结果是有序的
     * 后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
     */

    /**
     * 递归前序 根->左->右
     */
    public void reVlr(){
        reVlr(root);
        System.out.println();
    }

    /**
     * 迭代前序 根->左->右
     */
    public void itVlr(){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.empty()){
            while (node != null){
                stack.push(node);
                System.out.print(node.val+" ");
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }

    /**
     * 递归中序 左->根->右 遍历结果有序
     */
    public void reLdr(){
        reLdr(root);
        System.out.println();
    }

    /**
     * 迭代中序 左->根->右 遍历结果有序
     */
    public void itLdr(){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.empty()){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.val+" ");
            node = node.right;
        }
    }

    /**
     * 递归后序 左->右->根
     */
    public void reLrd(){
        reLrd(root);
        System.out.println();
    }

    /**
     * 迭代后序 左->右->根
     */
    public void itLrd(){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode preNode = null;
        while (node != null || !stack.empty()){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if(node.right == null || node.right == preNode){
                System.out.print(node.val+" ");
                node = stack.pop();
                preNode = node;
                node = null;
            }else{
                node = node.right;
            }
        }
    }

    public void reVlr(TreeNode node){
        if(node != null){
            System.out.print(node.val+" ");
            reVlr(node.left);
            reVlr(node.right);
        }
    }

    public void reLdr(TreeNode node){
        if(node != null) {
            reLdr(node.left);
            System.out.print(node.val + " ");
            reLdr(node.right);
        }
    }

    public void reLrd(TreeNode node){
        if(node != null) {
            reLrd(node.left);
            reLrd(node.right);
            System.out.print(node.val + " ");
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.addNode(7);
        bst.addNode(3);
        bst.addNode(10);
        bst.addNode(1);
        bst.addNode(5);
        bst.addNode(9);
        bst.addNode(12);
        System.out.println("前序递归遍历：");
        bst.reVlr();
        System.out.println("前序迭代遍历：");
        bst.itVlr();
        System.out.println();
        System.out.println("中序递归遍历：");
        bst.reLdr();
        System.out.println("中序迭代遍历：");
        bst.itLdr();
        System.out.println();
        System.out.println("后序递归遍历：");
        bst.reLrd();
        System.out.println("后序迭代遍历：");
        bst.itLrd();
    }
}
