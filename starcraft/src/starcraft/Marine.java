package starcraft;

public class Marine extends GroundUnit implements Attackable {
    public Marine() {
        super("Marine", 50, 6); 
    }

    @Override
    public void attack(Unit target) {
        super.attack(target); 
    }
}
