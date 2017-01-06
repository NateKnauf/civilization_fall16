public class Technology {

    private int understanding = 0;
    private boolean foundMeaningOfLife = false;
    private int experienceLevel = 0;
    private boolean builtWonderOfTheWorld = false;

    public void philosophize() {
        understanding += 25;
        checkUnderstanding();
    }

    public void improveWriting() {
        understanding += 10;
        checkUnderstanding();
    }

    public void checkUnderstanding() {
        if (understanding > 200) {
            foundMeaningOfLife = true;
        }
    }

    public void increaseExperience(int exp) {
        experienceLevel += exp;
        if (experienceLevel > 200) {
            builtWonderOfTheWorld = true;
        }
    }

    public boolean hasTechnologyWin() {
        return (foundMeaningOfLife && builtWonderOfTheWorld);
    }

    public int getUnderstanding() {
        return understanding;
    }

    public int getBuildExperience() {
        return experienceLevel;
    }

}