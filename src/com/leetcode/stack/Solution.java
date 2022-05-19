package com.leetcode.stack;

import datastructure.linear.Stack2;
import org.junit.Test;

import java.util.HashMap;

public class Solution {

    private HashMap<Character, Character> map = new HashMap<>();

    public Solution() {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    @Test
    public void test() {
        System.out.println(isValidParentheses("{[()]}"));
        System.out.println(isValidParentheses("{[)]}"));
        System.out.println(isValidParentheses("{[()}"));
    }

    @Test
    public void test2() {
        System.out.println(isValidParentheses2("{[()]}"));
        System.out.println(isValidParentheses2("{[)]}"));
        System.out.println(isValidParentheses2("{[()}"));
    }

    public boolean isValidParentheses2(String str) {
        int len = str.length();

        Stack2<Character> stack = new Stack2<>();

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {  // 右括号
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char left = stack.pop();
                    if (c != map.get(left)) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    // 有效括号
    public boolean isValidParentheses(String str) {
        int len = str.length();

        Stack2<Character> stack = new Stack2<>();

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {  // 右括号
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char left = stack.pop();
                    if (left == '(' && c != ')') {
                        return false;
                    }
                    if (left == '{' && c != '}') {
                        return false;
                    }
                    if (left == '[' && c != ']') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

}
