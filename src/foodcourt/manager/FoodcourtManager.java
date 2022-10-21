package foodcourt.manager;

import java.util.ArrayList;
import java.util.List;

import foodcourt.io.FoodcourtIO;
import foodcourt.model.compare.NoAsc;
import foodcourt.model.vo.Foodcourt;

public class FoodcourtManager {

//	private FoodcourtMenu menu = new FoodcourtMenu();
	private FoodcourtIO foodcourtio = new FoodcourtIO();
	private List<Foodcourt> foodcourtList;
	
	private SalesTotalManager totalmanager = new SalesTotalManager();


	public FoodcourtManager() {
		super();
		List<Foodcourt> foodcourtList = foodcourtio.loadMenu();
		this.foodcourtList = foodcourtList != null ? foodcourtList : new ArrayList<>();
	}

	public FoodcourtManager(List<Foodcourt> foodcourtList) {
		super();
		this.foodcourtList = foodcourtList;
	}
	
	public List<Foodcourt> totalList() {
		return this.foodcourtList;
	}
	
	// 메뉴 목록에 메뉴 추가
	public void foodcourtMenuAdd(Foodcourt foodcourt) {
		foodcourtList.add(foodcourt);
		
		foodcourtio.saveMenu(foodcourtList);
					
		System.out.println("메뉴를 추가했습니다!");
		
	}
	
	// 메뉴 목록에 메뉴 삭제
	public boolean foodcourtMenuDelete(String menuname) {
		for (int i = 0; i < foodcourtList.size(); i++) {
			if (menuname.equals(foodcourtList.get(i).getName())) {
				foodcourtList.remove(i);
				foodcourtio.saveMenu(foodcourtList);
				return true;
			} // if end
		} // for end
		return false;
	}
	
	// 카테고리 변경
	public boolean menuCategoryChange(int beforecate, String name, int aftercate) {
		for (int i = 0; i < foodcourtList.size(); i++) {
			if (beforecate == foodcourtList.get(i).getCategory() && name.equals(foodcourtList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(aftercate, foodcourtList.get(i).getNo(), foodcourtList.get(i).getName(), foodcourtList.get(i).getPrice());
				foodcourtList.set(i, afterSet);
				foodcourtio.saveMenu(foodcourtList);
				return true;
			}
		}
		return false;
	}
	
	// 번호 변경
	public boolean menuNoChange(String beforeno, String name, String afterno) {
		for (int i = 0; i < foodcourtList.size(); i++) {
			if (beforeno.equals(foodcourtList.get(i).getNo()) && name.equals(foodcourtList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(foodcourtList.get(i).getCategory(), afterno, foodcourtList.get(i).getName(), foodcourtList.get(i).getPrice());
				foodcourtList.set(i, afterSet);
				foodcourtio.saveMenu(foodcourtList);
				return true;
			}
		}
		return false;
	}
	
	// 이름 변경
	public boolean menuNameChange(String beforname, int cate, String aftername) {
		for (int i = 0; i < foodcourtList.size(); i++) {
			if (cate == foodcourtList.get(i).getCategory() && beforname.equals(foodcourtList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(foodcourtList.get(i).getCategory(), foodcourtList.get(i).getNo(), aftername, foodcourtList.get(i).getPrice());
				foodcourtList.set(i, afterSet);
				foodcourtio.saveMenu(foodcourtList);
				return true;
			}
		}
		return false;
	}
	
	// 가격 변경
	public boolean menuPriceChange(int beforprice, String name, int afterprice) {
		for (int i = 0; i < foodcourtList.size(); i++) {
			if (beforprice == foodcourtList.get(i).getPrice() && name.equals(foodcourtList.get(i).getName())) {
				Foodcourt afterSet = new Foodcourt(foodcourtList.get(i).getCategory(), foodcourtList.get(i).getNo(), foodcourtList.get(i).getName(), afterprice);
				foodcourtList.set(i, afterSet);
				foodcourtio.saveMenu(foodcourtList);
				return true;
			}
		}
		return false;
	}

	// 메뉴 목록 출력
	public void detailMenuPrint(int cachoice) {	
		foodcourtio.loadMenu();
		foodcourtList.sort(new NoAsc());
		System.out.println();
		System.out.println("==================== 메뉴 목록 ====================");
		System.out.printf("%s %4s %9s %8s\n", "카테고리", "번호", "음식이름", "가격");
		for (int i = 0; i < foodcourtList.size(); i++) {
			Foodcourt food = foodcourtList.get(i);
			if (food.getCategory() == cachoice)
				System.out.printf("%d\t %s\t %s\t %,8d원 \n", food.getCategory(), food.getNo(), food.getName(), food.getPrice());
		}
		System.out.println();
		System.out.println("0. 뒤로가기");
		System.out.println("================================================");
	}


	public Foodcourt orderListAdd(int category, String choice) {
		foodcourtio.loadMenu();
		Foodcourt food = null;
		
		for (Foodcourt menu : foodcourtList) {
			if (category == menu.getCategory() && choice.equals(menu.getNo())) {
				food = menu;
				return food;
			}
		}
		System.out.println("잘못 입력하셨습니다. 다시 입력해주세요..");
		return food;
	}

}
