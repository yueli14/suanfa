/**
 * @Classname ValidParentheses
 * @Description TODO
 * @Date 2022/3/25 22:46
 * @Created by 28327
 */

package com.wcl.simple;

import java.util.HashMap;
import java.util.LinkedList;

public class ValidParentheses {
    /*
    判断括号的有效性可以使用「栈」这一数据结构来解决。

    我们遍历给定的字符串 s。当我们遇到一个左括号时，我们会期望在后续的遍历中，有一个相同类型的右括号将其闭合。由于后遇到的左括号要先闭合，因此我们可以将这个左括号放入栈顶。

    当我们遇到一个右括号时，我们需要将一个相同类型的左括号闭合。此时，我们可以取出栈顶的左括号并判断它们是否是相同类型的括号。如果不是相同的类型，或者栈中并没有左括号，
    那么字符串 ss 无效，返回 False。为了快速判断括号的类型，我们可以使用哈希表存储每一种括号。哈希表的键为右括号，值为相同类型的左括号。

    在遍历结束后，如果栈中没有左括号，说明我们将字符串 ss 中的所有左括号闭合，返回 \text{True}True，否则返回 \text{False}False。

    注意到有效字符串的长度一定为偶数，因此如果字符串的长度为奇数，我们可以直接返回 \text{False}False，省去后续的遍历判断过程。

     */
    public static void main(String[] args) {
//        String s = "()[]{}";
//        String s1 = "{[]}";
        String s2 = "(){}[]";
//        System.out.println(solution(s));
//        System.out.println(solution(s1));
        System.out.println(solution(s2));
    }

    public static Boolean solution(String s) {
        //边界判断
        if (s.length() % 2 != 0) {
            return false;
        }
        //hash表，存放括号
        HashMap<Character, Character> hashMap = new HashMap<>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };

        //创建栈，先进后出，所以判断的key值为右括号
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            //判断hash表中与s
            char c = s.charAt(i);

            if (hashMap.containsKey(c)) {
                //如果匹配到右括号，进行判断为空，直接返回false，或者头元素与该value不与map表中的左元素对应
                //判断时尽量使用否定进行判断,避免进入判断时，判断情况不足
                if (stack.isEmpty() || stack.peek() != hashMap.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                //如果没有匹配到右括号，放入栈中
                stack.push(c);
            }
        }
        //符合条件出栈完成，为真，反之，为否
        return stack.isEmpty();
    }


}