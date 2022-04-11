package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import com.google.gson.Gson;


import entidad.Coche;
import negocio.GestorCoche;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		
		GestorCoche gestor = new GestorCoche();
		boolean terminado = false;
		
		validarUsuario();
		
		do {
			menu();
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Introduzca los datos del coche:  Matricula | Marca | Modelo | Kilometros");
				String matricula = sc.next();
				String marca = sc.next();
				String modelo = sc.next();
				int km = sc.nextInt();
				Coche c = new Coche();
				
				c.setId(0);
				c.setMatricula(matricula);
				c.setMarca(marca);
				c.setModelo(modelo);
				c.setKilometros(km);
				
				
				int añadir  = gestor.alta(c);
				
				if(añadir == 0) {
					System.out.println("Coche añadido");
				}else if(añadir == 1) {
					System.out.println("Error de conexión con la BBDD");
				}else if(añadir == 2){
					System.out.println("La matricula es invalida");
				}else if(añadir == 3) {
					System.out.println("Los kilometros son invalidos");
				}else if(añadir == 4) {
					System.out.println("La matricula ya existe");
				}
				break;
			case 2:
				System.out.println("Introduzca la ID del coche que quiere eliminar");
				int id = sc.nextInt();
				
				Boolean eliminado = gestor.eliminar(id);
				if(eliminado == true) {
					System.out.println("Coche eliminado");
				}else {
					System.out.println("El coche no se pudo eliminar");
				}
				
				break;
			case 3:
				System.out.println("Introduzca los datos del coche a modificar: | Matricula | Marca | Modelo | Kilometros");
				id = sc.nextInt();
				matricula = sc.next();
				marca = sc.next();
				modelo = sc.next();
				km = sc.nextInt();
				c = new Coche();
				
				c.setId(id);
				c.setMatricula(matricula);
				c.setMarca(marca);
				c.setModelo(modelo);
				c.setKilometros(km);
				
				int mod = gestor.modificar(c);
				if(mod == 0) {
					System.out.println("Coche modificado");
				}else if(mod == 1) {
					System.out.println("Error de conexión con la BBDD");
				}else if(mod == 2){
					System.out.println("La matricula es invalida");
				}else if(mod == 3) {
					System.out.println("Los kilometros son invalidos");
				}
				break;
			case 4:
				System.out.println("Introduzca la ID del coche a buscar: ");
				id = sc.nextInt();
				
				List<Coche> lista = gestor.listar();
				
				if(!lista.isEmpty()) {
					c = gestor.obtener(id);
					System.out.println("Coche [ID: " + c.getId() + ", MATRICULA: " + c.getMatricula() + ", MARCA: " + c.getMarca() + ", MODELO: " + c.getModelo() + ", KILOMETROS: "+c.getKilometros()+"]");
				}else {
					System.out.println("No hay coches con esa ID");
				}
				
				break;
				
				
			case 5:
				System.out.println("Introduzca la matricula del coche a buscar: ");
				matricula = sc.next();
				
				lista = gestor.listar();
				
				if(!lista.isEmpty()) {
					c = gestor.obtener(matricula);
					System.out.println("Coche [ID: " + c.getId() + ", MATRICULA: " + c.getMatricula() + ", MARCA: " + c.getMarca() + ", MODELO: " + c.getModelo() + ", KILOMETROS: "+c.getKilometros()+"]");
				}else {
					System.out.println("No hay coches con esa matricula");
				}
				break;
			case 6:
				System.out.println("Introduzca la marca del coche a buscar: ");
				marca = sc.next();
				
				lista = gestor.listar();
				
				if(!lista.isEmpty()) {
					c = gestor.obtener(marca);
					System.out.println("Coche [ID: " + c.getId() + ", MATRICULA: " + c.getMatricula() + ", MARCA: " + c.getMarca() + ", MODELO: " + c.getModelo() + ", KILOMETROS: "+c.getKilometros()+"]");
				}else {
					System.out.println("No hay coches con esa marca");
				}
				break;
			case 7:
				System.out.println("Introduzca el modelo del coche a buscar: ");
				modelo = sc.next();
				
				lista = gestor.listar();
				
				if(!lista.isEmpty()) {
					c = gestor.obtener(modelo);
					System.out.println("Coche [ID: " + c.getId() + ", MATRICULA: " + c.getMatricula() + ", MARCA: " + c.getMarca() + ", MODELO: " + c.getModelo() + ", KILOMETROS: "+c.getKilometros()+"]");
				}else {
					System.out.println("No hay coches con ese modelo");
				}
				break;
			case 8:
				lista = gestor.listar();
				if(!lista.isEmpty())
				for(int i=0;i<lista.size();i++) {
					System.out.println(lista.get(i));
				}
				else
					System.out.println("No hay coches en la base de datos");
				break;
			case 9:
				
				System.out.println("Exportando los coches registrados en la base de datos a formato JSON... ");
				lista = gestor.listar();
				Gson gson = new Gson();
				String jsonCoche = gson.toJson(lista);
				
				File file = new File("Coches.json");
				
				try(FileWriter fw = new FileWriter(file)) {
					fw.write(jsonCoche);
					System.out.println("Fichero JSON generado con éxito.");
				} catch(IOException e) {
					e.printStackTrace();
				}
				break;
				
			case 10: 
				
				String[] columExcel = { "ID", "Matricula", "Marca", "Modelo",
			    "Kilometros" };
				lista = gestor.listar();
				
				Workbook workbook = new XSSFWorkbook();
			    Sheet sheet =  workbook.createSheet("Coches");
			    
			    //La configuracion de la cabecera
			    Font headerFont = workbook.createFont();
			    headerFont.setBold(true);
			    headerFont.setFontHeightInPoints((short) 14);
			    headerFont.setColor(IndexedColors.BLACK.getIndex());

			    CellStyle headerCellStyle = workbook.createCellStyle();
			    headerCellStyle.setFont(headerFont);

			    // Creamos una fila con los valores de la cabecera
			    Row headerRow = sheet.createRow(0);

			    for (int i = 0; i < columExcel.length; i++) {
			      Cell cell = headerRow.createCell(i);
			      cell.setCellValue(columExcel[i]);
			      cell.setCellStyle(headerCellStyle);
			    }

			    // Creamos las filas con datos
			    int rowNum = 1;

			    for (Coche coches : lista) {
			      Row row = sheet.createRow(rowNum++);
			      row.createCell(0).setCellValue(coches.getId());
			      row.createCell(1).setCellValue(coches.getMatricula());
			      row.createCell(2).setCellValue(coches.getMarca());
			      row.createCell(3).setCellValue(coches.getModelo());
			      row.createCell(4).setCellValue(coches.getKilometros());
			    }

			    // Resize para que se adapte al excel
			    for (int i = 0; i < columExcel.length; i++) {
			      sheet.autoSizeColumn(i);
			    }

			    // Escribimos en el excel
			    FileOutputStream fileOut = new FileOutputStream("Coches.xlsx");
			    workbook.write(fileOut);
			    fileOut.close();
				break;
				
			case 0:
				terminado = true;
				break;
			}
		}while(!terminado);
		
		System.out.println("Fin de programa");

	}
	
	private static void validarUsuario() {

		String nombre, contra;
		boolean contraBoolean = false;

		do {
			System.out.println("======== LOGIN =========");
			System.out.println("Introduzca el usuario: ");
			nombre = sc.next();
			System.out.println("Introduzca la contraseña: ");
			contra = sc.next();

			try {
				HttpRequest request = HttpRequest.newBuilder()
						.uri(new URI("http://localhost:8080/Ejercicio_WebLogin/usuarios/login?usuario="
								+ nombre + "&password=" + contra))
						.GET().build();

				HttpClient client = HttpClient.newHttpClient();

				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				
				JSONObject json = new JSONObject(response.body());
				contraBoolean = json.getBoolean("validado"); 
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} while (!contraBoolean);

	}
	
	
	
	private static void menu() {
		System.out.println();
		System.out.println("======== MENU ========");
		System.out.println("1- Alta de coche");
		System.out.println("2- Eliminar coche por id");
		System.out.println("3- Modificar coche por id");
		System.out.println("4- Buscar coche por id");
		System.out.println("5- Buscar coche por matricula");
		System.out.println("6- Buscar coche por marca");
		System.out.println("7- Buscar coche por modelo");
		System.out.println("8- Listar coches");
		System.out.println("9- Exportar coches a JSON");
		System.out.println("10- Exportar coches a EXCEL");
		System.out.println("0- Salir del programa");
	}

}
