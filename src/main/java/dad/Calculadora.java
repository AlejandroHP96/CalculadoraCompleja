package dad;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Calculadora extends Application {

    private TextField textFieldA, textFieldB, textFieldC, textFieldD;
    private TextField finalA, finalB;
    private ComboBox<String> operaciones;

    private Complejo numeroA, numeroB, numeroC, numeroD, finalCA, finalCB;
    private StringProperty operacionesProperty;

    @Override
    public void start(Stage primaryStage) throws Exception {

        numeroA = new Complejo();
        numeroB = new Complejo();
        numeroC = new Complejo();
        numeroD = new Complejo();
        finalCA = new Complejo();
        finalCB = new Complejo();

        operacionesProperty = new SimpleStringProperty();

        textFieldA = new TextField();
        textFieldA.setAlignment(Pos.CENTER);
        textFieldA.setPrefWidth(60);

        textFieldB = new TextField();
        textFieldB.setAlignment(Pos.CENTER);
        textFieldB.setPrefWidth(60);

        textFieldC = new TextField();
        textFieldC.setAlignment(Pos.CENTER);
        textFieldC.setPrefWidth(60);

        textFieldD = new TextField();
        textFieldD.setAlignment(Pos.CENTER);
        textFieldD.setPrefWidth(60);

        finalA = new TextField();
        finalA.setAlignment(Pos.CENTER);
        finalA.setPrefWidth(60);
        finalA.setEditable(false);

        finalB = new TextField();
        finalB.setAlignment(Pos.CENTER);
        finalB.setPrefWidth(60);
        finalB.setEditable(false);

        operaciones = new ComboBox<>();
        operaciones.getItems().addAll("+", "-", "*", "/");

        HBox primFila = new HBox(5, textFieldA, new Label("+"), textFieldB, new Label("i"));
        HBox segFila = new HBox(5, textFieldC, new Label("+"), textFieldD, new Label("i"));
        HBox tercFila = new HBox(5, finalA, new Label("+"), finalB, new Label("i"));

        VBox izquierda = new VBox(5, operaciones);
        izquierda.setAlignment(Pos.CENTER);

        VBox derecha = new VBox(5, primFila, segFila, new Separator(), tercFila);
        derecha.setAlignment(Pos.CENTER);

        HBox root = new HBox(5, izquierda, derecha);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("Calculadora Compleja");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Bindeos

        Bindings.bindBidirectional(textFieldA.textProperty(), numeroA.realProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(textFieldB.textProperty(), numeroB.imaginarioProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(textFieldC.textProperty(), numeroC.realProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(textFieldD.textProperty(), numeroD.imaginarioProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(finalA.textProperty(), finalCA.realProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(finalB.textProperty(), finalCB.imaginarioProperty(), new NumberStringConverter());
        operacionesProperty.bind(operaciones.getSelectionModel().selectedItemProperty());

        // button.setOnAction(e ->
        // onOperando(operaciones.getSelectionModel().getSelectedItem()));

        operacionesProperty.addListener(((o, ov, nv) -> onOperando(nv)));

    }

    private void onOperando(String nv) {

        switch (nv) {
        case "+":
            finalCA.realProperty().bind(numeroA.realProperty().add(numeroC.realProperty()));
            finalCB.imaginarioProperty().bind(numeroB.imaginarioProperty().add(numeroD.imaginarioProperty()));
            break;

        case "-":
            finalCA.realProperty().bind(numeroA.realProperty().subtract(numeroC.realProperty()));
            finalCB.imaginarioProperty().bind(numeroB.imaginarioProperty().subtract(numeroD.imaginarioProperty()));
            break;

        case "*":
            finalCA.realProperty().bind((numeroA.realProperty().multiply(numeroC.realProperty()))
                    .subtract((numeroB.imaginarioProperty()).multiply(numeroD.imaginarioProperty())));

            finalCB.imaginarioProperty().bind(numeroA.realProperty().multiply(numeroD.imaginarioProperty())
                    .add(numeroB.imaginarioProperty()).multiply(numeroC.realProperty()));
            break;

        case "/":
            finalCA.realProperty()
                    .bind(((numeroA.realProperty().multiply(numeroC.realProperty()))
                            .add((numeroB.imaginarioProperty().multiply(numeroD.imaginarioProperty()))))
                                    .divide((numeroC.realProperty().multiply(numeroC.realProperty()))
                                            .add(numeroD.imaginarioProperty().multiply(numeroD.imaginarioProperty()))));

            finalCB.imaginarioProperty()
                    .bind(((numeroB.imaginarioProperty().multiply(numeroC.realProperty()))
                            .subtract((numeroA.realProperty().multiply(numeroD.imaginarioProperty()))))
                                    .divide((numeroC.realProperty().multiply(numeroC.realProperty()))
                                            .add(numeroD.imaginarioProperty().multiply(numeroD.imaginarioProperty()))));
            break;
        default:
            break;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
