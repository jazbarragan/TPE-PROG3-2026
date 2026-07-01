import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Servicios {

    private final Map<String, Paquete> indicePorCodigo;
    private final Map<Boolean, List<Paquete>> indiceAlimentos;
    private final TreeMap<Integer, List<Paquete>> indicePorUrgencia;
    private List<Camion> camiones;


    public Servicios(String pathCamiones, String pathPaquetes) {
        this.indicePorCodigo = new HashMap<>();
        this.indiceAlimentos = new HashMap<>();
        this.indicePorUrgencia = new TreeMap<>(); // Inicializamos el árbol

        this.indiceAlimentos.put(true, new ArrayList<>());
        this.indiceAlimentos.put(false, new ArrayList<>());
        inicializarPaquetesDesdeCsv(pathPaquetes);

        this.camiones = new ArrayList<>();
        inicializarCamionesDesdeCsv(pathCamiones);

    }
    //leer el csv de camiones
    private void inicializarCamionesDesdeCsv(String pathCamiones) {
        try {
            Path path = Paths.get(pathCamiones);
            List<String> lineas = Files.readAllLines(path);

            for (int i = 1; i < lineas.size(); i++) {
                String[] partes = lineas.get(i).split(";");

                int id = Integer.parseInt(partes[0]);
                String patente = partes[1];
                boolean refrigerado = partes[2].equals("1");
                Float capacidadMaxima = Float.parseFloat(partes[3]);

                Camion camion = new Camion(id, patente, refrigerado, capacidadMaxima);
                this.camiones.add(camion);
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el archivo de camiones: " + pathCamiones, e);
        }
    }

     //leer el csv de paquetes
    private void inicializarPaquetesDesdeCsv(String pathPaquetes) {
        try {
            Path path = Paths.get(pathPaquetes);
            List<String> lineas = Files.readAllLines(path);

            for (int i = 1; i < lineas.size(); i++) {
                String[] partes = lineas.get(i).split(";");

                int id = Integer.parseInt(partes[0]);
                String codigoIdentificador = partes[1];
                Float peso = Float.parseFloat(partes[2]);
                boolean contieneAlimentos = partes[3].equals("1");
                int urgencia = Integer.parseInt(partes[4]);

                Paquete paquete = new Paquete(id, codigoIdentificador, peso, contieneAlimentos, urgencia);

                this.indicePorCodigo.put(paquete.getCodigoIdentificador(), paquete);
                this.indiceAlimentos.get(paquete.isContieneAlimentos()).add(paquete);

                this.indicePorUrgencia.putIfAbsent(paquete.getUrgencia(), new ArrayList<>());
                this.indicePorUrgencia.get(paquete.getUrgencia()).add(paquete);
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el archivo de paquetes: " + pathPaquetes, e);
        }
    }

    // --- SERVICIO 1 (O(1)) ---
    public Paquete servicio1(String codigo) {
        return this.indicePorCodigo.get(codigo);
    }

    // --- SERVICIO 2 (O(1)) ---
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return this.indiceAlimentos.get(contieneAlimentos);
    }

    /**
     * SERVICIO 3: Retorna paquetes en un rango de urgencia (inclusive).
     * O(log N + K)     
     */
    public List<Paquete> servicio3(int urgenciaMin, int urgenciaMax) {
        List<Paquete> resultado = new ArrayList<>();

        Map<Integer, List<Paquete>> subMapa = this.indicePorUrgencia.subMap(urgenciaMin, true, urgenciaMax, true);

        for (List<Paquete> paquetes : subMapa.values()) {
            resultado.addAll(paquetes);
        }

        return resultado;

    }
}