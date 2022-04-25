/**
 * @Classname MiddleOrderTraversalOfBinaryTree
 * @Description 二叉树的中序遍历
 * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，
 * 而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程
 * @Date 2022/4/25 10:58
 * @Created by 28327
 */

package com.wcl.simple;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MiddleOrderTraversalOfBinaryTree {
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
        TreeNode treeNode = new TreeNode(1,
                new TreeNode(2, new TreeNode(3, null, null), new TreeNode(6, null, null)),
                new TreeNode(4, new TreeNode(7, null, null), new TreeNode(5, null, null)));

        solution4(treeNode);

    }

    /***
     * 递归调用
     *  inorder(root) 表示当前遍历到root 节点的答案，那么按照定义，我们只要递归调用
     *  inorder(root.left) 来遍历 root 节点的左子树，然后将 root 节点的值加入答案，再递归调用
     *  inorder(root.right) 来遍历root 节点的右子树即可，递归终止的条件为碰到空节点。
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
     *
     * @param root
     * @return
     */
    public static List<Integer> solution1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        res.stream().forEach(System.out::println);
        return res;

    }


    public static void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public static List<Integer> solution2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();
        while (root != null || !stk.isEmpty()) {
            //将二叉树左边的全部放入到栈当中
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
//            将所有的节点都出栈
            root = stk.pop();
            res.add(root.val);
            //这里相当于在遍历叉树中的右子数
            root = root.right;
        }
        res.stream().forEach(System.out::println);
        return res;

    }

    /**
     * 颜色标记法
     * 兼具栈迭代方法的高效，又像递归方法一样简洁易懂，更重要的是，这种方法对于前序、中序、后序遍历，能够写出完全一致的代码。
     *
     * 其核心思想如下：
     *
     * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
     * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
     * 如果遇到的节点为灰色，则将节点的值输出
     *class Solution:
     *     def inorderTraversal(self, root: TreeNode) -> List[int]:
     *         WHITE, GRAY = 0, 1
     *         res = []
     *         stack = [(WHITE, root)]
     *         while stack:
     *             color, node = stack.pop()
     *             if node is None: continue
     *             if color == WHITE:
     *                 stack.append((WHITE, node.right))
     *                 stack.append((GRAY, node))
     *                 stack.append((WHITE, node.left))
     *             else:
     *                 res.append(node.val)
     *         return res
     * 作者：hzhu212
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
     *
     * @param root
     * @return
     */
//    public static List<Integer> solution3(TreeNode root) {
//
//    }

    /**
     * Morris 遍历算法是另一种遍历二叉树的方法，它能将非递归的中序遍历空间复杂度降为O(1)
     * 如果 xx 无左孩子，先将 xx 的值加入答案数组，再访问 xx 的右孩子，即 x=x.right。
     * 如果 xx 有左孩子，则找到 xx 左子树上最右的节点（即左子树中序遍历的最后一个节点，xx 在中序遍历中的前驱节点），我们记为 predecessor。
     * 根据predecessor 的右孩子是否为空，进行如下操作。
     * 如果predecessor 的右孩子为空，则将其右孩子指向 xx，然后访问 xx 的左孩子，即 x=x.left。
     * 如果predecessor 的右孩子不为空，则此时其右孩子指向 xx，说明我们已经遍历完 xx 的左子树，
     * 我们将predecessor 的右孩子置空，将 xx 的值加入答案数组，然后访问 xx 的右孩子，即x=x.right。
     * <p>
     * <p>
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
     *
     * @param root
     * @return
     */
    public static List<Integer> solution4(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        res.stream().forEach(System.out::println);
        return res;

    }

}