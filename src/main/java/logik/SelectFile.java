package logik;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

/**
 * Created by Antoshka on 07.01.2018.
 */
public class SelectFile// extends Application
 {


    public String getFile() {
        return file;
    }


    private String file = null;


    private FileChooser fileChooser = new FileChooser();


    public String getFilePath(Stage primaryStage){

        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Text Dateien (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extentionFilter);
        fileChooser.setTitle("Datei auswählen");
        fileChooser.setInitialDirectory( new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\"));
        File chosenFile = fileChooser.showOpenDialog(null);

        String path;
        if(chosenFile != null) {
            path = chosenFile.getPath();
        } else {
            //default return value
            path = null;
        }
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        System.out.println(path);
        return path;
    }
}
