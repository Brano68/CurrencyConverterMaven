package sk.kosickaakademia.gui;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainGui extends Application {

    private Text text = new Text("Exchange");
    private Group p2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //set a rotating label
        text.setFont(Font.font("Verdena", FontWeight.BLACK, 25));
        text.setX(165);
        text.setY(40);
        text.setEffect(new Reflection());
        p2 = new Group(text);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), text);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAxis(new Point3D(15,2,10));
        rotateTransition.play();


        Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("introduction.fxml"));
        primaryStage.setTitle("Exchange");
        final Group root1 = new Group(p2, root);

        primaryStage.setScene(new Scene(root1, 450, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
