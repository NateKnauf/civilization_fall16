package model;

import javafx.scene.image.Image;

/**
 * Represents a MayanPyramid that can increase philosophy.
 *
 * @version 1.0
 * @author Jim Harris
 */
class MayanPyramid extends Landmark {

    /**
     * Public constructor.
     *
     * @param owner the Civilization that owns this Building.
     */
    public MayanPyramid(Civilization owner) {
        super(owner);
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().getTechnology().philosophize();
    }

    @Override
    public String toString() {
        return "Mayan Pyramid " + super.toString();
    }

    @Override
    public Image getImage() {
        return new Image(
            "File:./src/main/java/view/Civ_Icon/mayan_pyramid_icon.PNG");
    }
}
