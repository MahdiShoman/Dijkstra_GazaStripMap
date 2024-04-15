import java.util.ArrayList;
import java.util.Comparator;

public class Vertex implements Comparable<Vertex> {
    String name;
    boolean known ;
    double distance = Integer.MAX_VALUE ;//weight of edge between it and the pre
//    Edge [] edges ;
    ArrayList<Node> adjacent =new ArrayList<>();
    Vertex preVertex;// to use it to get path
    double cityCoordinatesX =0;
    double cityCoordinatesY =0;
    double x = 0 ; //on the img
    double y = 0 ;
    public Vertex() {
        preVertex =null;
    }
    public Vertex(String name, boolean known, double distance) {
        this.name = name;
        this.known = known;
        this.distance = distance;
    }

    public Vertex(String name, boolean known, double distance, Vertex preVertex) {
        this.name = name;
        this.known = known;
//        this.distance = distance;
        this.preVertex = preVertex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getPreVertex() {
        return preVertex;
    }

    public void setPreVertex(Vertex preVertex) {
        this.preVertex = preVertex;
    }

    public double getCityCoordinatesX() {
        return cityCoordinatesX;
    }

    public void setCityCoordinatesX(double cityCoordinatesX) {
        this.cityCoordinatesX = cityCoordinatesX;
    }

    public double getCityCoordinatesY() {
        return cityCoordinatesY;
    }

    public void setCityCoordinatesY(double cityCoordinatesY) {
        this.cityCoordinatesY = cityCoordinatesY;
    }

    @Override
    public int compareTo(Vertex o) {
        if(distance < o.distance){
            return  -1;
        }
        if(distance > o.distance){
            return  1;
        }

        return 0;
    }
}
