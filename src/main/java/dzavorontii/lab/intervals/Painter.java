package dzavorontii.lab.intervals;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    private void drawBackground(){
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
    }

    public void clear(){
        gc.clearRect(0, 0, width, height);
    }

    public static float maxValue(Interval... intervals) {
        float max = 0;

        for (Interval i : intervals){
            max = Math.max(
                    Math.max(Math.abs(i.left), max),
                    Math.max(Math.abs(i.right), max)
            );
        }
        return max;
    }
}
