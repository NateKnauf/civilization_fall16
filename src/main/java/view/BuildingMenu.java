package view;

import controller.GameController;
import model.Building;
import model.Settlement;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class should represents the bulding menu
 */
public class BuildingMenu extends AbstractMenu {
    /**
    * there should be an invest and demolish button for this menu
    * as well as functions associated with the buttons
    */
    public BuildingMenu() {
        //TODO

        Button investButton = new Button("Invest");
        investButton.setMinWidth(getContents().getPrefWidth());
        investButton.setOnMousePressed(e -> {
                if (GameController.getCivilization().getTreasury()
                        .getCoins() >= 25) {
                    ((Building) GameController.getLastClicked().getTile()
                        .getOccupant()).invest();
                    GameController.getCivilization().getTreasury().spend(25);
                    GameScreen.getInstance().update();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Invest Error");
                    alert.setHeaderText("You can't afford that!");
                    alert.setContentText("Get a job, lazy millenial!");

                    alert.showAndWait();
                }
            });
        addMenuItem(investButton);

        Button demolishButton = new Button("Demolish");
        demolishButton.setMinWidth(getContents().getPrefWidth());
        demolishButton.setOnMousePressed(e -> {
                if ((GameController.getCivilization().getNumSettlements() > 1
                        && GameController.getLastClicked().getTile()
                        .getOccupant() instanceof Settlement)
                        || (GameController.getLastClicked().getTile()
                        .getOccupant() instanceof Building && !(GameController
                        .getLastClicked().getTile().getOccupant()
                        instanceof Settlement))) {
                    ((Building) GameController.getLastClicked().getTile()
                        .getOccupant()).demolish();
                    GameController.getLastClicked().getTile().setOccupant(null);
                    GameScreen.getInstance().update();
                    System.out.println("demolish!");
                    GameController.setLastClicked(
                        GameController.getLastClicked());
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Demolish Error");
                    alert.setHeaderText("You can't demolish that!");
                    if (GameController.getLastClicked().getTile()
                        .getOccupant() instanceof Settlement) {
                        alert.setContentText("You'd run out of Settlements!");
                    } else {
                        alert.setContentText("Try demolishing something else!");
                    }

                    alert.showAndWait();
                }
            });
        addMenuItem(demolishButton);
    }
}
