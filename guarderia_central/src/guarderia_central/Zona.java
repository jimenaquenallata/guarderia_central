package guarderia_central;

import java.io.Serializable;
import java.util.ArrayList;

public class Zona implements Serializable {

    private char letra;
    private String tipoVehiculo;
    private int cantVehiculos, cantGarages;
    private String ancho, profundidad;

    private ArrayList<Garage> garages;
    private ArrayList<Empleado> empleadosZona;

    public Zona() {
        garages = new ArrayList<Garage>(cantGarages);
        empleadosZona = new ArrayList<Empleado>();
    }

    public ArrayList<Garage> getGarages() {
        return garages;
    }

    public ArrayList<Empleado> getEmpleadosZona() {
        return empleadosZona;
    }

    public void agregarGarage(Garage g) {
        garages.add(g);
    }

    public int garagesOcupados() {
        return garages.size();
    }

    public void agregarEmpleado(Empleado e) {
        empleadosZona.add(e);
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String x) {
        this.ancho = x;
    }

    public int getCantVehiculos() {
        cantVehiculos = garages.size();
        return cantVehiculos;
    }

    public void setCantGarages(int x) {
        this.cantGarages = x;
    }

    public int getCantGarages() {
        return cantGarages;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String x) {
        this.profundidad = x;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char l) {
        this.letra = l;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String l) {
        this.tipoVehiculo = l;
    }

    public void mostrar() {
        System.out.println("Letra: " + letra);
        System.out.println("Tipo de Vehiculo: " + tipoVehiculo);
        System.out.println("Cantidad de Garages: " + cantGarages);
        System.out.println("Cantidad de Empleados: " + empleadosZona.size());
        System.out.println("Garages.ancho: " + ancho);
        System.out.println("Garages.profundidad: " + profundidad);
    }
}
