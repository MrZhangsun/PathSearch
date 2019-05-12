package com.hsbc.pathsearch;

import com.hsbc.pathsearch.graph.DirectedGraph;
import com.hsbc.pathsearch.graph.Reporter;
import com.hsbc.pathsearch.graph.Side;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/11 下午10:00
 */
public class AnswerTestCases {

    private Reporter<String> reporter = new Reporter<>();
    private DirectedGraph<String> maps = new DirectedGraph<>();

    @Before
    public void initMap() {
        /*
         * A B C D E (5)
         */
        maps.addPoint("A");
        maps.addPoint("B");
        maps.addPoint("C");
        maps.addPoint("D");
        maps.addPoint("E");

        /*
         * Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7 (9)
         */
        maps.addSide(new Side<>("A", "B", 5));

        maps.addSide(new Side<>("B", "C", 4));

        maps.addSide(new Side<>("C", "D", 8));

        maps.addSide(new Side<>("D", "C", 8));

        maps.addSide(new Side<>("D", "E", 6));

        maps.addSide(new Side<>("A", "D", 5));

        maps.addSide(new Side<>("C", "E", 2));

        maps.addSide(new Side<>("E", "B", 3));

        maps.addSide(new Side<>("A", "E", 7));

        System.out.println("Loaded the map: \r\n" + maps);
    }

    /**
     * 1.The distance of the route A-B-C.
     */
    @Test
    public void question1Test() {
        String distance = maps.calculateDistance("A", "B", "C");
        System.out.println("The distance of the route A-B-C: " + distance);
        Assert.assertEquals(distance, "9");
    }
     /**
     * 1.The distance of the route A-D.
     */
    @Test
    public void question2Test() {
        String distance = maps.calculateDistance("A", "D");
        System.out.println("The distance of the route A-D: " + distance);
        Assert.assertEquals(distance, "5");
    }
    /**
     * 1.The distance of the route A-D-C.
     */
    @Test
    public void question3Test() {
        String distance = maps.calculateDistance("A", "D", "C");
        System.out.println("The distance of the route A-D-C: " + distance);
        Assert.assertEquals(distance, "13");
    }
    /**
     * 1.The distance of the route A-E-B-C-D.
     */
    @Test
    public void question4Test() {
        String distance = maps.calculateDistance("A", "E", "B", "C", "D");
        System.out.println("The distance of the route A-E-B-C-D: " + distance);
        Assert.assertEquals(distance, "22");
    }
    /**
     * 1.The distance of the route A-B-C.
     */
    @Test
    public void question5Test() {
        String distance = maps.calculateDistance("A", "E", "D");
        System.out.println("The distance of the route A-E-D: " + distance);
        Assert.assertEquals(distance, "NO_SUCH_ROUTE");
    }

    /**
     * 6.The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below,
     * there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
     */
    @Test
    public void test6() {
        Reporter<String> search = maps.search("C", "C", -1, 3);
        List<String> paths = search.getPaths();
        System.out.println(paths);
        Assert.assertEquals(2, paths.size());
    }

    /**
     * 7.The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below,
     * there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
     */
    @Test
    public void question7Test() {
        List<String> paths = maps.search("A", "C", -1, 4, true);
        System.out.println(paths);
        Assert.assertEquals(paths.size(), 3);
    }

    /**
     *  8.The length of the shortest route (in terms of distance to travel) from A to C.
     */
    @Test
    public void test8() {
        Reporter<String> reporter = maps.search("A", "C");
        String minPath = reporter.getMinPath();
        System.out.println(minPath);
        Assert.assertEquals("Path: [A--5-->B--4-->C] Weight: [9]\r\n", minPath);
    }


}
