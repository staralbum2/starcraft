package atm;

import java.util.ArrayList;

public class Account {
	private String num;
	private int money;
	
	Account (String num){
		this.num = num;
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
	@Override
	public String toString() {
	
		return this.num +"  "+ this.money + "원";
	}
	
}
