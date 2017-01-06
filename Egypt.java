public class Egypt {
    private Population myPopulation;
    private Treasury myTreasury;
    private CoalMine myCoalMine;
    private River myRiver;
    private Technology myTechnology;
    private Strategy myStrategy;
    private Settlement[] mySettlements;

    private Desert myDesert;

    public Egypt() {
        myPopulation = new Population();
        myTreasury = new Treasury();
        myCoalMine = new CoalMine();
        myRiver = new River("Nile");
        myTechnology = new Technology();
        myStrategy = new Strategy();
        mySettlements = new Settlement[10];
        myDesert = new Desert();
    }

    public boolean settle(Settlement settling) {
        if (getNumSettlements() < 10) {
            for (int s = 0; s < mySettlements.length; s++) {
                if (mySettlements[s] == null) {
                    mySettlements[s] = settling;
                    s = mySettlements.length * 99 + 99;
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumSettlements() {
        int n = 0;
        for (int s = 0; s < mySettlements.length; s++) {
            if (mySettlements[s] != null) {
                n++;
            }
        }
        return n;
    }

    public Population getPopulation() {
        return myPopulation;
    }

    public Technology getTechnology() {
        return myTechnology;
    }

    public Strategy getStrategy() {
        return myStrategy;
    }

    public Settlement[] getSettlements() {
        return mySettlements;
    }

    public CoalMine getCoalMine() {
        return myCoalMine;
    }

    public Treasury getTreasury() {
        return myTreasury;
    }

    public River getRiver() {
        return myRiver;
    }

    public Desert getDesert() {
        return myDesert;
    }

    public boolean buildPyramid(Settlement target) {
        if (target.build(myTreasury.getCoins(), myPopulation, 500, 100)) {
            myTechnology.increaseExperience(10);
            return true;
        }
        return false;
    }

    public void practiceHieroglyphics() {
        myTechnology.improveWriting();
        myPopulation.increaseHappiness(10);
    }

}