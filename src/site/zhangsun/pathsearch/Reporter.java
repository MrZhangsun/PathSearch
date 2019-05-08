package site.zhangsun.pathsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: Path Search Reporter.
 *
 * @author zhangsunjiankun - 2019/5/8 下午9:10
 */
public class Reporter {

    /**
     * Path used to track valid path
     */
    private Path path;

    /**
     * Valid paths list
     */
    private List<Path> paths;

    /**
     * Initialize root and paths list
     *
     * @param root start node
     */
    public Reporter(Node root) {
        this.path = new Path(root);
        this.paths = new ArrayList<>(16);
    }

    /**
     * Calculate how many paths from the start node to end node.
     */
    public List<Path> avaliablePaths() {
        return paths;
    }

    public void theMinPath() {

    }

    public void theMaxPath() {

    }

    /**
     * Record the node chain
     *
     * @param vector current vector
     */
    public void record(Node.Vector vector) {
        int weight = vector.getSide();
        Node header = vector.getHeader();
        path.append(weight, header);
    }

    /**
     * Clear records chain
     */
    public void clear() {
        path.clear();
    }

    /**
     * Find a valid path, count it to valid path list
     */
    public void count() {
        paths.add(path);
        clear();
    }



}
