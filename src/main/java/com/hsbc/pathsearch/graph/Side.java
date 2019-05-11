package com.hsbc.pathsearch.graph;

import java.util.Objects;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/10 下午8:43
 */
public class Side<E> {
    private E start;
    private E end;
    private int weight;

    public Side(E start, E end) {
        this(start, end, 0);
    }

    public Side(E start, E end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Side<E> reverse() {
        E tmp = this.start;
        this.start = this.end;
        this.end = tmp;
        return this;
    }

    public boolean contains(E e) {
        return this.start.equals(e) || this.end.equals(e);
    }

    public E getStart() {
        return this.start;
    }

    public void setStart(E e) {
        this.start = e;
    }

    public E getEnd() {
        return this.end;
    }

    public void setEnd(E e) {
        this.end = e;
    }

    public int getWeight() {
        return this.weight;
    }

    public void set(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Side<?> side = (Side<?>) o;

        return weight == side.weight &&
                start.equals(side.start) &&
                end.equals(side.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Side{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
