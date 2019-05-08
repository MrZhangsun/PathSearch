import site.zhangsun.pathsearch.InitialMap;
import site.zhangsun.pathsearch.Node;
import site.zhangsun.pathsearch.NodeMap;
import site.zhangsun.pathsearch.Reporter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murphy Zhang Sun
 */
public class Main {

    public static void main(String[] args) {
        InitialMap initialMap = () -> {
            Map<String, Node> map = new HashMap<>(3);
            Node a = new Node("A");
            Node b = new Node("B");
            Node c = new Node("C");

            Node.Vector ab = new Node.Vector(10, b);
            Node.Vector ac = new Node.Vector(13, c);
            Node.Vector bc = new Node.Vector(18, c);

            /*
             * a ---> b
             * a ---> c
             * b ---> c
             */
            a.getVectors().add(ab);
            a.getVectors().add(ac);
            b.getVectors().add(bc);

            map.put(a.getName(), a);
            map.put(b.getName(), b);
            map.put(c.getName(), c);
            return map;
        };

        NodeMap nodeMap = new NodeMap(initialMap);
        Reporter reporter = nodeMap.search("A", "C");

    }
}
