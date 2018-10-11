package model.data_structures;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class StackTest {

	private ListStack<Integer> list;
	@Before
	public void setUp1()
	{
		list = new ListStack<>();
	}
	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
	}
	@Test
	public void testSize() {
		assertEquals(0, list.size());
	}
	@Test
	public void testPush() {
		list.push(1);
		assertEquals(1, list.size());
	}
	@Test
	public void testPop() {
		list.push(1);
		list.pop();
		assertTrue(list.isEmpty());
	}

}
