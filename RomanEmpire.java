public class RomanEmpire {
    private Population myPopulation;
    private Treasury myTreasury;
    private CoalMine myCoalMine;
    private River myRiver;
    private Technology myTechnology;
    private Strategy myStrategy;
    private Settlement[] mySettlements = new Settlement[10];

    private Hills myHills;

    public RomanEmpire() {
        myPopulation = new Population();
        myTreasury = new Treasury();
        myCoalMine = new CoalMine();
        myRiver = new River("Tiber");
        myTechnology = new Technology();
        myStrategy = new Strategy();
        mySettlements = new Settlement[10];
        myHills = new Hills();
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

    public Hills getHills() {
        return myHills;
    }

    public boolean buildAqueduct(Settlement target) {
        if (target.build(myTreasury.getCoins(), myPopulation, 250, 130)) {
            myTechnology.increaseExperience(10);
            return true;
        }
        return false;
    }

    public boolean buildBathHouse(Settlement target) {
        if (target.build(myTreasury.getCoins(), myPopulation, 110, 20)) {
            myTechnology.increaseExperience(10);
            return true;
        }
        return false;
    }

    public boolean buildVilla(Settlement target) {
        if (target.build(myTreasury.getCoins(), myPopulation, 80, 15)) {
            myTechnology.increaseExperience(5);
            return true;
        }
        return false;
    }

    public void studyPhilosophy() {
        if (myPopulation.getHappiness() >= 10) {
            myPopulation.decreaseHappiness(10);
            myTechnology.philosophize();
        }
    }

}