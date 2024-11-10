package starcraft;

abstract public class FlyUnit extends Unit {
	FlyUnit(String name,int hp,int power) {
		super(name,hp,power);
	}

	abstract void canFly();
}
