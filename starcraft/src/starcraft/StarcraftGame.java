package starcraft;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StarcraftGame {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Unit> units = new ArrayList<>();
    private static ArrayList<Unit> enemyUnits = new ArrayList<>();
    
    private static StarcraftGame starcraftGame = new StarcraftGame();
    private static Random random = new Random();

    StarcraftGame() {}

    static StarcraftGame getInstance() {
        return starcraftGame;
    }

    public static void run() {
        createInitialUnits();

        while (true) {
            printMenu();
            int sel = input("메뉴선택");
            if (sel == 1) {
                createUnitMenu();
            } else if (sel == 2) {
                performAction();
            } else if (sel == 0) {
                break;
            }
        }
    }

    private static void createInitialUnits() {
        units.add(new SCV());
        enemyUnits.add(new SCV());

        
    }

    private static void createUnitMenu() {
        System.out.println("어떤 유닛을 생성하시겠습니까?");
        System.out.println("1) 마린");
        System.out.println("2) 탱크");
        System.out.println("3) 드랍쉽");
        System.out.println("0) 취소");

        int choice = input("유닛 선택");

        if (choice == 1) {
            units.add(new Marine());
            System.out.println("마린 유닛이 생성되었습니다.");
        } else if (choice == 2) {
            units.add(new Tank());
            System.out.println("탱크 유닛이 생성되었습니다.");
        } else if (choice == 3) {
            units.add(new DropShip());
            System.out.println("드랍쉽 유닛이 생성되었습니다.");
        } else if (choice == 0) {
            System.out.println("유닛 생성이 취소되었습니다.");
        } else {
            System.out.println("잘못된 선택입니다.");
        }

      
        createEnemyUnit();
    }

    private static void createEnemyUnit() {
       
        int randomChoice = random.nextInt(3) + 1;  
        
        switch (randomChoice) {
            case 1:
                enemyUnits.add(new Marine());
                System.out.println("적이 마린 유닛을 생성했습니다.");
                break;
            case 2:
                enemyUnits.add(new Tank());
                System.out.println("적이 탱크 유닛을 생성했습니다.");
                break;
            case 3:
                enemyUnits.add(new DropShip());
                System.out.println("적이 드랍쉽 유닛을 생성했습니다.");
                break;
        }
    }

    private static void printMenu() {
        System.out.println("starcraft");
        System.out.println("보유중인 유닛:");
        for (int i = 0; i < units.size(); i++) {
            System.out.println(i + 1 + ") " + units.get(i));
        }
        System.out.println("1) 유닛 생성");
        System.out.println("2) 행동 지시");
        System.out.println("0) 종료");
    }

    private static int input(String msg) {
        System.out.println(msg + " : ");
        String input = scanner.nextLine();
        int num = -1;
        try {
            num = Integer.parseInt(input);
        } catch (Exception e) {
            System.err.println("숫자를 입력하세요.");
        }
        return num;
    }

    private static void performAction() {
        System.out.println("보유중인 유닛:");
        for (int i = 0; i < units.size(); i++) {
            System.out.println(i + 1 + ") " + units.get(i));
        }

        int attackerIndex = input("공격할 유닛 선택") - 1;
        if (attackerIndex < 0 || attackerIndex >= units.size()) {
            System.out.println("잘못된 유닛 선택입니다.");
            return;
        }

        Unit attacker = units.get(attackerIndex);
        if (attacker instanceof DropShip && ((DropShip) attacker).getProtectedUnit() != null) {
            System.out.println("현재 드랍쉽에 탑승중인 유닛이 있습니다. 해당 유닛은 공격할 수 없습니다.");
            return;
        }

        System.out.println("공격 대상 유닛:");
        for (int i = 0; i < enemyUnits.size(); i++) {
            System.out.println(i + 1 + ") " + enemyUnits.get(i));
        }

        int targetIndex = input("공격할 대상 선택") - 1;
        if (targetIndex < 0 || targetIndex >= enemyUnits.size()) {
            System.out.println("잘못된 적 유닛 선택입니다.");
            return;
        }

        Unit target = enemyUnits.get(targetIndex);
        System.out.println(attacker.getName() + "가 " + target.getName() + "을 공격합니다.");

        attacker.attack(target);

        if (target.getHp() <= 0) {
            System.out.println(target.getName() + " 유닛이 사망했습니다.");
            enemyUnits.remove(target);
        }

        if (enemyUnits.isEmpty()) {
            System.out.println("게임 종료! 당신이 승리했습니다.");
            System.exit(0);
        }

        if (!enemyUnits.isEmpty()) {
            Unit enemyAttacker = enemyUnits.get(0);
            Unit enemyTarget = units.get(0);
            System.out.println(enemyAttacker.getName() + "가 " + enemyTarget.getName() + "을 공격합니다.");
            enemyAttacker.attack(enemyTarget);

            if (enemyTarget.getHp() <= 0) {
                System.out.println(enemyTarget.getName() + " 유닛이 사망했습니다.");
                units.remove(enemyTarget);
            }

            if (units.isEmpty()) {
                System.out.println("게임 종료! 적이 승리했습니다.");
                System.exit(0);
            }
        }
    }
}
