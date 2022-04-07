package entidad;

import java.util.HashSet;
import java.util.Set;

public class Coche {
	
	private static final Set<String> matriculas = new HashSet<String>();
	
	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private int kilometros;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula){
        
		this.matricula = matricula;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public int getKilometros() {
		return kilometros;
	}
	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}
	
	public String toString() {
		return "Coche [ID: " + id + ", MATRICULA: " + matricula + ", MARCA: " + marca + ", MODELO: " + modelo + ", KILOMETROS: "+kilometros+"]";
	}
	
	
}
