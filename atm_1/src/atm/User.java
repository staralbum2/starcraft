package atm;

import java.util.ArrayList;

public class User {
	private int code;
	private String name;
	private String id;
	private String passWord;
	private ArrayList<Account> accounts;
	
	public User(int code, String name,String id,String passWord) {
		this.name=name;
		this.code=code;
		this.id=id;
		this.passWord = passWord;
	}
	public void addAcount(Account account) {
		accounts.add(account);
	}
	public void removeAcount(Account account) {
		accounts.remove(account);
	}
}
