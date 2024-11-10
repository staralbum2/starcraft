package starcraft;

public class DropShip extends Unit implements Repair {
    protected Unit protectedUnit;

    public DropShip() {
        super("DropShip", 150, 0);
    }

    public void boardUnit(Unit unit) {
        if (protectedUnit == null) {
            protectedUnit = unit;
            System.out.println(unit.getName() + "가 " + this.getName() + "에 탑승했습니다.");
        } else {
            System.out.println("이미 다른 유닛이 탑승 중입니다.");
        }
    }

    @Override
    public void attack(Unit target) {
        if (protectedUnit != null) {
            System.out.println(this.getName() + "가 공격을 대신 맞습니다.");
            super.attack(target);
        } else {
            super.attack(target);
        }
    }

    public void die() {
        if (protectedUnit != null) {
            System.out.println(protectedUnit.getName() + "는 이제 " + this.getName() + "에서 보호되지 않습니다.");
            protectedUnit = null;
        }
    }

    @Override
    public String toString() {
        String status = super.toString();
        if (protectedUnit != null) {
            status += " (현재 " + protectedUnit.getName() + " 탑승중)";
        }
        return status;
    }
}
