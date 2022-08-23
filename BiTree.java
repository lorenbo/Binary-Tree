package com.cdtu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: Loren
 * @Date: 2022/4/24 - 04 - 24 - 18:13
 * @Decsription:问题描述：
 * 二叉排序树或者是一棵空树，或者是具有下列性质的二叉树；
 * 1. 若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 2. 若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；
 * 3. 左、右子树也分别为二叉排序树；
 *
 * 输入说明：
 * 第一行是测试数据的组数 N，后面是 N 组测试数据。
 * 每组测试数据占用一行，由若干由空格分隔的正整数构成，以 0 作为结束标志。
 *
 * 输出要求：
 * 对每一组测试数据输出以下三行数据
 * 第一行：一个整数，表示创建的二叉排序树的高度
 * 第二到第四行分别输出该二叉排序树的前序、中序、后序遍历结果
 * 遍历结果中的每个整数后跟一个空格。
 */

public class BiTree {
    public static void main(String[] args) {
        System.out.println("请输入数据组数：");
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        String[] arr=new String[2*n];
        for (int i=0;i<arr.length;i++)
            arr[i]=scanner.next();
        for (int i=0;i<n;i++){
            char[] preOrders = arr[2*i].toCharArray();//转化成字符型数组
            char[] inOrders = arr[2*i+1].toCharArray();
            mainBuild(preOrders, inOrders);
            System.out.println("");
        }
    }

//    构造树节点(数据、左子树、右子树)
    private char data;
    private BiTree leftNode;
    private BiTree rightNode;
//    有参构造
    public BiTree(char data, BiTree leftNode, BiTree rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
//
    public BiTree(char data){
        this(data,null,null);
    }
//setter、getter
    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public BiTree getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BiTree leftNode) {
        this.leftNode = leftNode;
    }

    public BiTree getRightNode() {
        return rightNode;
    }

    public void setRightNode(BiTree rightNode) {
        this.rightNode = rightNode;
    }


    public static BiTree mainBuild(char[] preOrders, char[] inOrders){;
       BiTree tree=buildTree(preOrders, inOrders);
        System.out.print("树的深度为："+GetDepth(tree)+" ");
        PostOderTraverse(tree);
        return tree;

    }


//   根据前序和后序创建”Binarytree“
    /*前序：根左右
    * 中序：左根右
    * 后序：左右根
    * */

    private static BiTree buildTree(char[] preOrders, char[] inOrders) {
    if (preOrders.length == 0 || inOrders.length == 0) {
        return null;
    }
    BiTree tree = new BiTree((char) preOrders[0]);
    int index = search(0, inOrders.length, inOrders, tree.getData());
    //构建左子树
    tree.setLeftNode(buildTree(Arrays.copyOfRange(preOrders, 1, index + 1),
            Arrays.copyOfRange(inOrders, 0, index)));
    //构建右子树
    tree.setRightNode(buildTree(Arrays.copyOfRange(preOrders, index + 1, preOrders.length),
            Arrays.copyOfRange(inOrders, index + 1, inOrders.length)));
    return tree;
}
   //得到索引
    private static int search(int start, int end, char[] inOrders, int data) {
        for (int i = start; i < end; i++) {
            if (data == inOrders[i]) {
                return i;
            }
        }
        return -1;
    }

//求树的深度:
/*  每个节点都可以看作根节点
    *根节点（任意一个节点）的深度等于它的左子树或右子树深度最大值+1
    *从根结点开始遍历，若遍历到叶子节点，深度为0
    *使用递归，分别求出左子树的深度、右子树的深度，两个深度的较大值+1即可
    */
    public static  int GetDepth(BiTree tree){
        if (tree==null)
            return 0;
        else {
            int D1=GetDepth(tree.leftNode);
            int D2=GetDepth(tree.rightNode);
            return Math.max(D1,D2)+1;
        }
    }


//后序遍历递归算法
    public static void PostOderTraverse(BiTree tree){
        if (tree==null)return;
        PostOderTraverse(tree.leftNode);
        PostOderTraverse(tree.rightNode);
        System.out.print(tree.data);
    }
}

/*
*
*   输入示例：
5
FPEOKADLJMHNGCIB PFDAJLKONHMECGBI
LABODJTISPFKCGENRMHQ TJPSIDOCKEGFBRNMAHLQ
NIJKAFGLBMCHED JINAKBLHCMEGFD
MIJCDBGAEHOFLNPK CJBDAGIFOHPNLKEM
CNMBGAFRHLJIPQODKES RFAGBHMLNIJCDOQEKPS

输出示例：
7 PDJLAKNHMOCBIGEF
9 TPSIJDCEGKFORMNBHAQL
8 JIABHCEMLGDFKN
7 CBAGDJFOPNKLHEIM
8 RFAGHBLMIJNDOEKQSPC*/