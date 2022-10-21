package foodcourt.model.compare;

import java.util.Comparator;

import foodcourt.model.vo.Foodcourt;

public class NoAsc implements Comparator<Foodcourt> {

	@Override
	public int compare(Foodcourt o1, Foodcourt o2) {
		if (o1.getCategory() - o2.getCategory() > 0) {
			return 1;
		} else if (o1.getCategory() - o2.getCategory() < 0){
			return -1;
		} else {
			return o1.getNo().compareTo(o2.getNo());
		}
	}

}
