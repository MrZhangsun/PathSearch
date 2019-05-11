package com.hsbc.pathsearch.graph;

import com.hsbc.pathsearch.stack.LinkedListStack;
import com.hsbc.pathsearch.stack.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function:
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
        if (pathStack.contains(side))
            return false;

        pathStack.push(side);
        return true;
    }

    public Side pop() {
        return pathStack.pop();
    }

    public void record() {
        paths.add(pathStack.copy());
        pop();
    }

    public List<String> getPaths() {
        if (paths.isEmpty())
            return null;

        List<String> ps = new ArrayList<>(paths.size());

        for (Stack<Side<E>> stack : paths) {
            StringBuilder sb = new StringBuilder();
            final int size = stack.size();
            for (int j = 0; j < size; j++) {
                Side<E> side = stack.pop();
                if (j == 0) {
                    sb.append(side.getStart()).append("--").append(side.getWeight()).append("-->").append(side.getEnd());
                    continue;
                }
                sb.append("--").append(side.getWeight()).append("-->").append(side.getEnd());
            }
            ps.add(sb.toString());
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

    public Stack<Side<E>> getMinPath() {
        Map<Integer, Integer> statistics = statistics();
        final Integer[] min = {0};
        final Integer[] minKey = new Integer[1];
        statistics.forEach((k, v) -> {
            if (v < min[0]) {
                minKey[0] = k;
                min[0] = v;
            }
        });

        return paths.get(minKey[0]);
    }

    public Stack<Side<E>> getMaxPath() {
        Map<Integer, Integer> statistics = statistics();
        final Integer[] max = {0};
        final Integer[] maxKey = new Integer[1];
        statistics.forEach((k, v) -> {
            if (v > max[0]) {
                maxKey[0] = k;
                max[0] = v;
            }
        });

        return paths.get(maxKey[0]);
    }
}
