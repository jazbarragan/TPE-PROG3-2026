public class Paquete {
	private int id;
	private String codigoIdentificador;
	private Float peso;
	private boolean contieneAlimentos;
	private int urgencia;

	public Paquete(int id, String codigoIdentificador, Float peso, boolean contieneAlimentos, int urgencia) {
		this.id = id;
		this.codigoIdentificador = codigoIdentificador;
		this.peso = peso;
		this.contieneAlimentos = contieneAlimentos;
		this.urgencia = validarUrgencia(urgencia);
	}

	private static int validarUrgencia(int urgencia) {
		if (urgencia < 1) {
			return 1;
		}
		if (urgencia > 100) {
			return 100;
		}
		return urgencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoIdentificador() {
		return codigoIdentificador;
	}

	public void setCodigoIdentificador(String codigoIdentificador) {
		this.codigoIdentificador = codigoIdentificador;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public boolean isContieneAlimentos() {
		return contieneAlimentos;
	}

	public void setContieneAlimentos(boolean contieneAlimentos) {
		this.contieneAlimentos = contieneAlimentos;
	}

	public int getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(int urgencia) {
		this.urgencia = validarUrgencia(urgencia);
	}

	@Override
	public String toString() {
		return "Paquete{id=" + id + ", codigoIdentificador='" + codigoIdentificador + "', peso=" + peso + ", contieneAlimentos=" + contieneAlimentos + ", urgencia=" + urgencia + "}";
	}
}
