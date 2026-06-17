import java.util.ArrayList;

public class Solucion {
    private String tecnica;
    private ArrayList<Camion> camiones;
    private ArrayList<Paquete> paquetes; 
    private int metricaCosto; 

    public Solucion(String tecnica, ArrayList<Camion> camiones, ArrayList<Paquete> paquetes, int metricaCosto) {
        this.tecnica = tecnica;
        this.camiones = new ArrayList<>(camiones); 
        this.paquetes = new ArrayList<>(paquetes); 
        this.metricaCosto = metricaCosto;
    }

    private int getPesoNoAsignado() {
        int pesoTotal = 0;
        for (Paquete p : this.paquetes) {
            pesoTotal += p.getPeso(); 
        }
        return pesoTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Encabezado según la técnica
        sb.append(this.tecnica).append("\n");
        
        // Solución obtenida desglosando cada camión y sus paquetes
       // Solución obtenida desglosando cada camión y sus paquetes uno debajo de otro
        sb.append("Solución obtenida:\n");
        for (Camion c : camiones) {
            // Imprime el encabezado del camión y abre una nueva línea
            sb.append("  - Camión ").append(c.getId()).append(":\n"); 
            
            ArrayList<Paquete> paquetesDelCamion = c.getPaquetes();
            for (Paquete p : paquetesDelCamion) {
                // Agrega espacios al inicio para la indentación y un salto de línea al final
                sb.append("      * ").append(p.toString()).append("\n"); 
            }
        }
        
        // Peso no asignado
        sb.append("Peso no asignado: ").append(getPesoNoAsignado()).append(" kg.\n");
        
        // Métrica adaptada según la técnica
        if (this.tecnica.equalsIgnoreCase("Backtracking")) {
            sb.append("Métrica para analizar el costo de la solución (cantidad de estados generados): ")
              .append(this.metricaCosto).append(".\n");
        } else if (this.tecnica.equalsIgnoreCase("Greedy")) {
            sb.append("Métrica para analizar el costo de la solución (cantidad de candidatos considerados): ")
              .append(this.metricaCosto).append(".\n");
        }
        
        return sb.toString();
    }
}