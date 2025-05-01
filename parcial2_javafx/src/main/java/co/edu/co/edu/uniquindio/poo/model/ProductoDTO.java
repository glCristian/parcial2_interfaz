package co.edu.co.edu.uniquindio.poo.model;

public class ProductoDTO {
    private  String nombre;
    private  int cantidad;

    public ProductoDTO(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
}
