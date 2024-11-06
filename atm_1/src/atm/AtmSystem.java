package atm;

import java.util.ArrayList;
import java.util.Scanner;

import javax.lang.model.element.ModuleElement.UsesDirective;

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
	private final int SAVE = 6;
	private final int LOAD = 7;
	private final int LOG_OUT = 8;
	private final int EXIT = 0;
	
	private Scanner scan = new Scanner(System.in);
	
	private ArrayList<ArrayList<User>> users;
	private int log = -1;
	
	public void run() {
		// 회원탈퇴,가입
		// 계좌 신청 철회(1인 3개맥스)
		// 로그인
		// +뱅킹기능 입출금조회이체계좌신청철회
		// 파일 저장 로드
		while (true) {
			printMenu();
			int sel = (int)input(NUMBER,"메뉴 선택");
			switch (sel) {
			case JOIN:
				break;

			case LOG_IN:
				logIn();
				break;
			default:
				System.err.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	private void logIn() {
		String id = (String)input(STRING,"ID");
		String pw = (String)input(STRING,"PW");
		int idIdx = users.indexOf(id);
		if(idIdx != users.indexOf(pw) || users.indexOf(pw)!= -1 || idIdx != -1) {
			System.err.println("계정 정보를 다시 확인하세요.");
			return;
		}
		log = idIdx;
		System.out.println("로그인 성공");
		afterLogin();
	}
	private void afterLogin() {
		printSecondMenu();
		int sel = (int)input(NUMBER,"메뉴 선택");
	}
	
	private Object input(int type,String msg) {
		System.out.println(msg + " ");
		String input = null;
		if(type == STRING) {
			while(true) {
				input = scan.nextLine();
				if(!input.equals("")) 
					return input;
			}
		}
		if(type == NUMBER) {
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
