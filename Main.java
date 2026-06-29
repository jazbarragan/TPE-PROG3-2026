import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // -----------------------------------------------------------------
        // 1. CARGA DE DATOS
        // -----------------------------------------------------------------
        ArrayList<Camion> camiones = leerCamiones();
        ArrayList<Paquete> paquetes = leerPaquetes();

        System.out.println("==================================================");
        System.out.println("           DATOS CARGADOS INICIALMENTE            ");
        System.out.println("==================================================");
        System.out.println("-> Camiones disponibles: " + camiones.size());
        System.out.println("-> Paquetes por enviar:  " + paquetes.size());

        // -----------------------------------------------------------------
        // 2. PRUEBA DE SERVICIOS
        // -----------------------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("               PRUEBA DE SERVICIOS                ");
        System.out.println("==================================================");

        Servicios servicios = new Servicios("camiones.csv", "paquetes.csv");

        // Servicio 1: Búsqueda por Código
        System.out.println("[Servicio 1] Buscando PKG001: " + servicios.servicio1("PKG001"));
        System.out.println("[Servicio 1] Buscando inexistente: " + servicios.servicio1("P0dcer01"));

        // Servicio 2: Filtrado por Alimentos
        System.out.println("\n[Servicio 2] Paquetes con alimentos: " + servicios.servicio2(true));
        System.out.println("[Servicio 2] Paquetes sin alimentos: " + servicios.servicio2(false));

        // Servicio 3: Rango de Urgencia
        System.out.println("\n[Servicio 3] Paquetes con urgencia entre 2 y 80: \n" + servicios.servicio3(50, 70));

        // -----------------------------------------------------------------
        // 3. EJECUCIÓN ALGORITMO GREEDY (Ávido)
        // -----------------------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("                 EJECUCIÓN GREEDY                 ");
        System.out.println("==================================================");

        Greedy greedy = new Greedy();
        Solucion solucionGreedy = greedy.asignarPaquetes(paquetes, camiones);
        System.out.println(solucionGreedy);

        // -----------------------------------------------------------------
        // 4. EJECUCIÓN ALGORITMO BACKTRACKING (Fuerza Bruta Optimizado)
        // -----------------------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("              EJECUCIÓN BACKTRACKING              ");
        System.out.println("==================================================");

        // Volvemos a leer los camiones para tenerlos limpios/vacíos de paquetes
        ArrayList<Camion> camionesBacktracking = leerCamiones();
        ArrayList<Paquete> paqueteBacktracking = leerPaquetes();
        Backtracking backtracking = new Backtracking();

        Solucion solucionBacktracking = backtracking.buscarMejorAsignacion(paqueteBacktracking, camionesBacktracking);
        System.out.println(solucionBacktracking);

       
    }

    private static Path getCsvPath(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            return path;
        }

        Path userDir = Paths.get(System.getProperty("user.dir"));
        Path candidate = userDir.resolve("TPE-PROG3-2026").resolve(fileName);
        if (Files.exists(candidate)) {
            return candidate;
        }

        candidate = userDir.resolve(fileName);
        if (Files.exists(candidate)) {
            return candidate;
        }

        candidate = userDir.resolve("src").resolve(fileName);
        if (Files.exists(candidate)) {
            return candidate;
        }

        throw new IOException("Archivo no encontrado: " + fileName + ". Rutas buscadas: "
                + path.toAbsolutePath() + ", "
                + candidate.toAbsolutePath());
    }

    // Cargar camiones desde el .csv
    private static ArrayList<Camion> leerCamiones() {
        ArrayList<Camion> camiones = new ArrayList<>();

        try {
            Path path = getCsvPath("camiones.csv");
            List<String> lineas = Files.readAllLines(path);

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
    private static ArrayList<Paquete> leerPaquetes() {
        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            Path path = getCsvPath("paquetes.csv");
            List<String> lineas = Files.readAllLines(path);

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
    private static void imprimirPaquetes(ArrayList<Paquete> paquetes) {
        for (Paquete paquete : paquetes) {
            System.out.println(paquete);
        }
    }

    private static void imprimirSolucionGreedy(ArrayList<Camion> camiones) {
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