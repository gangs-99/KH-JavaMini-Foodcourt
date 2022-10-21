package foodcourt.model.vo;

import java.io.Serializable;
import java.util.Objects;


public class Foodcourt implements Serializable, Comparable<Foodcourt> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String no;
	private int category;
	private String name;
	private int price;
	private long count = 0;
	private long totalPrice = 0;
	
	public Foodcourt() {
		super();
	}
	
	public Foodcourt(int category, String no, String name, int price) {
		super();
		this.no = no;
		this.category = category;
		this.name = name;
		this.price = price;
	}

	public Foodcourt(int category, String no, String name, int price, long count) {
		super();
		this.no = no;
		this.category = category;
		this.name = name;
		this.price = price;
		this.count = count;
		this.totalPrice = price * count;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	public long getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, count, name, no, price, count, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foodcourt other = (Foodcourt) obj;
		return category == other.category && count == other.count && Objects.equals(name, other.name)
				&& Objects.equals(no, other.no) && price == other.price && count == other.count && totalPrice == other.totalPrice;
	}

	@Override
	public String toString() {
		return category + " \t " + no + " \t " +  name + " \t " + price + "원 \t";
	}
	
	@Override
	public int compareTo(Foodcourt other) {
		return this.category - other.category;
	}
	
	
} // class end
