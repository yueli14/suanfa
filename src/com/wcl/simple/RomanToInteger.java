/**
 * @Classname RomanToInteger
 * @Description TODO
 * @Date 2022/3/24 12:23
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
罗马数字包含以下七种字符:I，V，X，L，C，D和M。
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，
而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：


 */
public class RomanToInteger {
    public static void main(String[] args) {
        String str = "III";
//        System.out.println(solution1(str));
        System.out.println(solution2(str));
    }

    public static int solution1(String s) {
        //将罗马值对应的数组组成map表
        HashMap<String, Integer> map = new HashMap<>();
        String str = "IVXLCDM";
        int[] ints = new int[]{1, 5, 10, 50, 100, 500, 1000};
        for (int i = 0; i < ints.length; ++i) {
            map.put(str.substring(i, i + 1), ints[i]);
        }
        //创建一个数组存储比对后的罗马数值
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            //s中的值在map中存在，就放入数组
            if (map.containsKey(s.substring(i, i + 1))) {
                list.add(map.get(s.substring(i, i + 1)));
            }
        }
        //遍历list中的值，如果左边的数比右边的数小，那么就对左边的进行取反操作
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                list.set(i, -list.get(i));
            }
        }
        //将list中的值相加
        int result = 0;
        for (int l : list
        ) {
            result+=l;
        }
        return result;
    }
    public static int solution2(String s){
        Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            //charAt是返回指定索引位置的char值，
            int value = symbolValues.get(s.charAt(i));
//            System.out.println(value);
//            如果索引在n-1之内，且它的值比后一位要大，总值就减，反之则加
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;



    }
}