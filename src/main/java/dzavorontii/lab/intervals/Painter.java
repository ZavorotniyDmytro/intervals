package dzavorontii.lab.intervals;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Painter {
    double margin = 5;
    double height;
    double width;
    GraphicsContext gc;

    public Painter(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        height = canvas.getHeight();
        width = canvas.getWidth();
        gc.setLineWidth(2.0d);
        drawBackground();
    }

    public void drawBackground(){
        gc.strokeRoundRect(margin, margin, width - margin*2, height - margin*2, 10, 10);
    }

    public void drawAxesForInterval(Interval i, double y, double maxValue, String upperText, String lowerText){
        double marginLine = 15;
        double endLine = 6;
        double labelHeight = 7;
        double textMargin = width/12;
        // xy xy
        // X-axis
        gc.strokeLine(margin+marginLine, y + height / 6, width - marginLine - margin, y + height / 6);
        gc.strokeLine(width - marginLine - margin, y + height / 6, width - marginLine - margin - endLine, y + height / 6 - endLine);
        gc.strokeLine(width - marginLine - margin, y + height / 6, width - marginLine - margin - endLine, y + height / 6 + endLine);

        // Y-axis
        gc.strokeLine(width / 2, y + margin+marginLine, width / 2,  y + margin+marginLine + height / 6);
        gc.strokeLine(width / 2, y + margin+marginLine, width / 2 - endLine, y + margin+marginLine + endLine);
        gc.strokeLine(width / 2, y + margin+marginLine, width / 2 + endLine, y + margin+marginLine + endLine);

        gc.setStroke(Color.RED);

        gc.strokeLine(width/2 + ((width/2 - margin - marginLine * 2) * i.left/maxValue),y + height / 6 - labelHeight, width/2 + ((width/2 - margin - marginLine * 2) * i.left/maxValue),y + height / 6 + labelHeight);
        gc.strokeLine(width/2 + ((width/2 - margin - marginLine * 2) * i.right/maxValue),y + height / 6 - labelHeight, width/2 + ((width/2 - margin - marginLine * 2) * i.right/maxValue),y + height / 6 + labelHeight);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(0.7d);

        gc.strokeText(upperText, margin, y + textMargin);
        gc.strokeText(lowerText, margin, y + height/6 + textMargin);

        gc.setLineWidth(2.0d);
// gc.strokeLine(50, 50, 50, 50);   // Y-axis

        // Draw labels
        //gc.strokeText("X", 355, 255);
//        gc.strokeText("Y", 45, 45);
    }

    public void clear(){
        gc.clearRect(0, 0, width, height);
    }

    public static double maxValue(Interval a, Interval b, Interval result) {
        return Math.max(Math.max(a.right, a.left), Math.max(
                Math.max(b.right, b.left),
                Math.max(result.right, result.left)
        ));
    }
}
