package dzavorontii.lab.intervals;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField addANumber;
    @FXML private Button calculate;
    @FXML private Button close;
    @FXML private TextField divBNumber;
    @FXML private TextField leftA;
    @FXML private TextField leftB;
    @FXML private TextField multiANumber;
    @FXML private TextField munisBNumber;
    @FXML private ToggleGroup operation;
    @FXML private Button resultInA;
    @FXML private Button resultInB;
    @FXML private TextField rightA;
    @FXML private TextField rightB;
    @FXML private Canvas canvas;

    @FXML void initialize() {

        calculate.setOnAction(event -> {
            Interval result;
            Painter painter = new Painter(canvas.getGraphicsContext2D());
            RadioButton currentOperation = (RadioButton) operation.getSelectedToggle();
            if (currentOperation.getId() == null) return;
            Interval A = new Interval(Integer.parseInt(leftA.getText()), Integer.parseInt(rightA.getText()));
            Interval B = new Interval(Integer.parseInt(leftB.getText()), Integer.parseInt(rightB.getText()));

            switch (currentOperation.getId()) {
                case "addBtn" -> {
                    result = Interval.createAddInterval(A, B);
                }
                case "minusBtn" -> {
                    result = Interval.createMinusInterval(A, B);
                }
                case "multiplyBtn" -> {
                    result = Interval.createMultiplyInterval(A, B);
                }
                case "divBtn" -> {
                    result = Interval.createDivInterval(A, B);
                }
                case "divHypothesisBtn" -> {
                    result = Interval.createDivHypothesisInterval(A, B);
                }
                default -> {
                    return;
                }
            }

            System.out.println(result);
        });


        close.setOnAction(event -> { });
    }
}
