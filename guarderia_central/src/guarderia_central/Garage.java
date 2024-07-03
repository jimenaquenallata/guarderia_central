package guarderia_central;

import java.io.Serializable;
import java.time.LocalDate;

public class Garage implements Serializable {

    private int nro;
    private int cont_luz;
    private boolean serv_mant;
    private LocalDate fecha_compra, fecha_asignacion;
    private Vehiculo v;
    private Socio prop;
    private Zona zona;

    public Garage(Zona z) {
        this.zona = z;
    }

    public Zona getZona() {
        return zona;
    }

    public Vehiculo getVehiculo() {
        return v;
    }

    public void setVehiculo(Vehiculo x) {
        v = x;
    }

    public Socio getProp() {
        return prop;
    }

    public void setProp(Socio x) {
        prop = x;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int x) {
        this.nro = x;
    }

    public int getCont_luz() {
        return cont_luz;
    }

    public void setcont_luz(int x) {
        this.cont_luz = x;
    }

    public boolean getServMant() {
        return serv_mant;
    }

    public void setServMant(boolean x) {
        this.serv_mant = x;
    }

    public LocalDate getFechaCompra() {
        return fecha_compra;
    }

    public void setFechaCompra(LocalDate x) {
        this.fecha_compra = x;
    }

    public LocalDate getFechaAsignacion() {
        return fecha_asignacion;
    }

    public void setFechaAsignacion(LocalDate x) {
        this.fecha_asignacion = x;
    }

    public void mostrar() {
        System.out.println("Garage nro: " + getNro() + "\t"
                + "zona: " + getZona().getLetra());
    }

}
