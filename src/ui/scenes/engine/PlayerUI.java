package ui.scenes.engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class handles the visualization of the Player
 * @author pim
 *
 */

public class PlayerUI {
	
	private ImageView characterImage;
	private int curRow;
	private int curCol;

	private final String PATH = "resources/images/Sprites/Character/Pokemon/default.png";
	
	public PlayerUI() {
		buildCharacter();
	}
	 
	private void buildCharacter() {
		setCharacterImage(PATH);
	}
	
	/*setting size of character image
	 * */
	public void setCharacterImageSize(double d) {
		characterImage.setFitHeight(d);
		characterImage.setFitWidth(d); 
	}
	
	/*setting character image
	 * */
	public void setCharacterImage(String path) {
		characterImage = new ImageView(new Image(path));
	}
	
	public ImageView getCharacterImageView() {
		return characterImage;
	}
	
	public void setColumn(int column) {
		curCol = column;
	}
	
	public void setRow(int row) {
		curRow = row;
	}
	
	public void setPosX(double d) {
		getCharacterImageView().setLayoutX(d);
	}
	
	public void setPosY(double posY) {
		getCharacterImageView().setLayoutY(posY);
	}
	
	public int getRowCharacter() {
		return curRow;
	}
	
	public int getColumnCharacter() {
		return curCol;
	}
}