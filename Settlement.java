public class Settlement {
    private Building[] builds = new Building[4];
    private int buildCount = 0;
    private String name;

    public Settlement(String nName) {
        name = nName;
    }

    public void addBuilding(Building friend) {
        if (buildCount == builds.length) {
            expandSettlement();
        }
        builds[buildCount] = friend;
        buildCount++;
    }

    public boolean build(int allottedMoney, Population population,
            int cost, int workersRequired) {
        if (cost < allottedMoney && population.canWork(workersRequired)) {
            addBuilding(new Building(cost, workersRequired));
            return true;
        }
        return false;
    }

    public void expandSettlement() {
        Building[] newBuilds = new Building[builds.length * 2];
        for (int b = 0; b < builds.length; b++) {
            newBuilds[b] = builds[b];
        }
        builds = newBuilds;
    }

    public String getName() {
        return name;
    }

}