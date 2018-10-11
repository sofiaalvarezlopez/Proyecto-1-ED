package model.data_structures;

import junit.framework.TestCase;
import model.data_structures.Node;
import model.vo.VOTrip;

public class DoublyLinkedListTest extends TestCase
{

	private DoublyLinkedList<VOTrip> list;

	private void setUpScenario1() throws Exception
	{
		VOTrip trip = new VOTrip(2000, "3/31/2017 23:59:07", "3/31/2017 23:59:07", 321, 567, 654, "FromStation", 456, "ToStation", "Sofia", "Female", "2000");
		VOTrip trip1 = new VOTrip(1994, "3/31/2017 23:59:07", "3/31/2017 23:59:07", 321, 567, 654, "FromStation", 456, "ToStation", "Milo", "Male", "1994");
		list = new DoublyLinkedList<VOTrip>(new Node<VOTrip>(trip));
		list.addAtEnd(trip1);
	}
	
	private void setUpScenario2()
	{
		list = new DoublyLinkedList<VOTrip>();
	}
	
	public void testInitialState() throws Exception
	{
		setUpScenario1();
		assertTrue("There must be two nodes.", list.size() == 2);
		
		setUpScenario2();
		assertTrue("There must be two nodes.", list.size() == 0);
	}
	
	public void testAddAtBeginning() throws Exception
	{
		setUpScenario1();
		VOTrip trip1 = new VOTrip(26111994, "3/31/2017 23:59:07", "3/31/2017 23:59:07", 321, 567, 654, "FromStation", 456, "ToStation", "Novio", "Male", "1994");
		list.addAtBeginning(trip1);
		assertTrue("The elements should be the same.", list.getNode(0).getElement().getid() == trip1.getid());
	
		setUpScenario2();
		list.addAtBeginning(trip1);
		assertNotNull("The elements should not be null", list.getNode(0));
		
		trip1 = null;
		try
		{
			list.addAtBeginning(trip1);
			fail("Can not add a null element");
		}
		catch (Exception e) 
		{
			// It should catch the Exception.
		}
		

	}
	
	public void testAddLast() throws Exception
	{
		setUpScenario1();
		VOTrip trip2 = new VOTrip(2611, "3/31/2017 23:59:07", "3/31/2017 23:59:07", 321, 567, 654, "FromStation", 456, "ToStation", "Milo", "Male", "1994");
		list.addAtEnd(trip2);
		assertTrue("The elements should be the same.", list.getNode(list.size() - 1).getElement().getid() == trip2.getid());
		
		setUpScenario2();
		list.addAtEnd(trip2);
		assertNotNull("The elements should not be null", list.getNode(0));
		
		trip2 = null;
		try
		{
			list.addAtEnd(trip2);
			fail("Can not add a null element");
		}
		catch (Exception e) 
		{
			// It should catch the Exception.
		}	
	}
	
	public void testAddAtK() throws Exception
	{
		setUpScenario1();
		VOTrip trip3 = new VOTrip(28062000, "3/31/2017 23:59:07", "3/31/2017 23:59:07", 321, 567, 654, "FromStation", 456, "ToStation", "Sofi", "Female", "2000");
		list.addAtK(1, trip3);
		assertTrue("The IDs should be the same.", list.getNode(1).getElement().getid() == trip3.getid());
		
		setUpScenario2();
		list.addAtK(0, trip3);
		assertNotNull("The elements should not be null", list.getNode(0));
		
		trip3 = null;
		try
		{
			list.addAtK(2, trip3);
			fail("Can not add a null element");
		}
		catch (Exception e) 
		{
			// It should catch the Exception.
		}
	}
	
	public void testDelete() throws Exception
	{
		setUpScenario1();
		list.delete(list.getElement(0));
		assertTrue("", list.getElement(0).getid() == 1994);
		
		setUpScenario1();
		list.delete(list.getElement(1));
		try
		{
			list.getElement(1);
			fail("It should generate the Exception");
		}
		catch (Exception e) 
		{
			// It should generate the Exception.
		}
		
		setUpScenario2();
		try
		{
			list.delete(list.getElement(0));
			fail("It should generate the Exception");
		}
		catch (Exception e) 
		{
			// It should generate the Exception.
		}		
	}
	
	public void testDeleteAtK() throws Exception
	{
		setUpScenario1();
		VOTrip trip = list.deleteAtK(0);
		assertTrue("", trip.getid() == 2000);
		
		setUpScenario1();
		list.deleteAtK(1);
		try
		{
			list.getElement(1);
			fail("It should generate the Exception");
		}
		catch (Exception e) 
		{
			// It should generate the Exception.
		}	
	}
}
