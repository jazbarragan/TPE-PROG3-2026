import java.util.ArrayList;

public class Greedy {

    /**
     * Estrategia Greedy:
     *
     * 1. Se ordenan los paquetes priorizando:
     *    - Mayor peso.
     *
     * 2. Se ordenan los camiones por capacidad máxima.
     *
     * 3. En cada iteración se toma el paquete de mayor prioridad
     *    y se lo asigna al primer camión que pueda transportarlo.
     *
     * 4. Si ningún camión puede cargar el paquete, éste se agrega
     *    a la lista de paquetes no asignados.
     *
     * La estrategia es greedy porque toma decisiones locales
     * sin reconsiderar asignaciones realizadas anteriormente.
     */

    private ArrayList<Paquete> paquetesNoAsignados = new ArrayList<>();

    public Solucion asignarPaquetes(ArrayList<Paquete> paquetes, ArrayList<Camion> camiones) {
        int estados = 0;

        ArrayList<Camion> camionesUtilizados = new ArrayList<>();

        paquetes = ordenarPaquetesPorPeso(paquetes);
        camiones = ordenarCamionesPorCapacidad(camiones);

        while (!paquetes.isEmpty()) {

            Paquete paqueteActual = paquetes.get(0);
            estados++;

            Camion camionSeleccionado = seleccionarCamion(camiones, paqueteActual);

            if (camionSeleccionado != null) {

                camionSeleccionado.cargarPaquete(paqueteActual);

                if (!camionesUtilizados.contains(camionSeleccionado)) {
                    camionesUtilizados.add(camionSeleccionado);
                }

            } else {
                paquetesNoAsignados.add(paqueteActual);
            }

            paquetes.remove(0);
        }

        return new Solucion("Greedy", camionesUtilizados, paquetesNoAsignados, estados);
    }

    public ArrayList<Paquete> ordenarPaquetesPorPeso(ArrayList<Paquete> paquetes) {

        paquetes.sort((p1, p2) -> {
            return Float.compare(p2.getPeso(), p1.getPeso());
        });

        return paquetes;
    }

    public ArrayList<Camion> ordenarCamionesPorCapacidad(ArrayList<Camion> camiones) {

        camiones.sort((c1, c2) ->
                Float.compare(c1.getCapacidadMaxima(), c2.getCapacidadMaxima()));

        return camiones;
    }

    public Camion seleccionarCamion(ArrayList<Camion> camiones, Paquete paquete) {

        for (Camion camionActual : camiones) {

            boolean tieneCapacidad =
                    camionActual.getPesoDisponible() >= paquete.getPeso();

            boolean cumpleRefrigeracion =
                    !paquete.isContieneAlimentos() || camionActual.isRefrigerado();

            if (tieneCapacidad && cumpleRefrigeracion) {
                return camionActual;
            }
        }

        return null;
    }

    public ArrayList<Paquete> getPaquetesNoAsignados() {
        return paquetesNoAsignados;
    }
}