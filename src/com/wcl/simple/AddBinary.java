/**
 * @Classname AddBinary
 * @Description 二进制求和
 * @Date 2022/4/6 21:55
 * @Created by 28327
 */
/*
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
    示例 1:
    输入: a = "11", b = "1"
    输出: "100"
    示例 2:
    输入: a = "1010", b = "1011"
    输出: "10101
 */
package com.wcl.simple;

public class AddBinary {
    public static void main(String[] args) {
        String a = "11", b = "1";
        System.out.println(solution2(a, b));
    }

    /*
    将字符串转换为十进制相加之后在转换为二进制
     */
    public static String solution(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    /*
    模拟 二进制中我们需要「逢二进一」。
    具体的，我们可以取n=max{∣a∣,∣b∣},循环 nn 次，从最低位开始遍历。我们使用一个变量carry 表示上一个位置的进位，初始值为 0
    。记当前位置对其的两个位为ai和bi,则每一位的答案为(carry+ai+bi)mod2,下一位的进位为 (carry+ai+bi)/2
    重复上述步骤，直到数字 a 和 b 的每一位计算完毕。最后如果carry 的最高位不为 0，则将最高位添加到计算结果的末尾
    注意，为了让各个位置对齐，你可以先反转这个代表二进制数字的字符串，然后低下标对应低位，高下标对应高位。
    当然你也可以直接把 aa 和 bb 中短的那一个补 00 直到和长的那个一样长，然后从高位向低位遍历，对应位置的答案按照顺序存入答案字符串内，
    最终将答案串反转。这里的代码给出第一种的实现。
     */
    public static String solution2(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        //循环相加两个字符串相同长度的低位数部分
        while (i >= 0 && j >= 0) {
            int sum = carry;
            sum += a.charAt(i--) - '0';
            sum += b.charAt(j--) - '0';
            carry = sum / 2;
            builder.append(sum % 2);
        }
        // 如果 a 还没遍历完成（a串比b串长），则继续遍历添加 a 的剩余部分
        while (i >= 0) {
            int sum = carry + a.charAt(i--) - '0';
            carry = sum / 2;
            builder.append(sum % 2);
        }
        // 如果 b 还没遍历完成（b串比a串长），则继续遍历添加 b 的剩余部分
        while (j >= 0) {
            int sum = carry + b.charAt(j--) - '0';
            carry = sum / 2;
            builder.append(sum % 2);
        }
        //如果 carry 不等于0 还有个进位数没加进去，需要补充
        if (carry == 1) {
            builder.append(carry);
        }
        //反转字符串获得正常结果
        return builder.reverse().toString();
    }
}