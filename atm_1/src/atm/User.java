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
	@Override
	public String toString() {
		String info = "";
		
		info += String.format("%s  %s님의 계좌목록", this.id,this.name);
		for(int i=0;i<accounts.size();i++) {
			if(accounts != null)
				info+= "\n" + accounts.get(i);
		}
		return info;
	}
}
