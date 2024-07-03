package guarderia_central;

import java.util.Scanner;

public class EntradaSalida {

    public static String scaneo(){
        Scanner scanner = new Scanner(System.in);
        String x=scanner.nextLine();
        return x;
    }
    public static int scaneoE(){
        Scanner scanner = new Scanner(System.in);
        int x=scanner.nextInt();
        return x;
    }
    public static char leerChar(String texto) {
        mostrarString(texto);
        String st = scaneo();
        return (st == null || st.length() == 0 ? '0' : st.charAt(0));
        //return st;
    }

    public static String leerString(String texto) {
        mostrarString(texto);
        String st = scaneo();
        return st;
    }
    public static int leerInt(String t){
        mostrarString(t);
        int i=scaneoE();
        return i;
    }

    public static boolean leerBoolean(String texto) {
        boolean r = false;
        int i;
        do {
            mostrarString(texto);
            System.out.println("1-si\t2-no");
            i = scaneoE();
            if (i == 1) {
                r = true;
            } else if (i == 2) {
                r = false;
            }
        } while (i != 1 && i != 2);
        return r;
    }

    public static void mostrarString(String s) {
        //JOptionPane.showMessageDialog(null, s);
        System.out.println(s);
    }

}
