package com.lanou.bean;

import java.io.Serializable;

public class LogBean implements Serializable {
	
	
	private String name;
	
	private int numbers;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	
	
	
}
