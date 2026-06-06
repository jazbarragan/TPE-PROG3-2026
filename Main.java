import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Leer camiones del .csv e imprimirlos
        ArrayList<Camion> camiones = leerCamiones();
        //imprimirCamiones(camiones);

        // Leer paquetes del .csv e imprimirlos
        ArrayList<Paquete> paquetes = leerPaquetes();
        //imprimirPaquetes(paquetes);

        // --- SERVICIOS ---
        Servicios servicios = new Servicios();
        servicios.inicializarIndices(paquetes);

        // --- PRUEBA SERVICIO 1 ---
        System.out.println("----- Servicio 1 (Por Código) -----");

        System.out.println(servicios.servicio1("P001"));
        System.out.println(servicios.servicio1("P0dcer01"));

        // --- PRUEBA SERVICIO 2 ---
        System.out.println("\n----- Servicio 2 (Por Alimentos) ------");
        // Podés probar el servicio 2 que optimizamos antes de forma directa e
        // instantánea
        System.out.println("Paquetes con alimentos: " + servicios.servicio2(true));
        System.out.println("Paquetes sin alimentos: " + servicios.servicio2(false));

        System.out.println("----- Servicio 3 (de un X a un Y) -----");
        System.out.println("Paquetes con urgencia entre X y Y: " + servicios.servicio3(2,80));
    
    
        System.out.println("------------------------GREEDY------------------------");
        Greedy greedy = new Greedy();
        ArrayList<Camion> solucionGreedy = greedy.asignarPaquetes(paquetes, camiones);
        imprimirSolucionGreedy(solucionGreedy);

    
    }

    // Cargar camiones desde el .csv
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

    // Cargar paquetes desde el .csv
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

    // Imprimir paquetes
    public static void imprimirPaquetes(ArrayList<Paquete> paquetes) {
        for (Paquete paquete : paquetes) {
            System.out.println(paquete);
        }
    }

    public static void imprimirSolucionGreedy(ArrayList<Camion> camiones) {
        for (Camion camion : camiones) {
            System.out.print("Camion con id " + camion.getId() + " tiene los paquetes cargados con id: ");

            if (camion.getPaquetes().isEmpty()) {
                System.out.println("ninguno");
            } else {
                for (int i = 0; i < camion.getPaquetes().size(); i++) {
                    System.out.print(camion.getPaquetes().get(i).getId());

                    if (i < camion.getPaquetes().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
    }
}