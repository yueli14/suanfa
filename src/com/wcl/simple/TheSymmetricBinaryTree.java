/**
 * @Classname TheSymmetricBinaryTree
 * @Description 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 它们的两个根结点具有相同的值
 * 每个树的右子树都与另一个树的左子树镜像对称
 * @Date 2022/4/26 10:34
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.LinkedList;
import java.util.Queue;

public class TheSymmetricBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        public static void main(String[] args) {
            TreeNode treeNode = new TreeNode(1, new TreeNode(2, null, null),
                    new TreeNode(3, null, null));
            TreeNode treeNode2 = new TreeNode(1, new TreeNode(3, new TreeNode(4, null, null), null),
                    new TreeNode(3, null, new TreeNode(4, null, null)));
            System.out.println(solution2(treeNode2));
        }

        /**
         * [1,2,2,null,3,null,3]
         *
         * @param root
         * @return
         */
        public static boolean solution1(TreeNode root) {
            if (root == null) {
                return false;
            } else if (root.left == null || root.right == null) {
                return false;

            } else if (root.right.val == root.left.val) {
                return true;
            } else {
                return solution1(root.left) && solution1(root.right);
            }
        }

        /**
         * 递归
         * 它们的两个根结点具有相同的值
         * 每个树的右子树都与另一个树的左子树镜像对称
         *
         * @param root
         * @return
         */
        public static boolean solution2(TreeNode root) {
            return check(root, root);
        }

        public static boolean check(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            //这里递归应该分开写，分别对左右进行检查
            return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
        }

        /**
         * 迭代
         * 首先我们引入一个队列，这是把递归程序改写成迭代程序的常用方法。初始化时我们把根节点入队两次。
         * 每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像），
         * 然后将两个结点的左右子结点按相反的顺序插入队列中。当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，
         * 该算法结束。
         * <p>
         * 链接：https://leetcode-cn.com/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode-solution/
         *
         * @param root
         * @return
         */
        public static boolean solution3(TreeNode root) {
            return check2(root, root);
        }

        public static boolean check2(TreeNode u, TreeNode v) {
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.offer(u);
            q.offer(v);
            while (!q.isEmpty()) {
                u = q.poll();
                v = q.poll();
                if (u == null && v == null) {
                    continue;
                }

                if ((u == null || v == null) || (u.val != v.val)) {
                    return false;
                }
                //判断左右
                q.offer(u.left);
                q.offer(v.right);
                //判断右左
                q.offer(u.right);
                q.offer(v.left);
            }
            return true;
        }

    }
}