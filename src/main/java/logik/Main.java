package logik;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
        Button sendEmails = new Button("Emails versenden");
        Button exitButton = new Button("Schließen");

        openFile.setPrefSize(400, 30);
        createPDF.setPrefSize(400, 30);
        sendEmails.setPrefSize(400, 30);
        exitButton.setPrefSize(400,30);

        vbox.getChildren().add(openFile);
        vbox.getChildren().add(createPDF);
        vbox.getChildren().add(sendEmails);
        vbox.getChildren().add(exitButton);
        vbox.setStyle("-fx-font-size: 12pt");

        Scene scene = new Scene(vbox, 400, 175);



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
                    System.out.println("if");
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
                        ProvideMails.counter = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    mailmassager.setText("PDF Dateien konnten nicht erstellt werden");
                }

        }});

        sendEmails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (permissionToSendEmails) {
                    permissionToSendEmails = false;
                    for (Customer customer : createListOfCustomers.getListOfCustomers()) {
                        if(customer.getCountry().equals("CH")){
                            ProvideMails.counter++;
                            continue;
                        }
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
                    mailmassager.setText("FEHLER: Emails Bereits versendet /  Keine PDFs vorher erstellt.");
                }

            }
        });

        exitButton.setOnAction(event -> System.exit(0));


            primaryStage.setTitle("Amazon PDF Creator v1.0.0");
            primaryStage.setOnCloseRequest(event -> System.exit(0));
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
