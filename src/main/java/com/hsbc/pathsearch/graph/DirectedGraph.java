package com.hsbc.pathsearch.graph;

import java.util.*;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/10 下午7:53
 */
public class DirectedGraph<E> {

    /**
     * Total count of the points in the map
     */
    private int pn;

    /**
     * Total count of the sides in the map
     */
    private int sn;

    /**
     * Map used to build points relation ship
     */
    private Map<E, Sides<E>> maps;

    public DirectedGraph() {
        this.pn = 0;
        this.sn = 0;
        maps = new HashMap<>(6);
    }

    /**
     * Add a point into the map
     *
     * @param point point
     */
    public void addPoint(E point) {
        Sides<E> sides = maps.get(point);
        if (sides != null)
            throw new IllegalArgumentException("This point: "+ point +" you want to add has been existed");
        maps.put(point, new Sides<E>());
        pn++;
    }

    public void removePoint(E point) {
        // Remove this point in both in-sides and out-sides
        Sides<E> sides = maps.get(point);

        // Remove in sides
        List<Side<E>> inSides = sides.getInSides();
        Iterator<Side<E>> inSidesIterator = inSides.iterator();
        while (inSidesIterator.hasNext()) {
            Side<E> next = inSidesIterator.next();
            E start = next.getStart();
            Sides<E> peerSides = maps.get(start);
            List<Side<E>> peerOutSides = peerSides.getOutSides();
            // Remove this side from peer out-sides
            if (peerOutSides.contains(next)) {
                peerOutSides.remove(next);
                sn--;
            }
        }

        // Remove out sides
        List<Side<E>> outSides = sides.getOutSides();
        Iterator<Side<E>> outSidesIterator = outSides.iterator();
        while (outSidesIterator.hasNext()) {
            Side<E> next = outSidesIterator.next();
            E end = next.getEnd();
            Sides<E> peerSides = maps.get(end);
            List<Side<E>> peerInSides = peerSides.getInSides();

            // Remove the side from peer in-sides
            if(peerInSides.contains(next)) {
                peerInSides.remove(next);
                sn--;
            }
        }

        // Remove this node
        maps.remove(point);
        pn--;
    }

    /**
     * Add side between two points
     *
     * @param side side
     */
    public void addSide(Side<E> side) {
        E start = side.getStart();
        E end = side.getEnd();
        Sides<E> startSides = maps.get(start);
        Sides<E> endSides = maps.get(end);
        if (startSides == null)
            throw new IllegalArgumentException("The start point: "+ start +" doesn't exist in the map");
        if (endSides == null)
            throw new IllegalArgumentException("The end point: "+ end +" doesn't exist in the map");
        if (start.equals(end))
            throw new IllegalArgumentException("The start point is the same with end point");

        // Before add the side, check the existence of reverse side
        List<Side<E>> endInSides = endSides.getInSides();
        if (endInSides.contains(side))
            throw new IllegalStateException("This side from "+ end + "to " + start + " has been existed");

        // If the side start -> end doesn't exist, then add
        List<Side<E>> startOutSides = startSides.getOutSides();
        if (!startOutSides.contains(side)) {
            startOutSides.add(side);
            sn++;
        }

        // maintain the end point's in-Sides
        endInSides.add(side);
    }

    public int getPn() {
        return pn;
    }

    public int getSn() {
        return sn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        maps.forEach((k, v) -> {
            sb.append(k);
            sb.append(", ").append(v);
            sb.append("\r\n");
        });

        sb.append("total points: ").append(pn).append(", ");
        sb.append("total sides: ").append(sn);
        return sb.toString();
    }

    /**
     * Search routes from point to point
     *
     * @param start start point
     * @param end end point
     * @return search report
     */
    public Reporter<E> search(E start, E end) {
        Reporter<E> reporter = new Reporter<>();
        Sides<E> startSides = maps.get(start);
        Sides<E> endSides = maps.get(end);
        if (startSides == null)
            throw new IllegalArgumentException("Given start point: "+ start +" doesn't exist in the map");
        if (endSides == null)
            throw new IllegalArgumentException("Given end point: "+ end +" doesn't exist in the map");
        if (start.equals(end))
            throw new IllegalArgumentException("Start point is the same with End point");

        List<Side<E>> startOutSides = startSides.getOutSides();
        if (startOutSides.isEmpty())
            return reporter;

        return search(startOutSides, end, reporter);
    }

    /**
     * Search routes from point to point
     *
     * @param startOutSides start point's out-sides
     * @param aim aim point
     * @param reporter search result
     * @return search report
     */
    private Reporter<E> search(List<Side<E>> startOutSides, E aim, Reporter<E> reporter) {
        startOutSides.forEach(side -> {
            // avoid loop directional ring
            if (!reporter.push(side))
                return;

            // find one path
            E end = side.getEnd();
            if (aim.equals(end)) {
                reporter.record();
                return;
            }

            // if meet end point, stack pop
            Sides<E> sides = maps.get(end);
            List<Side<E>> outSides = sides.getOutSides();
            if (outSides.isEmpty()) {
                reporter.pop();
                return;
            }

            search(outSides, aim, reporter);
            // after all the sides are searched, pop super side
            reporter.pop();
        });
        return reporter;
    }

    public Reporter<E> search(E start, E end, int limit, int size) {
        Reporter<E> reporter = new Reporter<>();
        Sides<E> startSides = maps.get(start);
        Sides<E> endSides = maps.get(end);
        if (startSides == null)
            throw new IllegalArgumentException("Given start point: "+ start +" doesn't exist in the map");
        if (endSides == null)
            throw new IllegalArgumentException("Given end point: "+ end +" doesn't exist in the map");

        List<Side<E>> startOutSides = startSides.getOutSides();
        if (startOutSides.isEmpty())
            return reporter;
        return search(startOutSides, end, reporter, limit, size);
    }

    /**
     * Search routes from point to point
     *
     * @param startOutSides start point's out-sides
     * @param aim aim point
     * @param reporter search result
     * @return search report
     */
    private Reporter<E> search(List<Side<E>> startOutSides, E aim, Reporter<E> reporter, int limit, int size) {
        startOutSides.forEach(side -> {
            if (!reporter.push(side, limit, size))
                return;

            // find one path
            E end = side.getEnd();
            if (aim.equals(end)) {
                reporter.record();
            }

            // if meet end point, stack pop
            Sides<E> sides = maps.get(end);
            List<Side<E>> outSides = sides.getOutSides();
            if (outSides.isEmpty()) {
                reporter.pop();
                return;
            }

            search(outSides, aim, reporter, limit, size);
            // after all the sides are searched, pop super side
            reporter.pop();
        });
        return reporter;
    }

    /**
     * Calculate route distance
     *
     * @param nodes nodes
     * @return route distance
     */
    public String calculateDistance(E ... nodes) {
        int result = 0;
        List<Boolean> finds = new ArrayList<>(nodes.length);
        for (int i = 0; i < nodes.length - 1; i++) {
            Sides<E> sides = maps.get(nodes[i]);
            List<Side<E>> outSides = sides.getOutSides();
            for (Side<E> outSide : outSides) {
                E end = outSide.getEnd();
                if (end.equals(nodes[i + 1])) {
                    finds.add(true);
                    result += outSide.getWeight();
                }
            }

        }

        if (finds.size() != nodes.length - 1)
            return "NO_SUCH_ROUTE";
        else
            return String.valueOf(result);
    }

    /**
     * Detect loop rings from given start point
     *
     * @param start start point
     * @return search report
     */
    public Reporter<E> detectLoopRings(E start) {
        Reporter<E> reporter = new Reporter<>();
        Sides<E> sides = maps.get(start);
        if (sides == null)
            throw new IllegalArgumentException("Given start point: "+ start +" doesn't exist in the map");

        List<Side<E>> outSides = sides.getOutSides();
        if (outSides.isEmpty())
            return reporter;

        return search(outSides, start, reporter);
    }


}
