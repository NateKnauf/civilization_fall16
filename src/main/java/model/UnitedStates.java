package model;

/**
 * Represents the United States civilization.
 *
 * @version 2.0
 * @author Angie Palm, Jim Harris
 */
public class UnitedStates extends Civilization {
    private Hills hills = new Hills();

    /**
     * Public constructor.
     */
    public UnitedStates() {
        super("United States");
    }

    @Override
    public String explore() {
        int resources = hills.mineCoal();
        produceResources(resources);
        return "You go mining and get " + resources + " resources!";
    }

    /**
     * @return the Hills for this Civilization.
     */
    public Hills getHills() {
        return hills;
    }

    @Override
    public MilitaryUnit getSiegeUnit() {
        return new WallUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new LincolnMemorial(this);
    }

}
