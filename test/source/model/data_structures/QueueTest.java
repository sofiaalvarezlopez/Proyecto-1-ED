package model.data_structures;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class QueueTest {

	private ListQueue<Integer> list;
	@Before
	public void setUp1()
	{
		list = new ListQueue<>();
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
	}
	@Test
	public void testsize() {
		assertEquals(0, list.size());
	}
	@Test
	public void testEnqueue() {
		list.enqueue(1);
		assertEquals(1, list.size());
	}
	@Test
	public void testDequeue() {
		list.enqueue(1);
		list.dequeue();
		assertTrue(list.isEmpty());
	}
}
