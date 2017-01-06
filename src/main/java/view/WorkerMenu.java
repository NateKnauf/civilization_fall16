package view;

import controller.GameController;
import model.Convertable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class WorkerMenu extends AbstractMenu {
    /**
     * There should be a convert and move button
     * as well as the functions associated with those
     * buttons
     */
    public WorkerMenu() {
        //TODO
        Button moveButton = new Button("Move");
        moveButton.setMinWidth(getContents().getPrefWidth());
        moveButton.setOnMousePressed(e -> {
                GameController.moving();
                GameScreen.getInstance().update();
            });
        addMenuItem(moveButton);

        Button convertButton = new Button("Convert");
        convertButton.setMinWidth(getContents().getPrefWidth());
        convertButton.setOnMousePressed(e -> {
                if (GameController.getLastClicked().getTile().getOccupant()
                        .isWorker()) {
                    if (((Convertable) GameController.getLastClicked().getTile()
                            .getOccupant()).canConvert(
                            GameController.getLastClicked().getTile()
                            .getType())) {
                        GameController.getLastClicked().getTile().setOccupant(
                            ((Convertable) GameController.getLastClicked()
                            .getTile().getOccupant()).convert());
                        GameController.setLastClicked(
                            GameController.getLastClicked());
                        GameScreen.getInstance().update();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Convert Error");
                        alert.setHeaderText("You can't convert that!");
                        alert.setContentText("That tile type won't work!");

                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Convert Error");
                    alert.setHeaderText("You can't convert that!");
                    alert.setContentText("That isn't a converting unit!");

                    alert.showAndWait();
                }
            });
        addMenuItem(convertButton);

    }
}