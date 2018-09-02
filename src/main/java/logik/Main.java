package logik;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Main extends Application{
    private boolean permissionToCreatePDF = false;
    private boolean permissionToSendEmails = false;
    private String pathToFile = null;
    CreateListOfCustomers createListOfCustomers = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label mailmassager;
        VBox vbox = new VBox();
        mailmassager = new Label("Status:");
        mailmassager.setPrefSize(400, 30);
        vbox.getChildren().add(mailmassager);
        Button openFile = new Button("Datei auswählen");
        Button createPDF = new Button("PDF Dateien erstellen");
        Button exitButton = new Button("Schließen");

        openFile.setPrefSize(400, 30);
        createPDF.setPrefSize(400, 30);
        exitButton.setPrefSize(400,30);

        vbox.getChildren().add(openFile);
        vbox.getChildren().add(createPDF);
        vbox.getChildren().add(exitButton);
        vbox.setStyle("-fx-font-size: 12pt");

        Scene scene = new Scene(vbox, 400, 140);



        openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SelectFile f = new SelectFile();
                    pathToFile = f.getFilePath(new Stage());
                    if(pathToFile!=null){
                        mailmassager.setText("Status: Datei wurde ausgewählt");
                        permissionToCreatePDF=true;
                    }
                    else{
                        mailmassager.setText("Status: Es wurde keine Datei ausgewählt");
                    }
                    System.out.println(pathToFile);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        createPDF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(permissionToCreatePDF){
                    try {

                        List<String> listOfRawCustomers =new ReadFile(pathToFile).getRawDataOfCustomers();
                         createListOfCustomers = new CreateListOfCustomers(listOfRawCustomers);
                        List<Customer> listOfCustomers = createListOfCustomers.getListOfCustomers();
                        String directoryPath = ProvideResults.createFolder();
                        for (Customer customer : listOfCustomers) {
                                        CreatePdf p = new CreatePdf(directoryPath);
                                        p.manipulatePdf(customer);
                        }
                        mailmassager.setText(listOfCustomers.size()+ " PDF Dateien wurden erfolgreich erstellt");
                        permissionToSendEmails = true;
                        permissionToCreatePDF = false;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    mailmassager.setText("PDF Dateien konnten nicht erstellt werden");
                }

        }});



        exitButton.setOnAction(event -> System.exit(0));


            primaryStage.setTitle("Amazon PDF Creator v1.0.0");
            primaryStage.setOnCloseRequest(event -> System.exit(0));
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }

}
