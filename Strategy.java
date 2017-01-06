public class Strategy {
    private int strategyLevel = 0;
    private static final int BATTLE_INCREASE = 10;
    private static final int SIEGE_INCREASE = 40;
    private boolean conqueredTheWorld = false;

    public void battle() {
        strategyLevel += BATTLE_INCREASE;
        checkStrategy();
    }

    public void siege() {
        strategyLevel += SIEGE_INCREASE;
        checkStrategy();
    }

    public void checkStrategy() {
        if (strategyLevel > 180) {
            conqueredTheWorld = true;
        }
    }

    public int getStrategyLevel() {
        return strategyLevel;
    }

    public boolean conqueredTheWorld() {
        return conqueredTheWorld;
    }
}