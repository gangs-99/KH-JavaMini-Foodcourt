package foodcourt.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import foodcourt.io.FoodcourtIO;
import foodcourt.manager.FoodcourtManager;
import foodcourt.manager.SalesManager;
import foodcourt.manager.SalesTotalManager;
import foodcourt.model.compare.DateAsc;
import foodcourt.model.compare.NoAsc;
import foodcourt.model.compare.TotalCountDsc;
import foodcourt.model.vo.Foodcourt;
import foodcourt.model.vo.OrderMenu;
import foodcourt.model.vo.Sales;

public class FoodcourtMenu {
	
	private FoodcourtIO foodcourtio = new FoodcourtIO();

	FoodcourtManager manager = new FoodcourtManager();
	SalesManager salesmanager = new SalesManager();
	SalesTotalManager totalmanager = new SalesTotalManager();
	
	private List<OrderMenu> ordermenu;
	private List<Foodcourt> salesdailytotal;
	private LocalDate today = LocalDate.now();

	private static final String ADMIN_PASS = "0000";
	Scanner sc = new Scanner(System.in);

	public void foodcourt() {
		String foodcourt = "================= 푸드코드 키오스크 =================\n"
				+ "0. 관리자\n"
				+ "1. 주문하기\n"
				+ "2. 종료하기\n"
				+ "================================================\n"
				+ ">> 번호 선택 : ";

		while (true) {
			ordermenu = new ArrayList<>();
			System.out.print(foodcourt);
			String choice = sc.nextLine();

			switch (choice) {
			case "0" :
				if (inputPass().equals(ADMIN_PASS)) {
					adminMenu();
				} else {
					System.out.println("비밀번호가 다릅니다... 확인하고 다시 시도해주세요.");
				}
				break;
			case "1" :
				categoryMenu();
				break;
			case "2" :
				System.out.println("프로그램을 종료합니다..");
				System.exit(0);
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			} // switch end
		}

	} // foodcourt() end

	public void adminMenu() {
		String adminmenu = "=================== 관리자 설정 ===================\n"
				+ "0. 메뉴 목록 출력하기\n"
				+ "1. 메뉴 추가하기\n"
				+ "2. 메뉴 변경하기\n"
				+ "3. 메뉴 삭제하기\n"
				+ "4. 매출 관리\n"
				+ "5. 처음 화면으로 돌아가기\n"
				+ "================================================\n"
				+ ">> 번호 선택 : ";

		while (true) {
			System.out.println();
			System.out.print(adminmenu);
			boolean result;

			switch (sc.nextLine()) {
			case "0" :
				printList(manager.totalList());
				break;
			case "1" :
				Foodcourt menu = inputMenuAdd();
				manager.foodcourtMenuAdd(menu);
				totalmanager.totalcourtMenuAdd(menu);
				break;
			case "2" :
				adminChangeMenu();
				break;
			case "3" :
				String name = inputMenuName();
				result = manager.foodcourtMenuDelete(name);
				totalmanager.totalMenuDelete(name);
				if (result == true) {
					System.out.println("해당 메뉴가 삭제되었습니다.");
				} else {
					System.out.println("잘못된 메뉴입니다. 확인해주세요.");
				}
				break;
			case "4" :
				adminSalesMenu();
				break;
			case "5" :
				System.out.println();
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	} // adminMenu() end
	
	public void adminChangeMenu() {
		String adminchangemenu = "==================== 메뉴 변경 ====================\n"
				+ "0. 관리자 설정 메뉴로 돌아가기\n"
				+ "1. 카테고리 변경하기\n"
				+ "2. 메뉴 번호 변경하기\n"
				+ "3. 메뉴 이름 변경하기\n"
				+ "4. 메뉴 가격 변경하기\n"
				+ "================================================\n"
				+ ">> 번호 선택 : ";
		
		while (true) {
			System.out.println();
			System.out.print(adminchangemenu);
			String choice = sc.nextLine();
			String findname;

			switch (choice) {
			case "0" :
				return;
			case "1" :
				System.out.println("\n변경 전...");
				int beforcate = inputMenuCategory();
				findname = inputMenuName();
				
				System.out.println("\n변경 후...");
				int aftercate = inputMenuCategory();
				
				totalmanager.menuCategoryChange(beforcate, findname, aftercate);
				changeResult(manager.menuCategoryChange(beforcate, findname, aftercate));
				break;
			case "2" :
				System.out.println("\n변경 전...");
				String beforno = inputMenuNo();
				findname = inputMenuName();
				
				System.out.println("\n변경 후...");
				String afterno = inputMenuNo();
				
				totalmanager.menuNoChange(beforno, findname, afterno);
				changeResult(manager.menuNoChange(beforno, findname, afterno));
				break;
			case "3" :
				System.out.println("\n변경 전...");
				int cate = inputMenuCategory();
				String befoname = inputMenuName();
				
				System.out.println("\n변경 후...");
				String aftername = inputMenuName();
				
				totalmanager.menuNameChange(befoname, cate, aftername);
				changeResult(manager.menuNameChange(befoname, cate, aftername));
				break;
			case "4" :
				System.out.println("\n변경 전...");
				int befoprice = inputMenuPrice();
				findname = inputMenuName();
				
				System.out.println("\n변경 후...");
				int afterprice = inputMenuPrice();
				
				totalmanager.menuPriceChange(befoprice, findname, afterprice);
				changeResult(manager.menuPriceChange(befoprice, findname, afterprice));
				break;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			} // switch end
		} // while end
	} // adminChangeMenu() end
	
	public void adminSalesMenu() {
		String adminchangemenu = "==================== 매출 관리 ===================\n"
				+ "0. 관리자 설정 메뉴로 돌아가기\n"
				+ "1. 일일 매출 내역 출력하기\n"
				+ "2. 일일 매출액 출력하기\n"
				+ "3. 누적 매출 내역 출력하기\n"
				+ "4. 일일 메뉴 별 판매 수량 목록 및 Best3 출력하기\n"
				+ "5. 누적 메뉴 별 판매 수량 목록 및 Best3 출력하기\n"
				+ "================================================\n"
				+ ">> 번호 선택 : ";
		
		while (true) {
			System.out.println();
			System.out.print(adminchangemenu);
			String choice = sc.nextLine();
		
			switch (choice) {
			case "0" :
				return;
			case "1" :
				printDailySalesList(salesmanager.getSalesList());
				break;
			case "2" :
				dailySalesList(salesmanager.dailySales());
				break;
			case "3" :
				System.out.println("\n원하는 기간의 시작 날짜 입력해주세요.");
				LocalDate date1 = inputdate();
				System.out.println("\n원하는 기간의 마지막 날짜 입력해주세요.");
				LocalDate date2 = inputdate();
				printSalesList(salesmanager.getSalesList(), date1, date2);
				break;
			case "4" :
				salesDailyTotalCheckedAdd(salesmanager.getSalesList(), inputdate());
				printDailySalesTopThree(salesdailytotal);
				printDailySalesTotal(salesdailytotal);
				break;
			case "5" :
				printSalesTopThree(totalmanager.getSalesTotalList());
				printSalesTotal(totalmanager.getSalesTotalList());
				break;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		} // while end
	} // adminChangeMenu() end

	public void categoryMenu() {
		String categorymenu = "=================== 카테고리 선택 ==================\n"
				+ "1. 한식\n"
				+ "2. 중식\n"
				+ "3. 양식\n"
				+ "4. 분식\n"
				+ "0. 초기 화면으로 돌아가기\n"
				+ "================================================\n"
				+ ">> 번호 선택 : ";

		while (true) {
			System.out.println();
			System.out.print(categorymenu);
			String cachoice = sc.nextLine();

			switch (cachoice) {
			case "0" :
				System.out.println();
				return;
			case "1" :
			case "2" :
			case "3" :
			case "4" :
				detailMenu(cachoice);
				break;
			default :
				System.out.println("다시 입력해주세요.");
				break;
			} // switch end
		} // while end

	} // categoryMenu() end

	public void detailMenu(String cachoice) {
		int category = Integer.parseInt(cachoice);

		// 메뉴 목록 출력
		manager.detailMenuPrint(category);

		System.out.print(">> 번호 선택 : ");
		String choice = sc.nextLine();
		if ("0".equals(choice)) {
			return;
		}

		if (manager.orderListAdd(category, choice) == null) {
			return;
		} else {
			int count = inputCount();
			orderMenuCheckedAdd(new OrderMenu(manager.orderListAdd(category, choice), count));
		}
		
		
		System.out.println("----------------------------------------------------------");
		System.out.printf("%4s %4s %9s %6s %12s %10s\n", "카테고리", "번호", "음식이름", "가격", "개수", "주문금액");
		for (int i = 0; i < ordermenu.size(); i++) {
			OrderMenu order = ordermenu.get(i);
			System.out.printf("%d\t %s\t %s\t %,6d원\t %d개\t %,d원 \n", order.getCategory(), order.getNo(), order.getName(), order.getPrice(), order.getTotalCount(), order.getTotalPrice());
		}
		System.out.println("----------------------------------------------------------");
		
		if (orderAddChoice() == false)
			paymentChoice();

	} // detailMenu() end
	
	public void paymentChoice() {
		String paymentchoice = "=================== 결제방법 선택 ==================\n"
				+ "1. 현금\n"
				+ "2. 카드\n"
				+ "0. 뒤로 돌아가기\n"
				+ "================================================\n"
				+ ">> 결제방법 선택 : ";

		System.out.println();
		System.out.println(paymentchoice);
		String paychoise = sc.next();
		sc.nextLine();

		switch (paychoise) {
		case "0" :
			return;
		case "1" :
			cashReceipt();
		case "2" :
			receipt();
			System.out.println();
			orderDetailsPrint();
			System.out.println();
			salesListAdd(ordermenu);
			salesTotalCheckedAdd(ordermenu);
//			salesTotalCheckedAdd(salesmanager.getSalesList());
			foodcourt();
			break;
		default :
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요..");
			return;
		} // switch end

	} // paymentChoice() end

	
	// 비밀번호 입력
	public String inputPass() {
		System.out.print(">> 관리자 비밀번호를 입력하세요 : ");
		return sc.nextLine();
	} // inputPass() end

	
	// 수량 입력
	public int inputCount() {
		while (true) {
			try {
				System.out.print(">> 수량을 입력해주세요 : ");				
				int count = sc.nextInt();
				sc.nextLine();
				return count;
			} catch (Exception e) {
				System.out.println("잘못된 타입의 입력값입니다. 다시 입력해주세요.");
				sc.nextLine();
			}
		} // while end
	} // inputCount() end
	

	// 주문 추가 여부 문의
	public boolean orderAddChoice() {
		System.out.print("주문을 추가하시겠습니까? (y/n) ");
		String yn = sc.next();
		sc.nextLine();
		if ("n".equals(yn))
			return false;
		return true;
	} // orderAddChoice() end
	

	// 현금영수증 여부 출력
	public void cashReceipt() {
		String number = null;

		System.out.print("현금영수증 하시겠습니까? (y/n) : ");

		String yn = sc.next();
		sc.nextLine();
		if ("y".equals(yn)) {
			while (true) {
				try {
					System.out.print("현금영수증 할 번호를 입력해주세요(숫자만 입력해주세요!!) : ");
					
					number = sc.nextLine();
					System.out.printf("%s 번호로 현금영수증을 발행했습니다.\n", number);
					return;
				} catch (Exception e) {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.\n");
				}
			} // while end
		} // if end
	} // cashReceipt() end

	
	// 영수증 출력 여부 문의
	public void receipt() {
		System.out.print("영수증을 출력받으시겠습니까? (y/n) : ");
		if ("y".equals(sc.next())) {
			System.out.println("영수증 출력 완료!");
		}
		sc.nextLine();
	} // receipt() end

	
	// 주문 결제 내역 출력
	public void orderDetailsPrint() {
		long paymentPrice = 0;
		System.out.println("------------------------------------------------------------------------------");
		System.out.print("주문하신 내역은 ");
		for (int i = 0; i < ordermenu.size(); i++) {
			paymentPrice += ordermenu.get(i).getTotalPrice();
			if (i != (ordermenu.size() - 1)) {
				System.out.print(ordermenu.get(i).getName() + " " + ordermenu.get(i).getTotalCount() + "개, ");
			} else {
				System.out.print(ordermenu.get(i).getName() + " " + ordermenu.get(i).getTotalCount() + "개 ");
			}
		}
		System.out.printf("하셔서 총 %,d원 결제 완료되었습니다.\n", paymentPrice);
		System.out.println("------------------------------------------------------------------------------");
	} // orderDetailsPrint() end
	

	// 주문 내역 추가 확인
	public void orderMenuCheckedAdd(OrderMenu ordermenu) {
		for (int i = 0; i < this.ordermenu.size(); i++) {
			OrderMenu order =  this.ordermenu.get(i);
			if (order.getCategory() == ordermenu.getCategory() && order.getName().equals(ordermenu.getName())) {
				order.setTotalCount(order.getTotalCount() + ordermenu.getTotalCount());
				order.setTotalPrice(order.getTotalPrice() + ordermenu.getTotalPrice());
				return;
			}
		} // for end
		this.ordermenu.add(ordermenu);
	} // orderMenuCheckedAdd() end
	
	
	// 메뉴 추가
	public Foodcourt inputMenuAdd() {
		while (true) {
			try {
				System.out.print("\n카테고리(1.한식 / 2.중식 / 3.양식 / 4.분식)를 입력해주세요.(숫자만) : ");
				int category = sc.nextInt();
				System.out.print("메뉴 번호를 입력해주세요 : ");
				String no = sc.next();
				System.out.print("메뉴 이름을 입력해주세요 : ");
				String name = sc.next();
				System.out.print("가격을 입력해주세요 : ");
				int price = sc.nextInt();
				sc.nextLine();
				
				return new Foodcourt(category, no, name, price);
			} catch (Exception e) {
				System.out.println("잘못된 타입의 입력값입니다. 다시 입력해주세요.");
				sc.nextLine();
			}
		} // while end
		
	} // inputMenuAdd() end

	
	// 카테고리 입력
	public int inputMenuCategory() {
		while (true) {
			try {
				System.out.print("카테고리 번호를 입력해주세요 : ");
				int cate = sc.nextInt();
				sc.nextLine();
				return cate;
			} catch (Exception e) {
				System.out.println("잘못된 타입의 입력값입니다. 다시 입력해주세요.");
				sc.nextLine();
			}
		} // while end
	} // inputMenuCategory() end


	// 메뉴 번호 입력
	public String inputMenuNo() {
		System.out.print("메뉴 번호를 입력해주세요 : ");
		return sc.nextLine();
	} // inputMenuNo() end
	
	
	// 메뉴 이름 입력
	public String inputMenuName() {
		System.out.print("메뉴 이름을 입력해주세요 : ");
		return sc.nextLine();
	} // inputMenuName() end
	
	
	// 가격 입력
	public int inputMenuPrice() {
		while (true) {
			try {
				System.out.print("메뉴 가격을 입력해주세요 : ");
				int price = sc.nextInt();
				sc.nextLine();
				return price;
			} catch (Exception e) {
				System.out.println("잘못된 타입의 입력값입니다. 다시 입력해주세요.");
				sc.nextLine();
			}
		} // while end
	} // inputMenuPrice() end


	// 날짜 입력
	public LocalDate inputdate() {
		while (true) {
			try {					
				System.out.print("다음 형식으로 날짜를 입력해주세요.(ex. 20221018) : ");
				String date = sc.nextLine();
				int year = Integer.parseInt(date.substring(0, 4));
				int month = Integer.parseInt(date.substring(4, 6));
				int day = Integer.parseInt(date.substring(6));
					
				return LocalDate.of(year, month, day);
				
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요..");
			}
		} // while end
	} // inputdate() end
	
	
	// 변경 여부 출력
	public void changeResult(boolean bool) {
		if (bool == true) {
			System.out.println("변경이 완료되었습니다.");
		} else {
			System.out.println("변경 실패... 확인 후 다시 변경해주세요.");
		}
	} // changeResult() end
	

	// 전체 메뉴 목록 출력
	public void printList(List<Foodcourt> foodcourt) {
		foodcourt.sort(new NoAsc());
		System.out.println();
		System.out.println("=============== 메뉴 목록 ===============");
		System.out.printf("%s %4s %9s %8s\n", "카테고리", "번호", "음식이름", "가격");
		for (int i = 0; i < foodcourt.size(); i++) {
			Foodcourt food = foodcourt.get(i);
			System.out.printf("%d\t %s\t %s\t %,8d원 \n", food.getCategory(), food.getNo(), food.getName(), food.getPrice());
		}
		System.out.println("======================================");
	} // printList() end
	
	
	// 기간 별 주문 목록 출력
	public void printSalesList(List<Sales> saleslist, LocalDate date1, LocalDate date2) {
		long price = 0;
		Collections.sort(saleslist, new DateAsc());
		System.out.println();
		System.out.println("--------------------------------- 기간별 주문 목록 ---------------------------------");
		System.out.printf("%4s %4s %9s %6s %12s %10s %11s\n", "카테고리", "번호", "음식이름", "가격", "수량", "주문금액", "주문날짜");
		for (int i = 0; i < saleslist.size(); i++) {
			Sales sale = saleslist.get(i);
			int startdate = date1.compareTo(sale.getDate());
			int enddate = date2.compareTo(sale.getDate());
			if ((startdate <= 0) && (enddate >= 0)) {
				System.out.printf("%d\t %s\t %s\t %,6d원\t %d개\t %,d원 \t%s \n", sale.getCategory(), sale.getNo(), sale.getName(), sale.getPrice(), sale.getCount(), sale.getTotalPrice(), sale.getDate());
				price += sale.getTotalPrice();
			} else {
				continue;
			}
		}
		System.out.println("------------------------------------------------------------------------------");
		System.out.printf("%s ~ %s 총 매출액은 %,d입니다.\n", date1, date2, price);
		System.out.println("------------------------------------------------------------------------------");
	} // printSalesList() end
	
	
	// 일일 주문 목록 출력
	public void printDailySalesList(List<Sales> saleslist) {
		System.out.println();
		System.out.println("--------------------------------- 일일 주문 목록 ---------------------------------");
		System.out.printf("%5s %4s %8s %5s %13s %10s %10s\n", "카테고리", "번호", "음식이름", "가격", "수량", "주문금액", "주문날짜");
		for (int i = 0; i < saleslist.size(); i++) {
			Sales sale = saleslist.get(i);
			if (today.equals(sale.getDate()))
				System.out.printf("%d\t %s\t %s\t %,6d원\t %d개\t %,d원 \t%s \n", sale.getCategory(), sale.getNo(), sale.getName(), sale.getPrice(), sale.getCount(), sale.getTotalPrice(), sale.getDate());
		}
		System.out.println("------------------------------------------------------------------------------");
	} // printList() end
	
	
	// 일일 매출액 출력
	public void dailySalesList(long total) {
		System.out.printf("오늘(%s) 일일 매출액은 %,d원 입니다.\n\n", today, total);
	} // dailySalesList() end

	
	// 총 판매 내역 목록 추가
	public void salesListAdd(List<OrderMenu> ordermenuList) {
		System.out.println();
		for (OrderMenu order : ordermenuList) {
			salesmanager.getSalesList().add(new Sales(order));
		}
		
		foodcourtio.saveSales(salesmanager.getSalesList());
	} // salesListAdd() end
	
	
	// 일일 누적 메뉴 별 총 판매 수량 계산
	public void salesDailyTotalCheckedAdd(List<Sales> saleslist, LocalDate date) {
		salesdailytotal = foodcourtio.loadMenu();
		
		for (int i = 0; i <salesdailytotal.size(); i++) {
			long count = salesdailytotal.get(i).getCount();
			long price = salesdailytotal.get(i).getTotalPrice();
			for (Sales sale : saleslist) {
				if (salesdailytotal.get(i).getName().equals(sale.getName()) && date.compareTo(sale.getDate()) == 0) {
					count += sale.getTotalCount();
					price += sale.getTotalPrice();
					salesdailytotal.get(i).setCount(count);
					salesdailytotal.get(i).setTotalPrice(price);
				}
			} // for each end
		} // for end
	} // salesTotalCheckedAdd() end
	
	// 일일 누적 메뉴 별 판매 수량 출력
	public void printDailySalesTotal(List<Foodcourt> salesdailytotal) {
		salesdailytotal.sort(new NoAsc());
		System.out.println();
		System.out.println("-------------------- 일일 메뉴 별 판매 수량 목록 --------------------");
		System.out.printf("%4s %4s %9s %6s %12s %8s\n", "카테고리", "번호", "음식이름", "가격", "판매수량", "판매금액");
		for (int i = 0; i < salesdailytotal.size(); i++) {
			Foodcourt dailytotal = salesdailytotal.get(i);
			System.out.printf("%d\t %s\t %s\t %,6d원\t %d개\t %,d원 \n", dailytotal.getCategory(), dailytotal.getNo(), dailytotal.getName(), dailytotal.getPrice(), dailytotal.getCount(), dailytotal.getTotalPrice());
		}
		System.out.println("--------------------------------------------------------------");
	} // printSalesTotal() end
	
	
	// 일일 매뉴 별 판매 수량 Top3 출력
	public void printDailySalesTopThree(List<Foodcourt> salesdailytotal) {
		salesdailytotal.sort(new TotalCountDsc());
		System.out.println();
		System.out.println("------------------- 일일 메뉴 별 판매 수량 Top3 --------------------");
		System.out.printf("%4s %4s %9s %6s %12s %8s\n", "카테고리", "번호", "음식이름", "가격", "판매수량", "판매금액");
		for (int i = 0; i < 3; i++) {
			Foodcourt total = salesdailytotal.get(i);
			System.out.printf("%d\t %s\t %s\t %,6d원\t %d개\t %,d원 \n", total.getCategory(), total.getNo(), total.getName(), total.getPrice(), total.getCount(), total.getTotalPrice());
		}
		System.out.println("--------------------------------------------------------------");
	} // printSalesTopThree() end
	
	
	// 누적 메뉴 별 총 판매 수량 계산
	public void salesTotalCheckedAdd(List<OrderMenu> ordermenu) {
		foodcourtio.loadSalesTotal();
		
		for (int i = 0; i < totalmanager.getSalesTotalList().size(); i++) {
			long count = totalmanager.getSalesTotalList().get(i).getCount();
			long price = totalmanager.getSalesTotalList().get(i).getTotalPrice();
			for (OrderMenu order : ordermenu) {
				if (totalmanager.getSalesTotalList().get(i).getName().equals(order.getName())) {
					count += order.getTotalCount();
					price += order.getTotalPrice();
					totalmanager.getSalesTotalList().get(i).setCount(count);
					totalmanager.getSalesTotalList().get(i).setTotalPrice(price);
				}
			} // for each end
		} // for end
		foodcourtio.saveSalesTotal(totalmanager.getSalesTotalList());
	} // salesTotalCheckedAdd() end
	
	
	// 누적 메뉴 별 판매 수량 출력
	public void printSalesTotal(List<Foodcourt> salesTotal) {
		foodcourtio.loadSalesTotal();
		salesTotal.sort(new NoAsc());
		System.out.println();
		System.out.println("---------------------- 메뉴 별 판매 수량 목록 ----------------------");
		System.out.printf("%4s %4s %9s %6s %12s %8s\n", "카테고리", "번호", "음식이름", "가격", "판매수량", "판매금액");
		for (int i = 0; i < salesTotal.size(); i++) {
			Foodcourt total = salesTotal.get(i);
			System.out.printf("%d\t %s\t %s\t %,6d원\t %d개\t %,d원 \n", total.getCategory(), total.getNo(), total.getName(), total.getPrice(), total.getCount(), total.getTotalPrice());
		}
		System.out.println("--------------------------------------------------------------");
	} // printSalesTotal() end
	
	
	// 매뉴 별 판매 수량 Top3 출력
	public void printSalesTopThree(List<Foodcourt> salesTotal) {
		foodcourtio.loadSalesTotal();
		salesTotal.sort(new TotalCountDsc());
		System.out.println();
		System.out.println("--------------------- 메뉴 별 판매 수량 Top3 ---------------------");
		System.out.printf("%4s %4s %9s %6s %12s %8s\n", "카테고리", "번호", "음식이름", "가격", "판매수량", "판매금액");
		for (int i = 0; i < 3; i++) {
			Foodcourt total = salesTotal.get(i);
			System.out.printf("%d\t %s\t %s\t %,6d원\t %d개\t %,d원 \n", total.getCategory(), total.getNo(), total.getName(), total.getPrice(), total.getCount(), total.getTotalPrice());
		}
		System.out.println("--------------------------------------------------------------");
	} // printSalesTopThree() end

} // class end
