module dzavorontii.lab.intervals {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens dzavorontii.lab.intervals to javafx.fxml;
    exports dzavorontii.lab.intervals;
}