package dzavorontii.lab.intervals;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

public class Controller {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField addANumber;
    @FXML private Label resultField;
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
    Interval resultInterval = new Interval();

    @FXML void initialize() {
        Painter painter = new Painter(canvas);
        calculate.setOnAction(event -> {
            painter.clear();


            RadioButton currentOperation = (RadioButton) operation.getSelectedToggle();
            if (currentOperation.getId() == null) return;
            Interval A = new Interval(Float.parseFloat(leftA.getText()), Float.parseFloat(rightA.getText()));
            Interval B = new Interval(Float.parseFloat(leftB.getText()), Float.parseFloat(rightB.getText()));

            switch (currentOperation.getId()) {
                case "addBtn" -> {
                    setResultInterval(Interval.createAddInterval(A, B));
                }
                case "minusBtn" -> {
                    setResultInterval(Interval.createMinusInterval(A, B));
                }
                case "multiplyBtn" -> {
                    setResultInterval(Interval.createMultiplyInterval(A, B));
                }
                case "divBtn" -> {
                    setResultInterval(Interval.createDivInterval(A, B));
                }
                case "divHypothesisBtn" -> {
                    setResultInterval(Interval.createDivHypothesisInterval(A, B));
                }
                default -> {
                    return;
                }
            }
            Interval result = getResultInterval();
            painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "+");
            painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
            painter.drawAxesForInterval(result, canvas.getHeight() / 3 * 2, Painter.maxValue(A, B, result), "Result", "");

            resultField.setText("[" + result.left + ", " + result.right + "]");
            System.out.println(result);
        });

        resultInA.setOnAction(event -> {
            leftA.setText(String.valueOf(resultInterval.left));
            rightA.setText(String.valueOf(resultInterval.right));
        });

        resultInB.setOnAction(event -> {
            leftB.setText(String.valueOf(resultInterval.left));
            rightB.setText(String.valueOf(resultInterval.right));
        });

        close.setOnAction(event -> { });
    }

    public void setResultInterval(Interval i) {
        this.resultInterval = i;
    }

    public Interval getResultInterval() {
        return resultInterval;
    }
}
