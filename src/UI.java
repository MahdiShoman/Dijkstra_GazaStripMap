import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class UI extends Application {

    static ComboBox<String> sourceCityComboBox;
    static ComboBox<String> destinationCityComboBox;
    private Button runButton;
    private Button clearButton;
    private TextArea pathTextArea;
    private Label distanceLabel;
    private Label distanceValueLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("City Distance Calculator");

        // Create UI components
        sourceCityComboBox = createComboBox("Source City:");
        destinationCityComboBox = createComboBox("Destination City:");
        runButton = new Button("Run");

        pathTextArea = new TextArea();
        pathTextArea.setEditable(false);
        distanceLabel = new Label("Distance:");
        distanceValueLabel = new Label();

        // Set up the layout using GridPane
        GridPane gridPane = createGridPane();
        BorderPane borderPane =new BorderPane();
        borderPane.setCenter(gridPane);
        Image mapImage = new Image("GazaMap.jpg"); // Replace with the actual path

        // Create an ImageView and set the map image
        ImageView imageView = new ImageView(mapImage);

        // Get the graphics context of the image view
        imageView.setOnMouseClicked(e -> Map.handleMouseClick(e.getX(), e.getY()));
        borderPane.setLeft(imageView);
        // Set up the scene
        primaryStage.setScene(new Scene(borderPane, 1200, 800));
        primaryStage.show();
    }
    Stage stage = new Stage();
    Vertex[] vertex;// to load the cities name to comboBox
    static Pane pane;
    void createUi (){
        stage.setTitle("City Distance Calculator");

        // Create UI components
        sourceCityComboBox = createComboBox("Source City:");
        destinationCityComboBox = createComboBox("Destination City:");
        runButton = new Button("Run");
        clearButton=new Button("Clear");
        pathTextArea = new TextArea();
        pathTextArea.setEditable(false);
        distanceLabel = new Label("Distance:");
        distanceValueLabel = new Label();

        // Set up the layout using GridPane
        GridPane gridPane = createGridPane();
        BorderPane borderPane =new BorderPane();
        borderPane.setCenter(gridPane);
        Image mapImage = new Image("GazaMap.jpg"); // Replace with the actual path

        // Create an ImageView and set the map image
        ImageView imageView = new ImageView(mapImage);

        // Get the graphics context of the image view
        imageView.setOnMouseClicked(e -> Map.handleMouseClick(e.getX(), e.getY()));
        borderPane.setLeft(pane);
        stage.setScene(new Scene(borderPane,1210,742));
        stage.show();

    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Add UI components to the gridPane
        gridPane.add(createLabel("Source City:"), 0, 0);
        gridPane.add(sourceCityComboBox, 1, 0);

        gridPane.add(createLabel("Destination City:"), 0, 1);
        gridPane.add(destinationCityComboBox, 1, 1);

        gridPane.add(runButton, 0, 2, 2, 1);

        gridPane.add(createLabel("Path:"), 0, 3);
        gridPane.add(pathTextArea, 1, 3);

        gridPane.add(distanceLabel, 0, 4);
        gridPane.add(distanceValueLabel, 1, 4);

        gridPane.add(clearButton,1,5);

        // Set action for the Run button
//        runButton.setOnAction(event -> calculateDistanceAndPath());
        AtomicBoolean noError = new AtomicBoolean(true);
        runButton.setOnAction(event -> {
            Main.fileReader.calculateDijkstra(sourceCityComboBox.getValue(),destinationCityComboBox.getValue());
            pathTextArea.setText(Dijkstraa.citiesPath+"");
            if(pathTextArea.getText().trim().equals(destinationCityComboBox.getValue())){// there is no path
                noError.set(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("no path");
                alert.show();
                System.out.println("no path");
                distanceValueLabel.setText(" ");
            }
            if (noError.get())
                distanceValueLabel.setText(Objects.requireNonNull(Dijkstraa.getCity(destinationCityComboBox.getValue(), Dijkstraa.vertices)).distance+" ");

            //draw the line
            drawLineBetweenCities(Dijkstraa.linePath);
        });
        clearButton.setOnAction(actionEvent -> {
            Main.start();
            sourceCityComboBox.setValue("Source City:");
            destinationCityComboBox.setValue("Destination City:");
            pathTextArea.setText("");
            Dijkstraa.citiesPath.delete(0,Dijkstraa.citiesPath.length());
            Dijkstraa.linePath.clear();
            distanceValueLabel.setText("");
            removeLinesFromPane(pane);
        });

        return gridPane;
    }
    Line pathLine;
//    private Polygon pathArrow;
    private void drawLineBetweenCities(ArrayList<Vertex> linePath) {
        //System.out.println(linePath);

        for (int i = 0; i < linePath.size(); i++) {

            // Get the coordinates of the source and destination cities
            /*double sourceX = sourceCityComboBox.getLayoutX() + sourceCityComboBox.getWidth() / 2;
            double sourceY = sourceCityComboBox.getLayoutY() + sourceCityComboBox.getHeight() / 2;

            double destX = destinationCityComboBox.getLayoutX() + destinationCityComboBox.getWidth() / 2;
            double destY = destinationCityComboBox.getLayoutY() + destinationCityComboBox.getHeight() / 2;*/
            if (i+1<linePath.size()) {
                double sourceX = linePath.get(i).x;
                double sourceY = linePath.get(i).y;

                double destX = linePath.get(i + 1).x;
                double destY = linePath.get(i + 1).y;
                pathLine=new Line();

                // Set the line coordinates
                pathLine.setStartX(sourceX);
                pathLine.setStartY(sourceY);
                pathLine.setEndX(destX);
                pathLine.setEndY(destY);

                // Set line properties (color, etc.)
                pathLine.setStroke(Color.MEDIUMAQUAMARINE);
                pathLine.setStrokeWidth(2.0);

                pane.getChildren().add(pathLine);
            }
        }


       /* // Add the line to the GridPane
        GridPane gridPane = (GridPane) pathTextArea.getParent();
        gridPane.getChildren().add(pathLine);*/
    }
    private void removeLinesFromPane(Pane pane) {
        Iterator<Node> iterator = pane.getChildren().iterator();
        while (iterator.hasNext()) {
            Node child = iterator.next();
            if (child instanceof Line) {
                iterator.remove();
            }
        }
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        return label;
    }

    private ComboBox<String> createComboBox(String promptText) {
       /* ObservableList<String> cities = FXCollections.observableArrayList(
                "Gaza City", "City2", "City3" // Add your actual city names here
        );*/
        ObservableList<String> cities = FXCollections.observableArrayList();
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].name.charAt(0) != '.') {
                cities.add(vertex[i].name);
            }

        }
        //cities.addAll(getCitiesName());
        ComboBox<String> comboBox = new ComboBox<>(cities);
        comboBox.setPromptText(promptText);
        return comboBox;
    }



  /*  private void drawArrowBetweenCities(ArrayList<Vertex> linePath) {

        for (int i = 0; i < linePath.size(); i++) {

            // Get the coordinates of the source and destination cities
            *//*double sourceX = sourceCityComboBox.getLayoutX() + sourceCityComboBox.getWidth() / 2;
            double sourceY = sourceCityComboBox.getLayoutY() + sourceCityComboBox.getHeight() / 2;

            double destX = destinationCityComboBox.getLayoutX() + destinationCityComboBox.getWidth() / 2;
            double destY = destinationCityComboBox.getLayoutY() + destinationCityComboBox.getHeight() / 2;*//*
            if (i + 1 < linePath.size()) {
                double sourceX = linePath.get(i).x;
                double sourceY = linePath.get(i).y;

                double destX = linePath.get(i + 1).x;
                double destY = linePath.get(i + 1).y;
                //pathArrow=new Arrow();
                pathArrow = new Polygon(sourceX, sourceY, destX, destY);
                // Set the line coordinates

                // Set line properties (color, etc.)
                pathArrow.setStroke(Color.CHOCOLATE);
                pathArrow.setStrokeWidth(2.0);

                pane.getChildren().add(pathArrow);
            }
        }

    }*/
}
