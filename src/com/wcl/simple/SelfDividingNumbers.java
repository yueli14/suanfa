/**
 * @Classname SelfDividingNumbers
 * @Description TODO
 * @Date 2022/3/31 23:26
 * @Created by 28327
 */

package com.wcl.simple;


import java.util.ArrayList;
import java.util.List;

/*
自除数是指可以被它包含的每一位数整除的数。
例如，128 是一个 自除数 ，因为128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
自除数 不允许包含 0 。
给定两个整数left和right ，返回一个列表，列表的元素是范围[left, right]内所有的 自除数
来源：力扣（LeetCode）

 */
public class SelfDividingNumbers {
    public static void main(String[] args) {
        int left = 47, right = 85;
        System.out.println(solution4(left, right).toString());
    }

    //直接判断
    /*
    根据自除数的定义，如果一个整数不包含 00 且能被它包含的每一位数整除，则该整数是自除数。判断一个整数是否为自除数的方法是遍历整数的每一位，判断每一位数是否为 00 以及是否可以整除该整数。
    遍历整数的每一位的方法是，每次将当前整数对 1010 取模即可得到当前整数的最后一位，然后将整数除以 1010。重复该操作，直到当前整数变成 00 时即遍历了整数的每一位。
     */
    //判断自除数
    public static boolean isSelfDividing(int num) {
        int temp = num;
        while (temp > 0) {
            //个位数
            int digit = temp % 10;
            //当数字带有0，那么肯定不是自除数
            if (digit == 0 || num % digit != 0) {
                return false;
            }
            //个位以上
            temp /= 10;
        }
        return true;
    }


    public static List<Integer> solution4(int left, int right) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public static boolean divideT(int x, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (x % list.get(i) != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> solution5(int left, int right) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        while (left <= right) {
            String s = String.valueOf(left);

            int n = s.length();
            for (int i = 0; i < n; i++) {
                list.add(Integer.valueOf(s.charAt(i)));
                System.out.println(list);
            }
            if (divideT(left, list)) {
                list2.add(left);
                left++;
                list.clear();
            }
            left++;
            list.clear();
        }

        return list2;

    }
}
