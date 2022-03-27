/**
 * @Classname RemoveElement
 * @Description TODO
 * @Date 2022/3/27 19:04
 * @Created by 28327
 */

package com.wcl.simple;

/*
    给你一个数组 nums和一个值 val，你需要原地移除所有数值等val的元素，并返回移除后数组的新长度。
    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] ints = new int[]{2, 1, 1, 2, 2, 3, 3, 4, 5};
//        System.out.println(solution3(ints, 2));
        System.out.println(solution2(ints, 2));
    }

    //
    /*
    由于题目要求删除数组中等于 val 的元素，因此输出数组的长度一定小于等于输入数组的长度，我们可以把输出的数组直接写在输入数组上。
    可以使用双指针：右指针right指向当前将要处理的元素，左指针left 指向下一个将要赋值的位置。
    如果右指针指向的元素不等于 val，它一定是输出数组的一个元素，我们就将右指针指向的元素复制到左指针位置，然后将左右指针同时右移；
    如果右指针指向的元素等于 val，它不能在输出数组里，此时左指针不动，右指针右移一位。

    作者：LeetCode-Solution

     */
    public static int solution2(int[] nums, int val) {
        int n = nums.length;
        if (n == 0 || nums == null) {
            return n;
        }

        int p = 0;
        for (int i = 0; i < n; i++) {
            // 如果不相等，则复制到p索引上
            //相等则让p指针不移动,i指针动了，则直接跳过该元素了
            if (nums[i] != val) {
                nums[p] = nums[i];
                p++;
            }
        }
        return p;
    }

    //奇奇怪怪
    public static int solution3(int[] nums, int val) {
        int n = nums.length;
        if (n == 0 || nums == null) {
            return n;
        }
        //右指针
        int q = 0;
        //左指针
        int p = 0;
        while (q < n) {
            if (val != nums[q]) {
                nums[p] = nums[q];
                q++;
                p++;
            } else {
                q++;

            }
        }
        return p;

    }
}