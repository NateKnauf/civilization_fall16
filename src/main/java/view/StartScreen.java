package view;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * This class should have and display
 * 1. a background
 * 2. a list of Civilizations
 * 3. a Start button
 */
public class StartScreen extends StackPane {

    private Button startButton;
    private ListView<CivEnum> myList;
    private ListView<Integer> mySizes;

    /**
     * constuctor of the start screen. Should set the background
     * image and display a list of civilizations and a start button
     */
    public StartScreen() {
        //TODO

        Image background = new Image(
            "File:./src/main/java/view/civ_background.png");
        ImageView bgview = new ImageView();
        bgview.setImage(background);

        GridPane grid = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        grid.getColumnConstraints().add(column1);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(36);
        grid.getRowConstraints().addAll(row1, row2);

        ObservableList names = FXCollections.observableArrayList(
            CivEnum.ANCIENT_EGYPT, CivEnum.QIN_DYNASTY, CivEnum.ROMAN_EMPIRE,
            CivEnum.UNITED_STATES, CivEnum.MAYAN_EMPIRE,
            CivEnum.OTTOMAN_EMPIRE);
        ListView<CivEnum> civList = new ListView<CivEnum>(names);
        civList.getSelectionModel().select(CivEnum.ANCIENT_EGYPT);

        ObservableList size = FXCollections.observableArrayList(
                2, 4, 5, 7, 10, 14);
        ListView<Integer> sizeList = new ListView<Integer>(size);
        sizeList.getSelectionModel().select(new Integer(7));

        myList = civList;
        mySizes = sizeList;

        Text chooseTitle = new Text(
            "Select a Civilization and Game Size to Begin");
        chooseTitle.setFont(new Font("Arial", 24));
        chooseTitle.setFill(Color.RED);

        Button chooseBtn = new Button("START");
        startButton = chooseBtn;

        VBox vbox = new VBox(8);
        vbox.getChildren().addAll(chooseTitle, new HBox(8, civList, sizeList),
            chooseBtn);
        vbox.setAlignment(Pos.CENTER);

        grid.add(vbox, 0, 1);
        grid.setAlignment(Pos.TOP_CENTER);

        this.getChildren().addAll(bgview, grid);
    }
    /**
         * gets the start button
         * @return the start button
         */
    public Button getStartButton() {
        //TODO
        return startButton;
    }
    /**
     * return a ListView of CivEnums representing the list of
     * available civilizations to choose from
     * @return listview of CivEnum
     */
    public ListView<CivEnum> getCivList() {
        return myList;
    }

    public ListView<Integer> getSizeList() {
        return mySizes;
    }

}