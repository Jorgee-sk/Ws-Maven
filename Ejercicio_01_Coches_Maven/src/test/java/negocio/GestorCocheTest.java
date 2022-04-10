package negocio;

import negocio.GestorCoche;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import entidad.Coche;

public class GestorCocheTest {

	@Test
	public void testGetByKilometros() { 
		GestorCoche c = new GestorCoche();
		Coche coche = new Coche();
		coche.setMatricula("1111FFF");
		coche.setMarca("Fiat");
		coche.setModelo("Panda");
		coche.setKilometros(0);
		
		
		c.alta(coche);
		assertEquals(coche.getKilometros(), (c.obtener("1111FFF")).getKilometros());
	}
	
	@Test
	public void testGetByMarca() { 
		GestorCoche c = new GestorCoche();
		Coche coche = new Coche();
		coche.setMatricula("1111FFF");
		coche.setMarca("Fiat");
		coche.setModelo("Panda");
		coche.setKilometros(0);
		
		
		c.alta(coche);
		assertEquals(coche.getMarca(), (c.obtener("1111FFF")).getMarca());
	}
	
	@Test
	public void testGetByModelo() { 
		GestorCoche c = new GestorCoche();
		Coche coche = new Coche();
		coche.setMatricula("1111FFF");
		coche.setMarca("Fiat");
		coche.setModelo("Panda");
		coche.setKilometros(0);
		
		
		c.alta(coche);
		assertEquals(coche.getModelo(), (c.obtener("1111FFF")).getModelo());
	}
	
}
