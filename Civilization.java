import java.util.Scanner;
public class Civilization {
    private static boolean playing = true;
    private static String[][] cultures = {
        {"American", "George Washington", "Mr. President"},
        {"Zulu", "Shaka", "Your Majesty"},
        {"English", "Queen Elizabeth I", "Your Majesty"},
        {"Chinese", "Wu Zetian", "Your Imperial Majesty"}};
    private static int myCiv = -1;
    private static String[] cities = new String[5];
    private static String[] actions = {"Settle a City",
        "Demolish a City",
        "Build Militia",
        "Research Technology",
        "Attack Enemy City",
        "End Turn"};
    private static int day = 0;
    private static int attacks = 0;
    private static double gold = 20.5;
    private static double resources = 30;
    private static int happiness = 10;
    private static int troops = 0;
    private static int techPoints = 0;
    private static int cityCount = 0;

    public static void main(String[] args) {
        Scanner textBox = new Scanner(System.in);
        String in;
        System.out.println("Welcome to Civilization!");
        System.out.println("There are four civilization cultures:");
        for (int c = 0; c < cultures.length; c++) {
            System.out.println("\t" + cultures[c][0]);
        }
        System.out.println("Name the culture you'd like:");
        while (myCiv == -1) {
            in = getInput(textBox);
            for (int i = 0; i < cultures.length; i++) {
                if (cultures[i][0].toLowerCase().equals(in)
                        || cultures[i][0].toLowerCase().contains(in)) {
                    myCiv = i;
                }
            }
            if (myCiv == -1) {
                System.out.println("Sorry, '" + in
                    + "' isn't a culture in this game!");
                System.out.println("Only these four cultures exist:");
                for (int c = 0; c < cultures.length; c++) {
                    System.out.println("\t" + cultures[c][0]);
                }
            }
        }
        if (myCiv == 0 || myCiv == 2) {
            System.out.println("You've chosen to be an "
                + cultures[myCiv][0] + " civilization!");
        } else {
            System.out.println("You've chosen to be a "
                + cultures[myCiv][0] + " civilization!");
        }
        System.out.println("You must be " + cultures[myCiv][1]
            + "! It's a pleasure to meet you, " + cultures[myCiv][2] + "!");
        System.out.println("Every civilization needs a city!");
        System.out.println(cultures[myCiv][2]
            + ", what would you like to name the first city?");
        in = getInput(textBox);
        cities[0] = in;
        System.out.println(in + "? What a lovely name for a city!");
        boolean understood = false;
        while (playing) {
            understood = false;
            dayInfo();
            do {
                in = getChoice(getInput(textBox), actions);
                if (in != null) {
                    if (in.equals("settle a city")) {
                        understood = true;
                        if (gold < 20.5) {
                            System.out.println("It costs 15.5 gold"
                                + " to settle a new city-");
                            System.out.println("You don't have enough!");
                        } else if (cityCount >= 5) {
                            System.out.println("Current cities:");
                            printArray(cities);
                            System.out.println("You can't have more than 5!");
                        } else {
                            System.out.println("Current cities:");
                            printArray(cities);
                            System.out.println("What would you like to name "
                                + "the next city, " + cultures[myCiv][2] + "?");
                            in = getInput(textBox);
                            System.out.println(in + "? What a lovely name "
                                + "for a city!");
                            for (int c = 0; c < cities.length; c++) {
                                if (cities[c] == null) {
                                    cities[c] = in;
                                    c = cities.length + 99;
                                }
                            }
                        }
                    } else if (in.equals("demolish a city")) {
                        understood = true;
                        if (cityCount > 1) {
                            System.out.println("Which city would you like to "
                                + "demolish, " + cultures[myCiv][2] + "?");
                            in = getInput(textBox);
                            String citName;
                            for (int c = 0; c < cities.length; c++) {
                                citName = cities[c].toLowerCase();
                                if (in != null && cities[c] != null
                                        && citName.contains(in.toLowerCase())) {
                                    System.out.println(cities[c]
                                        + " was demolished!");
                                    cities[c] = null;
                                    c = 99;
                                    resources += 1.5;
                                }
                                if (c == cities.length - 1) {
                                    System.out.println(in + " can't be found!");
                                }
                            }
                        } else {
                            System.out.println("You need at least one city!");
                        }
                    } else if (in.equals("build militia")) {
                        understood = true;
                        if (gold >= 5 && resources >= 3) {
                            gold -= 5;
                            resources -= 3;
                            troops += 1;
                            System.out.println("You built a new militia!");
                        } else {
                            System.out.println("It costs 5 gold and 3 "
                                + "resources to build up a new militia-");
                            System.out.println("You don't have enough!");
                        }
                    } else if (in.equals("research technology")) {
                        understood = true;
                        if (gold >= 50 && resources >= 2) {
                            gold -= 50;
                            resources -= 2;
                            techPoints += 1;
                            System.out.println("You got a technology point!");
                        } else {
                            System.out.println("It costs 50 gold and 2 "
                                + "resources to research-");
                            System.out.println("You don't have enough!");
                        }
                    } else if (in.equals("attack enemy city")) {
                        understood = true;
                        if (troops >= 6) {
                            attacks += 1;
                            System.out.println("You attacked an enemy city!");
                            troops -= 6;
                            gold += 10;
                            happiness -= 3;
                        } else {
                            System.out.println("It costs 6 troops to attack-");
                            System.out.println("You don't have enough!");
                        }
                    } else if (in.equals("end turn")) {
                        System.out.println("You wasted the day, "
                            + cultures[myCiv][2] + "!");
                        understood = true;
                    } else {
                        System.out.println("I'm afraid I don't understand, "
                            + cultures[myCiv][2] + "...");
                    }
                } else {
                    System.out.println("I'm afraid I don't understand, "
                        + cultures[myCiv][2] + "...");
                }
            } while (!understood);
            dayUpdate();
        }
    }

    public static void countCities() {
        cityCount = 0;
        for (String city : cities) {
            if (city != null) {
                cityCount++;
            }
        }
    }

    public static void dayUpdate() {
        countCities();
        System.out.println("Day " + day + " has ended!");
        day++;
        if (resources % 2 == 0) {
            happiness += 1;
        } else {
            happiness -= 3;
        }
        resources += 1;
        if (happiness > 20) {
            resources += 5 * cityCount;
        }
        gold += 3 * cityCount;
        checkWin();
    }

    public static void dayInfo() {
        System.out.println("It's now Day " + day + " of history!");
        System.out.println("These are your cities!");
        printArray(cities);
        System.out.println("Your " + cultures[myCiv][0] + " society has");
        System.out.println("\tAttacked    : " + attacks + " times");
        System.out.println("\tGold        : " + String.format("%.2f", gold));
        System.out.println("\tResources   : "
            + String.format("%.2f", resources));
        System.out.println("\tHappiness   : " + happiness);
        System.out.println("\tTroops      : " + troops  + " military units");
        System.out.println("\tTech Points : " + techPoints);
        System.out.println("Today you can");
        printArray(actions);
    }

    public static void checkWin() {
        if (techPoints >= 20) {
            System.out.println("You have 20 Technology Points!");
            System.out.println(cultures[myCiv][2]
                + ", your civilization has become so advanced...");
            System.out.println("It has transcended existence!");
            System.out.println(cultures[myCiv][0]
                + " civilization has won!");
            System.out.println(" C O N G R A T U L A T I O N S ");
            playing = false;
        }
        if (attacks >= 10) {
            System.out.println("You destroyed 10 enemy cities!");
            System.out.println(cultures[myCiv][2]
                + ", the rest of the world is dead...");
            System.out.println("You're the only one left!");
            System.out.println(cultures[myCiv][0]
                + " civilization has won!");
            System.out.println(" C O N G R A T U L A T I O N S ");
            playing = false;
        }
    }

    public static String getInput(Scanner t) {
        String input = t.nextLine();
        return input.toLowerCase();
    }

    public static String getChoice(String val, String[] arr) {
        for (String s : arr) {
            if (s.toLowerCase().equals(val.toLowerCase())
                    || s.toLowerCase().contains(val.toLowerCase())) {
                return s.toLowerCase();
            }
        }
        return null;
    }

    public static void printArray(String[] arr) {
        for (String s : arr) {
            if (s != null) {
                System.out.println("\t" + s);
            }
        }
    }
}