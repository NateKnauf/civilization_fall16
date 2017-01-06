package view;

import javafx.scene.layout.GridPane;
import model.Map;
import model.TerrainTile;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class GridFX extends GridPane {
    private static Map map = new Map(10, 10);
    private static GridFX instance = new GridFX();
    private static int size = 10;

    static {
        size = 10;
        instance = new GridFX();
    }

    private GridFX() {
        map = new Map(size, size);
        instance = this;    //pseudo singleton so that update can be called
        for (int r = 0; r < getSize(); r++) {
            for (int c = 0; c < getSize(); c++) {
                TerrainTileFX newt = new TerrainTileFX(map.getTile(r, c));
                newt.setSize(700 / size);
                this.add(newt, c, r);
            }
        }
    }

    public void redo() {
        map = new Map(size, size);
        instance = this;    //pseudo singleton so that update can be called
        this.getChildren().clear();
        for (int r = 0; r < getSize(); r++) {
            for (int c = 0; c < getSize(); c++) {
                TerrainTileFX newt = new TerrainTileFX(map.getTile(r, c));
                newt.setSize(700 / size);
                this.add(newt, c, r);
            }
        }
    }

    public static void setSize(int newSize) {
        size = newSize;
        instance = new GridFX();
        instance.redo();
        System.out.println(size);
    }

    public static int getSize() {
        return size;
    }

    public static void update() {
        instance.getChildren().forEach(
                n -> ((TerrainTileFX) n).updateTileView());
    }

    public static boolean adjacent(TerrainTileFX current, TerrainTileFX other) {
        return adjacent(current.getTile(), other.getTile());
    }

    public static boolean adjacent(TerrainTile selected, TerrainTile other) {
        int srow = selected.getRow();
        int scol = selected.getCol();
        int orow = other.getRow();
        int ocol = other.getCol();
        return (Math.abs(orow - srow) <= 1) && (Math.abs(ocol - scol) <= 1);
    }

    public static Map getMap() {
        return map;
    }

    public static GridFX getInstance() {
        return instance;
    }
}
