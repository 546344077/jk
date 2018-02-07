package com.lanou.bean;

import java.io.Serializable;
/**
 * 货物 销量前10
 * @author 就是我
 *
 */
public class ProductBean implements Serializable {
	
	//货物名称
	private String  factory_name;
	//货物数量
	private int countnum;

	public String getFactory_name() {
		return factory_name;
	}
	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}
	public int getCountnum() {
		return countnum;
	}
	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}

	
	
	
}
