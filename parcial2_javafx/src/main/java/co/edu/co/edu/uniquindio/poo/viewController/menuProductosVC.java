package co.edu.co.edu.uniquindio.poo.viewController;

import co.edu.co.edu.uniquindio.poo.app.App;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.co.edu.uniquindio.poo.model.*;

import java.util.*;

public class menuProductosVC {

    @FXML private TextField txtNombre, txtPrecio, txtComponente, txtFiltro, txtClonarCantidad;
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, String> colComponentes;

    @FXML private TableView<ProductoDTO> tablaConteo;
    @FXML private TableColumn<ProductoDTO, String> colNombreConteo;
    @FXML private TableColumn<ProductoDTO, Integer> colCantidadConteo;
    @FXML
    private Button btnVolver;


    private List<String> componentesTemp = new ArrayList<>();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));
        colPrecio.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));
        colComponentes.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.join(", ", data.getValue().getComponentes())));

        colNombreConteo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));
        colCantidadConteo.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCantidad()));

        actualizarTabla(RegistroGlobal.getInstancia().getProductos());
    }

    @FXML
    private void agregarComponente() {
        String comp = txtComponente.getText().trim().toLowerCase();
        if (!comp.isEmpty()) {
            componentesTemp.add(comp);
            txtComponente.clear();
        }
    }

    @FXML
    private void crearProducto() {
        try {
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText());

            Producto nuevo = new Producto.Builder()
                    .setNombre(nombre)
                    .setPrecio(precio)
                    .build();

            componentesTemp.forEach(nuevo.getComponentes()::add);

            RegistroGlobal.getInstancia().agregarProducto(nuevo);
            actualizarTabla(RegistroGlobal.getInstancia().getProductos());
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio debe ser un número válido.");
        }
    }

    @FXML
    private void filtrarProductos() {
        String comp = txtFiltro.getText().trim();
        List<Producto> filtrados = AnalizadorProductos.filtrarPorComponente(comp);
        actualizarTabla(filtrados);
    }

    @FXML
    private void ordenarPorPrecio() {
        List<Producto> ordenados = AnalizadorProductos.ordenarPorPrecio();
        actualizarTabla(ordenados);
    }

    @FXML
    private void clonarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Error", "Selecciona un producto para clonar.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtClonarCantidad.getText().trim());
            for (int i = 0; i < cantidad; i++) {
                RegistroGlobal.getInstancia().agregarProducto(seleccionado.clone());
            }
            actualizarTabla(RegistroGlobal.getInstancia().getProductos());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Cantidad inválida para clonar.");
        }
    }

    @FXML
    private void contarProductosPorNombre() {
        List<Producto> productos = RegistroGlobal.getInstancia().getProductos();
        Map<String, Integer> conteo = new HashMap<>();

        for (Producto producto : productos) {
            String nombre = producto.getNombre();
            conteo.put(nombre, conteo.getOrDefault(nombre, 0) + 1);
        }

        List<ProductoDTO> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            resultado.add(new ProductoDTO(entry.getKey(), entry.getValue()));
        }

        tablaConteo.setItems(FXCollections.observableArrayList(resultado));
    }

    private void actualizarTabla(List<Producto> productos) {
        tablaProductos.setItems(FXCollections.observableArrayList(productos));
    }

    @FXML
    void volverAlMenu() {
        App.cargarVista("/menuPrincipal.fxml");
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtPrecio.clear();
        componentesTemp.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
