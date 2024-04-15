import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Map extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    static Pane pane = new Pane();
   static Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("City Map");

        StackPane root = new StackPane();


        // Load the map image
        Image mapImage = new Image("GazaMap.jpg"); // Replace with the actual path

        // Create an ImageView and set the map image
        ImageView imageView = new ImageView(mapImage);

        // Get the graphics context of the image view
        imageView.setOnMouseClicked(e -> handleMouseClick(e.getX(), e.getY())); // Optional: Handle mouse clicks on the map




        Circle circle = new Circle();
/*        circle.setLayoutX(x);
        circle.setLayoutY(y);*/
        circle.setCenterX(403.96642685851356);
        circle.setCenterY(166.97160493827218);
        circle.setRadius(10);
        Circle circle1 = new Circle();
/*        circle.setLayoutX(x);
        circle.setLayoutY(y);*/
        circle1.setCenterX(403.96642685851356);
        circle1.setCenterY(81.16913580246873);
        circle1.setRadius(10);
        pane.getChildren().add(imageView);

     //  root.getChildren().add(imageView); 686.4604316546696     c        109.82716049382695
        Circle circle2 = new Circle();
/*        circle.setLayoutX(x);
        circle.setLayoutY(y);*/
        circle2.setLayoutX(686.4604316546696-60);
        circle2.setLayoutY(109.82716049382695-60);
        circle2.setRadius(10);
        pane.getChildren().addAll(circle1,circle,circle2);

        // Add cities with actual latitude and longitude 31.5667 34.6833 31.5500 34.6500
        drawCity(pane, "Gaza City", 31.5167 , 34.4500); // Example coordinates, replace with actual values
        drawCity(pane, "City2", 31.432864541412016 , 34.40367305575443); // Example coordinates

        primaryStage.setScene(new Scene(pane, mapImage.getWidth(), mapImage.getHeight()));
        primaryStage.show();
    }
    static Vertex[] vertices_path ;
    static Pane showCities(Vertex[] vertex){// show cities on the map
        vertices_path=vertex;
        // Load the map image
        Image mapImage = new Image("GazaMap.jpg"); // Replace with the actual path

        // Create an ImageView and set the map image
        ImageView imageView = new ImageView(mapImage);

        // Get the graphics context of the image view
        imageView.setOnMouseClicked(e -> handleMouseClick(e.getX(), e.getY())); // Optional: Handle mouse clicks on the map
        pane.getChildren().add(imageView);
        double x =0;
        double y =0;
        for (int i = 0; i < vertex.length; i++) {
            String name = vertex[i].name;

                x = getCoordonateX(x, vertex[i].cityCoordinatesY);
                y = getCoordonateY(y, vertex[i].cityCoordinatesX);
                vertex[i].x=x;
                vertex[i].y=y;
            if (name.charAt(0)!= '.') {
                Circle circle = new Circle();
/*        circle.setLayoutX(x);
        circle.setLayoutY(y);*/
                circle.setCenterX(x);
                circle.setCenterY(y);
                circle.setRadius(5);
                javafx.scene.text.Text cityLabel = new javafx.scene.text.Text(vertex[i].name);
                cityLabel.setLayoutX(x - 8);
                cityLabel.setLayoutY(y - 8);


                /*menuItem.s
                contextMenu.set*/
                pane.getChildren().add(cityLabel);
                pane.getChildren().add(circle);
                //System.out.println(x + "          d        " + y);


                circle.setOnMouseClicked(mouseEvent -> {
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem menuSItem = new MenuItem("Set Source");
                    MenuItem menuDItem = new MenuItem("Set Target");
                    contextMenu.getItems().addAll(menuSItem,menuDItem);
                    contextMenu.show(circle,circle.getCenterX() +400,circle.getCenterY()+100);
                    menuSItem.setOnAction(actionEvent -> {
                        UI.sourceCityComboBox.setValue(cityLabel.getText());
                    });
                    menuDItem.setOnAction(actionEvent -> {
                        UI.destinationCityComboBox.setValue(cityLabel.getText());
                    });
                });
            }
        }

        /*stage.setScene(new Scene(pane,600,742));
        stage.show();*/
        return pane;
    }
    private void drawCity(Pane root, String cityName, double latitude, double longitude) {
        // Convert latitude and longitude to coordinates on the image
      /*  double x = (longitude - MIN_LONGITUDE) * (589.0 / (MAX_LONGITUDE - MIN_LONGITUDE));
        double y = (MAX_LATITUDE - latitude) * (656.0 / (MAX_LATITUDE - MIN_LATITUDE));*/
        double x = (589.0 * (longitude - MIN_LONGITUDE)) / (MAX_LONGITUDE - MIN_LONGITUDE);
        double y = (695.0 * (latitude - MIN_LATITUDE)) / (MAX_LATITUDE - MIN_LATITUDE);
        System.out.println(x +"     c        " + y);
        // Create a label (or any other JavaFX node) to represent the city

        Circle circle = new Circle();
        /*circle.setLayoutX(x);
        circle.setLayoutY(y);*/
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(5);

        javafx.scene.text.Text cityLabel = new javafx.scene.text.Text(cityName);
        cityLabel.setLayoutX(x);
        cityLabel.setLayoutY(y);
        root.getChildren().add(cityLabel);

       /* Circle circle = new Circle(5); // Adjust the radius as needed
        circle.setFill(Color.RED);
        StackPane.setAlignment(circle, Pos.TOP_LEFT);
        StackPane.setMargin(circle, new Insets(y, 0, 0, x));*/
        root.getChildren().add(circle);
    }

    // Constants for map scaling
    private static final double MIN_LONGITUDE = 34.1959;
    private static final double MAX_LONGITUDE = 34.5694;
    private static final double MIN_LATITUDE = 31.6015;
    private static final double MAX_LATITUDE = 31.2142;

    static void handleMouseClick(double x, double y) {
        System.out.println("Mouse clicked at: (" + x + ", " + y + ")");
        // Add any additional handling logic for mouse clicks on the map
    }

    static double getCoordonateX(double x  , double longitude){//for the vertex
        x = (600 * (longitude - MIN_LONGITUDE)) / (MAX_LONGITUDE - MIN_LONGITUDE);
        return x;
    }
    static double getCoordonateY( double y , double latitude){
        y = (742.0 * (latitude - MIN_LATITUDE)) / (MAX_LATITUDE - MIN_LATITUDE);
        return y;
    }
}
