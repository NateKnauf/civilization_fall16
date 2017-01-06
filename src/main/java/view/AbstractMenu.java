package view;

import model.Civilization;

import controller.GameController;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;

/**
 * Created by RuYiMarone on 11/12/2016.
 */
public class AbstractMenu {
    public static final int PREFWIDTH = 140;
    private Button exploreButton = new Button("Explore");
    private Button endTurnButton = new Button("End Turn");

    private ImageView bgIm = new ImageView(
        "File:./src/main/java/view/civ_background.png");

    private Rectangle tileOverlay = new Rectangle(70, 70,
        Color.rgb(0, 0, 0, 0.0));
    private ImageView tileImage = new ImageView();
    private ImageView tileIcon = new ImageView();
    private StackPane fakeTile = new StackPane(
        tileImage, tileOverlay, tileIcon);
    private Text terrainInfo = new Text();
    private BorderPane terrainBox = new BorderPane(terrainInfo, null, null,
        null, fakeTile);
    private Text unitStatus = new Text();

    private Text resNames = new Text("Strategy: \nResources : \nSettlements: \n"
        + "Money: \nFood: \nHappiness: ");
    private VBox resNamesBox = new VBox(8, resNames);
    private Text resVals = new Text();
    private VBox resValsBox = new VBox(8, resVals);
    private StackPane resStack = new StackPane(resNamesBox, resValsBox);

    private VBox contents = new VBox(10, terrainBox, unitStatus, exploreButton,
        endTurnButton);
    private BorderPane menu = new BorderPane(contents, resStack, null, null,
        null);
    private StackPane stacker = new StackPane(bgIm, menu);

    public AbstractMenu() {
        menu.setPadding(new Insets(5, 5, 5, 5));
        contents.setPadding(new Insets(10, 0, 0, 0));
        menu.setMaxWidth(180);

        unitStatus.setWrappingWidth(180);
        unitStatus.setFont(new Font("Comic Sans MS", 16));
        unitStatus.setFill(Color.WHITE);

        bgIm.setViewport(new Rectangle2D(0, 0, 200, 700));

        resNames.setFont(new Font("Comic Sans MS", 20));
        resNames.setFill(Color.WHITE);
        resNames.setTextAlignment(TextAlignment.LEFT);
        resNamesBox.setAlignment(Pos.CENTER_LEFT);

        resVals.setFont(new Font("Comic Sans MS", 20));
        resVals.setFill(Color.YELLOW);
        resVals.setTextAlignment(TextAlignment.RIGHT);
        resValsBox.setAlignment(Pos.CENTER_RIGHT);

        terrainInfo.setFont(new Font("Comic Sans MS", 18));
        terrainInfo.setFill(Color.WHITE);
        terrainInfo.setTextAlignment(TextAlignment.CENTER);
        tileOverlay.setStroke(Color.YELLOW);

        contents.setFillWidth(true);
        contents.setAlignment(Pos.TOP_CENTER);
        contents.setPrefWidth(180);

        exploreButton.setMinWidth(contents.getPrefWidth());
        exploreButton.setOnMousePressed(e -> {
                GameController.getCivilization().explore();
                if (endTurn()) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setHeaderText("Congratulations");
                    newAlert.setTitle("You Won!");
                    newAlert.showAndWait();
                    System.exit(0);
                }
            });

        endTurnButton.setMinWidth(contents.getPrefWidth());
        endTurnButton.setOnAction(event -> {
                if (endTurn()) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setHeaderText("Congratulations");
                    newAlert.setTitle("You Won!");
                    newAlert.showAndWait();
                    System.exit(0);
                }
            });

        updateItems();
    }

    public BorderPane getMenu() {
        return menu;
    }

    public VBox getContents() {
        return contents;
    }
    /**
     * This method updates the items and return the vbox as
     * the menu
     */
    public StackPane getRootNode() {
        updateItems();
        return stacker;
    }
    /**
     * This method takes in a node and added the node as
     * a child of the vbox menu
     */
    public void addMenuItem(Node node) {
        contents.getChildren().add(node);
    }
    /**
     * ends the player's turn and check for winning condition
     */
    public boolean endTurn() {
        GameController.setLastClicked(null);
        GameController.tick();
        GameController.ai();
        GridFX.update();
        GameController.updateResourcesBar();
        if (GameController.getCivilization().getNumSettlements() <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your last settlement has been destroyed!");
            alert.setTitle("Game Over");
            alert.showAndWait();
            System.exit(0);
        }
        return GameController.getCivilization().getStrategy()
            .conqueredTheWorld() || GameController.getCivilization()
            .getTechnology().hasTechnologyWin();
    }

    private void updateItems() {
        unitStatus.setText("");

        Civilization player = GameController.getCivilization();
        if (player != null) {
            resVals.setText(player.getStrategy().getStrategyLevel()
                + "\n" + player.getResources()
                + "\n" + player.getNumSettlements()
                + "\n$" + player.getTreasury().getCoins()
                + "\n" + player.getFood()
                + "\n" + player.getHappiness());
        }

        if (GameController.getLastClicked() != null) {
            tileImage = new ImageView(
                GameController.getLastClicked().getTile().getImage());
            tileImage.setViewport(new Rectangle2D(0, 0, 70, 70));
            fakeTile.getChildren().clear();
            String terrainText = GameController.getLastClicked().getTile()
                .getType().toString();
            if (!GameController.getLastClicked().getTile().isEmpty()) {
                tileOverlay = new Rectangle(70, 70, GameController
                    .getLastClicked().getTile().getOccupant().getColor());
                tileOverlay.setStroke(Color.YELLOW);
                tileIcon = new ImageView(GameController
                    .getLastClicked().getTile().getOccupant().getImage());
                tileIcon.setFitWidth(50);
                tileIcon.setFitHeight(50);
                fakeTile.getChildren().addAll(tileImage, tileOverlay, tileIcon);
            } else {
                tileOverlay = new Rectangle(70, 70, Color.rgb(0, 0, 0, 0.0));
                tileOverlay.setStroke(Color.YELLOW);
                tileIcon = null;
                fakeTile.getChildren().addAll(tileImage, tileOverlay);
            }
            terrainText += "\n" + GameController.getLastClicked().getTile()
                .getRow();
            terrainText += ", " + GameController.getLastClicked().getTile()
                .getCol();
            terrainInfo.setText(terrainText);
            if (!GameController.getLastClicked().getTile().isEmpty()) {
                unitStatus.setText(GameController.getLastClicked().getTile()
                    .getOccupant().getStatusString());
            }
        }
    }
}