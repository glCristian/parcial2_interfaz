package co.edu.co.edu.uniquindio.poo.model;


/**
 * Módulo decorador que añade funcionalidad de detección de intrusos.
 */
public class DetectorIntrusos extends ModuloDecorator{

    public DetectorIntrusos(Dispositivo decorado) {
        super(decorado);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String estado() {
        return decorado.estado() + " + Módulo: Intrusos";
    }

    @Override
    public int prioridad() {
        return decorado.prioridad() + 3;
    }

    @Override
    public String tipo() {
        return decorado.tipo();
    }
    
}