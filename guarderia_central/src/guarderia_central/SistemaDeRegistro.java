package guarderia_central;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SistemaDeRegistro implements Serializable {

    private ArrayList<Zona> zonas;
    private ArrayList<Persona> personas;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Socio> listSoc = new ArrayList<Socio>();

    public SistemaDeRegistro() {
        zonas = new ArrayList<Zona>();
        personas = new ArrayList<Persona>();
        vehiculos = new ArrayList<Vehiculo>();
    }

    public void agregarSocio(Socio s) {
        listSoc.add(s);
    }

    public ArrayList<Socio> getListaSocios() {
        return listSoc;
    }

    public void pantallaRegistro() {
        EntradaSalida.mostrarString("Bienvenido/a al sistema de eleccion de cursos, Sr(a) Aspirante");
    }

    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(ArrayList<Zona> z) {
        this.zonas = z;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> v) {
        this.vehiculos = v;
    }

    public void agregarVehiculo(Vehiculo s) {
        vehiculos.add(s);
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public void serializar(String a) throws IOException {//grabar puede haber un error de entrada/salida
        FileOutputStream f = new FileOutputStream(a);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }//cargamos en la bd

    public SistemaDeRegistro deSerializar(String a) throws IOException, ClassNotFoundException {//leer ,eror comun que lee y no reconoce
        FileInputStream f = new FileInputStream(a);
        ObjectInputStream o = new ObjectInputStream(f);
        SistemaDeRegistro s = (SistemaDeRegistro) o.readObject();
        o.close();
        f.close();
        return s;
    }//extraemos de la bd

    public Persona buscarPersona(String datos) {
        int i = 0;
        boolean encontrado = false;
        Persona p = null;

        while (i < personas.size() && !encontrado) {
            p = personas.get(i);
            if (datos.equals(p.getUsuario() + ":" + p.getPassword())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return p;
        }
    }

}
