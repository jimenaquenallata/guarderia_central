package guarderia_central;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Socio extends Persona implements Serializable {

    private String dni;
    private String nombre;
    private LocalDate fecha_ingreso;
    private String direccion;
    private String telefono;

    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Garage> garages;

    public Socio(String u, String p, LocalDate f) {
        setUsuario(u);
        setPassword(p);
        setFecha(f);
        vehiculos = new ArrayList<Vehiculo>();
        garages = new ArrayList<Garage>();

    }

    public void agregarVehiculos(Vehiculo v) {
        vehiculos.add(v);
    }

    public void agregarGarages(Garage g) {
        garages.add(g);
    }

    public LocalDate getFecha() {
        return fecha_ingreso;
    }

    public void setFecha(LocalDate c) {
        this.fecha_ingreso = c;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<Garage> getGarages() {
        return garages;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String x) {
        this.nombre = x;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String x) {
        this.dni = x;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String x) {
        this.direccion = x;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String x) {
        this.telefono = x;
    }

    @Override
    public void mostrar() {
        System.out.println("Socio - Usuario: " + this.getUsuario());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Fecha ingreso: "
                + fecha_ingreso.getDayOfMonth() + "/"
                + fecha_ingreso.getMonthValue() + "/"
                + fecha_ingreso.getYear());
        System.out.println("Direccion: " + direccion);
        System.out.println("Telefono: " + telefono);

    }

    @Override
    public boolean proceder(SistemaDeRegistro sr) {
        EntradaSalida.mostrarString("Hola socio:" + getName());
        boolean salir = false;
        do {
            char op = EntradaSalida.leerChar("\nOpciones\n"
                    + "\t1-Ver mis vehiculos\n"
                    + "\t2-Ver mis garages\n"
                    + "\t3-Salir");
            switch (op) {
                case '1':
                    ArrayList<Vehiculo> vMios = getVehiculos();
                    if (vMios.isEmpty()) {
                        EntradaSalida.mostrarString("No posee vehiculos");
                    } else {
                        for (int i = 0; i < vMios.size(); i++) {
                            vMios.get(i).mostrar();
                        }
                    }

                    break;
                case '2':
                    ArrayList<Garage> gMios = getGarages();
                    if (gMios.isEmpty()) {
                        EntradaSalida.mostrarString("No posee Garages a su nombre");
                    } else {
                        for (int i = 0; i < gMios.size(); i++) {
                            gMios.get(i).mostrar();
                        }
                    }
                    break;
                case '3':
                    salir = true;
                    break;
                default:
                    EntradaSalida.mostrarString("ERROR: Opcion invalida");
                    break;
            }
        } while (!salir);

        return true;
    }
}
