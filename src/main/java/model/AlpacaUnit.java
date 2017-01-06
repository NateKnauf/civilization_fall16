package model;

import javafx.scene.image.Image;

/**
 * Represents a War Charior unit.
 *
 * @version 1.0
 * @author Jim Harris
 */
class AlpacaUnit extends HybridUnit {

    /**
     * Public constructor.
     *
     * @param owner the owner of this unit.
     */
    public AlpacaUnit(Civilization owner) {
        super(owner);
        this.setBaseEndurance(this.getBaseEndurance() * 4);
        this.damage(this.getHealth() / 2);
        this.regenerateEndurance();
    }

    @Override
    public char symbol() {
        return 'W';
    }


    @Override
    public String toString() {
        return "Alpaca Unit. " + super.toString();
    }

    @Override
    public Image getImage() {
        return new Image(
                "File:./src/main/java/view/Civ_Icon/alpaca_icon.png");
    }
}
