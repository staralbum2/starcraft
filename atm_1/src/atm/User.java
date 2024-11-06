package atm;

import java.util.ArrayList;

public class User {
	private int code;
	private String name;
	private ArrayList<Account> accounts;
	
	public User(int code, String name) {
		this.name=name;
		this.code=code;
	}
	public void addAcount(Account account) {
		accounts.add(account);
	}
	public void removeAcount(Account account) {
		accounts.remove(account);
	}
}1
