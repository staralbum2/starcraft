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
	private final int LOG_OUT = 7;
	private final int SAVE = 7;
	private final int LOAD = 8;

	private final int EXIT = 0;

	private Scanner scan = new Scanner(System.in);
	private Random ran = new Random();

	private ArrayList<User> users = new ArrayList<>();
	private int log = -1;

	public void run() {
		// 회원탈퇴,가입
		// 계좌 신청 철회(1인 3개맥스)
		// 로그인
		// +뱅킹기능 입출금조회이체계좌신청철회
		// 파일 저장 로드
		while (true) {
			printMenu();
			int sel = (int) input(NUMBER, "메뉴 선택");
			if (sel == JOIN)
				joinUser();
			else if (sel == LOG_IN)
				while (log != -1)
					logIn();
		}
	}

	private void printMenu() {
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
	}

	private void joinUser() {
		String id = (String) input(STRING, "ID");

		String pw = (String) input(STRING, "PW");
		String name = (String) input(STRING, "이름");
		int code = createRandomCode();
		User user = new User(code, name, id, pw);
		if (users.contains(user)) {
			System.out.println("중복되는 아이디입니다.");
			return;
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
		int idIdx = users.indexOf(id);
		if (idIdx != users.indexOf(pw) || users.indexOf(pw) != -1 || idIdx != -1) {
			System.err.println("계정 정보를 다시 확인하세요.");
			return;
		}
		log = idIdx;
		System.out.println("로그인 성공");
		afterLogin();
	}

	private void afterLogin() {
		printSecondMenu();
		int sel = (int) input(NUMBER, "메뉴 선택");
		switch (sel) {
		case ACCOUNT:
			accout();
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

		default:
			System.err.println("메뉴선택오류");
		}

	}

	private void printSecondMenu() {
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

	private void accout() {

	}

	private void deposit() {

	}

	private void withdrawl() {

	}

	private void transfer() {

	}

	private void check() {

	}
	private void logOut() {
		log = -1;
		System.out.println("로그아웃 완료.");
	}

	private void save() {

	}

	private void load() {

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
		return input;
	}
}
