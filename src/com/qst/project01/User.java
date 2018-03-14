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
		System.out.print("请输入用户名：");
		String username = Shop.sc.next();
		while (true) {
			System.out.print("请输入密码：");
			String password = Shop.sc.next();
			if(password.length()<6){
				System.out.println("密码长度过短，请重新设置6位以上密码！");
			}else{
				System.out.print("请再次输入密码：");
				String repassword = Shop.sc.next();
				if (password.equals(repassword)) {
					// System.out.println("注册成功！");
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					Shop.userList.add(user);
					Shop.saveListToFile();
					break;
				} else {
					System.out.println("两次密码不一致");
	
				}
			}
		}
		System.out.println("注册成功！");
	}
	
	public void login() {
		boolean loginResult = false;
		int count = 1;
		while (true) {
			if(count++ <= 3){
				System.out.print("请输入用户名：");
				String login_username = Shop.sc.next();
				System.out.print("请输入密码：");
				String login_password = Shop.sc.next();
				
				for (User user : Shop.userList) {
					if (login_username.equals(user.getUsername()) && login_password.equals(user.getPassword())) {
						System.out.println("登录成功!");
						this.setLogin(true);
						loginResult = true;
						break;
					}
				}
				if (loginResult == true) {
					break;
				} else {
					System.out.println("登录失败，请重新登录！");
				}
			}
			else{
				System.out.println("登录超过三次，请稍后登录！");
				break;
			}
		}
	}
	
	public void buy(){
		while(true){
			System.out.print("选择要购买的商品编号：");
			int id = Shop.sc.nextInt();
			if(id > 3){
				System.out.println("商品不存在！请选择已有商品...");
			}else{
				System.out.println("你将要购买的商品信息如下：");
				Good shopGood = this.findGoodById(id);
				System.out.println(shopGood);
				System.out.println("输入要购买的数量：");
				int num = Shop.sc.nextInt();
				
				if(num > shopGood.getNum()){
					System.out.println("要购买的数量超过库存数!请重新添加数量...");
				}else{
					Good myGood = new Good();
					/*myGood.setId(shopGood.getId());
					myGood.setName(shopGood.getName());
					myGood.setPrice(shopGood.getPrice());*/
					myGood = shopGood.clone();
					myGood.setNum(num);
					shopGood.setNum(shopGood.getNum()-num);
					Shop.myGoodList.add(myGood);
					System.out.println("是否继续:Y/N");
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
		System.out.println("所购买商品如下：");
		for(Good good : Shop.myGoodList){ 
			System.out.println(good);
			double price = good.getPrice();
			int num = good.getNum();
			int a = Shop.myGoodList.size();
			
			count = price * num;
			
		}
		System.out.println("总价格为：" + count + "元");
	}
	
	public void showGoodList(){
		System.out.println("商品列表如下：");
		for(Good good : Shop.goodList){
			System.out.println(good);
		}
	}
	
	
}
