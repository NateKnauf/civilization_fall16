package runner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Optional;

import controller.GameController;
import view.StartScreen;
import view.GameScreen;
import view.GridFX;
import model.QinDynasty;
import model.RomanEmpire;
import model.Egypt;
import model.UnitedStates;
import model.Mayan;
import model.OttomanEmpire;
import model.Bandit;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 */
public class CivilizationGame extends Application {

    private String settleName;
    private StartScreen root;
    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Civilization");
        root = new StartScreen();
        Button btn = root.getStartButton();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (root.getCivList().getSelectionModel().getSelectedItem()
                        != null) {
                    GridFX.setSize(root.getSizeList().getSelectionModel()
                        .getSelectedItem());
                    Scene nextScene = startGame();
                    if (nextScene != null) {
                        System.out.println(root.getCivList().getSelectionModel()
                            .getSelectedItem());
                        GridFX.getMap().putSettlement(settleName,
                            GameController.getCivilization(), 0,
                            GridFX.getSize() - 1);
                        GridFX.getMap().addEnemies(new Bandit(), 1);
                        primaryStage.setScene(nextScene);
                        ((GameScreen) nextScene.getRoot()).update();
                    }
                }
            }
        });

        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    /**
     * This is the main method that launches the javafx application
     */
    public static void main(String[] args) {
        launch(args);
    }

    public String getSettleName() {
        return settleName;
    }

    public StartScreen getRootNode() {
        return root;
    }
    /**
    * This method is responsible for setting the scene to the corresponding
    * layout.
    * and returning the scene.
    * @return Scene
    */

    public Scene startGame() {
        //TODO
        TextInputDialog dialog = new TextInputDialog("Town Name");
        dialog.setTitle("A New Settlement");
        dialog.setHeaderText("You have built a Settlement!");
        dialog.setContentText("Please enter the Name of your first town:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            switch (root.getCivList().getSelectionModel().getSelectedItem()) {
            case ANCIENT_EGYPT:
                GameController.setCivilization(new Egypt());
                break;
            case QIN_DYNASTY:
                GameController.setCivilization(new QinDynasty());
                break;
            case ROMAN_EMPIRE:
                GameController.setCivilization(new RomanEmpire());
                break;
            case UNITED_STATES:
                GameController.setCivilization(new UnitedStates());
                break;
            case MAYAN_EMPIRE:
                GameController.setCivilization(new Mayan());
                break;
            case OTTOMAN_EMPIRE:
                GameController.setCivilization(new OttomanEmpire());
                break;
            default:
                GameController.setCivilization(new RomanEmpire());
                break;
            }
            Scene gameScene = new Scene(GameScreen.getInstance(), 900, 700);
            settleName = result.toString();
            return gameScene;
        }

        //BorderPane root = new GameScreen();
        return null; //new Scene(root, 800, 600);
    }

}