package com.lorenzomar4.customprintsequencer.controller.CustomPrinterSequencerController;

import com.lorenzomar4.customprintsequencer.model.Generator;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByRangeNumber;
import com.lorenzomar4.customprintsequencer.model.Sequencer;
import com.lorenzomar4.customprintsequencer.model.chainOfValidation.TheInitialValidator;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Window;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomPrintSequencerController implements Initializable {

    @FXML
    TextField initialPage;
    @FXML
    TextField finalPage;

    String initialPagePrevius = "";
    String finalPagePrevius = "";

    @FXML
    TextArea onlyFrontPages;

    @FXML
    TextArea onlyOppositePages;



    @FXML
    Label totalNumberOfPages  =new Label("0");

    @FXML
    Label totalNumberOfSheet =new Label("0");

    @FXML
    Label totalNumberOfFrontPages =new Label("0");

    @FXML
    Label totalNumberOfOppositePages =new Label("0");;

    Generator generator;

    Sequencer sequencer;

    ByRangeNumber pageNumberReturnerType;

    @FXML
    protected void confirm(ActionEvent event) throws BusinessException {

        String initialPageString = initialPage.getText();
        String finalPageString = finalPage.getText();


        try {

            if (!initialPagePrevius.equals(initialPageString) || !finalPagePrevius.equals(finalPageString)) {

                TheInitialValidator.getInstanceValidator().validate(initialPageString, finalPageString);

                Integer finalPage = Integer.parseInt(finalPageString);
                Integer initialPage = Integer.parseInt(initialPageString);

                String frontPagesRangeString;
                String oppositePageRangeString;

                pageNumberReturnerType.setInitialRange(initialPage);
                pageNumberReturnerType.setFinalRange(finalPage);

                frontPagesRangeString = generator.onlyFrontPagesOfSheetString();
                oppositePageRangeString = generator.onlyOppositePagesOfSheetString();

                onlyFrontPages.setText(frontPagesRangeString);
                onlyOppositePages.setText(oppositePageRangeString);

                initialPagePrevius = initialPageString;
                finalPagePrevius = finalPageString;


                totalNumberOfSheet.setText(generator.numberOfFrontPages().toString());
                totalNumberOfFrontPages.setText(generator.numberOfFrontPages().toString());
                totalNumberOfPages.setText(generator.numberOfPagesTotal().toString());
                totalNumberOfOppositePages.setText(generator.numberOfOppositePages().toString());


            }

        } catch (BusinessException e) {
            Window ownerWindow = initialPage.getScene().getWindow();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());

            // Establece la ventana principal como propietaria del Alert
            alert.initOwner(ownerWindow);

            alert.showAndWait();

            onlyFrontPages.setText(e.getMessage());
            onlyOppositePages.setText(e.getMessage());
            restoreValuesOfReportLabels();
        }

    }


    public void restoreValuesOfReportLabels(){

        List<Label> listOfLabel = List.of(totalNumberOfPages,totalNumberOfSheet,
                totalNumberOfFrontPages,totalNumberOfOppositePages);

        listOfLabel.forEach(labelElement-> labelElement.setText("0") );

    }

    public void restoreValuesOfInputs(){

        List<TextInputControl> textInputControl = List.of(initialPage,finalPage,onlyFrontPages,onlyOppositePages);

        textInputControl.forEach(textInputElement-> textInputElement.setText(null));

    }

    @FXML
    public void clipFront() {
        String textToCopy = onlyFrontPages.getText();
        clipboard(textToCopy);
    }

    private void clipboard(String string) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(string);
        clipboard.setContent(content);

    }

    @FXML
    public void clipBack() {
        String textToCopy = onlyOppositePages.getText();
        clipboard(textToCopy);
    }

    @FXML
    public void cancel() {

        initialPagePrevius = "";
        finalPagePrevius = "";
        restoreValuesOfReportLabels();
        restoreValuesOfInputs();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sequencer = new Sequencer();
        generator = new Generator(sequencer);
        pageNumberReturnerType = new ByRangeNumber();
        sequencer.setPageNumberReturner(pageNumberReturnerType);

    }

}
