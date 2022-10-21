package foodcourt.manager;

import java.util.List;

import foodcourt.io.FoodcourtIO;
import foodcourt.model.vo.Foodcourt;

public class SalesTotalManager {

	private FoodcourtIO foodcourtio = new FoodcourtIO();
	private List<Foodcourt> salesTotalList;
	
	public SalesTotalManager() {
		super();
		List<Foodcourt> salesTotalList = foodcourtio.loadSalesTotal();
		this.salesTotalList = salesTotalList != null ? salesTotalList : foodcourtio.loadMenu();
	}

	public SalesTotalManager(List<Foodcourt> salesTotalList) {
		super();
		this.salesTotalList = salesTotalList;
	}

	public FoodcourtIO getFoodcourtio() {
		return foodcourtio;
	}

	public void setFoodcourtio(FoodcourtIO foodcourtio) {
		this.foodcourtio = foodcourtio;
	}

	public List<Foodcourt> getSalesTotalList() {
		return salesTotalList;
	}

	public void setSalesTotalList(List<Foodcourt> salesTotalList) {
		this.salesTotalList = salesTotalList;
	}
	
	// 메뉴 목록에 메뉴 추가
	public void totalcourtMenuAdd(Foodcourt foodcourt) {
		salesTotalList.add(foodcourt);
		
		foodcourtio.saveSalesTotal(salesTotalList);
	}

	// 메뉴 목록에 메뉴 삭제
	public boolean totalMenuDelete(String menuname) {
		for (int i = 0; i < salesTotalList.size(); i++) {
			if (menuname.equals(salesTotalList.get(i).getName())) {
				salesTotalList.remove(i);
				foodcourtio.saveSalesTotal(salesTotalList);
				return true;
			} // if end
		} // for end
		return false;
	}
	
	// 카테고리 변경
	public boolean menuCategoryChange(int beforecate, String name, int aftercate) {
		for (int i = 0; i < salesTotalList.size(); i++) {
			if (beforecate == salesTotalList.get(i).getCategory() && name.equals(salesTotalList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(aftercate, salesTotalList.get(i).getNo(), salesTotalList.get(i).getName(), salesTotalList.get(i).getPrice());
				salesTotalList.set(i, afterSet);
				foodcourtio.saveSalesTotal(salesTotalList);
				return true;
			}
		}
		return false;
	}
	
	// 번호 변경
	public boolean menuNoChange(String beforeno, String name, String afterno) {
		for (int i = 0; i < salesTotalList.size(); i++) {
			if (beforeno.equals(salesTotalList.get(i).getNo()) && name.equals(salesTotalList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(salesTotalList.get(i).getCategory(), afterno, salesTotalList.get(i).getName(), salesTotalList.get(i).getPrice());
				salesTotalList.set(i, afterSet);
				foodcourtio.saveSalesTotal(salesTotalList);
				return true;
			}
		}
		return false;
	}
	
	// 이름 변경
	public boolean menuNameChange(String beforname, int cate, String aftername) {
		for (int i = 0; i < salesTotalList.size(); i++) {
			if (cate == salesTotalList.get(i).getCategory() && beforname.equals(salesTotalList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(salesTotalList.get(i).getCategory(), salesTotalList.get(i).getNo(), aftername, salesTotalList.get(i).getPrice());
				salesTotalList.set(i, afterSet);
				foodcourtio.saveSalesTotal(salesTotalList);
				return true;
			}
		}
		return false;
	}
	
	// 가격 변경
	public boolean menuPriceChange(int beforprice, String name, int afterprice) {
		for (int i = 0; i < salesTotalList.size(); i++) {
			if (beforprice == salesTotalList.get(i).getPrice() && name.equals(salesTotalList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(salesTotalList.get(i).getCategory(), salesTotalList.get(i).getNo(), salesTotalList.get(i).getName(), afterprice);
				salesTotalList.set(i, afterSet);
				foodcourtio.saveSalesTotal(salesTotalList);
				return true;
			}
		}
		return false;
	}
	
} // class end
