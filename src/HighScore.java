/**
 * this class is the set up a Array list of highest scores for the game/ bug
 * world.
 * 
 * @author smeet
 */
public class HighScore {
	private String winner;
	private int score;
	private int round;

	// constructor
	public HighScore(int round, String winner, int score) {
		this.round = round;
		this.winner = winner;
		this.score = score;
	}

	// getter and setter to modify values
	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	// to string to enable easy score board print on the FX UI
	public String toString() {
		return "\t" + "Round " + round + "\t" + winner + "\t" + score + "\n";
	}

}
