package guarderia_central;

import java.io.Serializable;
import java.time.LocalDate;

public class Vehiculo implements Serializable {

    private String nombre;
    private String matricula;
    private String tipo;
    private String dimensiones;
    private Socio propietario;
    private Zona zona;
    private Garage garageAsignado;

    public Vehiculo(Socio x) {
        this.propietario = x;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String x) {
        this.nombre = x;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona x) {
        this.zona = x;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String x) {
        this.matricula = x;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String x) {
        this.tipo = x;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String x) {
        this.dimensiones = x;
    }

    public void mostrar() {
        System.out.println("Propietario: " + propietario.getName());
        System.out.println("Matricula: " + matricula);
        System.out.println("Tipo de Vehiculo: " + tipo);
        System.out.println("Dimensiones: " + dimensiones);

    }
}
