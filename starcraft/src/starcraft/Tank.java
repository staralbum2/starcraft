package starcraft;

public class Tank extends GroundUnit implements Attackable,Repair {
    public Tank() {
        super("Tank", 150, 35); 
    }

    @Override
    public void attack(Unit target) {
        super.attack(target); 
    }
}
