package com.qst.project01;

public class AdminDb extends UserDb {

	public void adminLogin() {
		int count = 1;
		while (true) {
			if (count++ <= 3) {
				System.out.print("���������Ա�û�����");
				String username = Shop.sc.next();
				System.out.print("���������Ա���룺");
				String password = Shop.sc.next();
				if (username.equals("admin") && password.equals("admin")) {
					System.out.println("����Ա��¼�ɹ�!");
					while (true) {
						int choice = this.showAdminMenu();
						this.chooseAdminMenu(choice);
						if (choice == 5) {
							break;
						}
					}
					break;
				} else {
					System.out.println("��¼ʧ�ܣ������µ�¼��");
				}
			} else {
				System.out.println("��¼�������Σ����Ժ��¼��");
				break;
			}
		}
	}

	private int showAdminMenu() {
		System.out.println("**********����Ա�˵�**********");
		System.out.println("\t1.�����Ʒ");
		System.out.println("\t2.�޸���Ʒ");
		System.out.println("\t3.ɾ����Ʒ");
		System.out.println("\t4.�鿴��Ʒ�б�");
		System.out.println("\t5.�˳���¼");
		System.out.println("**********����Ա�˵�**********");
		System.out.print("��ѡ��˵���");
		int choice = Shop.sc.nextInt();
		return choice;
	}

	private boolean chooseAdminMenu(int choice) {
		boolean result = true;
		String go_on = "Y";
		switch (choice) {
		case 1:
			System.out.println("��ѡ��Ĳ˵��ǣ������Ʒ");
			while (go_on.equals("Y")) {
				this.addGood();
				System.out.println("���Ƿ������ӣ�Y/N");
				go_on = Shop.sc.next();
				go_on = go_on.toUpperCase();
			}
			break;
		case 2:
			System.out.println("��ѡ��Ĳ˵��ǣ��޸���Ʒ");
			this.updateGood();
			break;
		case 3:
			System.out.println("��ѡ��Ĳ˵��ǣ�ɾ����Ʒ");
			this.deleteGood();
			break;
		case 4:
			System.out.println("��ѡ��Ĳ˵��ǣ��鿴��Ʒ�б�");
			super.showGoodList();
			break;
		case 5:
			System.out.println("ллʹ�ã�");
			result = false;
			break;
		default:
			System.out.println("[������������]");
			break;

		}
		return result;
	}

	// �����Ʒ
	private void addGood() {
		//System.out.print("��������Ʒ��ţ�");
		//int id = Shop.sc.nextInt();
		System.out.print("��������Ʒ���ƣ�");
		String name = Shop.sc.next();
		System.out.print("��������Ʒ�۸�");
		double price = Shop.sc.nextDouble();
		System.out.print("��������Ʒ������");
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
			System.out.println("�����Ʒ�ɹ���");
		}else{
			System.out.println("�����Ʒʧ�ܣ�");
		}
	}

	/*
	 * ����id������Ʒ
	 * 
	 * private Good findGoodById(int id){ Good returnGood = null; for(Good good
	 * : Shop.goodList){ if(good.getId() == id){ returnGood = good; break; } }
	 * return returnGood; }
	 */
	// �޸���Ʒ
	private void updateGood() {
		System.out.print("��������Ҫ�޸ĵ���Ʒ��ţ�");
		int id = Shop.sc.nextInt();
		
		Good good = this.findGoodById(id);
		if (good == null) {
			System.out.print("δ�ҵ���Ʒ��");
		} else {
			System.out.println("����Ʒ����Ϣ���£�");
			System.out.println("��Ʒid\t ��Ʒ����\t ��Ʒ�۸�\t ��Ʒ����");
			System.out.println(good.getId() + "\t" + good.getName() + "\t" + good.getPrice() + "\t" + good.getNum());
		}
		System.out.print("�������޸ĺ����Ʒ���ƣ�");
		String name = Shop.sc.next();
		System.out.print("�������޸ĺ����Ʒ�۸�");
		Double price = Shop.sc.nextDouble();
		System.out.print("�������޸ĺ����Ʒ������");
		int num = Shop.sc.nextInt();
		good.setNum(num);
		
		int i = good.update(good);
		if(i > 0){
			System.out.println("�޸���Ʒ�ɹ���");
		}else{
			System.out.println("�޸���Ʒʧ�ܣ�");
		}
	}

	// ɾ����Ʒ
	private void deleteGood() {
		System.out.print("��������Ҫɾ������Ʒ��ţ�");
		int id = Shop.sc.nextInt();
		Good good = this.findGoodById(id);
		Shop.goodList.remove(good);
		System.out.println("ɾ����Ʒ�ɹ���");
	}
	// �鿴��Ʒ
	/*
	 * private void showGoodList(){ System.out.println("��Ʒ�б����£�"); for(Good good
	 * : Shop.goodList){ System.out.println(good); } }
	 */
}
