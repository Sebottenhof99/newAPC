package logik;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Antoshka on 07.01.2018.
 */
public class SelectFile {

    private FileChooser fileChooser = new FileChooser();

    public String getFilePath(Stage primaryStage){


        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Text Dateien (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extentionFilter);
        fileChooser.setTitle("Datei auswählen");
        fileChooser.setInitialDirectory( new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\"));
        File chosenFile = fileChooser.showOpenDialog(null);

        String path;
        try {
             path = chosenFile.getPath();
        }catch (NullPointerException e){
            path = null;
        }

        primaryStage.setOnCloseRequest(event -> System.exit(0));

        return path;
    }
}
