package ui.scenes.engine;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.media.SnapShot;
import ui.media.SoundChooser;
import ui.media.SoundPlayer;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import engine.EngineController;

/**
 * @author Pim Chuaylua, Nisakorn Valyasevi
 *         <p>
 *         This class initializes player status ui.
 */
public class EngineSidePanel implements Observer {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private VBox vbox;
    private PropertiesUtilities util;
    private Character player;
    private EngineView gameEngine;
    private Label playerPos;
    private Label numPokemon;
    private SoundPlayer soundPlayer;
    private EngineController engineController;

    public EngineSidePanel(Parent root, UIBuilder builder, ResourceBundle resources, EngineView gameEngine, EngineController engineController) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        util = new PropertiesUtilities(myResources);
        vbox = new VBox(10);
        this.gameEngine = gameEngine;
        this.engineController = engineController;
        Font.loadFont(EngineSidePanel.class.getResource("/resources/fonts/PokemonGB.ttf").toExternalForm(), 20);
        initSidePanel();
        initStats();
    }

    /**
     * Creates the tab-based menu that will hold the ui.scenes.editor.objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    public void initSidePanel() {

        int itemMenuXPos = util.getIntProperty("statusPanelPosX");
        int itemMenuYPos = util.getIntProperty("statusPanelPosY");

        Pane itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        Pane canvas = new Pane();
        canvas.getStyleClass().add("canvas");
        canvas.setPrefSize(300, 1000);
        itemMenuRegion.getChildren().add(canvas);

        myBuilder.addComponent(myRoot, itemMenuRegion);
        itemMenuRegion.getChildren().add(vbox);

        vbox.setPadding(new Insets(10, 10, 10, 10));

        soundPlayer = new SoundPlayer(engineController.getMusic());
        soundPlayer.addNodeToControl(new SnapShot(gameEngine).getGroup());
        vbox.getChildren().add(soundPlayer.getGroup());
    }

    public void initStats() {
        vbox.getChildren().add(new Label("Your HP"));
        Button playerChart = new Button();
        playerChart.getStyleClass().add("playerChart");
        playerChart.setPrefSize(100, 10);
        vbox.getChildren().add(playerChart);
        vbox.getChildren().add(new Label("Your Position"));
        playerPos = new Label(engineController.getPlayerRow() + "," + engineController.getPlayerColumn());
        vbox.getChildren().add(playerPos);
        vbox.getChildren().add(new Label("Your Number of Pokemon"));
        numPokemon = new Label(String.valueOf(engineController.getPlayerNumPokemon()));
        vbox.getChildren().addAll(numPokemon, new Label("Battle History"));

    }

    public void stopMusic() {
        if (soundPlayer != null) {
            soundPlayer.stopMusic();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        playerPos.setText(engineController.getPlayerRow() + "," + engineController.getPlayerColumn());
        numPokemon.setText(String.valueOf(engineController.getPlayerNumPokemon()));

    }
}