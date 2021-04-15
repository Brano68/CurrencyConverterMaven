package sk.kosickaakademia.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sk.kosickaakademia.calculator.Calculator;
import sk.kosickaakademia.mongo.DatabaseMongo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class IntroductionController extends MainGui {

    @FXML
    private ImageView idPic;
    @FXML
    private TextField result;
    @FXML
    private TextField howMuchEuro;
    @FXML
    private ComboBox cBCurrency;

    public IntroductionController() throws FileNotFoundException {
        //nastavujem v controller aby sa spustil o 0,01s neskor a nastavil obrazok
        //lepsie bz bolo pouzit riesenie ako pri chat
        //ze si popytam ten
        PauseTransition pause = new PauseTransition(Duration.seconds(0.01));
        String path1 = "src/main/pictureintroduction/moneyy.jpg";
        FileInputStream inputstream1 = new FileInputStream(path1);
        Image image1 = new Image(inputstream1);
        pause.setOnFinished(event -> idPic.setImage(image1));
        pause.play();
    }

    @FXML
    private void cbPress(MouseEvent mouseEvent) {
        String[] arrayNameOfCurrency = new Calculator().getAllKey();
        Arrays.sort(arrayNameOfCurrency);
        for(int i = 0; i < arrayNameOfCurrency.length; i++){
            cBCurrency.getItems().add(arrayNameOfCurrency[i]);
        }
    }


    @FXML
    private void convert(MouseEvent mouseEvent) {
        if(howMuchEuro == null || cBCurrency == null){
            return;
        }
        if(checkIfIsNumber() == false){
            result.setText("Not number!!!");
            return;
        }
        //kolko euro
        double howMuchNumber = Double.parseDouble(howMuchEuro.getText().trim());

        //zistime kurz aky je
        String key = (String)cBCurrency.getValue();
        //
        double resultt = new Calculator().calculate(howMuchNumber,key);

        //a na zaver vypocitame result
        if(key.equals("BTC")){
            result.setText(String.valueOf(resultt));
            //tu urobime insert do monga
            new DatabaseMongo().insertIntoMongo(key, howMuchNumber, resultt);
        }else{
            resultt *= 100;
            System.out.println(resultt);
            int number = (int) resultt;
            System.out.println(number);
            double result2 = number/100.00;
            System.out.println(result2);
            result.setText(String.valueOf(result2));
            //tu urobime insert do monga
            new DatabaseMongo().insertIntoMongo(key, howMuchNumber, result2);
        }
    }

    private boolean checkIfIsNumber(){
        String string = howMuchEuro.getText();
        for(int i = 0; i < string.length(); i++){
            System.out.println(string.charAt(i));
            if(string.charAt(i) == '0' || string.charAt(i) == '1' || string.charAt(i) == '2'
            || string.charAt(i) == '3' || string.charAt(i) == '4' || string.charAt(i) == '5'
            || string.charAt(i) == '6' || string.charAt(i) == '7' || string.charAt(i) == '8'
            || string.charAt(i) == '9' || string.charAt(i) == '.'){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
}
