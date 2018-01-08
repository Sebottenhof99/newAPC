package logik;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
        Button openFile, createPDF, sendEmails, exitButton;
        VBox vbox = new VBox();
        mailmassager = new Label("Status:");
        mailmassager.setPrefSize(400, 30);
        vbox.getChildren().add(mailmassager);
        openFile = new Button();
        createPDF = new Button();
        sendEmails = new Button();
        exitButton = new Button();
        openFile.setText("Datei auswählen");
        createPDF.setText("PDF Dateien erstellen");
        sendEmails.setText("Emails versenden");
        exitButton.setText("Schließen");
        openFile.setPrefSize(400, 30);
        createPDF.setPrefSize(400, 30);
        sendEmails.setPrefSize(400, 30);
        exitButton.setPrefSize(400,30);

        vbox.getChildren().add(openFile);
        vbox.getChildren().add(createPDF);
        vbox.getChildren().add(sendEmails);
        vbox.getChildren().add(exitButton);

        Scene scene = new Scene(vbox, 400, 150);
        primaryStage.setTitle("Amazon PDF Creator v1.0.0");
        primaryStage.setOnCloseRequest(event -> System.exit(0));

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
                    if(pathToFile==null){
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

                        ReadFile rf = new ReadFile(pathToFile);
                        List<String> listOfRawCustomers = rf.getRawDataOfCustomers();
                         createListOfCustomers = new CreateListOfCustomers(listOfRawCustomers);
                        List<Customer> listOfCustomers = createListOfCustomers.getListOfCustomers();
                        String pathOfProps = ProvideResults.createFolder();
                        for (Customer customer : listOfCustomers) {

                                        CreatePdf p = new CreatePdf(pathOfProps);

                                        p.manipulatePdf(customer);
                        }
                        mailmassager.setText("Status: PDF Dateien wurden erfolgreich erstellt");
                        permissionToSendEmails = true;
                        ProvideMails.counter = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        }});

        sendEmails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (permissionToSendEmails) {
                    permissionToSendEmails = false;
                    for (Customer customer : createListOfCustomers.getListOfCustomers()) {
                        mailThread(customer);

                    }
                    try {
                        while ( ProvideMails.counter!=createListOfCustomers.getListOfCustomers().size() ){
                            System.out.println(ProvideMails.counter);

                        }
                        ProvideResults.moveAllFilesToTargetFolder();
                        mailmassager.setText("Status: " + ProvideMails.counter + " Emails wurden erfolgreich versendet!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    mailmassager.setText("FEHLER: Emaisl Bereits versendet /  Keine PDFs vorher erstellt.");
                }

            }
        });

        exitButton.setOnAction(event -> System.exit(0));

            primaryStage.setScene(scene);
            primaryStage.show();
        }

    public void mailThread(Customer customer){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                new ProvideMails().sendMail(customer);

            }};

        Thread thread = new Thread(task);
        thread.start();
    }
   // }
}
