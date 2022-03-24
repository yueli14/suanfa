/**
 * @Classname LongestCommonPrefix
 * @Description TODO
 * @Date 2022/3/24 19:45
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.*;

/*
    编写一个函数来查找字符串数组中的最长公共前缀。

    如果不存在公共前缀，返回空字符串""。



    示例 1：

    输入：strs = ["flower","flow","flight"]
    输出："fl"
    示例 2：

    输入：strs = ["dog","racecar","car"]
    输出：""
    解释：输入不存在公共前缀。

 */
public class LongestCommonPrefix {
    public static void main(String[] args) {

        String[] strs = new String[]{"", ""};
        System.out.println(solution1(strs));
    }

    /*
    横向查找 如果在尚未遍历完所有的字符串时，最长公共前缀已经是空串，则最长公共前缀一定是空串，因此不需要继续遍历剩下的字符串，直接返回空串即可。
     */
    //当遇到三个数以上时，拆分为两两比较
    public static String solution1(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        //取第一个数
        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            s = longestCommonPrefix(s, strs[i]);
            if (s.length() == 0) {
                break;
            }
        }
        return s;
    }

    public static String longestCommonPrefix(String str1, String str2) {
        //去最小位数进行比较
        int min = Math.min(str1.length(), str2.length());
        //创建索引，截取字符串
        int index = 0;
        while (index < min && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /*
        另一种方法是纵向扫描。纵向扫描时，从前往后遍历所有字符串的每一列，
        比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     */
    public static String solution2(String[] strs) {

    }

//    当输入空串时，有问腿

    public static String solution(String[] strs) {
        ArrayList<Character> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        if (strs.length == 1) {
            return strs[0];
        }
        if (strs.length == 0 || strs == null) {
            return "";
        }
        //对字符串进行排序
        //判断字符串中组字符最少的字符串
        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length - 1; j++) {
                if (strs[i].length() <= strs[j].length()) {
                    list2.add(strs[i]);
                } else
                    list2.add(strs[j]);
            }
        }
        String min = list2.get(0);
        //将相同的char放入list列表
        for (String s : strs) {
            for (int i = 0; i < min.length(); i++) {
                if (min.charAt(i) == s.charAt(i)) {
                    list.add(min.charAt(i));
                }
            }
        }

        //遍历list，最小公缀数写入min，继续循环，
        StringBuilder temp = new StringBuilder();
        for (Character c : list) {
            temp.append(c);
        }
        list.clear();
        min = String.valueOf(temp);

        if (min == "")
            return "";
        else
            return min;
    }


}
