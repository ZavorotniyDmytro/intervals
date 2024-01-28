package dzavorontii.lab.intervals;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

public class Controller {
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
                    Interval result = Interval.createAddInterval(A, B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "+");
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
                }
                case "minusBtn" -> {
                    Interval result = Interval.createMinusInterval(A, B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "-");
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
                }
                case "multiplyBtn" -> {
                    Interval result = Interval.createMultiplyInterval(A, B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "*");
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
                }
                case "divBtn" -> {
                    Interval result = Interval.createDivInterval(A, B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "/");
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
                }
                case "divHypothesisBtn" -> {
                    Interval result = Interval.createDivHypothesisInterval(A, B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "/(hypothesis)");
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
                }
                case "reflectionBtn" -> {
                    Interval result = Interval.createReflectionInterval(A);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, getResultInterval()), "A", "A⁻");
                }
                case "maxBtn" -> {
                    Interval result = Interval.createMaximumInterval(A, B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "v");
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
                }
                case "minBtn" -> {
                    Interval result = Interval.createMinimumInterval(A, B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, B, getResultInterval()), "A", "ʌ");
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(A, B, result), "B", "=");
                }
                case "inversionBtn" -> {
                    Interval result = Interval.createInversionInterval(B);
                    setResultInterval(result);
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 0, Painter.maxValue(B, getResultInterval()), "B", "B⁻¹");
                }
                case "addKtoA" -> {
                    float k = Float.parseFloat(addANumber.getText());
                    Interval result = Interval.createAddKForAInterval(A, k);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, getResultInterval()), "A", "+k");
                }
                case "minusKfromB" -> {
                    float k = Float.parseFloat(munisBNumber.getText());
                    Interval result = Interval.createMinusKFromBInterval(B, k);
                    setResultInterval(result);
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(B, result), "B", "-k");
                }
                case "multAtoK" -> {
                    float k = Float.parseFloat(multiANumber.getText());
                    Interval result = Interval.createMultiplyKInterval(A, k);
                    setResultInterval(result);
                    painter.drawAxesForInterval(A, canvas.getHeight() / 3 * 0, Painter.maxValue(A, getResultInterval()), "A", "*k");
                }
                case "divBtoK" -> {
                    float k = Float.parseFloat(divBNumber.getText());
                    Interval result = Interval.createDivKInterval(B, k);
                    setResultInterval(result);
                    painter.drawAxesForInterval(B, canvas.getHeight() / 3 * 1, Painter.maxValue(B, result), "B", "/k");
                }
                case "multiply" -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("multiply-intervals.fxml"));
                    try {
                        Parent root  = loader.load();
                        Dialog<Void> dialog = new Dialog<>();
                        dialog.setTitle("MultiplyIntervals");
                        dialog.setHeight(300);
                        dialog.getDialogPane().setContent(root);

                        dialog.showAndWait();

                        MultiplyController mc = loader.getController();
                        setResultInterval(Interval.createMultiplyInterval(mc.multiplyIntervals));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> {
                    return;
                }
            }
            Interval result = getResultInterval();
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
