package com.hsbc.pathsearch.graph;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/10 下午10:06
 */
public class DirectGraphTest {

    private DirectGraph<String> maps = new DirectGraph<>();

    @Test
    public void addPoint() {
        maps.addPoint("A");
        maps.addPoint("B");
        maps.addPoint("C");
        maps.addPoint("D");
        maps.addPoint("E");
        maps.addPoint("F");
        Assert.assertEquals(maps.getPn(), 6);
    }

    @Test
    public void removePoint() {
        addSide();
        maps.removePoint("A");
        System.out.println(maps);
        Assert.assertEquals(5, maps.getPn());
        Assert.assertEquals(6, maps.getSn());
    }

    @Test
    public void addSide() {
        addPoint();
        maps.addSide(new Side<>("A", "B", 2));
        maps.addSide(new Side<>("B", "C", 3));
        maps.addSide(new Side<>("C", "D", 2));
        maps.addSide(new Side<>("D", "E", 3));
        maps.addSide(new Side<>("E", "A", 2));
        maps.addSide(new Side<>("C", "F", 1));
        maps.addSide(new Side<>("E", "F", 2));
        maps.addSide(new Side<>("F", "B", 2));
        maps.addSide(new Side<>("F", "A", 1));
        System.out.println(maps);
        Assert.assertEquals(maps.getSn(), 9);
    }

    @Test
    public void getPn() {
        addSide();
        Assert.assertEquals(6, maps.getPn());
    }

    @Test
    public void getSn() {
        addSide();
        Assert.assertEquals(9, maps.getSn());
    }

    @Test
    public void search() {
    }
}