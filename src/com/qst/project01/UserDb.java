package com.qst.project01;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qst.project01.tools.JdbcTools;

public class UserDb implements Serializable {
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
			if (password.length() < 6) {
				System.out.println("密码长度过短，请重新设置6位以上密码！");
			} else {
				System.out.print("请再次输入密码：");
				String repassword = Shop.sc.next();
				if (password.equals(repassword)) {
					// System.out.println("注册成功！");
					UserDb user = new UserDb();
					user.setUsername(username);
					user.setPassword(password);
					// Shop.userList.add(user);;
					// Shop.saveListToFile();
					int i = user.save(user);
					if (i > 0) {
						System.out.println("注册成功！");
					}
					break;
				} else {
					System.out.println("两次密码不一致!");
				}
			}
		}
	}

	public int save(UserDb user) {
		String sql = "insert into user(name,password) values(?,?)";
		int i = JdbcTools.exec_update(sql, user.getUsername(), user.getPassword());
		return i;
	}

	public void login() {
		boolean loginResult = false;
		int count = 1;
		while (true) {
			if (count++ <= 3) {
				System.out.print("请输入用户名：");
				String login_username = Shop.sc.next();
				System.out.print("请输入密码：");
				String login_password = Shop.sc.next();

				Connection conn = JdbcTools.getConn();
				String sql = "select * from user where name=? and password=?";
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, login_username);
					ps.setString(2, login_password);

					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						System.out.println("登录成功！");
						this.setLogin(true);
						break;
					} else {
						System.out.println("登录失败！");
					}
					JdbcTools.Close(rs, ps, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				/*
				 * for (UserDb user : ShopDb.userList) { if
				 * (login_username.equals(user.getUsername()) &&
				 * login_password.equals(user.getPassword())) {
				 * System.out.println("登录成功!"); this.setLogin(true); loginResult
				 * = true; break; } } if (loginResult == true) { break; } else {
				 * System.out.println("登录失败，请重新登录！"); }
				 */
			} else {
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
					ShopDb.myGoodList.add(myGood);
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

	public Good findGoodById(int id) {
		Good returnGood = null;
		/*for (Good good : ShopDb.goodList) {
			if (good.getId() == id) {
				returnGood = good;
				break;
			}
		}*/
		Connection conn = JdbcTools.getConn();
		String sql = "select * from good where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				returnGood = new Good();
				returnGood.setId(id);
				returnGood.setName(rs.getString("name"));
				returnGood.setPrice(rs.getDouble("price"));
				returnGood.setNum(rs.getInt("num"));
			} else {
				System.out.println("登录失败！");
			}
			
			JdbcTools.Close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnGood;
	}

	public void showMyGoodList() {
		double count = 0;
		System.out.println("所购买商品如下：");
		for (Good good : ShopDb.myGoodList) {
			System.out.println(good);
			double price = good.getPrice();
			int num = good.getNum();

			count = price * num;

		}
		System.out.println("总价格为：" + count);
	}

	public void showGoodList() {
		System.out.println("商品列表如下：");
		for (Good good : ShopDb.goodList) {
			System.out.println(good);
		}
	}
}
