package com.hsbc.pathsearch.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/10 下午8:53
 */
public class Sides<E> {
    private List<Side<E>> inSides;
    private List<Side<E>> outSides;

    public Sides() {
        this.inSides = new ArrayList<Side<E>>(16);
        this.outSides = new ArrayList<Side<E>>(16);
    }

    public List<Side<E>> getInSides() {
        return this.inSides;
    }

    public List<Side<E>> getOutSides() {
        return this.outSides;
    }

    @Override
    public String toString() {
        return "Sides{" +
                "inSides=" + inSides +
                ", outSides=" + outSides +
                '}';
    }
}
