package interfaces;

import java.util.List;

import entidad.Usuario;

public interface DaoUsuarios {
	
	boolean alta(Usuario u);
	boolean eliminar(String nUsuario, String contra);
	boolean modificar(Usuario u);
	boolean obtener(Usuario u);
	List<Usuario> listar();
}
