package starcraft;

abstract public class Unit {
	private int hp;
	private final int MAX_HP;
	private int power;
	
	public Unit(int hp, int power){
		this.hp = hp;
		MAX_HP = hp;
		this.power = power;
	}
	
}
