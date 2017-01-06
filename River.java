import java.util.Random;

public class River {
    private static Random rand = new Random();

    private String name;
    private Fish[] fishes;

    public River(String nName) {
        name = nName;
        fishes = new Fish[5];
        for (int f = 0; f < 5; f++) {
            fishes[f] = new Fish(rand.nextInt(5));
        }
    }

    public Fish getFish() {
        Fish capture;
        for (int f = 0; f < 5; f++) {
            if (fishes[f] != null) {
                capture = fishes[f];
                fishes[f] = null;
                f = 99;
                return capture;
            }
        }
        return null;
    }

    public boolean replenishFish() {
        boolean hasFish = false;
        for (int f = 0; f < 5; f++) {
            if (fishes[f] != null) {
                hasFish = true;
            }
        }
        if (!hasFish) {
            for (int f = 0; f < 5; f++) {
                fishes[f] = new Fish(rand.nextInt(5));
            }
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }
}