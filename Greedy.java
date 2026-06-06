import java.util.ArrayList;

public class Greedy {
    ArrayList<Paquete> paquetesSinAsignar = new ArrayList<>();

    
    public ArrayList<Camion> asignarPaquetes(ArrayList<Paquete> paquetes, ArrayList<Camion> camiones){
        ArrayList<Camion> solucion = new ArrayList<>();
        paquetes = this.ordenarPaquetesPorPeso(paquetes); //ordenamos los paquetes para tomar siempre el primero.
        camiones = this.ordenarCamionesPorPeso(camiones); //ordenamos los camiones por peso de mayor a menor
        
        while(!paquetes.isEmpty()){ //mientras que tengamos paquetes para asiganar.
            Paquete x = paquetes.get(0); //tomamos el primer paquete, que es el mas pesado por la ordenación previa.

            Camion camionAsignado = esFactible(camiones, x); //es factible este paque a algun camion?
            if(camionAsignado != null){ //retorna un camion si, si. retorna null si, no.
               camionAsignado.cargarPaquete(x); //cargamos el paquete al camion asignado.
              if(!solucion.contains(camionAsignado))//si el camion asignado no esta en la solucion, lo agregamos.
                     solucion.add(camionAsignado); //agregamos el camion a la solucion.
               
            }else{
                this.paquetesSinAsignar.add(x);
            }

            paquetes.remove(0);

            if(!paquetes.isEmpty()){
            
                x = paquetes.get(0); //tomamos el siguiente paquete, que es el mas pesado por la ordenación previa.
           
            }//si ya no quedan paquetes, terminamos el proceso.
        }
        return solucion;
    }

    //ordenamos los paquetes del mas pesado al mas liviano para siempre tomar el primero.
    public ArrayList<Paquete> ordenarPaquetesPorPeso(ArrayList<Paquete> paquetes){
        //ordenamos los paquetes por peso de mayor a menor
        paquetes.sort((p1, p2) -> Float.compare(p2.getPeso(), p1.getPeso()));
        return paquetes;
    }

    public ArrayList<Camion> ordenarCamionesPorPeso(ArrayList<Camion> camiones){
        camiones.sort((c1, c2) -> Float.compare(c1.getCapacidadMaxima(), c2.getCapacidadMaxima()));
        return camiones;
    }



    public Camion esFactible(ArrayList<Camion> camiones, Paquete x){
        for (Camion c : camiones) {
            if( (c.getPesoDisponible() >= x.getPeso()) && ( (x.isContieneAlimentos() == c.isRefrigerado()) || !x.isContieneAlimentos() ) ){ //si el camion tiene espacio y si el paquete necesita refrigeracion, el camion debe ser refrigerado.
                return c;
            }
        }
        return null;
    }
}
