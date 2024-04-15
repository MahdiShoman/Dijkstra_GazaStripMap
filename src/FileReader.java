import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * rread the file
 * enter  data on vertex
 * add adjacent */
public class FileReader extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    static Vertex[] vertex; // use it in the main class (it has the vertex name,lat,lon,adjacent)
    @Override
    public void start(Stage primaryStage) {
        // Create a file chooser
 /*       FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Dijkstra graph file");

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            try {
                // Read the file using Scanner
                Scanner scanner = new Scanner(selectedFile);

                // Read the first line to get the number of cities and edges
                int numCities = scanner.nextInt();
                int numEdges = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Create arrays to store city information
                String[] cityNames = new String[numCities];

                 vertex = new Vertex[numCities];
                for (int i = 0; i < vertex.length; i++) {
                    vertex[i] = new Vertex();
                }



                List<Edge> edges = new ArrayList<>();
                // Read city information
                for (int i = 0; i < numCities; i++) {
                    String[] cityInfo = scanner.nextLine().split(" ");
//                    cityNames[i] = cityInfo[0];
//                    cityCoordinates[i][0] = Double.parseDouble(cityInfo[1]);
//                    cityCoordinates[i][1] = Double.parseDouble(cityInfo[2]);
                    String citynam=cityInfo[0];
                    Double x = Double.parseDouble(cityInfo[1]);
                    Double y= Double.parseDouble(cityInfo[2]);

                    vertex[i].name=(citynam);
                    vertex[i].cityCoordinatesX=(x);
                    vertex[i].cityCoordinatesY=(y);
                }

                // Create a list to store edge information


                // Read edge information
                for (int i = 0; i < numEdges; i++) {
                    Vertex city_1 =new Vertex();
                    Vertex city_2 =new Vertex();
                    String[] edgeInfo = scanner.nextLine().split(" ");
                    city_1.name=edgeInfo[0];
                    city_2.name=edgeInfo[1];
                    // create 2 var to use it for lat lon for own cities
                    int city1Idx=0;
                    int city2Idx=0;
                    //add the adjacent to the vertex
                    for (int j = 0; j < vertex.length-1; j++) {
                        if (city_1.name.equals(vertex[j].name)) {
                            city1Idx=j;
                        }
                        if (city_2.name.equals(vertex[j].name)) {
                            city2Idx=j;
                        }
                    }
//                    double distance = Integer.parseInt(edgeInfo[2]);
                   *//* double distance=Main.haversine(cityCoordinates[city1Idx][0],cityCoordinates[city1Idx][1],cityCoordinates[city2Idx][0]
                    ,cityCoordinates[city2Idx][1]);*//*
                    double distance= HaverSine.haversine(vertex[city1Idx].cityCoordinatesX,vertex[city1Idx].cityCoordinatesY,vertex[city2Idx].cityCoordinatesX
                            ,vertex[city2Idx].cityCoordinatesY);
                    edges.add(new Edge(city_1, city_2,  distance));
                }

                // Now you have the data in arrays and a list
                // Do whatever processing you need with cityNames, cityCoordinates, and edges
                printCityGraph(vertex, edges);
                // Close the scanner
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }*/
    }
    void start(File selectedFile){
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Dijkstra graph file");

        // Show the file chooser dialog
       // File selectedFile = new File(filePath);

        if (selectedFile != null) {
            try {
                // Read the file using Scanner
                Scanner scanner = new Scanner(selectedFile);

                // Read the first line to get the number of cities and edges
                int numCities = scanner.nextInt();
                int numEdges = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Create arrays to store city information
                String[] cityNames = new String[numCities];

                vertex = new Vertex[numCities];
                for (int i = 0; i < vertex.length; i++) {
                    vertex[i] = new Vertex();
                }



                List<Edge> edges = new ArrayList<>();
                // Read city information
                for (int i = 0; i < numCities; i++) {
                    String[] cityInfo = scanner.nextLine().split(" ");
//                    cityNames[i] = cityInfo[0];
//                    cityCoordinates[i][0] = Double.parseDouble(cityInfo[1]);
//                    cityCoordinates[i][1] = Double.parseDouble(cityInfo[2]);
                    String citynam=cityInfo[0];
                    Double x = Double.parseDouble(cityInfo[1]);
                    Double y= Double.parseDouble(cityInfo[2]);

                    vertex[i].name=(citynam);
                    vertex[i].cityCoordinatesX=(x);
                    vertex[i].cityCoordinatesY=(y);
                }

                // Create a list to store edge information


                // Read edge information
                for (int i = 0; i < numEdges; i++) {
                    Vertex city_1 =new Vertex();
                    Vertex city_2 =new Vertex();
                    String[] edgeInfo = scanner.nextLine().split(" ");
                    city_1.name=edgeInfo[0];
                    city_2.name=edgeInfo[1];
                    // create 2 var to use it for lat lon for own cities
                    int city1Idx=0;
                    int city2Idx=0;
                    //add the adjacent to the vertex
                    for (int j = 0; j < vertex.length-1; j++) {
                        if (city_1.name.equals(vertex[j].name)) {
                            city1Idx=j;
                        }
                        if (city_2.name.equals(vertex[j].name)) {
                            city2Idx=j;
                        }
                    }
//                    double distance = Integer.parseInt(edgeInfo[2]);
                   /* double distance=Main.haversine(cityCoordinates[city1Idx][0],cityCoordinates[city1Idx][1],cityCoordinates[city2Idx][0]
                    ,cityCoordinates[city2Idx][1]);*/
                    double distance= HaverSine.haversine(vertex[city1Idx].cityCoordinatesX,vertex[city1Idx].cityCoordinatesY,vertex[city2Idx].cityCoordinatesX
                            ,vertex[city2Idx].cityCoordinatesY);
                    edges.add(new Edge(city_1, city_2,  distance));
                }

                // Now you have the data in arrays and a list
                // Do whatever processing you need with cityNames, cityCoordinates, and edges
                printCityGraph(vertex, edges);
                // Close the scanner
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private static void printCityGraph(Vertex[] vertex, List<Edge> edges) {

         for (int i=0 ;i<vertex.length;i++){
       // for (String city : cityNames) {
            System.out.println("City: " + vertex[i].name);

            ArrayList<String> adjacentCities = new ArrayList<>();
            ArrayList<Double> distances = new ArrayList<>();
            int k =0; // for add adjacent to vertex
          for(int j = 0 ;j<edges.size()-1;j++){
//            for (Edge edge : edges) {
                if (edges.get(j).city1.name.equals(vertex[i].name)) {
                    String cityname = edges.get(j).city2.name;
                    adjacentCities.add(cityname);
                    distances.add(edges.get(j).distance);
                    //to add adjacent to vertex
                    vertex[i].adjacent.add(new Node());

//                    vertex[i].adjacent.get(k).vertex =edges.get(j).city2;
                    vertex[i].adjacent.get(k).vertex = getCity(cityname,vertex);
                    vertex[i].adjacent.get(k).weight= edges.get(j).distance;
                    k++;
                }
                else if (edges.get(j).city2.name.equals(vertex[i].name)) {
                    String cityname = edges.get(j).city1.name;
                    adjacentCities.add(cityname);
                    distances.add(edges.get(j).distance);

                    //to add adjacent to vertex
                    vertex[i].adjacent.add(new Node());
//                    vertex[i].adjacent.get(k).vertex =edges.get(j).city1;
                    vertex[i].adjacent.get(k).vertex = getCity(cityname,vertex);
                    vertex[i].adjacent.get(k).weight= edges.get(j).distance;
                    k++;
                }
            }
//            vertex[i].adjacent.get(i).vertex=
//            Edge[] edge = new Edge[6];
            //from here equal adjasent with edg and caculate the distance
            System.out.println("Adjacent Cities: " + adjacentCities);
            System.out.println("Distances: " + distances);
            System.out.println();
        }
        for (Vertex value : vertex) { // to be sure about the adjacent
            System.out.println(value.name);
            for (int j = 0; j < value.adjacent.size(); j++) {
                System.out.print(value.adjacent.get(j).vertex.name);
            }
            System.out.println();
        }

    }
    static Vertex getCity (String vertx,Vertex[]vertices1){
        for (Vertex vertex : vertices1) {
            if (vertex.name.equals(vertx)) {
                return vertex;
            }
        }
        return null;
    }

    Pane displayCites(){// call it after read file to display the cities on the map
       return Map.showCities(vertex);
    }

    Vertex[] calculateDijkstra(String sourceCity,String end){ //call it after the user choose the source city
       return Dijkstraa.dijkstra(sourceCity ,vertex ,end);// vertex array should have the all graph data (the table) based on sourceCity
    }



}
