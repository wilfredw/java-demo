package com.wei.java.algorithm;

/**
 * add-two-numbers
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class TwoLinkNumberPlus {

    public static void main(String[] args) {
        ListNode l1 = ListNode.of(new int[] {9, 9, 9, 9, 9, 9, 9});
        ListNode l2 = ListNode.of(new int[] {9, 9, 9, 9});
        ListNode ret = addTwoNumbers(l1, l2);
        System.out.println(ret);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Current = l1;
        ListNode l2Current = l2;

        ListNode result = null;
        ListNode last = null;
        int val = 0;
        int next = 0;
        while (l1Current != null && l2Current != null) {
            val = l1Current.val + l2Current.val + next;
            if (val >= 10) {
                next = val / 10;
                val = val % 10;
            } else {
                next = 0;
            }
            ListNode now = new ListNode(val);
            if (result == null) {
                result = now;
                last = now;
            } else {
                last.next = now;
                last = now;
            }
            l1Current = l1Current.next;
            l2Current = l2Current.next;
        }
        while (l1Current != null) {
            val = l1Current.val + next;
            if (val >= 10) {
                next = val / 10;
                val = val % 10;
            } else {
                next = 0;
            }
            ListNode now = new ListNode(val);
            if (result == null) {
                result = now;
                last = now;
            } else {
                last.next = now;
                last = now;
            }
            l1Current = l1Current.next;
        }
        while (l2Current != null) {
            val = l2Current.val + next;
            if (val >= 10) {
                next = val / 10;
                val = val % 10;
            } else {
                next = 0;
            }
            ListNode now = new ListNode(val);
            if (result == null) {
                result = now;
                last = now;
            } else {
                last.next = now;
                last = now;
            }
            l2Current = l2Current.next;
        }

        val = next;
        if (val > 0) {
            next = 0;
            ListNode now = new ListNode(val);
            if (result == null) {
                result = now;
                last = now;
            } else {
                last.next = now;
                last = now;
            }
        }
        return result;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        private void setNext(ListNode listNode) {
            this.next = listNode;
        }

        public static ListNode of(int[] value) {
            ListNode result = null;
            ListNode last = null;
            for (int i = 0; i < value.length; ++i) {
                ListNode current = new ListNode(value[i]);
                if (result == null) {
                    result = current;
                    last = current;
                } else {
                    last.setNext(current);
                    last = current;
                }
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.val + ", ");
            if (this.next != null) {
                stringBuilder.append(this.next.toString());
            }
            return stringBuilder.toString();
        }
    }

}
