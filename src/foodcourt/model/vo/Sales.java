package foodcourt.model.vo;

import java.time.LocalDate;
import java.util.Objects;

public class Sales extends OrderMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LocalDate date;

	public Sales() {
		super();
	}

	public Sales(int category, String no, String name, int price, int totalCount, int totalPrice) {
		super(category, no, name, price, totalCount);
		getTotalPrice();
		this.date = LocalDate.now();
	}
	
	public Sales(int category, String no, String name, int price, int totalCount, int totalPrice, LocalDate date) {
		this.setCategory(category);
		this.setNo(no);
		this.setName(name);
		this.setPrice(price);
		this.setCount(totalCount);
		this.setTotalCount(totalCount);
		this.setTotalPrice(totalPrice);
		this.date = date;
	}
	
	public Sales(OrderMenu ordermenu) {
		super(ordermenu.getCategory(), ordermenu.getNo(), ordermenu.getName(), ordermenu.getPrice(), ordermenu.getTotalCount());
		this.getTotalPrice();
		this.date = LocalDate.now();
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCategory(), getName(), getPrice(), getTotalCount(), getTotalPrice(), date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sales other = (Sales) obj;
		return super.getCategory() == other.getCategory() && Objects.equals(super.getName(), other.getName())
				&& super.getPrice() == other.getPrice() && super.getTotalCount() == other.getTotalCount() && getTotalPrice() == other.getTotalPrice() && this.date.equals(other.date);
	}

	@Override
	public String toString() {
		return super.toString() + this.date + " \t ";
		// .format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"))
	}
	
}
