package atm;

import java.util.ArrayList;

public class Account {
	private String num;
	private int money;
	private int cnt;
	
	Account (String num){
		this.num = num;
		this.cnt++;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return this.money;
	}
	
	public String getNum() {
		return this.num;
	}
	public int getCnt() {
		return this.cnt;
	}
	
}
