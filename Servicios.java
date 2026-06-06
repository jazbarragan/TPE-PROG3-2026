import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Servicios {
    
    private final Map<String, Paquete> indicePorCodigo;
    private final Map<Boolean, List<Paquete>> indiceAlimentos;
    
    // NUEVO ÍNDICE: Un Árbol para búsquedas por rangos de urgencia
    private final TreeMap<Integer, List<Paquete>> indicePorUrgencia;

    public Servicios() {
        this.indicePorCodigo = new HashMap<>();
        this.indiceAlimentos = new HashMap<>();
        this.indicePorUrgencia = new TreeMap<>(); // Inicializamos el árbol
        
        this.indiceAlimentos.put(true, new ArrayList<>());
        this.indiceAlimentos.put(false, new ArrayList<>());
    }

    /**
     * Procesa la lista por única vez, armando los TRES índices en un solo bucle.
     * Costo computacional: O(N log N) debido a la inserción en el árbol.
     */
    public void inicializarIndices(List<Paquete> paquetes) {
        for (Paquete paquete : paquetes) {
            // 1. Índice Servicio 1
            this.indicePorCodigo.put(paquete.getCodigoIdentificador(), paquete);
            
            // 2. Índice Servicio 2
            this.indiceAlimentos.get(paquete.isContieneAlimentos()).add(paquete);
            
            // 3. Índice Servicio 3 (Árbol por Urgencia)
            int urgencia = paquete.getUrgencia();
            // Si es la primera vez que vemos este nivel de urgencia, creamos su lista
            this.indicePorUrgencia.putIfAbsent(urgencia, new ArrayList<>());
            this.indicePorUrgencia.get(urgencia).add(paquete);
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
     * Costo computacional: Mucho menor a O(N). Solo procesa las urgencias del rango.
     */
    public List<Paquete> servicio3(int urgenciaMin, int urgenciaMax) {
        List<Paquete> resultado = new ArrayList<>();
        
        // subMap obtiene la parte del árbol entre el min y el max de forma ultra rápida
        // Los 'true' indican que incluya los extremos (inclusive)
        Map<Integer, List<Paquete>> rangoUrgencias = this.indicePorUrgencia.subMap(urgenciaMin, true, urgenciaMax, true);
        
        // Juntamos las listas de los niveles de urgencia que cayeron en el rango
        for (List<Paquete> listaPaquetes : rangoUrgencias.values()) {
            resultado.addAll(listaPaquetes);
        }
        
        return resultado;
    }
}