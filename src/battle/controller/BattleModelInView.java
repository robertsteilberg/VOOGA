package battle.controller;

/**
 * Interface to expose select methods from the Model to the View.
 * 
 * @author Daniel Chai
 */
public interface BattleModelInView {
	double getPlayerHP();
	double getEnemyHP();
	void setPlayerHP(double playerHP);
	void setEnemyHP(double enemyHP);
	boolean checkPlayerWon();
	boolean checkPlayerLost();
}