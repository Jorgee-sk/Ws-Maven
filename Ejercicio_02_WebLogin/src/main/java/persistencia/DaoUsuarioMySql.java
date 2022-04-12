package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;
import interfaces.DaoUsuarios;

public class DaoUsuarioMySql implements DaoUsuarios{
	
	private Connection conexion;
	
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String usuario = "root";
		String password = "";
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean alta(Usuario u) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into usuarios (USUARIO,CONTRASENIA) "
				+ " values(?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, u.getUser());
			ps.setString(2, u.getContrasenia());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + u.getUser());
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean eliminar(String nUsuario, String contra) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from usuarios where usuario = ? and contrasenia = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, nUsuario);
			ps.setString(2, contra);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("No se ha podido dar de baja el usuario: "+ nUsuario);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public boolean modificar(Usuario u) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update usuarios set USUARIO=?, CONTRASENIA=? WHERE USUARIO=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, u.getUser());
			ps.setString(2, u.getContrasenia());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
			else
				modificado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar la "
					+ " persona " + u);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public boolean obtener(Usuario u) {
		
		if(!abrirConexion()){
			return false;
		}		
		Usuario user = null;
		
		String query = "select USUARIO,CONTRASENIA from usuarios "
				+ "where usuario = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, u.getUser());
			
			user = new Usuario();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				user.setUser(rs.getString(1));
				user.setContrasenia(rs.getString(2));

			}
			if(u.getContrasenia().equals(user.getContrasenia()) 
					&& u.getUser().equals(user.getUser())) return true;
			
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el usuario "+ u.getUser());
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return false;
	}
	
	
	@Override
	public List<Usuario> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Usuario> listaUser = new ArrayList<>();
		
		String query = "select USUARIO,CONTRASENIA from usuarios";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Usuario user = new Usuario();
				user.setUser(rs.getString(1));
				user.setContrasenia(rs.getString(2));
				
				listaUser.add(user);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los usuarios");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaUser;
	}

}
