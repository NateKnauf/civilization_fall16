package view;

import controller.GameController;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
/**
 * This class represents the GameScreen class
 */
public class GameScreen extends BorderPane {

    private static ResourcesMenu menuRes = new ResourcesMenu();
    private static AbstractMenu activeMenu = new StatusMenu();
    private static GameScreen instance = new GameScreen();
    /**
     * Creates a new view into the game. this layout should include
     * the rescource bar, grid map, and action menus
     *
     */
    public GameScreen() {
        //this.setTop(menuRes.getRootNode());
        this.setRight(GridFX.getInstance());
    }

    /**
     * This method should update the gridfx and the resouce bar
     */
    public void update() {
        //menuRes.update();
        GridFX.update();
        this.setLeft(activeMenu.getRootNode());
        this.setAlignment(activeMenu.getRootNode(), Pos.TOP_LEFT);
    }
    /**
    * this method should return the resource menu
    * @return reosuce menu
    */
    public static ResourcesMenu getResources() {
        return menuRes;
    }

    public static AbstractMenu getActiveMenu() {
        return activeMenu;
    }

    /**
     * This method switches menus based on passed in game state.
     * Game.java calls this to selectively control which menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {
        //TODO
        switch (state) {
        case MILITARY:
            activeMenu = new MilitaryMenu();
            break;
        case WORKER:
            activeMenu = new WorkerMenu();
            break;
        case RECRUITING:
            activeMenu = new RecruitMenu();
            break;
        case BUILDING:
            activeMenu = new BuildingMenu();
            break;
        case NEUTRAL:
            activeMenu = new StatusMenu();
            break;
        default:
            activeMenu = new StatusMenu();
            break;
        }
        getInstance().update();
    }

    public static GameScreen getInstance() {
        return instance;
    }
}