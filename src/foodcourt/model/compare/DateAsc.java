package foodcourt.model.compare;

import java.util.Comparator;

import foodcourt.model.vo.Sales;

public class DateAsc implements Comparator<Sales> {

	@Override
	public int compare(Sales o1, Sales o2) {
		return o1.getDate().compareTo(o2.getDate());
	}

}
