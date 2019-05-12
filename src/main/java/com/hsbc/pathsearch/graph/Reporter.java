package com.hsbc.pathsearch.graph;

import com.hsbc.pathsearch.stack.LinkedListStack;
import com.hsbc.pathsearch.stack.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function: Map Search Reporter
 *
 * @author zhangsunjiankun - 2019/5/10 下午9:29
 */
public class Reporter<E> {

    private Stack<Side<E>> pathStack;
    private List<Stack<Side<E>>> paths;

    public Reporter() {
        pathStack = new LinkedListStack<>();
        paths = new ArrayList<>(16);
    }

    public boolean push(Side<E> side) {
        for (int i = 0; i < pathStack.size(); i++) {
            Side<E> pushedSide = pathStack.get(i);
            if (pushedSide.contains(side.getEnd()))
                return false;
        }

        pathStack.push(side);
        curTotalWeights += side.getWeight();
        return true;
    }

    public Side pop() {
        Side<E> side = pathStack.pop();
        curTotalWeights -= side.getWeight();
        return side;
    }

    public void record() {
        paths.add(pathStack.copy());
    }

    public List<String> getPaths() {
        if (paths.isEmpty())
            return null;

        List<String> ps = new ArrayList<>(paths.size());

        for (Stack<Side<E>> stack : paths) {
            ps.add(printStack(stack));
        }
        return ps;
    }

    private Map<Integer, Integer> statistics() {
        Map<Integer, Integer> statistics = new HashMap<>(paths.size());
        int totalWeight;
        for (int i = 0; i < paths.size(); i++) {
            totalWeight = 0;
            Stack<Side<E>> sideStack = paths.get(i);
            for (int j = 0; j < sideStack.size(); j++) {
                Side<E> side = sideStack.get(j);
                totalWeight += side.getWeight();
            }

            statistics.put(i, totalWeight);
        }

        return statistics;
    }

    /**
     * Get the min short path, return the first
     *
     * @return path stack
     */
    public String getMinPath() {
        Map<Integer, Integer> statistics = statistics();
        int minKey = 0;
        int minVal = statistics.get(minKey);
        for (int i = 1; i < statistics.size(); i++) {
            int v = statistics.get(i);
            if (v < minVal) {
                minKey = i;
                minVal = v;
            }
        }

        return printStack(paths.get(minKey));
    }

    /**
     * Get the max path, if equals, return the first
     *
     * @return path stack
     */
    public String getMaxPath() {
        Map<Integer, Integer> statistics = statistics();
        int maxKey = 0;
        int maxVal = statistics.get(maxKey);
        for (int i = 1; i < statistics.size(); i++) {
            int v = statistics.get(i);
            if (v > maxVal) {
                maxKey = i;
                maxVal = v;
            }
        }

        return printStack(paths.get(maxKey));
    }

    private String printStack(Stack<Side<E>> stack) {
        StringBuilder sb = new StringBuilder();
        int totalWeight = 0;
        final int size = stack.size();
        sb.append("Path: [");
        for (int j = 0; j < size; j++) {
            Side<E> side = stack.pop();
            totalWeight += side.getWeight();
            if (j == 0) {
                sb.append(side.getStart()).append("--").append(side.getWeight()).append("-->").append(side.getEnd());
                continue;
            }

            sb.append("--").append(side.getWeight()).append("-->").append(side.getEnd());
        }

        sb.append("]");
        sb.append(" Weight: [").append(totalWeight).append("]\r\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Reporter{" +
                "pathStack=" + pathStack +
                ", paths=" + paths +
                '}';
    }

    private int curTotalWeights;
    public boolean push(Side<E> side, int weight, int size) {
        if (weight != -1) {
            if (weight < (curTotalWeights) + side.getWeight())
                return false;
        }

        if (size != -1) {
            if (size < (pathStack.size() + 1))
                return false;
        }

        pathStack.push(side);
        curTotalWeights += side.getWeight();
        return true;
    }
}
