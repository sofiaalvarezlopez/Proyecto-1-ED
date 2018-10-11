package model.logic;

import model.vo.VOBike;
import java.util.Comparator;
public class BikesComparatorNumberOfTrips implements Comparator<VOBike>{
	@Override
	public int compare(VOBike c1, VOBike c2) {
		if(c1.getNumberOfTrips()>c2.getNumberOfTrips())
		{
			return 1;
		}
		else if(c1.getNumberOfTrips()<c2.getNumberOfTrips())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

}
