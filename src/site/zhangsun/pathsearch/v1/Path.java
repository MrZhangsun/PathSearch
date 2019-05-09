package site.zhangsun.pathsearch.v1;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/8 下午10:25
 */
public class Path {

    private Record root;
    private Record currentNode;

    public Path(Node root) {
        this.root = new Record(root.getName());
        this.currentNode = this.root;
    }

    public void append(int weight, Node next) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid nodes distance");
        }
        if (next == null) {
            throw new NullPointerException("node can't be null");
        }
        currentNode.next = new Record(next.getName(), weight);
        currentNode = currentNode.next;
    }

    /**
     * Clear current node chain to initial value
     */
    public void clear() {
        root.next = null;
        this.currentNode = this.root;
    }

    static class Record {
        public Record(String name, int weight, Record next) {
            this.name = name;
            this.weight = weight;
            this.next = next;
        }

        public Record(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public Record(String name) {
            this.name = name;
        }

        private String name;
        private int weight;
        private Record next;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Record getNext() {
            return next;
        }

        public void setNext(Record next) {
            this.next = next;
        }
    }
}
