package model;

import javafx.scene.image.Image;

/**
 * Represnts a Great Wall that can increase strategy.
 *
 * @version 1.0
 * @author Jim Harris
 */
class Ottoman extends Landmark {

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     */
    public Ottoman(Civilization owner) {
        super(owner);
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().getStrategy().battle();
    }

    @Override
    public String toString() {
        return "Ottoman " + super.toString();
    }

    @Override
    public Image getImage() {
        return new Image("File:./src/main/java/view/Civ_Icon/ottoman_icon.png");
    }
}
