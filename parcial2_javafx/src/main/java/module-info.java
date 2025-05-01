module co.edu.co.edu.uniquindio.poo {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.co.edu.uniquindio.poo.app to javafx.fxml;
    exports co.edu.co.edu.uniquindio.poo.app;

    opens co.edu.co.edu.uniquindio.poo.model to javafx.fxml;
    exports co.edu.co.edu.uniquindio.poo.model;

    opens co.edu.co.edu.uniquindio.poo.viewController to javafx.fxml;
    exports co.edu.co.edu.uniquindio.poo.viewController;

}
