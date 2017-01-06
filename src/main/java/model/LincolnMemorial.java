package model;

import javafx.scene.image.Image;
/**
 * Represents a LincolnMemorial that can increase happiness.
 *
 * @version 1.0
 * @author Jim Harris
 */
class LincolnMemorial extends Landmark {

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     */
    public LincolnMemorial(Civilization owner) {
        super(owner);
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().increaseHappiness(50);
    }


    @Override
    public String toString() {
        return "Lincoln Memorial " + super.toString();
    }

    @Override
    public Image getImage() {
        return new Image(
                "File:./src/main/java/view/Civ_Icon/lincoln_memorial_icon.png");
    }
}
