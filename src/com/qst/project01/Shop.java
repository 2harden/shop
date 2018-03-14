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
	static File userFile = new File("E:\\����ʵϰ\\Java SE\\��Ŀ\\Shop\\src\\com\\qst\\project01\\UserFile");
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
		Good good1 = new Good(1, "��������", 1999, 100);
		Good good2 = new Good(2, "��Ϊ�ֻ�", 3999, 100);
		Good good3 = new Good(3, "С�׵���", 1999, 100);
		goodList.add(good1);
		goodList.add(good2);
		goodList.add(good3);
	}

	private int showMainMenu() {
		System.out.println("**********��ӭ����������**********");
		System.out.println("\t1.ע��");
		System.out.println("\t2.��¼");
		System.out.println("\t3.�鿴�̳�");
		System.out.println("\t4.�鿴�ѹ���Ʒ");
		System.out.println("\t5.����Ա��¼");
		System.out.println("\t6.�˳�ϵͳ");
		System.out.println("**********��ӭ����������**********");
		System.out.print("��ѡ��˵���");
		int choice = sc.nextInt();
		return choice;
	}

	private boolean chooseMenu(int choice) {
		boolean result = true;
		switch (choice) {
		case 1:
			System.out.println("��ѡ��Ĳ˵��ǣ�ע��");
			user.register();
			break;
		case 2:
			System.out.println("��ѡ��Ĳ˵��ǣ���¼");
			user.login();
			break;
		case 3:
			System.out.println("��ѡ��Ĳ˵��ǣ��鿴�̳�");
			user.showGoodList();
			if (user.isLogin() == true) {
				user.buy();
			} else {
				System.out.println("��δ��¼�����ȵ�¼�ٹ���");
			}
			break;
		case 4:
			System.out.println("��ѡ��Ĳ˵��ǣ��鿴�ѹ���Ʒ");
			break;
		case 5:
			System.out.println("��ѡ��Ĳ˵��ǣ�����Ա��¼");
			Admin admin = new Admin();
			admin.adminLogin();
			break;
		case 6:
			System.out.println("ллʹ�ã�");
			result = false;
			break;
		default:
			System.out.println("[������������]");
			break;
		}
		return result;
	}
}
