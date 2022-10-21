package foodcourt.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import foodcourt.io.FoodcourtIO;
import foodcourt.model.vo.Sales;

public class SalesManager {

	private FoodcourtIO foodcourtio = new FoodcourtIO();
	private List<Sales> salesList;

	public SalesManager() {
		super();
		List<Sales> salesList = foodcourtio.loadSales();
		this.salesList = salesList != null ? salesList : new ArrayList<>();
	}

	public SalesManager(List<Sales> salesList) {
		super();
		this.salesList = salesList;
	}

	public FoodcourtIO getFoodcourtio() {
		return foodcourtio;
	}

	public void setFoodcourtio(FoodcourtIO foodcourtio) {
		this.foodcourtio = foodcourtio;
	}

	public List<Sales> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<Sales> salesList) {
		this.salesList = salesList;
	}
	
	
	// 일일 매출액 출력하기
	public long dailySales() {
		long daily = 0;
		foodcourtio.loadSales();
		for (Sales sale : salesList) {
			if (LocalDate.now().equals(sale.getDate()))
				daily += sale.getTotalPrice();
		}
		return daily;
	} // dailySales() end
	
} // class end
