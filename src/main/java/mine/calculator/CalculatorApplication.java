package mine.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 450);
        try {
            Image image = new Image(String.valueOf(CalculatorApplication.class.getResource("assets/icon.png")));
            stage.getIcons().add(image);


        } catch (Exception e) {
            System.out.println("NullPointer");
        }
        stage.setTitle("Калькулятор");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}