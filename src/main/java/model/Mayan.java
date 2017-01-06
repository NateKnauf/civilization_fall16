package model;

/**
 * Represents the Mayan Civilization.
 *
 * @version 2.0
 * @author Angie Palm, Jim Harris
 */
public class Mayan extends Civilization {
    private Desert desert = new Desert();

    /**
     * Public constructor.
     */
    public Mayan() {
        super("Mayan");
    }

    @Override
    public String explore() {
        int gold = desert.findTreasure();
        getTreasury().earn(gold);
        return "You explore the desert and find " + gold + " gold!";
    }

    /**
     * @return this civilization's Desert.
     */
    public Desert getDesert() {
        return desert;
    }

    @Override
    public HybridUnit getHybridUnit() {
        return new AlpacaUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new MayanPyramid(this);
    }
}
