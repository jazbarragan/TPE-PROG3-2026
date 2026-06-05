import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {

        //Leer camiones del .csv e imprimirlos
        ArrayList<Camion> camiones = leerCamiones();
        imprimirCamiones(camiones);

        //Leer paquetes del .csv e imprimirlos
        ArrayList<Paquete> paquetes = leerPaquetes();
        imprimirPaquetes(paquetes);

        //--- SERVICIOS ---
        Servicios servicios = new Servicios();
        System.out.println("--- Servicio 1 ---");
        System.out.println(servicios.servicio1(paquetes, "P001"))  ;
        System.out.println(servicios.servicio1(paquetes, "P0dcer01"))  ;


        System.out.println("--- Servicio 2 true ---");
        imprimirPaquetes(servicios.servicio2(paquetes, true));    
        System.out.println("--- Servicio 2 false ---");
        imprimirPaquetes(servicios.servicio2(paquetes, false));

        System.out.println("--- Servicio 3 ---");
        imprimirPaquetes(servicios.servicio3(paquetes, 9, 90));
    }

    //Cargar camiones desde el .csv
    public static ArrayList<Camion> leerCamiones() {
        ArrayList<Camion> camiones = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get("camiones.csv"));

            for (int i = 1; i < lineas.size(); i++) {
                String[] partes = lineas.get(i).split(";");

                int id = Integer.parseInt(partes[0]);
                String patente = partes[1];
                boolean refrigerado = partes[2].equals("1");
                Float capacidad = Float.parseFloat(partes[3]);

                camiones.add(new Camion(id, patente, refrigerado, capacidad));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return camiones;
    }

    public static void imprimirCamiones(ArrayList<Camion> camiones) {
        for (Camion camion : camiones) {
            System.out.println(camion);
        }
    }

    //Cargar paquetes desde el .csv
    public static ArrayList<Paquete> leerPaquetes() {
        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get("paquetes.csv"));

            for (int i = 1; i < lineas.size(); i++) {
                String[] partes = lineas.get(i).split(";");

                int id = Integer.parseInt(partes[0]);
                String codigoIdentificador = partes[1];
                Float peso = Float.parseFloat(partes[2]);
                boolean contieneAlimentos = partes[3].equals("1");
                int urgencia = Integer.parseInt(partes[4]);

                paquetes.add(new Paquete(id, codigoIdentificador, peso, contieneAlimentos, urgencia));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return paquetes;
    }

    //Imprimir paquetes
    public static void imprimirPaquetes(ArrayList<Paquete> paquetes) {
        for (Paquete paquete : paquetes) {
            System.out.println(paquete);
        }
    }
}