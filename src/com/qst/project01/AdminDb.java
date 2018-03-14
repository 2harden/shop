package com.qst.project01;

public class AdminDb extends UserDb {

	public void adminLogin() {
		int count = 1;
		while (true) {
			if (count++ <= 3) {
				System.out.print("请输入管理员用户名：");
				String username = Shop.sc.next();
				System.out.print("请输入管理员密码：");
				String password = Shop.sc.next();
				if (username.equals("admin") && password.equals("admin")) {
					System.out.println("管理员登录成功!");
					while (true) {
						int choice = this.showAdminMenu();
						this.chooseAdminMenu(choice);
						if (choice == 5) {
							break;
						}
					}
					break;
				} else {
					System.out.println("登录失败，请重新登录！");
				}
			} else {
				System.out.println("登录超过三次，请稍后登录！");
				break;
			}
		}
	}

	private int showAdminMenu() {
		System.out.println("**********管理员菜单**********");
		System.out.println("\t1.添加商品");
		System.out.println("\t2.修改商品");
		System.out.println("\t3.删除商品");
		System.out.println("\t4.查看商品列表");
		System.out.println("\t5.退出登录");
		System.out.println("**********管理员菜单**********");
		System.out.print("请选择菜单：");
		int choice = Shop.sc.nextInt();
		return choice;
	}

	private boolean chooseAdminMenu(int choice) {
		boolean result = true;
		String go_on = "Y";
		switch (choice) {
		case 1:
			System.out.println("您选择的菜单是：添加商品");
			while (go_on.equals("Y")) {
				this.addGood();
				System.out.println("你是否继续添加？Y/N");
				go_on = Shop.sc.next();
				go_on = go_on.toUpperCase();
			}
			break;
		case 2:
			System.out.println("您选择的菜单是：修改商品");
			this.updateGood();
			break;
		case 3:
			System.out.println("您选择的菜单是：删除商品");
			this.deleteGood();
			break;
		case 4:
			System.out.println("您选择的菜单是：查看商品列表");
			super.showGoodList();
			break;
		case 5:
			System.out.println("谢谢使用！");
			result = false;
			break;
		default:
			System.out.println("[您的输入有误！]");
			break;

		}
		return result;
	}

	// 添加商品
	private void addGood() {
		//System.out.print("请输入商品编号：");
		//int id = Shop.sc.nextInt();
		System.out.print("请输入商品名称：");
		String name = Shop.sc.next();
		System.out.print("请输入商品价格：");
		double price = Shop.sc.nextDouble();
		System.out.print("请输入商品数量：");
		int num = Shop.sc.nextInt();

		Good good = new Good();
		//good.setId(id);
		good.setName(name);
		good.setPrice(price);
		good.setNum(num);
		
		//Shop.goodList.add(good);
		int i = good.save(good);
		if(i > 0)
		{
			System.out.println("添加商品成功！");
		}else{
			System.out.println("添加商品失败！");
		}
	}

	/*
	 * 按照id查找商品
	 * 
	 * private Good findGoodById(int id){ Good returnGood = null; for(Good good
	 * : Shop.goodList){ if(good.getId() == id){ returnGood = good; break; } }
	 * return returnGood; }
	 */
	// 修改商品
	private void updateGood() {
		System.out.print("请输入需要修改的商品编号：");
		int id = Shop.sc.nextInt();
		
		Good good = this.findGoodById(id);
		if (good == null) {
			System.out.print("未找到商品！");
		} else {
			System.out.println("该商品的信息如下：");
			System.out.println("商品id\t 商品名称\t 商品价格\t 商品数量");
			System.out.println(good.getId() + "\t" + good.getName() + "\t" + good.getPrice() + "\t" + good.getNum());
		}
		System.out.print("请输入修改后的商品名称：");
		String name = Shop.sc.next();
		System.out.print("请输入修改后的商品价格：");
		Double price = Shop.sc.nextDouble();
		System.out.print("请输入修改后的商品数量：");
		int num = Shop.sc.nextInt();
		good.setNum(num);
		
		int i = good.update(good);
		if(i > 0){
			System.out.println("修改商品成功！");
		}else{
			System.out.println("修改商品失败！");
		}
	}

	// 删除商品
	private void deleteGood() {
		System.out.print("请输入需要删除的商品编号：");
		int id = Shop.sc.nextInt();
		Good good = this.findGoodById(id);
		Shop.goodList.remove(good);
		System.out.println("删除商品成功！");
	}
	// 查看商品
	/*
	 * private void showGoodList(){ System.out.println("商品列表如下："); for(Good good
	 * : Shop.goodList){ System.out.println(good); } }
	 */
}
