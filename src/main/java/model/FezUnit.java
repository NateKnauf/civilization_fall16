package model;

import javafx.scene.image.Image;

/**
 * Represents a Fez unit.
 *
 * @author Jim Harris
 * @version 1.0
 */
class FezUnit extends MeleeUnit {

    /**
     * Public constructor
     *
     * @param owner The owner of this unit.
     */
    public FezUnit(Civilization owner) {
        super(owner);
        this.setDamage((int) (this.getDamage() * 3));
        this.damage(this.getHealth() - 1);
    }

    @Override
    public void battle(MapObject o) {
        o.damage(this.getDamage());
        if (!o.isDestroyed() && o instanceof MeleeUnit) {
            damage(((MilitaryUnit) o).getDamage());
        }
    }

    @Override
    public char symbol() {
        return 'Z';
    }

    @Override
    public String toString() {
        return "Fez Unit. " + super.toString();
    }

    @Override
    public Image getImage() {
        return new Image("File:./src/main/java/view/Civ_Icon/fez_icon.png");
    }

}
