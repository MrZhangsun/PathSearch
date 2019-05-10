package com.hsbc.pathsearch.stack;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/10 下午8:36
 */
public interface Stack<E> {

    /**
     * Push an element in to the stack
     *
     * @param e element
     */
    void push(E e);

    /**
     * Pop an element from the top of the stack
     *
     * @return element
     */
    E pop();

    /**
     * View the first element in the stack
     *
     * @return element
     */
    E peek();

    /**
     * How many elements in the stack
     *
     * @return size
     */
    int size();

    /**
     * Is the stack empty?
     *
     * @return empty: true
     */
    boolean isEmpty();

    /**
     * The existence of the given element
     *
     * @param e given element
     * @return existence: true
     */
    boolean contains(E e);

    /**
     * Copy current stack information to another stack
     *
     * @return new stack
     */
    Stack<E> copy();

    /**
     * Check the element by index
     *
     * @param index position in the stack
     * @return element
     */
    E get(int index);
}
