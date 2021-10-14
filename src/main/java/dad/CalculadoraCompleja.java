package dad;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculadoraCompleja extends Application {

    private TextField numA,numB,numC,numD;
    private Button resultadoButton;
    private ComboBox miComboBox;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        numA = new TextField();
        numB = new TextField();
        numC = new TextField();
        numD = new TextField();

        miComboBox = new ComboBox<>();
        miComboBox.getItems().addAll("+");

        VBox rootV1 = new VBox(5);
        rootV1.setAlignment(Pos.CENTER);
        rootV1.getChildren().addAll(miComboBox);

        HBox rootH = new HBox(5,rootV1);

        Scene scene = new Scene(rootH);


        primaryStage.setTitle("Calculadora Compleja");
        primaryStage.setScene(scene);
        primaryStage.show();


        
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
