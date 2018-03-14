package com.qst.project01;

import com.qst.project01.tools.JdbcTools;

public class Good implements Cloneable {
	private int id;
	private String name;
	private Double price;
	private int num;

	public Good clone() {
		Good g1 = new Good();
		try {
			g1 = (Good) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return g1;
	}

	public Good() {
	};

	public Good(int id, String name, double price, int num) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", price=" + price + ", num=" + num;
	}

	public int save(Good good) {
		String sql = "insert into good(name,price,num) values(?,?,?)";
		int i = JdbcTools.exec_update(sql, good.getName(),good.getPrice(),good.getNum());
		return i;	
	}

	public int update(Good good) {
		String sql = "update good set name=?,price=?,num=? where id=?";
		int i = JdbcTools.exec_update(sql, good.getName(),good.getPrice(),good.getNum(),good.getId());
		return i;	
	}

}
