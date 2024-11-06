package atm;

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
		switch (sel) {
		case ACCOUNT:
			account();
			break;
		case DEPOSIT:
			deposit();
			break;
		case WITHDRAWAL:
			withdrawl();
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
			String ranAccout = String.format("111 - %d - 121%d", ran.nextInt(8998) + 1000, ran.nextInt(88) + 10);
			Account temp = new Account(ranAccout);
			if (!users.contains(temp)) {
				users.get(log).addAcount(temp);
				System.out.println(ranAccout);
				break;
			}
		}
	}

	private void removeAccount() {
		// 비밀번호 입력받고 계좌 세개보여준뒤
		// 번호입력받아 삭제
		//
		printMyAccount();
		int sel = (int)input(NUMBER, "철회하실 계좌 선택")-1;
		if(exceptionSelSize(sel, 0, users.get(log).getAccoutSize()-1))
			return;
		users.get(log).removeAcount(sel);
		System.out.println("삭제 완료");
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
		// 계좌 리스트 보여주ㅗㄱ 
		// 선택 계좌 > 입력계좌
		// 돈입력
		// 머니반영
	}

	private void withdrawl() {
		//이체할 계좌 목록보여주고 입력
		//이체받을 계좌입력 
		// 금액
		
	}

	private void transfer() {

	}

	private void check() {
		//리스트 세개
	}

	private void logOut() {
		log = -1;
		System.out.println("로그아웃 완료.");
	}

	private void save() {

	}

	private void load() {

	}

	private boolean exceptionSelSize(int sel, int start, int max) {
		return sel < start || sel > max;
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
