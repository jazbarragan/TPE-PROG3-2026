import java.util.ArrayList;

public class Backtracking {

    /**
     * Estrategia Backtracking:
     *
     * Se exploran todas las posibles asignaciones de paquetes a camiones.
     *
     * Para cada paquete existen dos tipos de decisiones:
     *
     * 1. Asignarlo a alguno de los camiones que puedan transportarlo.
     * 2. Dejarlo sin asignar.
     *
     * El algoritmo genera recursivamente todas las combinaciones válidas
     * y, al llegar a una solución completa, calcula el peso total de los
     * paquetes que quedaron sin asignar.
     *
     * Se conserva la solución que minimiza dicho peso, ya que el objetivo
     * es dejar fuera la menor cantidad posible de carga.
     */

    private ArrayList<Camion> mejorAsignacion;
    private ArrayList<Paquete> mejoresPaquetesNoAsignados;
    private float menorPesoNoAsignado;
    private int estadosGenerados;

    public ArrayList<Camion> buscarMejorAsignacion(
            ArrayList<Paquete> paquetes,
            ArrayList<Camion> camiones) {

        ArrayList<Paquete> paquetesNoAsignadosActuales = new ArrayList<>();

        mejorAsignacion = new ArrayList<>();
        mejoresPaquetesNoAsignados = new ArrayList<>();

        menorPesoNoAsignado = Float.MAX_VALUE;
        estadosGenerados = 0;

        buscarAsignacion(camiones,
                paquetesNoAsignadosActuales,
                0,
                paquetes);

        return mejorAsignacion;
    }

    private void buscarAsignacion(ArrayList<Camion> camiones, ArrayList<Paquete> paquetesNoAsignadosActuales,
            int indicePaquete, ArrayList<Paquete> paquetes) {

        estadosGenerados++;

        if (indicePaquete >= paquetes.size()) {

            float pesoNoAsignadoActual = calcularPesoNoAsignado(paquetesNoAsignadosActuales);

            if (pesoNoAsignadoActual < menorPesoNoAsignado) {

                menorPesoNoAsignado = pesoNoAsignadoActual;

                mejoresPaquetesNoAsignados = new ArrayList<>(paquetesNoAsignadosActuales);

                mejorAsignacion = copiarCamiones(camiones);
            }

            return;
        }

        Paquete paqueteActual = paquetes.get(indicePaquete);

        for (Camion camionActual : camiones) {

            if (puedeAsignarse(paqueteActual, camionActual)) {

                camionActual.cargarPaquete(paqueteActual);

                buscarAsignacion(camiones, paquetesNoAsignadosActuales, indicePaquete + 1, paquetes);

                camionActual.descagarPaqute(paqueteActual);
            }
        }

        paquetesNoAsignadosActuales.add(paqueteActual);

        buscarAsignacion(camiones, paquetesNoAsignadosActuales, indicePaquete + 1, paquetes);

        paquetesNoAsignadosActuales.remove(paquetesNoAsignadosActuales.size() - 1);
    }

    private boolean puedeAsignarse(Paquete paquete, Camion camion) {

        return camion.getPesoDisponible() >= paquete.getPeso()
                && (!paquete.isContieneAlimentos()
                        || camion.isRefrigerado());
    }

    private float calcularPesoNoAsignado(ArrayList<Paquete> paquetesNoAsignados) {

        float pesoTotal = 0;

        for (Paquete paquete : paquetesNoAsignados) {
            pesoTotal += paquete.getPeso();
        }

        return pesoTotal;
    }

    private ArrayList<Camion> copiarCamiones(ArrayList<Camion> camiones) {

        ArrayList<Camion> copia = new ArrayList<>();

        for (Camion camion : camiones) {
            copia.add(new Camion(camion));
        }

        return copia;
    }

    public float getMenorPesoNoAsignado() {
        return menorPesoNoAsignado;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public ArrayList<Paquete> getMejoresPaquetesNoAsignados() {
        return mejoresPaquetesNoAsignados;
    }

}