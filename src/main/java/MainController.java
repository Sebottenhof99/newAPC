import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logik.*;
import org.slf4j.Logger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Button buttonOpenFile;
    public Button buttonCreatePDFs;
    public Button closeButton;
    public Label status;

    String pathToFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status.setText("Programm gestartet!");
        buttonCreatePDFs.setDisable( true );
    }

    public void openFileButtonPressed(ActionEvent actionEvent) {
        SelectFile f = new SelectFile();
        String pathToFile = f.getFilePath(new Stage());
        if(pathToFile!=null){
            status.setText("Datei wurde ausgewählt.");
            this.pathToFile = pathToFile;
            buttonCreatePDFs.setDisable(false);
        }
        else{
            buttonCreatePDFs.setDisable(true);
            status.setText("Es wurde keine Datei ausgewählt");
        }
    }

    public void createPDFButtonPressed(ActionEvent actionEvent) {
        try {

            List<String> listOfRawCustomers =new ReadFile(pathToFile).getRawDataOfCustomers();
            CreateListOfCustomers createListOfCustomers = new CreateListOfCustomers(listOfRawCustomers);
            List<Customer> listOfCustomers = createListOfCustomers.getListOfCustomers();
            DuplicateDetector duplicateDetector = new DuplicateDetector();

            List<Customer> listOfCustomersAfterFilter =  duplicateDetector.filterDuplicate(listOfCustomers);

            ProvideResults.createFolder();
            String directoryPath = ProvideResults.getActualInvoicePath();
            for (Customer customer : listOfCustomersAfterFilter) {
                CreatePdf p = new CreatePdf(directoryPath);
                p.manipulatePdf(customer);
            }
            status.setText(listOfCustomersAfterFilter.size()+ " PDF Dateien wurden erfolgreich erstellt");
            buttonCreatePDFs.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeApplication(ActionEvent actionEvent) {
        System.exit(0);
    }
    }
