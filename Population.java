import java.util.Random;

public class Population {
    private Random rand = new Random();

    private int warriors = 50;
    private int civilians = 50;
    private int happiness = 200;

    public void increaseHappiness(int delta) {
        happiness += delta;
    }

    public void decreaseHappiness(int delta) {
        happiness -= delta;
        if (happiness < 0) {
            happiness = 0;
        }
    }

    public int getHappiness() {
        return happiness;
    }

    public int getWarriors() {
        return warriors;
    }

    public void setWarriors(int set) {
        warriors = set;
    }

    public int getCivilians() {
        return civilians;
    }

    public boolean canWork(int workers) {
        if (civilians >= workers) {
            civilians -= workers;
            return true;
        }
        return false;
    }

    public Game hunt(Hills gameland) {
        return gameland.hunt();
    }

    public Fish fish(River riverland) {
        return riverland.getFish();
    }

    public boolean canCook(Game game, CoalMine coalmine) {
        if (coalmine.getCoal() >= coalmine.getBurnCost() * 4) {
            coalmine.burn();
            coalmine.burn();
            coalmine.burn();
            coalmine.burn();
            warriors += 40;
            civilians += 60;
            return true;
        } else {
            return false;
        }
    }

    public boolean canCook(Fish fish, CoalMine coalmine) {
        if (coalmine.getCoal() >= coalmine.getBurnCost() * 4) {
            coalmine.burn();
            coalmine.burn();
            coalmine.burn();
            coalmine.burn();
            warriors += 10;
            civilians += 15;
            return true;
        } else {
            return false;
        }
    }

    public boolean canBattle() {
        if (warriors > rand.nextInt(100)) {
            warriors -= rand.nextInt(20);
            return true;
        }
        return false;
    }
}