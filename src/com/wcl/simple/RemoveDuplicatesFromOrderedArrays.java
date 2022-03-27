/**
 * @Classname RemoveDuplicatesFromOrderedArrays
 * @Description TODO
 * @Date 2022/3/27 15:41
 * @Created by 28327
 */

package com.wcl.simple;

/*
    给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
    由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么nums的
    前 k 个元素应该保存最终结果。
    将最终结果插入nums 的前 k 个位置后返回 k 。
    不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
 */
public class RemoveDuplicatesFromOrderedArrays {

    public static void main(String[] args) {
        int[] ints = new int[]{1, 1, 1, 2, 3, 3};
//        System.out.println(solution1(ints));
        System.out.println(solution3(ints));

    }

    /*
        首先注意数组是有序的，那么重复的元素一定会相邻。
        要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。
        考虑用 2 个指针，一个在前记作 p，一个在后记作 q，算法流程如下：
        1.比较 p 和 q 位置的元素是否相等。
        如果相等，q 后移 1 位
        如果不相等，将 q 位置的元素复制到 p+1 位置上，p 后移一位，q 后移 1 位
        重复上述过程，直到 q 等于数组长度。
        返回 p + 1，即为新数组长度。
        作者：max-LFszNScOfE

     */
    //双指针法
    public static int solution1(int[] nums) {

        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            //如果P位不等于q位
            if (nums[p] != nums[q]) {
                //间隔大于1的时候才能确定赋值
                if (q - p > 1) {
                    //那么将P+1，赋值为q位
                    nums[p + 1] = nums[q];
                }
                //p移到赋值位
                p++;
            }
            //如果P位等于q位， 则继续移动q指针，直到q不相等
            q++;
        }
        return p + 1;


    }
    /*
        为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。

    对于此类问题，我们应该进行如下考虑：

    由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
    对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
    举个🌰，我们令 k=1，假设有样例：[3,3,3,3,4,4,4,5,5,5]

    设定变量 idx，指向待插入位置。idx 初始值为 0，目标数组为 []

    首先我们先让第 1 位直接保留（性质 1）。idx 变为 1，目标数组为 [3]

    继续往后遍历，能够保留的前提是与 idx 的前面 1 位元素不同（性质 2），因此我们会跳过剩余的 3，将第一个 4 追加进去。idx 变为 2，目标数组为 [3,4]

    继续这个过程，跳过剩余的 4，将第一个 5 追加进去。idx 变为 3，目标数组为 [3,4,5]

    当整个数组被扫描完，最终我们得到了目标数组 [3,4,5] 和 答案 idx 为 3。

    作者：AC_OIer
    链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shua-chuan-lc-jian-ji-shuang-zhi-zhen-ji-2eg8/

     */

    public static int solution3(int[] nums) {
        return process(nums, 1);
    }

   public static int process(int[] nums, int k) {
        int idx = 0;
        for (int x : nums) {
            if (idx < k || nums[idx - k] != x) nums[idx++] = x;
        }
        return idx;
    }


    //自己写的垃圾QAQ
    public static int solution4(int[] ints) {

        int n = ints.length;
        if (n == 0) {
            return 0;
        }


        for (int j = 0; j < ints.length - 1; j++) {
            if (ints[j] == ints[j + 1]) {
                n -= 1;
            }
        }
        return n;
    }
}