/**
 * @Classname SumOfTwoNumber
 * @Description TODO
 * @Date 2022/3/23 23:09
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.HashMap;

/*
    输入：nums = [2,7,11,15], target = 9
    输出：[0,1]
    解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

 */
public class SumOfTwoNumber {
    public static void main(String[] args) throws Exception {
        int[] ints = {2, 3, 5, 6};
        int target = 8;
        int[] j = solution1(ints, target);
        for (int k : j) {
            System.out.println(k);
        }

        int[] k = solution2(ints, target);
        for (int z : j) {
            System.out.println(z);
        }


    }

    public static int[] solution1(int[] nums, int target) throws Exception {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            //判断是否含有数，如果有就返回，如果没有就继续放入map表<num,index>
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new Exception("nothing");
    }




    public static int[] solution2(int[] nums, int target) throws Exception {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new Exception("nothing");
    }
}

