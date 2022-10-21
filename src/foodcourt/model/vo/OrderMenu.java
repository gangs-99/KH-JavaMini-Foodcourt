package foodcourt.model.vo;

import java.util.Objects;

public class OrderMenu extends Foodcourt {

	
	private int totalCount;
	
	public OrderMenu() {
		super();
	}

	public OrderMenu(int category, String no, String name, int price, int count) {
		super(category, no, name, price, count);
		this.totalCount = count;
		getTotalPrice();
	}

	public OrderMenu(Foodcourt orderListAdd, int count) {
		super(orderListAdd.getCategory(), orderListAdd.getNo(), orderListAdd.getName(), orderListAdd.getPrice());
		this.totalCount = count;
		this.setTotalPrice(this.totalCount * this.getPrice());
//		this.getTotalPrice();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCategory(), getName(), getPrice(), totalCount, getTotalPrice());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderMenu other = (OrderMenu) obj;
		
		return super.getCategory() == other.getCategory() && Objects.equals(super.getName(), other.getName())
				&& super.getPrice() == other.getPrice() && totalCount == other.totalCount && getTotalPrice() == other.getTotalPrice();
	}

	@Override
	public String toString() {
		return super.toString() + totalCount + " 개\t " + getTotalPrice() + "원 \t";
	}

} // class end
