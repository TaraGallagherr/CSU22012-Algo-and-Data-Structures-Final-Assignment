

public class Edge {
    private double weight;
    private ConnectionNode startNode;
    private ConnectionNode targetNode;

    public Edge(double weight, ConnectionNode startNode, ConnectionNode targetNode) {

        this.weight = weight; //weight is representative of the distance of an edge
        this.startNode = startNode;
        this.targetNode = targetNode;
    }

    //setters and getters for the weight, startNodes and the goal/target nodes

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ConnectionNode getStartNode() {
        return startNode;
    }

    public void setStartNode(ConnectionNode startNode) {
        this.startNode = startNode;
    }

    public ConnectionNode getGoalNode() {
        return targetNode;
    }

    public void setGoalNode(ConnectionNode targetNode) {
        this.targetNode = targetNode;
    }
}

