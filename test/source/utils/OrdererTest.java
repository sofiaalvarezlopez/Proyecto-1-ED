package utils;
//import utils.Order;
import utils.Sort;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class OrdererTest {
	/**
	 * Ordenador que se probar�
	 */
	//private Order<Integer> orderer;
	private Sort<Integer> sorter;

	/**
	 * lista para realizar las pruebas
	 */
	private Comparable<Integer>[] lista;

	/**
	 * Prepara el escenario de pruebas
	 */
	@Before
	public void setUpEscenario1()
	{
		//orderer = new Order<Integer>();
		sorter = new Sort<Integer>();
		lista = new Integer[1000];
		for (int i = 0; i < lista.length; i++) 
		{
			int random = (int) Math.floor(Math.random() * 1000);
			lista[i] = random;
		}
	}

	//	@Test
	//	public void testQuick()
	//	{
	//		setUpEscenario1();
	//		//		orderer.ordenarQuick( lista);
	//		sorter.quickSort(lista);
	//		for (int i = 0; i < lista.length - 1; i++)
	//		{
	//			Integer int1 = ((Integer) lista[i]).intValue();
	//			Integer int2 = ((Integer) lista[i + 1]).intValue();
	//			System.out.println(lista[i]);
	//			assertTrue(" ordenada mal ascendentemente", int1 <= int2);
	//
	//		}
	//	}
	//
	//	@Test
	//	public void testMerge()
	//	{
	//		setUpEscenario1();
	//		sorter.mergeSort(lista);
	//		for (int i = 0; i < lista.length - 1; i++)
	//		{
	//			Integer int1 = ((Integer) lista[i]).intValue();
	//			Integer int2 = ((Integer) lista[i + 1]).intValue();
	//			System.out.println(lista[i]);
	//			assertTrue(" ordenada mal ascendentemente", int1 <= int2);
	//
	//		}
	//	}

}
