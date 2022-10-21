package foodcourt.model.compare;

import java.util.Comparator;

import foodcourt.model.vo.Foodcourt;

public class TotalCountDsc implements Comparator<Foodcourt> {

	@Override
	public int compare(Foodcourt o1, Foodcourt o2) {
		return (int) (o2.getCount() - o1.getCount());
	}

}
