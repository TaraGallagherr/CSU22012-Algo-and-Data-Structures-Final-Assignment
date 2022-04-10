import java.util.ArrayList;
import java.util.List;


    public class ConnectionNode implements Comparable<ConnectionNode> {
        public String name;
        private List<Edge> edges;
        //initialising all distances of nodes as infinite
        private double minDist = Double.MAX_VALUE;
        private ConnectionNode prevNode;

        public ConnectionNode(String name) {
            this.name = name;   //give the name of the node
            this.edges = new ArrayList<>();
        }

        public void addEdgeBeside(Edge edge) {
            this.edges.add(edge);
        }

        public List<Edge> getEdgesList() {
            return edges;
        }

        public void setEdgesList(List<Edge> edges) {
            this.edges = edges;
        }

        public double getMinDist() {
            return minDist;
        }

        public void setMinDist(double minDist) {
            this.minDist = minDist;
        }

        @Override
        public String toString() {
            return name;
        }

        public void setPrevNode(ConnectionNode prevNode){
            this.prevNode = prevNode;
        }

        public ConnectionNode getPrevNode(){
            return prevNode;
        }

        @Override
        public int compareTo(ConnectionNode otherNode) {
            return Double.compare(this.minDist, otherNode.minDist);
        }

    }


