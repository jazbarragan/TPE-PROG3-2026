import java.util.ArrayList;

public class Camion {
	private int id;
	private String patente;
	private boolean refrigerado;
	private Float capacidadMaxima;
	private Float pesoDisponible;
	private ArrayList<Paquete> paquetes = new ArrayList<>();

	public Camion(int id, String patente, boolean refrigerado, Float capacidadMaxima) {
		this.id = id;
		this.patente = patente;
		this.refrigerado = refrigerado;
		this.capacidadMaxima = capacidadMaxima;
        this.pesoDisponible = capacidadMaxima;
		this.paquetes = new ArrayList<>();
	}

    public void cargarPaquete(Paquete paquete){
        this.pesoDisponible -= paquete.getPeso();
        this.paquetes.add(paquete);
    }

	public void descagarPaqute(Paquete paquete){
		if (this.paquetes.contains(paquete)) {
			this.pesoDisponible += paquete.getPeso();
			this.paquetes.remove(paquete);	
		}		
	}

    public Float getPesoDisponible() {
        return pesoDisponible;
    }

	public ArrayList<Paquete> getPaquetes() {
		return new ArrayList<>(paquetes);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public boolean isRefrigerado() {
		return refrigerado;
	}

	public void setRefrigerado(boolean refrigerado) {
		this.refrigerado = refrigerado;
	}

	public Float getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(Float capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	@Override
	public String toString() {
		return "Camion{id=" + id + ", patente='" + patente + "', refrigerado=" + refrigerado + ", capacidadMaxima=" + capacidadMaxima + "}";
	}
}
