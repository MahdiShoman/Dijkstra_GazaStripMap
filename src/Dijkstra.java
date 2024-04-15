/*
import java.util.PriorityQueue;

public class Dijkstra {
    public static void main(String[] args) {
        gg();
    }
static void gg(){
    PriorityQueue<Vertex> g= new PriorityQueue<>();
    Vertex v1 = new Vertex("g",false,12.5);
    Vertex v2 = new Vertex("f",false,10.5);

    g.add(v1);
    g.add(v2);
    System.out.println(g.peek());
}
}
*/
import java.util.*;

public class Dijkstra {
    static class Vertex {
        int id;
        List<Integer> neighbors;

        Vertex(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
        }
    }
    static class TableEntry {
        List<Integer> header;
        boolean known;
        int dist;
        int path;

        TableEntry() {
            header = new ArrayList<>();
            known = false;
            dist = Integer.MAX_VALUE;
            path = -1; // notVertex
        }
    }
    static int numberOfVertices;
    static Vertex[] vertices;

    static void dijkstra(int start, int[][] weights, TableEntry[] table ,Vertex[] vertex) { //weighs is for distance on edges
        table[start].dist = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(v -> table[v].dist));
        minHeap.add(start);
//        PriorityQueue<Vertex> minHeapObj = new PriorityQueue<>(Comparator.comparingInt(v -> vertex[v.id].id));

        while (!minHeap.isEmpty()) {
            int v = minHeap.poll();
            table[v].known = true; // means the adjacent are checked

            for (int w : vertices[v].neighbors) {
                if (!table[w].known) { // if its unknown
                    int edgeWeight = weights[v][w];
                    if (table[v].dist + edgeWeight < table[w].dist) {
                        table[w].dist = table[v].dist + edgeWeight;
                        table[w].path = v;
                        minHeap.add(w);
                    }
                }
            }
        }
    }

    static void printPath(int vertex, TableEntry[] table) {
        if (table[vertex].path != -1) {
            printPath(table[vertex].path, table);
            System.out.print(" to ");
        }
        System.out.print(vertex + " ");
    }

    public static void main(String[] args) {
        // Initialize the graph and other necessary data structures
        numberOfVertices = 5; // Change accordingly
        vertices = new Vertex[numberOfVertices + 1];
        for (int i = 1; i <= numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }

        // Sample graph initialization (replace with your own graph data)
        vertices[1].neighbors.addAll(Arrays.asList(2, 3));
        vertices[2].neighbors.addAll(Arrays.asList(3, 4));
        vertices[3].neighbors.addAll(Collections.singletonList(4));
        vertices[4].neighbors.addAll(Collections.singletonList(5));

        // Assign weights to edges (replace with your own edge weights)
        int[][] weights = new int[numberOfVertices + 1][numberOfVertices + 1];
        weights[1][2] = weights[2][1] = 1;
        weights[1][3] = weights[3][1] = 2;
        weights[2][3] = weights[3][2] = 1;
        weights[2][4] = weights[4][2] = 3;
        weights[3][4] = weights[4][3] = 1;
        weights[4][5] = weights[5][4] = 2;

        TableEntry[] table = new TableEntry[numberOfVertices + 1];
        for (int i = 1; i <= numberOfVertices; i++) {
            table[i] = new TableEntry();
        }

        int startVertex = 2; // Change accordingly
        dijkstra(startVertex, weights, table , vertices);

        // Print shortest paths from the start vertex to all other vertices
        for (int i = 1; i <= numberOfVertices; i++) {
            System.out.print("Shortest path from " + startVertex + " to " + i + ": ");
            printPath(i, table);
            System.out.println(" with distance " + table[i].dist);
        }
    }
}
