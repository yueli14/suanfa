/**
 * @Classname MergeTwoOrderedArrays
 * @Description 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * @Date 2022/4/24 13:29
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.Arrays;
import java.util.Comparator;

public class MergeTwoOrderedArrays {
    public static void main(String[] args) {
        int m = 3, n = 3;
        int[] nums1 = new int[]{4, 5, 6, 0, 0, 0};
        int[] nums2 = new int[]{1, 2, 3};
        solution4(nums1, m, nums2, n);
    }

    public static void solution1(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; ++i) {
                nums1[i] = nums2[i];
            }
        }
        if (n == 0) {
            return;
        }
        //将两个数组合并
        int j = nums1.length - n;
        int k = 0;
        while (j < nums1.length && k < n) {
            nums1[j] = nums2[k];
            j++;
            k++;
        }

        Arrays.sort(nums1);
        Arrays.stream(nums1).forEach(System.out::println);
        //排序
//        int temp = 0;
//        for (int i = 0; i < nums1.length; i++) {
//            for (int z = 1; z < nums1.length; z++) {
//                if (nums1[i] > nums1[z]) {
//                    temp = nums1[i];
//                    nums1[i] = nums1[z];
//                    nums1[z] = temp;
//
//                }
//            }
//
//        }

    }

    /**
     * 直接合并法，最直观的方法是先将数组nums2
     * 放进数组nums1的尾部，然后直接对整个数组进行排序
     * https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetco-rrb0/
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void solution2(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
        Arrays.stream(nums1).forEach(System.out::println);
    }

    /**
     * 双指针法
     * 这一方法将两个数组看作队列，每次从两个数组头部取出比较小的数字放到结果中
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void solution3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0;
        int p2 = 0;
        //当前排序数组
        int cur;
        //排序后的数组
        int[] sorted = new int[m + n];
        while (p1 < m || p2 < n) {
            //m等于0情况
            if (p1 == m) {
                //开始给sort赋值
                cur = nums2[p2++]; //相当于 cur=nums2[p2];p2++;
                //n等于0情况
            } else if (p2 == n) {
                //
                cur = nums1[p1++];
                //p1<p2
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                //p1>=p2
                cur = nums2[p2++];
            }
            //
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }

    }


    /**
     * 逆向双指针
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void solution4(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            //m=0的情况
            if (p1 == -1) {
                cur = nums2[p2--];
                //n=0的情况
            } else if (p2 == -1) {
                cur = nums1[p1--];
                // p1大
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
                //p2大
            } else {
                cur = nums2[p2--];
            }
            //从倒数第一位开始赋值
            nums1[tail--] = cur;
        }

    }
}