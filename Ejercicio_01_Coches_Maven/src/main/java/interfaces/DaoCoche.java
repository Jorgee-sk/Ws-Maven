package interfaces;

import java.util.List;
import entidad.Coche;

public interface DaoCoche {
	
	boolean alta(Coche c);
	boolean eliminar(int id);
	boolean modificar(Coche c);
	Coche obtener(int id);
	Coche obtener(String matricula);
	Coche obtenerMarca(String marca);
	Coche obtenerModelo(String modelo);
	List<Coche> listar();

}
