package battle.view;


/**
 * Created by Bill Xiong on 12/7/16.
 * 
 * @author Bill Xiong
 */
public class EnemyView extends ItemView {
	private static final String ENEMY_NAME = "Enemy";
	public EnemyView(int hp, int x, int y, String filePath) {
		super(ENEMY_NAME, hp, x, y,filePath);
	}
}
