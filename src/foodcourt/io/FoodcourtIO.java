package foodcourt.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import foodcourt.model.vo.Foodcourt;
import foodcourt.model.vo.Sales;

public class FoodcourtIO {

	File menufile = new File("menu.dat");
	File salesfile = new File("sales.dat");
	File salestotalfile = new File("salesTotal.dat");
	
	// 메뉴 목록 읽어오기
	public List<Foodcourt> loadMenu() {
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(menufile)));) {
			return (List<Foodcourt>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	} // loadMenu() end
	
	
	// 메뉴 목록 파일 저장하기
	public void saveMenu(List<Foodcourt> foodcourtList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(menufile)));) {
			oos.writeObject(foodcourtList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // saveMenu() end
	
	
	// 매출 목록 읽어오기
	public List<Sales> loadSales() {
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(salesfile)));) {
			return (List<Sales>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	} // loadMenu() end
	
	
	// 메출 목록 파일 저장하기
	public void saveSales(List<Sales> salesList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(salesfile)));) {
			oos.writeObject(salesList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // saveMenu() end
	
	
	// 매출 목록 읽어오기
	public List<Foodcourt> loadSalesTotal() {
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(salestotalfile)));) {
			return (List<Foodcourt>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	} // loadMenu() end
	
	
	// 메출 목록 파일 저장하기
	public void saveSalesTotal(List<Foodcourt> salesTotalList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(salestotalfile)));) {
			oos.writeObject(salesTotalList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // saveMenu() end
	
} // class end
