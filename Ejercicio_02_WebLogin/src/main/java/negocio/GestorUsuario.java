package negocio;

import interfaces.DaoUsuarios;
import persistencia.DaoUsuarioMySql;

public class GestorUsuario {
	
	private DaoUsuarios daoUsuario = new DaoUsuarioMySql();
	
}
