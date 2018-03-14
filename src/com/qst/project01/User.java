package com.qst.project01;

import java.io.Serializable;

public class User implements Serializable{
	private String username;
	private String password;
	private boolean isLogin;
	
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public void register() {
		System.out.print("�������û�����");
		String username = Shop.sc.next();
		while (true) {
			System.out.print("���������룺");
			String password = Shop.sc.next();
			if(password.length()<6){
				System.out.println("���볤�ȹ��̣�����������6λ�������룡");
			}else{
				System.out.print("���ٴ��������룺");
				String repassword = Shop.sc.next();
				if (password.equals(repassword)) {
					// System.out.println("ע��ɹ���");
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					Shop.userList.add(user);
					Shop.saveListToFile();
					break;
				} else {
					System.out.println("�������벻һ��");
	
				}
			}
		}
		System.out.println("ע��ɹ���");
	}
	
	public void login() {
		boolean loginResult = false;
		int count = 1;
		while (true) {
			if(count++ <= 3){
				System.out.print("�������û�����");
				String login_username = Shop.sc.next();
				System.out.print("���������룺");
				String login_password = Shop.sc.next();
				
				for (User user : Shop.userList) {
					if (login_username.equals(user.getUsername()) && login_password.equals(user.getPassword())) {
						System.out.println("��¼�ɹ�!");
						this.setLogin(true);
						loginResult = true;
						break;
					}
				}
				if (loginResult == true) {
					break;
				} else {
					System.out.println("��¼ʧ�ܣ������µ�¼��");
				}
			}
			else{
				System.out.println("��¼�������Σ����Ժ��¼��");
				break;
			}
		}
	}
	
	public void buy(){
		while(true){
			System.out.print("ѡ��Ҫ�������Ʒ��ţ�");
			int id = Shop.sc.nextInt();
			if(id > 3){
				System.out.println("��Ʒ�����ڣ���ѡ��������Ʒ...");
			}else{
				System.out.println("�㽫Ҫ�������Ʒ��Ϣ���£�");
				Good shopGood = this.findGoodById(id);
				System.out.println(shopGood);
				System.out.println("����Ҫ�����������");
				int num = Shop.sc.nextInt();
				
				if(num > shopGood.getNum()){
					System.out.println("Ҫ������������������!�������������...");
				}else{
					Good myGood = new Good();
					/*myGood.setId(shopGood.getId());
					myGood.setName(shopGood.getName());
					myGood.setPrice(shopGood.getPrice());*/
					myGood = shopGood.clone();
					myGood.setNum(num);
					shopGood.setNum(shopGood.getNum()-num);
					Shop.myGoodList.add(myGood);
					System.out.println("�Ƿ����:Y/N");
					String choice = Shop.sc.next();
					choice = choice.toUpperCase();
					if(choice.equals("N")){
						break;	
					}
				}
			}
		}
		this.showMyGoodList();
		
	}
	
	public Good findGoodById(int id){
		Good returnGood = null;
		for(Good good : Shop.goodList){
			if(good.getId() == id){
				returnGood = good;
				break;
			}
		}
		return returnGood;
	}
	
	public void showMyGoodList(){
		double count = 0;
		System.out.println("��������Ʒ���£�");
		for(Good good : Shop.myGoodList){ 
			System.out.println(good);
			double price = good.getPrice();
			int num = good.getNum();
			int a = Shop.myGoodList.size();
			
			count = price * num;
			
		}
		System.out.println("�ܼ۸�Ϊ��" + count + "Ԫ");
	}
	
	public void showGoodList(){
		System.out.println("��Ʒ�б����£�");
		for(Good good : Shop.goodList){
			System.out.println(good);
		}
	}
	
	
}
