public class Node {
    Vertex vertex;
    Double destance;
    double weight;//weight between the vertex ant its adjacent
    Node next;

    public Node() {
       next=null;
       destance=Double.MAX_VALUE;
    }

    public Node(Vertex vertex, Double destance) {
        this.vertex = vertex;
        this.destance = destance;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public Double getDestance() {
        return destance;
    }

    public void setDestance(Double destance) {
        this.destance = destance;
    }
}
