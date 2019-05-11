package com.hsbc.pathsearch.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Function: Reporter Test
 *
 * @author zhangsunjiankun - 2019/5/11 下午1:43
 */
public class ReporterTest {

    private Reporter<String> reporter = new Reporter<>();
    private DirectedGraph<String> maps = new DirectedGraph<>();

    @Before
    public void initMap() {
        // add points 7
        maps.addPoint("A");
        maps.addPoint("B");
        maps.addPoint("C");
        maps.addPoint("D");
        maps.addPoint("E");
        maps.addPoint("F");
        maps.addPoint("G");

        // add sides 15
        maps.addSide(new Side<>("A", "B", 2));
        maps.addSide(new Side<>("B", "C", 3));
        maps.addSide(new Side<>("B", "E", 3));
        maps.addSide(new Side<>("C", "D", 2));
        maps.addSide(new Side<>("D", "E", 3));
        maps.addSide(new Side<>("E", "A", 2));
        maps.addSide(new Side<>("C", "F", 1));
        maps.addSide(new Side<>("E", "F", 2));
        maps.addSide(new Side<>("F", "B", 2));
        maps.addSide(new Side<>("F", "A", 4));
        maps.addSide(new Side<>("F", "D", 1));
        maps.addSide(new Side<>("C", "G", 2));
        maps.addSide(new Side<>("G", "A", 1));
        maps.addSide(new Side<>("G", "F", 3));
        maps.addSide(new Side<>("A", "D", 4));
        System.out.println(maps);
    }

    @Test
    public void push() {
        reporter.push(new Side<>("A", "B", 3));
        reporter.push(new Side<>("B", "C", 4));
        reporter.push(new Side<>("C", "D", 6));
        reporter.push(new Side<>("D", "A", 12));
        reporter.push(new Side<>("C", "A", 9));
        System.out.println(reporter);
        Assert.assertEquals(reporter.pop().getEnd(), "D");
    }

    @Test
    public void pop() {
        push();
        assert reporter.pop().getEnd().equals("C");
    }

    @Test
    public void record() {
        push();
        reporter.record();
        assert reporter.pop().getEnd().equals("B");
    }

    @Test
    public void getPaths() {
        Reporter<String> result = maps.search("A", "D");
        List<String> paths = result.getPaths();
        System.out.println(paths);
        Assert.assertEquals(paths.size(), 5);
    }

    @Test
    public void getMinPath() {
        Reporter<String> result = maps.search("A", "D");
        String minPath = result.getMinPath();
        System.out.println(minPath);
    }

    @Test
    public void getMaxPath() {
        Reporter<String> result = maps.search("A", "D");
        String maxPath = result.getMaxPath();
        System.out.println(maxPath);
    }
}