package guarderia_central;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleado extends Persona implements Serializable {
    
    private String codigo;
    private String nombre;
    private String especialidad;
    private String direccion;
    private String telefono;
    
    private ArrayList<Zona> zonasAsignadas;
    
    public Empleado(String u, String p, ArrayList<Zona> z) {
        setUsuario(u);
        setPassword(p);
        zonasAsignadas = z;
    }
    
    public void asignarZona(Zona z) {
        zonasAsignadas.add(z);
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String c) {
        this.codigo = c;
    }
    
    public String getName() {
        return nombre;
    }
    
    public void setName(String x) {
        this.nombre = x;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    public void setEspecialidad(String x) {
        this.especialidad = x;
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
    
    public ArrayList<Zona> getZonas() {
        return zonasAsignadas;
    }
    
    public void setZonas(ArrayList<Zona> z) {
        this.zonasAsignadas = z;
    }
    
    @Override//estoy sobreescribiendo el metod mostrar de la clase persona
    public void mostrar() {
        System.out.println("Empleado - Usuario: " + this.getUsuario());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Nombre: " + nombre);
        System.out.println("Codigo: " + codigo);
        System.out.println("Zonas a cargo: " + zonasAsignadas.size());
        System.out.println("Direccion: " + direccion);
        System.out.println("Telefono: " + telefono);
    }
    
    @Override
    public boolean proceder(SistemaDeRegistro sr) {
        EntradaSalida.mostrarString("hola emmpleado " + nombre);
        ArrayList<Zona> z = getZonas();
        if (z.isEmpty()) {
            EntradaSalida.mostrarString("No tiene zonas asignadas");
        } else {
            String str = "";
            for (int i = 0; i < z.size(); i++) {
                str = str + z.get(i).getLetra() + '\t';
            }
            EntradaSalida.mostrarString(str);
        }
        return true;
    }
    
}
