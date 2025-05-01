package co.edu.co.edu.uniquindio.poo.viewController;
import co.edu.co.edu.uniquindio.poo.app.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class MenuPrincipalVC {

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnDispositivos;

    @FXML
    void onAbrirVistaProductos(ActionEvent event) {
        App.cargarVista("/menuProductos.fxml");
    }

    @FXML
    void onAbrirVistaDispositivos(ActionEvent event) {
        //App.cargarVista();
    }

}
