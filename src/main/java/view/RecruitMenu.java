package view;

import controller.GameController;
import model.Civilization;
import model.Unit;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class RecruitMenu extends AbstractMenu {

    private Unit[] recruit = new Unit[9];
    /**
    * recuit menu should have a list of worker/units
    * to choose from. There should also be a select button
    * and the function of the button should be implemented
    *here
    */
    public RecruitMenu() {
        super();
        refreshRecruits();

        //addMenuItem(unitList);
        //addMenuItem(recruitButton);

        Text gridText = new Text("RECRUIT:");
        gridText.setFont(new Font("Comic Sans MS", 18));
        gridText.setFill(Color.WHITE);
        GridPane grid = new GridPane();
        Button[] btns = new Button[9];
        for (int i = 0; i < 9; i++) {
            ImageView recImage = new ImageView(getRecruit(i).getImage());
            recImage.setFitHeight(50);
            recImage.setFitWidth(50);
            recImage.setPreserveRatio(true);
            btns[i] = new Button("", recImage);
            btns[i].setTextAlignment(TextAlignment.CENTER);
            grid.add(btns[i], i % 3, i / 3);
            grid.setHgap(15);
            grid.setVgap(15);
            btns[i].setMinWidth(50);
            btns[i].setMinHeight(50);
            btns[i].setMaxWidth(50);
            btns[i].setMaxHeight(50);
            int a = i;
            btns[i].setOnMousePressed(e -> {
                    refreshRecruits();
                    if (getRecruit(a).isAffordable()) {
                        GameController.getLastClicked().getTile().setOccupant(
                            getRecruit(a));
                        getRecruit(a).applyInitialCosts();
                        GameScreen.getInstance().update();
                        GameController.setLastClicked(
                            GameController.getLastClicked());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("You can't afford that "
                            + getRecruit(a).getClass().getSimpleName() + "!");
                        alert.setTitle("Too expensive!");
                        alert.showAndWait();
                    }
                });
        }
        VBox gridBox = new VBox(8, gridText, grid);
        gridBox.setAlignment(Pos.CENTER);
        getMenu().setBottom(gridBox);
    }

    private void refreshRecruits() {
        Civilization player = GameController.getCivilization();
        if (player != null) {
            recruit = new Unit[9];
            recruit[0] = player.getMeleeUnit();
            recruit[1] = player.getRangedUnit();
            recruit[2] = player.getHybridUnit();
            recruit[3] = player.getSiegeUnit();
            recruit[4] = player.getSettlerUnit("Settlers");
            recruit[5] = player.getFarmerUnit();
            recruit[6] = player.getCoalMinerUnit();
            recruit[7] = player.getAnglerUnit();
            recruit[8] = player.getMasterBuilderUnit();
        }
    }

    public Unit getRecruit(int i) {
        refreshRecruits();
        return recruit[i];
    }

    public Unit[] getRecruits() {
        return recruit;
    }
}