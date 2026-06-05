import java.util.ArrayList;

public class Camion {
	private int id;
	private String patente;
	private boolean refrigerado;
	private Float capacidadMaxima;
    ArrayList<Paquete> paquetes;

	public Camion(int id, String patente, boolean refrigerado, Float capacidadMaxima) {
		this.id = id;
		this.patente = patente;
		this.refrigerado = refrigerado;
		this.capacidadMaxima = capacidadMaxima;
        this.paquetes = new ArrayList<>();
	}

    public void cargarPaquete(Paquete paquete){
        this.paquetes.add(paquete);
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
