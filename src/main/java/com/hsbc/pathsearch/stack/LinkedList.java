package com.hsbc.pathsearch.stack;

/**
 * Function: Linked List
 *
 * @author zhangsunjiankun - 2019/5/10 下午7:52
 */
public class LinkedList<E> {

    /**
     * dummy header doesn't store any element
     */
    private Node<E> dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node<>(null, null);
        size = 0;
    }

    /**
     * Get linked list size
     *
     * @return linked list's size
     */
    public int size() {
        return size;
    }

    /**
     * Is empty
     *
     * @return empty: true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add an element to linked list
     *
     * @param index position of linked list
     * @param e element
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("illegal index: " + index);

        Node<E> preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            // head = dummy head.next
            preNode = preNode.next;
        }

        preNode.next = new Node<>(e, preNode.next);
        size++;
    }

    /**
     * Add element at the first of the linked list
     *
     * @param e element
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * Add element at the end of the linked list
     *
     * @param e element
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * Get an element by index in the linked list
     *
     * @param index index in the linked list
     * @return element
     */
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("illegal index: " + index);
        Node<E> curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.e;
    }

    /**
     * Get first element of the linked list
     *
     * @return element
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * Get last element of the linked list
     *
     * @return element
     */
    public E getLast() {
        return get(size);
    }

    /**
     * Update element by index in the linked list
     *
     * @param index index in the linked list
     * @param e element
     */
    public void set(int index, E e) {
        Node<E> curNode = dummyHead.next;
        for (int i = 0; i < index; i++)
            curNode = curNode.next;

        curNode.e = e;
    }

    /**
     * Update the first element of the linked list
     *
     * @param e new element
     */
    public void setFirst(E e) {
        set(0, e);
    }

     /**
     * Update the last element of the linked list
     *
     * @param e new element
     */
    public void setLast(E e) {
        set(size, e);
    }

    /**
     * Check the existence of the given element
     *
     * @param e the given element
     * @return existence of the given element
     */
    public boolean contains(E e) {
        Node<E> curNode = dummyHead;
        while (curNode != null) {
            if (e.equals(curNode.e))
                return true;
            curNode = curNode.next;
        }
        return false;
    }

    /**
     * Remove an element from the linked list
     *
     * @param index position in the linked list
     * @return deleted element
     */
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("illegal index: " + index);
        Node<E> preNode = dummyHead;
        for (int i = 0; i < index; i++)
            preNode = preNode.next;

        Node<E> delNode = preNode.next;
        preNode.next = delNode.next;
        size--;
        return delNode.e;
    }

    /**
     * Remove first element from the linked list
     *
     * @return deleted element
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Remove last element from the linked list
     *
     * @return deleted element
     */
    public E removeLast() {
        return remove(size);
    }

    @Override
    public String toString() {
        Node<E> curNode = dummyHead.next;
        StringBuilder builder = new StringBuilder();
        builder.append("Head->");
        while (curNode != null) {
            builder.append(curNode.e).append("->");
            curNode = curNode.next;
        }
        builder.append("NULL");
        return builder.toString();
    }

    private class Node<E> {
        private E e;
        private Node<E> next;

        public Node(E e, Node<E> next) {
            this.next = next;
            this.e = e;
        }
        public Node(E e) {
            this(e, null);
        }

        public Node() {}

        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String toString() {
            if (e == null)
                return null;
            return this.e.toString();
        }
    }
}
