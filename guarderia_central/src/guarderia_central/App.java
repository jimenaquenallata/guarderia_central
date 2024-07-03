package guarderia_central;

import java.io.IOException;

public class App {

    public void ejecutar() {
        SistemaDeRegistro sistemaDeRegistro = new SistemaDeRegistro();
        boolean seguir;
        try {
            sistemaDeRegistro = sistemaDeRegistro.deSerializar("guarderia.txt");
            seguir = EntradaSalida.leerBoolean("SISTEMAS DE GUARDERIA CENTRAL\nDesea ingresar?");
        } catch (Exception e) {
            String usuario = EntradaSalida.leerString("Arranque inicial del sistema...\n"
                    + "Sr(a) Administrador(a), ingrese su nombre de usuario");
            if (usuario.equals("")) {
                throw new NullPointerException("ERROR: El usuario no puede ser nulo.");
            }
            String password = EntradaSalida.leerString("Ingrese contraseña:");
            if (password.equals("")) {
                throw new NullPointerException("ERROR: La password no puede ser nula.");
            }
            sistemaDeRegistro.getPersonas().add(new Administrador(usuario, password));
            try {
                sistemaDeRegistro.serializar("guarderia.txt");
                EntradaSalida.mostrarString("El arranque ha sido exitoso. Ahora se debe reiniciar el sistema...");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            seguir = false;
        }
        while (seguir) {
            String usuario = EntradaSalida.leerString("Ingrese el usuario:");
            String password = EntradaSalida.leerString("Ingrese contraseña:");

            Persona p = sistemaDeRegistro.buscarPersona(usuario + ":" + password);
            if (p == null) {
                EntradaSalida.mostrarString("ERROR: La combinacion usuario/password ingresada no es valida.");
            } else {
                seguir = p.proceder(sistemaDeRegistro);//inicio sesion?
                if (seguir) {
                    seguir = EntradaSalida.leerBoolean("Desea ingresar?");
                }
            }
        }

    }

}
