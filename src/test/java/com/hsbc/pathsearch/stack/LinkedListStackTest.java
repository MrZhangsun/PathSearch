package com.hsbc.pathsearch.stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/11 上午11:30
 */
public class LinkedListStackTest {

    private Stack<String> stack = new LinkedListStack<>();

    @Test
    public void push() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");
        System.out.println(stack);
        assert stack.size() == 5;
    }

    @Test
    public void pop() {
        push();
        String p1 = stack.pop();
        System.out.println(stack);
        assert p1.equals("E");
        String p2 = stack.pop();
        System.out.println(stack);
        assert p2.equals("D");
    }

    @Test
    public void peek() {
    }

    @Test
    public void size() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void contains() {
    }

    @Test
    public void copy() {
    }

    @Test
    public void get() {
    }
}