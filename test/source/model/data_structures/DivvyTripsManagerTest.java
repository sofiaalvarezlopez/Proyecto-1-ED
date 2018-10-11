package model.data_structures;
import static org.junit.Assert.*;
import org.junit.*;
import model.logic.DivvyTripsManager;
import model.vo.VOStation;
import model.vo.VOTrip;

public class DivvyTripsManagerTest 
{
	private final static String stationsFile = "./test/source/data/Stations_Test.csv";
	private final static String tripsFile = "./test/source/data/Trips_Test.csv";
	private final static String bikeRoutesFile = "./test/source/data/bikeRoutesTest.JSON";

	private DivvyTripsManager manager;

	@Before
	public void setUpScenario1()
	{
		manager = new DivvyTripsManager();
	}

	@Test
	public void testLoadStations()
	{
		manager.loadStations(stationsFile);
		assertEquals(5, manager.getNumberOfStations());
		Iter <VOStation> iterator = (Iter<VOStation>) manager.getStationList().iterator();
		int count = 5;
		assertEquals(count, iterator.current.getElement().getId());
		while(iterator.hasNext())
		{
			VOStation s = iterator.next();
			count--;
			assertEquals(count, s.getId());
		}
	}

	@Test
	public void testLoadTrips()
	{

		manager.loadTrips(tripsFile);
		assertEquals(8, manager.getTripList().size());
		assertEquals(8, manager.getTripList().size());
		Iter <VOTrip> iterator = (Iter<VOTrip>) manager.getTripList().iterator();
		int id = 15763135;
		assertEquals(id, iterator.current.getElement().getid());
		while(iterator.hasNext())
		{
			VOTrip t = iterator.next();
			id++;
			assertEquals(id, t.getid());
		}		
	}

	@Test
	public void testLoadBikeRoutes()
	{
		manager.loadBikeRoutes(bikeRoutesFile);
		assertEquals(9, manager.getNumberOfBikeRoutes());
	}

}
