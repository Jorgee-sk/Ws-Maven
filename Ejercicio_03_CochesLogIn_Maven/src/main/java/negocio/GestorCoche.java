package negocio;

import java.util.List;

import entidad.Coche;
import interfaces.DaoCoche;
import persistencia.DaoCocheMySql;

public class GestorCoche {
		//Usamos sql
		private DaoCoche daoCoche = new DaoCocheMySql();
		
		
		public int alta(Coche c){
			
			
			
			if(c.getMatricula().length() == 7 && c.getKilometros()>=0) {
				if(getMatriculaUnique(c.getMatricula())) {
					return 4;
				}
				boolean alta = daoCoche.alta(c);
				if(alta) {
					return 0;
				}else {
					return 1;
				}
			}else if(c.getMatricula().length() >= 7){
				return 2;
			}else {
				return 3;
			}
		}
		
		public boolean eliminar(int id){
			boolean eliminado = daoCoche.eliminar(id);
			return eliminado;
		}
		
	
		public int modificar(Coche c){
			if(c.getMatricula().length() == 7 && c.getKilometros()>=0) {
				boolean modificada = daoCoche.modificar(c);
				if(modificada) {
					return 0;
				}else {
					return 1;
				}
			}else if(c.getMatricula().length() >= 7){
				return 2;
			}else {
				return 3;
			}
		}
		
		public Boolean getMatriculaUnique(String matricula) {
		 
				if(daoCoche.obtener(matricula)!=null){
					return true;
				}else {
					return false;
				}
		}
		
		public Coche obtener(int id){
			
			Coche car = daoCoche.obtener(id);
			return car;
		}
		
		public Coche obtener(String matricula){
			Coche car = daoCoche.obtener(matricula);
			return car;
		}
		
		public Coche obtenerMarca(String marca){
			Coche car = daoCoche.obtenerMarca(marca);
			return car;
		}
		
		public Coche obtenerModelo(String modelo){
			Coche car = daoCoche.obtenerModelo(modelo);
			return car;
		}
		
		public List<Coche> listar(){
			List<Coche> listaCoches = daoCoche.listar();
			return listaCoches;
		}
		
	
}
