package site.zhangsun.pathsearch.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: Node means search point.
 *
 * @author zhangsunjiankun - 2019/5/8 下午7:41
 */
public class Node {

    public Node(String name, List<Vector> vectors) {
        this.name = name;
        this.vectors = vectors;
    }

    public Node(String name) {
        this.name = name;
        this.vectors = new ArrayList<>(6);
    }

    /**
     * Node Name
     */
    private String name;

    /**
     * Vector List
     */
    private List<Vector> vectors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vector> getVectors() {
        if (this.vectors == null) {
            this.vectors = new ArrayList<>(6);
        }
        return vectors;
    }

    public void setVectors(List<Vector> vectors) {
        this.vectors = vectors;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", vectors=" + vectors +
                '}';
    }

    /**
     * Vector is a object used to describe path direction and next node.
     */
    public static class Vector {

        public Vector(int side, Node header) {
            this.side = side;
            this.header = header;
        }

        /**
         * the weight between two nodes
         */
        private int side;

        /**
         * the header of the vector
         */
        private Node header;

        public int getSide() {
            return side;
        }

        public void setSide(int side) {
            this.side = side;
        }

        public Node getHeader() {
            return header;
        }

        public void setHeader(Node header) {
            this.header = header;
        }

        @Override
        public String toString() {
            return "Vector{" +
                    "side=" + side +
                    ", header=" + header +
                    '}';
        }
    }

}
