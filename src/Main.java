import site.zhangsun.pathsearch.v1.*;

import java.util.HashMap;
import java.util.List;
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
            Node d = new Node("D");

            Node.Vector ab = new Node.Vector(10, b);
            Node.Vector ac = new Node.Vector(13, c);
            Node.Vector bc = new Node.Vector(18, c);
            Node.Vector ad = new Node.Vector(20, d);
            Node.Vector db = new Node.Vector(14, b);

            /*
             * a ---> b
             * a ---> c
             * b ---> c
             */
            a.getVectors().add(ab);
            a.getVectors().add(ac);
            b.getVectors().add(bc);
            d.getVectors().add(ad);
            b.getVectors().add(db);

            map.put(a.getName(), a);
            map.put(b.getName(), b);
            map.put(c.getName(), c);
            map.put(d.getName(), d);
            return map;
        };

        NodeMap nodeMap = new NodeMap(initialMap);
        Reporter reporter = nodeMap.search("A", "C");
        List<Path> paths = reporter.avaliablePaths();
        System.out.println(paths.size());
    }
}
