/**
 * @Classname PlusOne
 * @Description TODO
 * @Date 2022/4/1 23:06
 * @Created by 28327
 */

package com.wcl.simple;
/*
    难点：进位
    给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
    最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
    你可以假设除了整数0之外，这个整数不会以零开头。
    输入：digits = [1,2,3]
    输出：[1,2,4]
    解释：输入数组表示数字 123。
    输入：digits = [4,3,2,1]
    输出：[4,3,2,2]
    解释：输入数组表示数字 4321
    输入：digits = [0]
    输出：[1]
 */

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        int[] digits = new int[]{1, 1, 9};
        System.out.println(Arrays.toString(solution0(digits)));
    }

    /*
    找出最长的后缀 9
    们只需要对数组 \digits 进行一次逆序遍历，找出第一个不为 9 的元素，将其加一并将后续所有元素置零即可。如果
    digits 中所有的元素均为 9，那么对应着「思路」部分的第三种情况，我们需要返回一个新的数组。

    作者：LeetCode-Solution
。
     */
    public static int[] solution0(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            //判断最长字串9，得到起始索引
            if (digits[i] != 9) {
                ++digits[i];
                //将9置零
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;

    }


    public static int[] solution31(int[] digits) {
        if (digits[digits.length - 1] == 0 && digits.length == 1) {
            return new int[]{1};
        }
        if (digits[digits.length - 1] != 9 && digits.length == 1) {
            return new int[]{digits[digits.length - 1] + 1};
        }

        int j = 0, index = 0;
        while (j < digits.length) {
            //如果不为九
            if (digits[j] != 9) {
                //最大9起始串索引
                index = j;
                j++;
            } else
                j++;
        }
        //判断全是9情况
        if (j - index == digits.length) {
            int[] ints = new int[digits.length + 1];
            for (int i = 0; i < digits.length; i++) {
                ints[0] = 1;
                ints[i + 1] = 0;
            }
            return ints;
        } else {
            //判断是否为最长9字串，也就是判断数组的最后一个值是不也是为9
            if (digits[digits.length - 1] != 9) {
                digits[digits.length - 1] = digits[digits.length - 1] + 1;
            } else {

                //从索引位置开始赋值
                digits[index] = digits[index] + 1;
                for (int i = index + 1; i < digits.length; i++) {
                    digits[i] = 0;
                }

            }
            return digits;
        }
    }


    public static int[] solution32(int[] digits) {
        if (digits[digits.length - 1] == 0 && digits.length == 1) {
            return new int[]{1};
        }
        int i = 0;
        int[] ints = new int[digits.length + 1];
        while (i < digits.length) {
            if (digits[i] != 9) {
                break;
            } else {
                ints[0] = 1;
                ints[i + 1] = 0;
                i++;
            }

        }
        int j = digits.length - 1;
        if (digits[j] == 9) {
            digits[j] = 0;
            digits[j - 1] = digits[j - 1] + 1;
        } else {
            digits[j] = digits[j] + 1;
        }

        return digits;
    }

    //边界溢出的情况
    public static int[] solution3(int[] digits) {
        int length = digits.length;
        double sum = 0L;
        //还原为int数
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i] * Math.pow(10, length - 1);
            length--;
        }
        //加一操作
        double x = sum + 1;
        //转化为字符串
        String s = String.valueOf(x);
        //创建一个相同长度的数组
        int[] ints = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            //将每一个转换为int并赋值
            ints[i] = Integer.valueOf(s.substring(i, i + 1));
        }
        return ints;
    }

}