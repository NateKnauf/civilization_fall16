import java.util.Random;
import java.util.Scanner;

public class Desert {
    private static Random rand = new Random();
    private Scanner scan = new Scanner(System.in);

    public int findTreasure() {
        int treasureTrove = rand.nextInt(500) + 1;
        boolean playerLost = false;
        if (rand.nextInt(10) == 0) {
            playerLost = true;
        }
        while (playerLost) {
            playerLost = lost();
        }
        return treasureTrove;
    }

    public boolean lost() {
        System.out.println("You have gotten lost! Press 1 to try escaping the"
            + " Desert.\nHopefully you can make it out alive.");
        int move = scan.nextInt();
        return (move == 1) ? false : true;
    }
}