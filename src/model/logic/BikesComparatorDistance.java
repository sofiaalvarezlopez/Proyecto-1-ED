package model.logic;

import model.vo.VOBike;
import java.util.Comparator;
public class BikesComparatorDistance implements Comparator<VOBike>{
	@Override
	public int compare(VOBike c1, VOBike c2) {
		if(c1.getTotalDistance()>c2.getTotalDistance())
		{
			return 1;
		}
		else if(c1.getTotalDistance()<c2.getTotalDistance())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

}
