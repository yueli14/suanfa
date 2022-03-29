/**
 * @Classname MaxSubArray
 * @Description TODO
 * @Date 2022/3/29 22:51
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    子数组 是数组中的一个连续部分。
    输入：nums = [5,4,-1,7,8]       输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    输出：23                         输出：6
    输入：nums = [1]                 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
    输出：1

 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution3(nums));

    }

    /*
    动态规划

     */
    public static int solution2(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /*
    分治
    https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
     */
    public static int solution1(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

   static class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }


    public static Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public static Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /*
    奇怪的解法
     */
    public static int solution3(int[] nums) {
        //边界判断
        int n = nums.length;
        if (n == 1) {
            return nums[n - 1];
        }
        ArrayList<Integer> lists = new ArrayList<Integer>();
        int sum = 0;
        int i = 0;
        while (i < n) {
            //找到数组中第一个大于0的整数
            if (nums[i] > 0) {
                //开始循环剩余的数
                for (int j = i; j < n; j++) {
                    int temp = sum + nums[j];
                    if (temp >= sum) {
                        sum = temp;
                    } else {
                        lists.add(temp);
                        sum = 0;
                        temp = 0;
                    }

                }
            }
            i++;
        }
        if (lists.isEmpty()) {
            return 0;
        }
        return Collections.max(lists);

    }

}
