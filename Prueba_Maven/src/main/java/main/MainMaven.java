package main;

import java.util.ArrayList;
import java.util.List;

public class MainMaven {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola Mundo maven");
		
		List<String> mvnList = new ArrayList<String>();
		mvnList.add("Esto es");
		mvnList.add("Maven");
		
		mvnList.forEach(s -> System.out.println(s));
		
	}

}
