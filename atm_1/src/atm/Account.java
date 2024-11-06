package atm;

import java.util.ArrayList;

public class Account {
	private int num;
	private int money;
	
	Account (int num){
		this.num = num;	
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return this.money;
	}
	
	public int getNum() {
		return this.num;
	}
	
}
