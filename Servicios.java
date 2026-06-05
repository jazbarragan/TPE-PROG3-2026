import java.util.ArrayList;

public class Servicios {
    
    public Paquete servicio1(ArrayList<Paquete> paquetes, String codigo) {
        // Servicio 1: Dado un código de paquete (String), retornar toda la información
        //del paquete asociado. En caso de no existir, retornar null.
        for (Paquete paquete : paquetes) {
            if (paquete.getCodigoIdentificador().equals(codigo)) {
                return paquete;
            }
        }
        return null;
    }

    public ArrayList<Paquete> servicio2(ArrayList<Paquete> paquetes, boolean contieneAlimentos) {
        // Servicio 2: Dado un booleano que indica si se buscan paquetes que
        // contienen alimentos (true) o que no contienen alimentos (false), retornar el
        // listado de paquetes correspondiente.
        ArrayList<Paquete> resultado = new ArrayList<>();
        for (Paquete paquete : paquetes) {
            if (paquete.isContieneAlimentos() == contieneAlimentos) {
                resultado.add(paquete);
            }
        }
        return resultado;
    }

    public ArrayList<Paquete> servicio3(ArrayList<Paquete> paquetes, int urgenciaMin, int urgenciaMax) {
            // Servicio 3: Dados dos valores enteros que representan un nivel de urgencia
            // mínimo y máximo, retornar todos los paquetes cuyo nivel de urgencia se
            // encuentre dentro de ese rango (inclusive)
        ArrayList<Paquete> resultado = new ArrayList<>();
        for (Paquete paquete : paquetes) {
            if (paquete.getUrgencia() >= urgenciaMin && paquete.getUrgencia() <= urgenciaMax) {
                resultado.add(paquete);
            }
        }
        return resultado;
    }
}
