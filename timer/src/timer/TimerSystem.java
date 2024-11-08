package timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class TimerSystem {
	
	private TimerSystem(){
		
	}
	private static TimerSystem instance = new TimerSystem();
	
	public static TimerSystem getInstance() {
		return instance;
	}
	
	private static Scanner scanner = new Scanner(System.in);
	
	private static SimpleDateFormat sdf;
	private static boolean isRun = true;
	private static int minute;
	private static int second;
	
	public static void run() {
		while(isRun) {
			setTimer();
			runTimer();
		}
	}
	private static void setTimer() {
		System.out.println("분 : ");
		minute = scanner.nextInt();
		System.out.println("초 : ");
		second = scanner.nextInt();
	}
	private static void runTimer() {
		int time = minute * 60 + second;
		
		sdf = new SimpleDateFormat("HH:mm:ss.S");
		while(time > 0) {
			Calendar cal = Calendar.getInstance();
			String message = sdf.format(cal.getTime());
			message += String.format("(%2d)", time--);
			System.out.println(message);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Timeout!");
	}
}
