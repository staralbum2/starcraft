package atm;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AtmSystem {
	private final int STRING = 1;
	private final int NUMBER = 2;
	private final int JOIN = 1;
	private final int LOG_IN = 2;
	private final int ACCOUNT = 1;
	private final int ADD = 1;
	private final int REMOVE = 2;
	private final int DEPOSIT = 2;
	private final int WITHDRAWAL = 3;
	private final int TRANSFER = 4;
	private final int CHECK = 5;
	private final int LOG_OUT = 6;
	private final int SAVE = 7;
	private final int LOAD = 8;

	private final int EXIT = 0;

	private Scanner scan = new Scanner(System.in);
	private Random ran = new Random();

	private String name;
	private ArrayList<User> users = new ArrayList<>();
	private int log = -1;

	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	private String fileName =   "ATM.txt";
	private String home = System.getProperty("user.home");
	private String sep = System.getProperty("file.separator");
	private String doc = "documents";
	private String path = String.format("%s%s%s%s%s", home, sep, doc, sep, fileName);
	private File file;

	AtmSystem(String name) {
		this.name = name;
	}

	public void run() {
		// 회원탈퇴,가입
		// 계좌 신청 철회(1인 3개맥스)
		// 로그인
		// +뱅킹기능 입출금조회이체계좌신청철회
		// 파일 저장 로드
		while (true) {
			printMenu();
			int sel = (int) input(NUMBER, "메뉴 선택");
			if (exceptionSelSize(sel, EXIT, LOG_IN))
				continue;
			if (sel == JOIN)
				joinUser();
			else if (sel == LOG_IN) {
				logIn();
				while (log != -1)
					afterLogin();
			} else if (sel == EXIT) {
				System.out.println(name + " atm 서비스 종료");
				break;
			}
		}
	}

	private void printMenu() {
		System.out.println("=== " + name + " ATM 서비스 ===");
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		System.out.println("0.종료");
	}

	private void joinUser() {
		String id = (String) input(STRING, "ID");
		String pw = (String) input(STRING, "PW");
		String name = (String) input(STRING, "이름");
		int code = createRandomCode();
		User user = new User(code, name, id, pw);
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(id)) {
				System.out.println("중복되는 아이디가 있숩니다.");
				return;
			}
		}
		users.add(user);
		System.out.println("가입완료.");
	}

	private int createRandomCode() {
		while (true) {
			int code = ran.nextInt(9000) + 1000;
			if (!users.contains(code))
				return code;
		}
	}

	private void logIn() {
		String id = (String) input(STRING, "ID");
		String pw = (String) input(STRING, "PW");
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getId().equals(id) && user.getPw().equals(pw)) {
				log = i;
				System.out.println("로그인 성공");
				return;
			}
		}
		System.err.println("계정 정보를 다시 확인하세요.");
	}

	private void afterLogin() {
		printSecondMenu();
		int sel = (int) input(NUMBER, "메뉴 선택");
		if (exceptionSelSize(sel, ACCOUNT, LOAD))
			return;
		if (exceptionAccountNum(sel))
			return;
		switch (sel) {
		case ACCOUNT:
			account();
			break;
		case DEPOSIT:
			deposit();
			break;
		case WITHDRAWAL:
			withdrawal();
			break;
		case TRANSFER:
			transfer();
			break;
		case CHECK:
			check();
			break;
		case LOG_OUT:
			logOut();
			break;
		case SAVE:
			save();
			break;
		case LOAD:
			load();
			break;
		}

	}

	private void printSecondMenu() {
		System.out.println(users.size());
		System.out.println(log);
		System.out.println("1) 계좌관리");
		System.out.println("2) 입금");
		System.out.println("3) 출금");
		System.out.println("4) 이체");
		System.out.println("5) 조회");
		System.out.println("6) 로그아웃");
		System.out.println("7) 저장");
		System.out.println("8) 불러오기");
		System.out.println("0) 종료");
	}

	private void account() {
		printAccountMenu();
		System.out.println(users.get(log).getAccoutSize());
		int sel = (int) input(NUMBER, "메뉴 선택");
		if (exceptionSelSize(sel, ADD, REMOVE))
			return;
		
		if (sel == ADD) {
			if (users.get(log).getAccoutSize() >= 3) {
				System.err.println("개설 가능 한도초과 입니다.");
				return;
			}
			addAccount(); // 3개까지가능
			System.out.println("개설 완료");
		} else if (sel == REMOVE) {
			if (users.get(log).getAccoutSize() <= 0) {
				System.err.println("개설하신 계좌가 없습니다.");
				return;
			}
			removeAccount(); // 0개땐 불가능
		}
	}

	private void addAccount() {
		while (true) {
			int ranAccout = 1110000121 + ran.nextInt((9999) + 1) * 1000;
			Account temp = new Account(ranAccout);
			if (!users.contains(temp)) {
				users.get(log).addAcount(temp);
				System.out.println(temp);
				break;
			}
		}
	}

	private void removeAccount() {

		printMyAccount();
		int sel = (int) input(NUMBER, "철회하실 계좌 선택") - 1;
		if (exceptionSelSize(sel, 0, users.get(log).getAccoutSize() - 1))
			return;

		System.out.println("철회 완료");
		users.get(log).removeAcount(sel);
	}

	private void printMyAccount() {
		System.out.println(users.get(log));
	}

	private void printAccountMenu() {
		System.out.println(log);
		System.out.println("1) 계좌 생성");
		System.out.println("2) 계좌 철회");
	}

	private void deposit() {
		printMyAccount();
		int sel = (int) input(NUMBER, "입금하실 계좌 선택") - 1;
		if (exceptionSelSize(sel, 0, users.get(log).getAccoutSize() - 1))
			return;
		int cash = (int) input(NUMBER, "입금하실 금액");
		if (cash <= 100) {
			System.out.println("100원 이하 입금 불가");
			return;
		}
		users.get(log).getAccout().get(sel).setMoney(cash);
		System.out.println("입금 완료");
	}

	private void withdrawal() {
		printMyAccount();
		int sel = (int) input(NUMBER, "입금하실 계좌 선택") - 1;
		User temp = users.get(log);
		if (exceptionSelSize(sel, 0, temp.getAccoutSize() - 1))
			return;
		int cash = (int) input(NUMBER, "출금하실 금액");
		if (cash <= 100) {
			System.out.println("100원 이하 출금 불가");
			return;
		}
		if (cash > temp.getAccout().get(sel).getMoney()) {
			System.out.println("잔액이 부족합니다.");
			return;
		}
		temp.getAccout().get(sel).setMoney(-cash);
		System.out.println("출금 완료");
	}

	private void transfer() {
		printMyAccount();
		int sel = (int) input(NUMBER, "이체하실 계좌 선택") - 1;
		int transAccount = (int) input(NUMBER, "이체받을 계좌번호");
		int findUserIdx = -1;
		int findIdx = -1;
		for (int i = 0; i < users.size(); i++) {
			User temp = users.get(i);
			ArrayList<Account> tempAccount = temp.getAccout();
			for (int j = 0; j < tempAccount.size(); j++) {
				if (tempAccount.get(j).getNum() == transAccount) {
					findUserIdx = i;
					findIdx = j;
					break;
				}
			}
		}
		if (findUserIdx == -1) {
			System.out.println("이체받을 계좌를 다시 확인하세요.");
			return;
		}
		User give = users.get(log);
		User receive = users.get(findUserIdx);
		int cash = (int) input(NUMBER, "이체하실 금액");
		if (cash <= 100) {
			System.out.println("100원 이하 이체 불가");
			return;
		}
		if (cash > give.getAccout().get(sel).getMoney()) {
			System.out.println("잔액이 부족합니다.");
			return;
		}
		give.getAccout().get(sel).setMoney(-cash);
		receive.getAccout().get(findIdx).setMoney(cash);
		System.out.println("이체 완료");
	}

	private void check() {
		printMyAccount();
	}

	private void logOut() {
		log = -1;
		System.out.println("로그아웃 완료.");
	}

	private void save() {
		
		// 유저명 
		// ㄴ 이름 코드 아이디 비번 계좌
		// 					 ㄴ 생성된 계좌 
		try {
			file = new File(path);
			fileWriter = new FileWriter(file);
			String info = "";
			for(int i=0;i<users.size();i++) {
				User tempUser = users.get(i);
				info = String.format("%d,%s,%s,%s", tempUser.getCode(),tempUser.getName(),tempUser.getId(),tempUser.getPw());
				for(int j=0;j<tempUser.getAccout().size();j++) {
					Account tempAccount = tempUser.getAccout().get(j);	
					info += "+" + tempAccount.getNum() + "+" + tempAccount.getMoney();
				}
				if(i<users.size()-1) {
					info+="\n";
				}
			}
			fileWriter.write(info);
			System.out.println("파일저장성공");
		} catch (Exception e) {
			System.err.println("파일저장실패");
			e.printStackTrace();
		}
		finally {
			try {
				fileWriter.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void load() {

	}

	private boolean exceptionSelSize(int sel, int start, int max) {
		if (sel < start || sel > max) {
			System.err.println("선택범위 초과");
			return true;
		}
		return false;
	}

	private boolean exceptionAccountNum(int sel) {
		if ((sel >= DEPOSIT && sel <= CHECK) && users.get(log).getAccout().size() == 0) {
			System.err.println("개설된 계좌가 없습니다.");
			return true;
		}
		return false;
	}

	private Object input(int type, String msg) {
		System.out.println(msg + " ");
		String input = null;
		if (type == STRING) {
			while (true) {
				input = scan.nextLine();
				if (!input.equals(""))
					return input;
			}
		}
		if (type == NUMBER) {
			input = scan.nextLine();
			try {
				int num = Integer.parseInt(input);
				return num;
			} catch (Exception e) {
				System.err.println("숫자를 입력하세요.");
			}
		}
		return -1;
	}
}
