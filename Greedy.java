import java.util.ArrayList;

public class Greedy {
    ArrayList<Paquete> paquetesSinAsignar = new ArrayList<>();

    
    public ArrayList<Camion> asignarPaquetes(ArrayList<Paquete> paquetes, ArrayList<Camion> camiones){
        ArrayList<Camion> solucion = new ArrayList<>();

        while(!esSolucion(solucion) && !paquetes.isEmpty()){
           //selecciona el paquete mas pesado
            Paquete x = this.seleccionar(paquetes);

            //este metodo devuelve
            //Camion: para asignar
            //null: no se puede asignar a ningun camion
            Camion camionAsignado = esFactible(camiones, x);
            if(camionAsignado != null){
                for (Camion c : camiones) {
                    if(c.getId() == camionAsignado.getId()){
                        c.cargarPaquete(x);
                        solucion.add(c);
                    }
                    
                }
            }else{
                this.paquetesSinAsignar.add(x);
            }

            paquetes.remove(x);
            
            x = this.seleccionar(paquetes);
            
        }
        return solucion;
    }

//ORDENAR LOS PAQUETES DEL MAS PESANDO AL MAS LIBINO, PARA QUE SOLO AGREGER Y QUITE SIEMPRE EL PRIMERO Y NO TENGA QUE BUSCAR POR
// POR TODO EL ARREGLO
    public Paquete seleccionar(ArrayList<Paquete> paquetes) {
    // 1. Inicializamos en null por seguridad (si la lista está vacía, devuelve null)
    Paquete paqueteSolucion = new Paquete(0, null, null, false, 0); 
    float pesoMax = 0;

    for (Paquete paquete : paquetes) {
        // 2. Buscamos el mayor peso
        if (paquete.getPeso() > pesoMax) {
            pesoMax = paquete.getPeso();
            paqueteSolucion = paquete; 
        }
    }
    
    return paqueteSolucion;
    }


    public Camion esFactible(ArrayList<Camion> camiones, Paquete x){
        //va a retornar si existe un camion de la lista de camiones 
        //que cumpla con las restricciones para asignar el paque, sino null
    }
}
