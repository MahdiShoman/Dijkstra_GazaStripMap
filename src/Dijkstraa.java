import java.util.ArrayList;

/**
 * this class want a (source city & vertex array & destination city )
 * it calculate the distance between the cities
 * and the shortest path between it
 */
public class Dijkstraa {
    static Vertex[] vertices;

    static Vertex[] dijkstra(String start, Vertex[] vertexs, String end) { //weighs is for distance on edges

        int startIdx = 0;//to know what is the index of source city
        int endIDX = 0;
        System.out.println(start + " starrtt");

        for (int i = 0; i < vertexs.length ; i++) {
            if (vertexs[i].name.equals(start)) {
                vertexs[i].distance = 0;
                startIdx = i;
                vertexs[i].preVertex = null;
                vertexs[i].known=false;
            } else {
                vertexs[i].distance = Integer.MAX_VALUE;
                vertexs[i].preVertex = null;
                vertexs[i].known=false;
            }
            if (vertexs[i].name.equals(end)) {
                endIDX = i;
            }
        }
        // Create a custom comparator for comparing vertexs based on distance
        Xheap<Vertex> xheap = new Xheap(vertexs.length);
        xheap.insert(vertexs[startIdx]);
//        int k = 0;
        while (!xheap.isEmpty()) {
            Vertex v = xheap.removeMin();
            v.known = true; // means the adjacent are checked
            if (vertexs[endIDX] == v) {
                break;
            }
            for (int i = 0; i < v.adjacent.size(); i++) {
                if (!v.adjacent.get(i).vertex.known) { // if its unknown
                    double edgeWeight = v.adjacent.get(i).weight;
                    if (v.distance + edgeWeight < v.adjacent.get(i).vertex.distance) {
                        v.adjacent.get(i).vertex.distance = v.distance + edgeWeight;
                        v.adjacent.get(i).vertex.preVertex = v;
                        xheap.insert(v.adjacent.get(i).vertex);

                    }
                }
            }
        }

        vertices = vertexs;
        System.out.println(vertexs[5].name);
        System.out.println(vertexs[5].distance);
        System.out.println(vertexs[5].adjacent.size());
        printPath(end, vertexs);
        System.out.println();
   /*     minHeap.add(vertexs[startIdx]);
//        int k = 0;
        while (!minHeap.isEmpty()) {
            Vertex v = minHeap.poll();
            v.known = true; // means the adjacent are checked
            for (int i = 0; i < v.adjacent.size(); i++) {
//            for (int w : vertexs[v].neighbors) {
                if (!v.adjacent.get(i).vertex.known) { // if its unknown
                    double edgeWeight = v.adjacent.get(i).weight;
                    if (v.distance + edgeWeight < v.adjacent.get(i).vertex.distance) {
                        v.adjacent.get(i).vertex.distance = v.distance + edgeWeight;
                        v.adjacent.get(i).vertex.preVertex = v;
                        minHeap.add(v.adjacent.get(i).vertex);
                    }
                }
            }
        }*/
        //minHeap.add();
//        Map.showCities(vertexs);
        return vertexs;
    }

    static StringBuilder citiesPath = new StringBuilder();
    static ArrayList<Vertex> linePath = new ArrayList<>();

    static void printPath(String endVertex, Vertex[] vertices1) {
        Vertex v = getCity(endVertex, vertices1);
        linePath.add(v);
        if (v.preVertex != null) {
            printPath(v.preVertex.name, vertices1);
            System.out.print(" to ");
            citiesPath.append("  ->  ");
        }
        System.out.print(endVertex + " ");
        citiesPath.append(endVertex).append(" ");
    }

    //  to get the vertex based on city name
    static Vertex getCity(String vertx, Vertex[] vertices1) {
        for (Vertex vertex : vertices1) {
            if (vertex.name.equals(vertx)) {
                return vertex;
            }
        }
        return null;
    }
}
