package com.qst.project01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
	static Scanner sc = new Scanner(System.in);

	static List<Good> goodList = new ArrayList();
	static List<Good> myGoodList = new ArrayList();
	static List<User> userList = new ArrayList();
	static File userFile = new File("E:\\江苏实习\\Java SE\\项目\\Shop\\src\\com\\qst\\project01\\UserFile");
	User user = new User();

	public static void saveListToFile() {
		try {
			FileOutputStream fos = new FileOutputStream(userFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readListFromFile() {
		try {
			FileInputStream fis = new FileInputStream(userFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			userList = (List) object;
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Shop shop = new Shop();
		shop.run();
	}

	private void run() {
		this.initGoodList();
		this.readListFromFile();
		boolean go_on = true;
		while (go_on) {
			int choice = this.showMainMenu();
			go_on = this.chooseMenu(choice);
		}
	}

	private void initGoodList() {
		Good good1 = new Good(1, "海尔冰箱", 1999, 100);
		Good good2 = new Good(2, "华为手机", 3999, 100);
		Good good3 = new Good(3, "小米电视", 1999, 100);
		goodList.add(good1);
		goodList.add(good2);
		goodList.add(good3);
	}

	private int showMainMenu() {
		System.out.println("**********欢迎进入电子书城**********");
		System.out.println("\t1.注册");
		System.out.println("\t2.登录");
		System.out.println("\t3.查看商城");
		System.out.println("\t4.查看已购商品");
		System.out.println("\t5.管理员登录");
		System.out.println("\t6.退出系统");
		System.out.println("**********欢迎进入电子书城**********");
		System.out.print("请选择菜单：");
		int choice = sc.nextInt();
		return choice;
	}

	private boolean chooseMenu(int choice) {
		boolean result = true;
		switch (choice) {
		case 1:
			System.out.println("您选择的菜单是：注册");
			user.register();
			break;
		case 2:
			System.out.println("您选择的菜单是：登录");
			user.login();
			break;
		case 3:
			System.out.println("您选择的菜单是：查看商城");
			user.showGoodList();
			if (user.isLogin() == true) {
				user.buy();
			} else {
				System.out.println("还未登录，请先登录再购买！");
			}
			break;
		case 4:
			System.out.println("您选择的菜单是：查看已购商品");
			break;
		case 5:
			System.out.println("您选择的菜单是：管理员登录");
			Admin admin = new Admin();
			admin.adminLogin();
			break;
		case 6:
			System.out.println("谢谢使用！");
			result = false;
			break;
		default:
			System.out.println("[您的输入有误！]");
			break;
		}
		return result;
	}
}
