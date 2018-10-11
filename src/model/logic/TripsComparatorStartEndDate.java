package model.logic;

import model.vo.VOTrip;
import java.util.Comparator;
public class TripsComparatorStartEndDate implements Comparator<VOTrip>{
	@Override
	public int compare(VOTrip c1, VOTrip c2) {
		return c1.getStart_time().compareTo(c2.getEnd_time());
	}

}
