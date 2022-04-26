/**
 * @Classname MaximumDepthOfBinaryTree
 * @Description 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * @Date 2022/4/26 11:10
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
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

    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, null, null),
                new TreeNode(3, null, null));
        TreeNode treeNode2 = new TreeNode(1, new TreeNode(3, new TreeNode(4, null, null), null),
                new TreeNode(3, null, new TreeNode(4, null, null)));

        solution2(treeNode2);
    }

    /**
     * 深度优先算法
     * 如果我们知道了左子树和右子树的最大深度 ll 和 rr，那么该二叉树的最大深度即为
     * max(l,r)+1
     * 具体而言，在计算当前二叉树的最大深度时，可以先递归计算出其左子树和右子树的最大深度，然后在 O(1)O(1) 时间内计算出当前二叉树的最大深度。递归在访问到空节点时退出。
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/
     *
     * @param root
     * @return
     */
    public static int solution1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = solution1(root.left);
            int rightHeight = solution1(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }

    }

    /**
     * 广度优先搜索 秒！！！
     * 此时我们广度优先搜索的队列里存放的是「当前层的所有节点」。
     * 每次拓展下一层的时候，不同于广度优先搜索的每次只从队列里拿出一个节点，
     * 我们需要将队列里的所有节点都拿出来进行拓展，这样能保证每次拓展完的时候队列里存放的是当前层的所有节点，
     * 即我们是一层一层地进行拓展，最后我们用一个变量 ans 来维护拓展的次数，该二叉树的最大深度即为 ans。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public static int solution2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        //迭代判断queue中的数据
        while (!queue.isEmpty()) {
            int size = queue.size();
            // root中的节点放入带队列当中
            while (size > 0) {
                TreeNode node = queue.poll();
                //相当于将每个节点的子节点放入到队列中，依次进行判断
                //注意这里的子节点都是一层的，所以当这一层循坏退出时，就对最大深度进行加一判断
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

}