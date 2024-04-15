import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    /**
     * read a file
     * display the cities
     * wait the user to select the source & destination cities and click run
     * call the calculateDijkstra method
     * draw the line cross the path *********
     * display the distance between the source  & destination cities
     * print the path of it
     * */

    static FileReader fileReader = new FileReader();
   static UI ui = new UI();
//    static Stage stage = new Stage();
    public static void main(String[] args) {
        launch(args);
        //fileReader.start("new.txt");

    }
    static File selectedFile=null;
    @Override
    public void start(Stage stage) throws Exception {
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Dijkstra graph file");

        // Show the file chooser dialog
         selectedFile = fileChooser.showOpenDialog(stage);

        fileReader.start(selectedFile);
        ui.vertex=FileReader.vertex;
        UI.pane=fileReader.displayCites();
        ui.createUi();


    }
    static void start(){

       // fileReader.start(selectedFile);
        ui.vertex=FileReader.vertex;
        UI.pane=fileReader.displayCites();
        ui.createUi();
    }
}
