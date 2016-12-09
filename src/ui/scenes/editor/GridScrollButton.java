package ui.scenes.editor;

import javafx.scene.Parent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.ImageView;
import resources.properties.PropertiesUtilities;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Harshil Garg
 */
public class GridScrollButton {

    private static final String SCROLL_RESOURCES = "resources/properties/scroll";

    private Parent myRoot;
    private ScrollAnimation myScrollAnimation;

    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private PropertiesUtilities myUtil;

    private ImageView upScroll;
    private ImageView rightScroll;
    private ImageView downScroll;
    private ImageView leftScroll;

    private Circle center;

    public GridScrollButton(Parent root, ScrollAnimation scrollAnimation) {
        myRoot = root;
        myScrollAnimation = scrollAnimation;

        myResources = ResourceBundle.getBundle(SCROLL_RESOURCES);
        myBuilder = new UIBuilder();
        myUtil = new PropertiesUtilities(myResources);

        setUpButtons();
        setUpListeners();
    }

    private void setUpButtons() {
        String path = myUtil.getStringProperty("image");

        ComponentProperties up = new ComponentProperties(200, 200)
                .path(path)
                .width(50)
                .height(50)
                .rotate(180);

        ComponentProperties right = new ComponentProperties(240, 240)
                .path(path)
                .width(50)
                .height(50)
                .rotate(270);

        ComponentProperties down = new ComponentProperties(200, 280)
                .path(path)
                .width(50)
                .height(50)
                .rotate(0);

        ComponentProperties left = new ComponentProperties(160, 240)
                .path(path)
                .width(50)
                .height(50)
                .rotate(90);

        upScroll = (ImageView) myBuilder.addNewImageView(myRoot, up);
        rightScroll = (ImageView) myBuilder.addNewImageView(myRoot, right);
        downScroll = (ImageView) myBuilder.addNewImageView(myRoot, down);
        leftScroll = (ImageView) myBuilder.addNewImageView(myRoot, left);

        center = (Circle) myBuilder.addComponent(myRoot, new Circle(225, 265, 12, Color.AZURE));

    }

    private void setUpListeners() {
        upScroll.setOnMouseEntered(e -> {myScrollAnimation.setScrollSpeedButtons(); myScrollAnimation.up(); myScrollAnimation.play();});
        upScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        rightScroll.setOnMouseEntered(e -> {myScrollAnimation.setScrollSpeedButtons();myScrollAnimation.right(); myScrollAnimation.play();});
        rightScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        downScroll.setOnMouseEntered(e -> {myScrollAnimation.setScrollSpeedButtons();myScrollAnimation.down(); myScrollAnimation.play();});
        downScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        leftScroll.setOnMouseEntered(e -> {myScrollAnimation.setScrollSpeedButtons();myScrollAnimation.left(); myScrollAnimation.play();});
        leftScroll.setOnMouseExited(e -> myScrollAnimation.stop());

        center.setOnMouseClicked(e -> {myScrollAnimation.setScrollSpeedButtons();myScrollAnimation.center(); myScrollAnimation.play();});
    }

    public void trackpadStartScroll(ScrollEvent event) {
        // Horizontal movement
        myScrollAnimation.setScrollSpeedTrackpad();
        double x = event.getDeltaX();
        double y = event.getDeltaY();
        if (Math.abs(x) > Math.abs(y)) {
            if (event.getDeltaX() > 0) {
                myScrollAnimation.left();
            }
            else {
                myScrollAnimation.right();
            }
        }
        // Vertical movement
        else {
            if (event.getDeltaY() > 0) {
                myScrollAnimation.up();
            }
            else {
                myScrollAnimation.down();
            }
        }
        myScrollAnimation.play();
    }

    public void trackpadEndScroll(ScrollEvent event) {
        myScrollAnimation.stop();
    }

}
