package com.hsbc.pathsearch.graph;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/10 下午10:06
 */
public class DirectedGraphTest {

    private DirectedGraph<String> maps = new DirectedGraph<>();

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
        maps.addSide(new Side<>("F", "D", 1));

        System.out.println(maps);
        Assert.assertEquals(maps.getSn(), 10);
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
        addSide();
        Reporter<String> reporter = maps.search("A", "D");
        List<String> paths = reporter.getPaths();
        paths.forEach(System.out::println);
        Assert.assertEquals(paths.size(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchSamePoint() {
        addSide();
        Reporter<String> reporter = maps.search("A", "A");
        List<String> paths = reporter.getPaths();
        paths.forEach(System.out::println);
    }

    @Test
    public void calculateDistance() {
        addSide();
        String s = maps.calculateDistance("A", "B", "C");
        System.out.println(s);
        Assert.assertEquals(s, "5");
    }

    @Test
    public void detectLoopRings() {
        addSide();
        Reporter<String> reporter = maps.detectLoopRings("A");
        List<String> paths = reporter.getPaths();
        System.out.println(paths);
    }
}