package entidad;

public class Usuario {
	
	private int id;
	private String user;
	private String contrasenia;
	
	
	public Usuario(int id, String nombre, String password) {
		super();
		this.id = id;
		this.user = nombre;
		this.contrasenia = password;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUser() {
		
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
