package view;

import controller.GameController;
import javafx.scene.control.Button;

/**
 * Created by William on 11/11/2016.
 */

public class MilitaryMenu extends AbstractMenu {
    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */
    public MilitaryMenu() {
        //TODO
        Button attackButton = new Button("Attack");
        attackButton.setMinWidth(getContents().getPrefWidth());
        attackButton.setOnMousePressed(e -> {
                GameController.attacking();
                GameScreen.getInstance().update();
            });
        addMenuItem(attackButton);

        Button moveButton = new Button("Move");
        moveButton.setMinWidth(getContents().getPrefWidth());
        moveButton.setOnMousePressed(e -> {
                GameController.moving();
                GameScreen.getInstance().update();
            });
        addMenuItem(moveButton);
    }
}
