/**
 * @Classname SearchInsert
 * @Description TODO
 * @Date 2022/3/29 8:39
 * @Created by 28327
 */

package com.wcl.simple;

/*
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    输入: nums = [1,3,5,6], target = 5 nums = [1,3,5,6], target = 2 输入: nums = [1,3,5,6], target = 7
    输出: 2                            输出: 1                       输出: 4
 */
public class SearchInsert {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 2;
        System.out.println(solution3(nums, target));
    }

    /*
        二分法 nums[pos−1]<target≤nums[pos]
        「在一个有序数组中找第一个大于等于 target 的下标」
     */
    public static int solution3(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        //既然len有可能是答案，那么我们将len设为右边界，就可以避免特殊情况进行判断。
        int right = len;
        // 在区间 nums[left..right] 里查找第 1 个大于等于 target 的元素的下标
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left..mid]
                right = mid;
            }
        }
        return left;
    }


    //能看。。
    public static int solution4(int[] nums, int target) {
        // 边界判断
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        for (int i = 0; i < nums.length; i++) {
            //当有数比当前数大或者相等的时候，返回当前数的索引
            if (target <= nums[i]) return i;
        }
        return 0;
    }

}