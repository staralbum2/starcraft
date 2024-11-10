package starcraft;

abstract public class Unit implements Attackable {
    private int hp;
    private final int MAX_HP;
    private int power;
    private String name;

    public Unit(String name, int hp, int power) {
        this.hp = hp;
        this.MAX_HP = hp;
        this.name = name;
        this.power = power;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return MAX_HP;
    }

    public String getName() {
        return this.name;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void attack(Unit target) {
        if (target != null && target.getHp() > 0) {
            target.setHp(target.getHp() - this.power); 
            System.out.println(this.name + "가 " + target.getName() + "을 공격하여 " + this.power + "의 피해를 입혔습니다.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%d/%d) - 공격력: %d", name, hp, MAX_HP, power);
    }
}
