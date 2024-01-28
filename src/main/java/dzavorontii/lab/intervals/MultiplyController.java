package dzavorontii.lab.intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MultiplyController {

    @FXML private Button add;
    @FXML private Button del;
    @FXML private Button calculateMultiply;
    @FXML private TextField left;
    @FXML private Label list;
    @FXML private TextField right;
    List<Interval> multiplyIntervals = new ArrayList<>();

    @FXML void initialize() {
        calculateMultiply.setOnAction(event -> {
            Stage stage = (Stage) calculateMultiply.getScene().getWindow();
            stage.close();
        });

        add.setOnAction(event -> {
            multiplyIntervals.add(new Interval(Float.parseFloat(left.getText()), Float.parseFloat(right.getText())));
            list.setText(rewriteResult());
        });

        del.setOnAction(event -> {
            multiplyIntervals.remove(multiplyIntervals.size() - 1);
            list.setText(rewriteResult());
        });
    }

    private String rewriteResult() {
        String res = "Список доданих інтервалів:\n";
        return res + listIntervalsToString();
    }

    private String listIntervalsToString(){
        return multiplyIntervals.stream().map(Interval::toString).collect(Collectors.joining("\n"));
    }

}

