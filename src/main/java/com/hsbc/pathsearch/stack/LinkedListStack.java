package com.hsbc.pathsearch.stack;


/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/10 下午9:31
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public Stack<E> copy() {
        Stack<E> copy = new LinkedListStack<>();
        for (int i = 0; i < linkedList.size(); i++)
            copy.push(linkedList.get(i));
        return copy;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > linkedList.size())
            throw new IndexOutOfBoundsException("illegal index: " + index);

        return linkedList.get(index);
    }

    @Override
    public String toString() {
        // TODO rewrite
        return linkedList.toString();
    }
}
