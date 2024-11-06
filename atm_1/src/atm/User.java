package atm;

import java.util.ArrayList;

public class User {
	private int code;
	private String name;
	private String id;
	private String passWord;
	private ArrayList<Account> accounts = new ArrayList<>();

	public User(int code, String name, String id, String passWord) {
		this.name = name;
		this.code = code;
		this.id = id;
		this.passWord = passWord;
	}

	public void addAcount(Account account) {
		accounts.add(account);
	}

	public void removeAcount(int idx) {
		accounts.remove(idx);
	}

	public int getAccoutSize() {
		return accounts.size();
	}

	public String getId() {
		return this.id;
	}

	public String getPw() {
		return this.passWord;
	}

	@Override
	public String toString() {
		String info = "";
		info += String.format("%s님의 계좌목록", this.name);
		for (int i = 0; i < accounts.size(); i++) 
			info += String.format("\n%d) %s", i+1,accounts.get(i).getNum());	
	
		return info;
	}
}
