/**
 * @Classname DeleteDuplicatesLIstElement
 * @Description 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * @Date 2022/4/24 10:20
 * @Created by 28327
 */

package com.wcl.simple;

public class DeleteDuplicatesLIstElement {
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
        ListNode listNode = new ListNode(1,
                new ListNode(2, new ListNode(4, new ListNode(4))));
        ListNode listNode2 = new ListNode(1,
                new ListNode(2, new ListNode(2, new ListNode(4))));
        System.out.println(solution1(listNode2));
    }

    /**
     * 一次遍历
     * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
     * @param head
     * @return
     */
    public static ListNode solution1(ListNode head) {

        ListNode copy = head;

        if (head == null) {
            return null;
        }

        while (copy.next != null) {
            if (copy.val == copy.next.val) {
                //让指针跳过当前项，指向下一项
                copy.next = copy.next.next;
            } else {
                //将指针指向的当前项向后移一位
                copy = copy.next;
            }
        }

        return head;


    }
}