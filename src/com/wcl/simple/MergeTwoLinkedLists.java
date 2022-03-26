/**
 * @Classname MergeTwoLinkedLists
 * @Description TODO
 * @Date 2022/3/25 23:29
 * @Created by 28327
 */

package com.wcl.simple;

/*
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoLinkedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode listNode2 = new ListNode(1, new ListNode(3, new ListNode(4)));
//        System.out.println(solution1(listNode, listNode2).toString());
        System.out.println(solution2(listNode, listNode2).toString());
    }

    /*
    递归
    思路
    我们可以如下递归地定义两个链表里的 merge 操作（忽略边界情况，比如空链表等）：
    list1[0]+merge(list1[1:],list2) list1[0]<list2[0]
    list2[0]+merge(list1,list2[1:]) otherwise
     也就是说，两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并

     */
    public static ListNode solution1(ListNode l1, ListNode l2) {
        //边界判断
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = solution1(l1.next, l2);
            return l1;
        } else {
            l2.next = solution1(l1, l2.next);
            return l2;
        }
    }

    /*
        思路
    我们可以用迭代的方法来实现上述算法。当 l1 和 l2 都不是空链表时，
    判断 l1 和 l2 哪一个链表的头节点的值更小，
    将较小值的节点添加到结果里，当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     */
    public static ListNode solution2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }


}