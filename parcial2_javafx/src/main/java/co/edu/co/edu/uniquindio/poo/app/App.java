package co.edu.co.edu.uniquindio.poo.app;

import co.edu.co.edu.uniquindio.poo.model.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        cargarDatosPrueba();
        stage = primaryStage;
        cargarVista("/menuPrincipal.fxml");
        stage.show();
    }

    public static void cargarVista(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            Object viewController = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void cargarDatosPrueba() {
        RegistroGlobal registro = RegistroGlobal.getInstancia();

        Producto jugoNaranja = new Producto.Builder()
                .setNombre("Jugo de Naranja")
                .addComponente("naranja")
                .addComponente("agua")
                .addComponente("azúcar")
                .setPrecio(5_000)
                .build();

        Producto jugoMango = new Producto.Builder()
                .setNombre("Jugo de Mango")
                .addComponente("mango")
                .addComponente("agua")
                .addComponente("azúcar")
                .setPrecio(5_500)
                .build();

        Producto sandwich = new Producto.Builder()
                .setNombre("Sándwich")
                .addComponente("pan")
                .addComponente("jamón")
                .addComponente("queso")
                .setPrecio(7_000)
                .build();

        Producto empanada = new Producto.Builder()
                .setNombre("Empanada")
                .addComponente("papa")
                .addComponente("carne")
                .addComponente("masa")
                .setPrecio(2_000)
                .build();

        registro.agregarProducto(jugoNaranja);
        registro.agregarProducto(jugoMango);
        registro.agregarProducto(sandwich);
        registro.agregarProducto(empanada);
    }


    public static void main(String[] args) {
        launch();
    }

}