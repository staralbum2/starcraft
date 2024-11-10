package starcraft;

public class SCV extends GroundUnit implements Attackable, Repair {

    public SCV() {
        super("SCV", 60, 5);  
    }

    @Override
    public void attack(Unit target) {
        super.attack(target);  
    }

    public void repair(Unit target) {
        if (target instanceof Repair && target.getHp() < target.getMaxHp()) {
            while (target.getHp() < target.getMaxHp()) {
                target.setHp(target.getHp() + 1); 
                System.out.println(target.getName() + "을 수리 중입니다. 현재 HP: " + target.getHp() + "/" + target.getMaxHp());
                try {
                    Thread.sleep(300); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(target.getName() + "의 수리가 완료되었습니다.");
        } else {
            System.out.println(target.getName() + "는 수리할 수 없습니다.");
        }
    }
}
