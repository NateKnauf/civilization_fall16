package model;

/**
 * Represents the Ottoman Empire Civiization.
 *
 * @version 2.0
 * @author Angie Palm, Jim Harris
 */
public class OttomanEmpire extends Civilization {
    private Hills hills = new Hills();

    /**
     * Public constructor.
     */
    public OttomanEmpire() {
        super("Ottoman Empire");
    }

    @Override
    public String explore() {
        int food = hills.hunt().getHealth();
        hills.replenishGame();
        makeFood(food);
        return "You go hunting and get " + food + " food!";
    }

    /**
     * @return the Hills for this Civilization.
     */
    public Hills getHills() {
        return hills;
    }

    @Override
    public MeleeUnit getMeleeUnit() {
        return new FezUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new Ottoman(this);
    }
}
