package view;


import controller.GameController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.shape.StrokeType;
import model.TerrainTile;


/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class TerrainTileFX extends StackPane {
    private Rectangle overlay;
    private ImageView background;
    private TerrainTile tile;
    private ImageView icon = new ImageView("File:./bologna");
    private int size = 70;
    /**
     * Constructor for TerrainTileFX.
     * Creates a Rectangle for the highlighting and overlay
     * Creates ImageViews for the background terrain and icon
     * Transitions states when a tile is clicked
     * @param tile
     */
    public TerrainTileFX(TerrainTile tile) {
        this.tile = tile;
        overlay = new Rectangle(size, size, Color.rgb(0, 0, 0, 0.0));
        overlay.setStroke(Color.BLACK);
        overlay.setStrokeType(StrokeType.INSIDE);
        this.background = new ImageView(tile.getImage());
        this.getChildren().addAll(background, overlay);
        updateTileView();
        this.setOnMousePressed(event -> {
                GameController.setLastClicked(this);
            });
    }
    /**
     * gets the TerrainTile of this TerrainTileFX
     * @return TerrainTile
     */
    public TerrainTile getTile() {
        return tile;
    }

    public void setSize(int x) {
        size = x;
        overlay = new Rectangle(size, size, Color.rgb(0, 0, 0, 0.0));
        overlay.setStroke(Color.BLACK);
        overlay.setStrokeType(StrokeType.INSIDE);
        this.background = new ImageView(tile.getImage());
        this.background.setFitWidth(size);
        this.background.setFitHeight(size);
        this.getChildren().addAll(background, overlay);
        updateTileView();
    }
    /**
     * this method updates the view of this TerrainTileFX.
     * It should check if the TerrainTile is empty. If it is empty,
     * set the overlay to be transparent. If it is not empty, fill
     * the overlay with the color of the occupant on the terrain tile
     * If the TerrainTileFX contains an icon, remove it. If the tile is
     * not empty, get the image of the occupant of the tile and add the
     *image of the occupant to the tile.
     */
    public void updateTileView() {
        icon = null;
        this.getChildren().clear();
        if (getTile().isEmpty()) {
            overlay = new Rectangle(size, size, Color.rgb(0, 0, 0, 0.0));
            overlay.setStroke(Color.BLACK);
            overlay.setStrokeType(StrokeType.INSIDE);
            if (GameController.getLastClicked() == this) {
                overlay.setStroke(Color.YELLOW);
            }
            this.getChildren().addAll(background, overlay);
        } else {
            overlay = new Rectangle(size, size, getTile().getOccupant()
                .getColor());
            overlay.setStroke(Color.BLACK);
            overlay.setStrokeType(StrokeType.INSIDE);
            if (GameController.getLastClicked() == this) {
                overlay.setStroke(Color.YELLOW);
            }
            icon = new ImageView(getTile().getOccupant().getImage());
            icon.setFitWidth(size * 3 / 4);
            icon.setFitHeight(size * 3 / 4);
            icon.setPreserveRatio(true);
            Text health = new Text("" + getTile().getOccupant().getHealth());
            health.setFont(new Font("Comic Sans MS Bold", 14));
            health.setFill(Color.LAWNGREEN);
            VBox healthBox = new VBox(health);
            healthBox.setAlignment(Pos.BOTTOM_RIGHT);
            healthBox.setMinWidth(size);
            healthBox.setMinHeight(size);
            this.getChildren().addAll(background, overlay, icon, healthBox);
            icon.setX(-10);
        }
    }
}