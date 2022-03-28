/**
 * @Classname ImplementstrStr
 * @Description TODO
 * @Date 2022/3/28 22:07
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.ArrayList;
import java.util.List;

/*
    给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
    如果不存在，则返回 -1
    来源：力扣（LeetCode）
 */
public class ImplementstrStr {
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "llo";
        System.out.println(solution5(haystack, needle));
    }

    /*
        暴力匹配
    我们可以让字符串 needle 与字符串 haystack 的所有长度为 mm 的子串均匹配一次。
    为了减少不必要的匹配，我们每次匹配失败即立刻停止当前子串的匹配，对下一个子串继续匹配。
    如果当前子串匹配成功，我们返回当前子串的开始位置即可。如果所有子串都匹配失败，则返回 -1−1。
    作者：LeetCode-Solution
     */
    public static int solution5(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
//        循环条件只需要到n-m，如果还没找到子串，那么意味着没有子串了
        //外循环控制hayde匹配
        for (int i = 0; i <= n - m; i++) {
            //减少匹配次数
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                //i+j就是让hay的指针向后移动一位，这样原始索引就能够保存下来
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /*
    朴素解法
    直观的解法的是：枚举原串 ss 中的每个字符作为「发起点」，每次从原串的「发起点」和匹配串的「首位」开始尝试匹配：
    匹配成功：返回本次匹配的原串「发起点」。
    匹配失败：枚举原串的下一个「发起点」，重新尝试匹配。
    作者：AC_OIer
     */
    public static int solution2(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        char[] s = haystack.toCharArray(), p = needle.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m)
                return i;
        }
        return -1;
    }


    /*
     Knuth-Morris-Pratt 算法，
     */
    public static int solution1(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /*
    "mississippi"  "issip"，当这种情况时代码会出现问题
     */
    public static int solution3(String haystack, String needle) {
        //边界判断
        if (needle == "") {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        ArrayList<Integer> integers = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < needle.length()) {
            //如果不相等，移动j指针
            if (needle.charAt(i + j) != haystack.charAt(j)) {
                j++;
                //如果j已经遍历完成，并且i指针没有移动过，
                // 那么我们可以确定两个字符串中没有相同项
                if (j == haystack.length() && i == 0) {
                    return -1;
                }
            } else {
                //如果两者相等，那么双指针加一
                integers.add(j);
                i++;
                j++;
            }
        }
        if (integers.isEmpty()) {
            return -1;
        }
        return integers.get(0);
    }

    /*
     奇怪的解法
     */
    public static int solution4(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}