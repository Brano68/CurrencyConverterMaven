package sk.kosickaakademia.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sk.kosickaakademia.calculator.Calculator;

public class IntroductionController extends Main{


    @FXML
    private TextField result;
    @FXML
    private TextField howMuchEuro;
    @FXML
    private ComboBox cBCurrency;


    @FXML
    private void cbPress(MouseEvent mouseEvent) {
        String[] arrayNameOfCurrency = new Calculator().getAllKey();
        for(int i = 0; i < arrayNameOfCurrency.length; i++){
            cBCurrency.getItems().add(arrayNameOfCurrency[i]);
        }
    }


    @FXML
    private void convert(MouseEvent mouseEvent) {
        if(howMuchEuro == null || cBCurrency == null){
            return;
        }
        //kolko euro
        double howMuchNumber = Double.parseDouble(howMuchEuro.getText().trim());
        //zistime kurz aky je
        String key = (String)cBCurrency.getValue();
        //
        double resultt = new Calculator().calculate(howMuchNumber,key);

        //a na zaver vypocitame result
        result.setText(String.valueOf(resultt));
    }
}
