package model;

import javafx.scene.image.Image;

/**
 * Represents a Wall unit.
 *
 * @version 1.0
 * @author Jim Harris
 */
class WallUnit extends MilitaryUnit {

    /**
     * Public constructor.
     *
     * @param owner the owner of this unit.
     */
    public WallUnit(Civilization owner) {
        super(500, owner, 0, 0, 30, 10, 10, 0);
    }

    @Override
    public void attack(MapObject o) {
        getOwner().getStrategy().siege();
        battle(o);
    }

    @Override
    public void battle(MapObject o) {
        if (o instanceof Building) {
            o.damage(this.getDamage());
        }
    }

    @Override
    public char symbol() {
        return '#';
    }

    @Override
    public String toString() {
        return "Wall Unit. " + super.toString();
    }

    @Override
    public Image getImage() {
        return new Image(
                "File:./src/main/java/view/Civ_Icon/wall_icon.png");
    }
}
