package site.zhangsun.pathsearch;

import java.util.List;
import java.util.Map;

/**
 * Function: Node Map.
 *
 * @author zhangsunjiankun - 2019/5/8 下午7:41
 */
public class NodeMap {

    /**
     * Map to store nodes
     */
    private Map<String, Node> nodes;

    public NodeMap(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    public NodeMap(InitialMap map) {
        this.nodes = map.initialize();
    }

    /**
     * Search Paths from start node to end node
     *
     * @param start start node
     * @param end end node
     * @return search report
     */
    public Reporter search(String start, String end) {
        if (start == null || end == null) {
            throw new NullPointerException("Empty Node Name");
        }

        Node startNode = this.nodes.get(start);
        Node endNode = this.nodes.get(end);
        if (startNode == null || endNode == null) {
            throw new NullPointerException("Node doesn't exist in the map");
        }

        List<Node.Vector> vectors = startNode.getVectors();
        if (vectors.isEmpty()) {
            throw new IllegalStateException("Single Node");
        }
        Reporter reporter = new Reporter(startNode);
        return search(vectors, end, reporter);
    }

    /**
     * Search paths and record paths to report
     *
     * @param vectors start node vectors list
     * @param end destination node name
     * @param reporter reporter
     * @return reporter
     */
    private Reporter search(List<Node.Vector> vectors, String end, Reporter reporter) {

        for (Node.Vector vector : vectors) {
            Node header = vector.getHeader();
            // record node chain
            reporter.record(vector);
            List<Node.Vector> vs = header.getVectors();
            if (vs.isEmpty()) {
                // Can not find valid path
                reporter.clear();
                continue;
            }

            String name = header.getName();
            if (end.equalsIgnoreCase(name)) {
                // Find one, add it to reporter
                reporter.count();
                continue;
            }

            search(vs, end, reporter);
        }

        return reporter;
    }

}
