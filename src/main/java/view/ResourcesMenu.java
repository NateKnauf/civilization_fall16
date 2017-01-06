package view;

import controller.GameController;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.Civilization;

public class ResourcesMenu {

    private HBox root;
    /**
    * creates a resource bar and display the current state of
    * your civilization's resouces
    */
    public ResourcesMenu() {
        root = new HBox(8);
        root.getChildren().add(new Text("Resources Barrrrr!"));
    }
    /**
    * should update all the resouce values to the current
    * state of your resource values
    */
    public void update() {
        getRootNode().getChildren().clear();
        Civilization player = GameController.getCivilization();
        Text resText = new Text("Strategy Level:"
            + player.getStrategy().getStrategyLevel()
            + "\tResources: " + player.getResources()
            + "\tSettlements: " + player.getNumSettlements()
            + "\tMoney: " + player.getTreasury().getCoins()
            + "\tFood: " + player.getFood()
            + "\tHappiness: " + player.getHappiness());
        getRootNode().getChildren().add(resText);
    }
    /**
    * updates the resource bar and returns the resource bar
    * @return a hbox representation of the resource bar
    */
    public HBox getRootNode() {
        return root;
    }
}