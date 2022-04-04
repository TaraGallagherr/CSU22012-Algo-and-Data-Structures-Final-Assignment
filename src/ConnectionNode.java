import java.util.ArrayList;
import java.util.List;


    public class ConnectionNode  {
        public String name;
        private List<Edge> edges;
        private double minDist = Double.MAX_VALUE;       //constant holding largesr possible value of type double
                                                        // will be updated to become smaller as smaller distances found

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

    }


