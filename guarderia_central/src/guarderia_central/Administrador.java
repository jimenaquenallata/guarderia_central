package guarderia_central;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Administrador extends Persona implements Serializable {

    public Administrador(String u, String p) {
        setUsuario(u);
        setPassword(p);
    }

    @Override
    public void mostrar() {
        System.out.println("Administrador - Usuario: " + this.getUsuario());
        System.out.println("Password: " + this.getPassword());
    }

    @Override
    public boolean proceder(SistemaDeRegistro sr) {
        char opc;
        boolean seguir = true;
        do {
            System.out.println("\n=============================================");
            opc = EntradaSalida.leerChar(
                    "OPCIONES DEL ADMINISTRADOR\n"
                    + "[1] Dar de alta una Zona\n"
                    + "[2] Dar de alta un Empleado\n"
                    + "[3] Dar de alta un Socio\n"
                    + "[4] Registrar vehiculo\n"
                    + "[5] Registrar propiedad de garage a un SOCIO\n"
                    + "[6] Mostrar el contenido del sistema\n"
                    + "[7] Salir de este menu\n"
                    + "[8] Salir del sistema");
            switch (opc) {
                case '1':
                    altaZona(sr);
                    break;
                case '2':
                    altaEmpleado(sr);
                    //******************
                    break;
                case '3':
                    altaSocio(sr);
                    break;
                case '4':
                    registrarVehiculo(sr);
                    break;
                case '5':
                    ventaGarage(sr);
                    break;
                case '6':
                    mostrarBD(sr);
                    break;
                case '7':
                    seguir = true;
                    break;
                case '8':
                    seguir = false;
                    break;
                default:
                    EntradaSalida.mostrarString("ERROR: Opcion invalida");
                    opc = '*';
            }

            if (opc >= '1' && opc <= '5') {
                try {
                    sr.serializar("guarderia.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (opc != '7' && opc != '8');

        return seguir;
    }

    public void altaZona(SistemaDeRegistro sr) {

        char letra = EntradaSalida.leerChar("ALTA DE ZONA\nNota:"
                + "Las letras de las zonas deben ser ingresadas solamente en mayuscula.\n"
                + "Letra de la Zona:");
        if (letra < 65 || letra > 90) {
            EntradaSalida.mostrarString("ERROR: letra no valida");
        } else {
            ArrayList<Zona> ListadoZonas = sr.getZonas();
            ArrayList<Character> ListadoLetraZ = new ArrayList<Character>();
            for (int i = 0; i < ListadoZonas.size(); i++) {
                ListadoLetraZ.add(ListadoZonas.get(i).getLetra());
            }
            if (ListadoLetraZ.contains(letra)) {
                EntradaSalida.mostrarString("ERROR: La Zona ya figura en el sistema");
            } else {
                String tipoVehiculo = EntradaSalida.leerString("Ingrese el tipo de vehiculos que se guardan en esta zona:");
                if (tipoVehiculo.equals("")) {
                    EntradaSalida.mostrarString("ERROR: tipo de vehiculo no valido");
                } else {
                    Zona z = new Zona();
                    z.setLetra(letra);
                    z.setTipoVehiculo(tipoVehiculo);
                    z.setCantGarages(EntradaSalida.leerInt("Ingrese cantidad de garages que tiene la zona: "));
                    z.setAncho(EntradaSalida.leerString("Ingrese ancho de los garages: "));
                    z.setProfundidad(EntradaSalida.leerString("Ingrese profundidad de los garages: "));
                    sr.getZonas().add(z);
                    EntradaSalida.mostrarString("Se ha incorporado la zona al sistema");
                }
            }

        }
    }

    public void altaEmpleado(SistemaDeRegistro sr) {
        ArrayList<Zona> ListadoZonas = sr.getZonas();
        if (ListadoZonas.isEmpty()) {//ve si hay zonas reg
            EntradaSalida.mostrarString("ERROR: Primero deben darse de alta las zonas");
        } else {
            String usuario = EntradaSalida.leerString("ALTA DE EMPLEADO\nUsuario del empleado:");
            if (usuario.equals("")) {
                EntradaSalida.mostrarString("ERROR: usuario no valido");
            } else {
                String password = EntradaSalida.leerString("ingrese cotraseña");
                if (password.equals("")) {
                    EntradaSalida.mostrarString("ERROR: password no valida");
                } else {
                    Persona p = sr.buscarPersona(usuario + ":" + password);
                    if (p != null) {
                        EntradaSalida.mostrarString("ERROR: El usuario ya figura en el sistema.");
                    } else {
                        ArrayList<Zona> zonasEmpleado = new ArrayList<Zona>();
                        Zona zona = null;
                        char letraZona;
                        boolean masZonas, opcionVal;
                        int k = 0;
                        do {
                            String zonaStr = "";
                            for (int i = 0; i < ListadoZonas.size(); i++) {
                                zona = ListadoZonas.get(i);
                                if (!zonasEmpleado.contains(zona)) {
                                    zonaStr = zonaStr + zona.getLetra() + " - " + zona.getTipoVehiculo() + '\n';

                                }
                            }
                            if (zonaStr.equals("")) {
                                masZonas = false;
                                EntradaSalida.mostrarString("No hay zonas disponibles en el sistema.");
                            } else {
                                letraZona = EntradaSalida.leerChar(zonaStr + "Elija una zona,ingresando la letra:");
                                k = 0;
                                opcionVal = false;
                                while (k < ListadoZonas.size() && !opcionVal) {
                                    zona = ListadoZonas.get(k);
                                    if (zona.getLetra() == (letraZona)) {
                                        opcionVal = true;
                                    } else {
                                        k++;
                                    }
                                }
                                if (!opcionVal || zonasEmpleado.contains(zona)) {
                                    EntradaSalida.mostrarString("ERROR: codigo no valido.");
                                } else {
                                    zonasEmpleado.add(zona);
                                }

                                masZonas = EntradaSalida.leerBoolean("Desea ingresar mas zonas?");
                            }
                        } while (masZonas);
                        if (zonasEmpleado.isEmpty()) {
                            EntradaSalida.mostrarString("ERROR: No se ha ingresado ninguna zona,por lo tanto no se puede incorporar al empleado");
                        } else {
                            //p = new Empleado(usuario, password, zonasEmpleado);
                            Empleado e = new Empleado(usuario, password, zonasEmpleado);
                            e.setName(EntradaSalida.leerString("Ingrese nombre del empleado:"));
                            e.setCodigo(EntradaSalida.leerString("Ingrese codigo del empleado:"));
                            e.setEspecialidad(EntradaSalida.leerString("Ingrese especialidad del empleado:"));
                            e.setDireccion(EntradaSalida.leerString("Ingrese direcion del empleado:"));
                            e.setTelefono(EntradaSalida.leerString("Ingrese telefono del empleado:"));
                            sr.getPersonas().add(e);
                            ListadoZonas.get(k).agregarEmpleado(e);
                            EntradaSalida.mostrarString("Se ha incorporado el Empleado al sistema");

                        }

                    }
                }
            }
        }
    }

    public void altaSocio(SistemaDeRegistro sr) {

        String us = EntradaSalida.leerString("ALTA DE SOCIO\nIngrese usuario:");
        if (us.equals("")) {
            EntradaSalida.mostrarString("EROR: usuario invalido.");
        } else {
            String pass = EntradaSalida.leerString("ingrese contraseña:");
            if (pass.equals("")) {
                EntradaSalida.mostrarString("ERROR: password invalida.");
            } else {
                Persona p = sr.buscarPersona(us + ":" + pass);
                if (p != null) {
                    EntradaSalida.mostrarString("ERROR: este usuario ya figura en el sistema.");
                } else {
                    Socio s = new Socio(us, pass, LocalDate.now());
                    s.setName(EntradaSalida.leerString("Ingrese nombre del socio:"));
                    s.setDNI(EntradaSalida.leerString("Ingrese DNI del socio:"));
                    s.setDireccion(EntradaSalida.leerString("Ingrese direcion del socio:"));
                    s.setTelefono(EntradaSalida.leerString("Ingrese telefono del socio:"));
                    sr.agregarSocio(s);
                    sr.getPersonas().add(s);

                    EntradaSalida.mostrarString("Se ha incorporado el socio.");
                }
            }
        }
    }

    public void registrarVehiculo(SistemaDeRegistro sr) {

        ArrayList<Socio> ListadoSocios = sr.getListaSocios();
        if (ListadoSocios.isEmpty()) {
            EntradaSalida.mostrarString("ERROR: Primero deben darse de alta los socios");
        } else {
            ArrayList<Zona> listaZonas = sr.getZonas();
            if (listaZonas.isEmpty()) {
                EntradaSalida.mostrarString("ERROR: Primero deben darse de alta las zonas");
            } else {
                ArrayList<String> ListadoNombres = new ArrayList<String>();
                EntradaSalida.mostrarString("SOCIOS....");
                for (int i = 0; i < ListadoSocios.size(); i++) {
                    ListadoNombres.add(ListadoSocios.get(i).getName());
                    EntradaSalida.mostrarString("\t" + (i + 1) + "-" + ListadoSocios.get(i).getName());
                }
                int p = EntradaSalida.leerInt("seleccione propietario del vehiculo:") - 1;
                if (p < 0 || p > ListadoSocios.size()) {
                    EntradaSalida.mostrarString("ERROR: propietario no valido");
                } else {
                    ArrayList<Zona> ListaZ = sr.getZonas();
                    EntradaSalida.mostrarString("Tipo de auto");
                    for (int i = 0; i < ListaZ.size(); i++) {
                        EntradaSalida.mostrarString("\t" + (i + 1) + "-" + ListaZ.get(i).getTipoVehiculo());
                    }
                    int ta = EntradaSalida.leerInt("Selecciones tipo") - 1;//pensemos que esto ademas del tipo de auto nos da la zona del auto
                    if (ta < 0 || ta > ListaZ.size()) {
                        EntradaSalida.mostrarString("ERROR: tipo invalido");
                    } else {
                        int c = ListaZ.get(ta).getCantGarages() - ListaZ.get(ta).garagesOcupados();
                        System.out.println("Cantidad de garages disponibles: " + c);
                        if (c <= 0) {
                            EntradaSalida.mostrarString("Ya no quedan garages disponibles");
                        } else {
                            String patente = EntradaSalida.leerString("Ingrese matricula del vehiculo:");
                            if (patente.equals("")) {
                                EntradaSalida.mostrarString("ERROR: matricula no valida");
                            } else {
                                ArrayList<Vehiculo> ListadoVehiculos = sr.getVehiculos();
                                ArrayList<String> ListadoMatriculas = new ArrayList<String>();
                                for (int i = 0; i < ListadoVehiculos.size(); i++) {
                                    ListadoMatriculas.add(ListadoVehiculos.get(i).getMatricula());
                                }
                                if (ListadoMatriculas.contains(patente)) {
                                    EntradaSalida.mostrarString("ERROR: El vehiculo ya figura en el sistema");
                                } else {
                                    Vehiculo v = new Vehiculo(ListadoSocios.get(p));
                                    Garage g = new Garage(listaZonas.get(ta));

                                    v.setTipo(ListaZ.get(ta).getTipoVehiculo());
                                    v.setDimensiones(ListaZ.get(ta).getAncho() + "x" + ListaZ.get(ta).getProfundidad());
                                    v.setMatricula(patente);
                                    v.setNombre(EntradaSalida.leerString("Nombre: "));
                                    g.setVehiculo(v);
                                    g.setNro(listaZonas.get(ta).garagesOcupados() + 1);
                                    g.setFechaAsignacion(LocalDate.now());
                                    g.setServMant(EntradaSalida.leerBoolean("Quiere servicios de mantenimiento?"));
                                    ListaZ.get(ta).agregarGarage(g);
                                    sr.agregarVehiculo(v);
                                    ListadoSocios.get(p).agregarVehiculos(v);
                                    EntradaSalida.mostrarString("Se ha incorporado el vehiculo al sistema");

                                }
                            }
                        }
                    }

                }
            }
        }
    }

    public void ventaGarage(SistemaDeRegistro sr) {

        ArrayList<Zona> lZonas = sr.getZonas();
        if (lZonas.isEmpty()) {
            EntradaSalida.mostrarString("ERROR: Primero deben darse de alta las zonas");
        } else {
            ArrayList<Socio> lSocios = sr.getListaSocios();
            if (lSocios.isEmpty()) {
                EntradaSalida.mostrarString("ERROR: Primero deben darse de alta los socios");
            } else {
                for (int i = 0; i < lSocios.size(); i++) {
                    EntradaSalida.mostrarString("\t" + (i + 1) + "-" + lSocios.get(i).getName());
                }
                int x = EntradaSalida.leerInt("seleccione el socio que va adquirir la propiedad") - 1;
                if (x < 0 || x > lSocios.size()) {
                    EntradaSalida.mostrarString("ERROR: propietario invalido");
                } else {
                    for (int i = 0; i < lZonas.size(); i++) {
                        EntradaSalida.mostrarString("\t" + (i + 1) + "-" + lZonas.get(i).getLetra());
                    }
                    int l = EntradaSalida.leerInt("seleccione zona") - 1;
                    if (l < 0 || l > lZonas.size()) {
                        EntradaSalida.mostrarString("ERROR: zona invalido");
                    } else {
                        int c = lZonas.get(l).getCantGarages() - lZonas.get(l).garagesOcupados();
                        System.out.println("Cantidad de garages disponibles: " + c);
                        if (c <= 0) {
                            EntradaSalida.mostrarString("Ya no quedan garages disponibles");
                        } else {
                            boolean follow = EntradaSalida.leerBoolean("desea continuar");
                            if (!follow) {
                                EntradaSalida.mostrarString("OPERACION CANCELADA....");
                            } else {
                                Garage g = new Garage(lZonas.get(l));

                                g.setNro(lZonas.get(l).garagesOcupados() + 1);
                                System.out.println("Obtuvo el garage Nro: " + g.getNro());
                                g.setFechaCompra(LocalDate.now());
                                g.setServMant(EntradaSalida.leerBoolean("Quiere servicios de mantenimiento?"));
                                System.out.println(lSocios.get(x).getName() + " ya es propietario del garage nro - "
                                        + (lZonas.get(l).garagesOcupados() + 1) + " - de la zona - " + lZonas.get(l).getLetra());
                                lZonas.get(l).agregarGarage(g);
                                lSocios.get(x).agregarGarages(g);
                            }
                        }
                    }

                }
            }
        }
    }

    public void mostrarBD(SistemaDeRegistro sr) {

        System.out.println("\n=============================================");
        System.out.println("Personas");
        ArrayList<Persona> vecPer = sr.getPersonas();
        for (int i = 0; i < vecPer.size(); i++) {
            vecPer.get(i).mostrar();
            System.out.println("");
        }
        System.out.println("*****************************");
        System.out.println("\nZonas");
        ArrayList<Zona> vecZonas = sr.getZonas();
        if (vecZonas.isEmpty()) {
            System.out.println("No hay Zonas registrados en el sistema.");
        } else {
            for (int i = 0; i < vecZonas.size(); i++) {
                vecZonas.get(i).mostrar();
                System.out.println("");
            }
        }
        System.out.println("*****************************");
        System.out.println("\nVehiculos");
        ArrayList<Vehiculo> veh = sr.getVehiculos();
        if (veh.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");

        } else {
            for (int i = 0; i < veh.size(); i++) {
                veh.get(i).mostrar();
                System.out.println("");
            }
        }
    }

}
