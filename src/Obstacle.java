import javafx.scene.shape.Circle;

/**
 * Having a class but extending to circle gives the functionality. This Class
 * allows to use the methods and fields of the circle
 * 
 * @author patelsmee
 */
public class Obstacle extends Circle {
	String Symbol = "=";

	public Obstacle(double centerX, double centerY, double radius, String Symbol) {

		super(centerX, centerY, radius);
		this.Symbol = Symbol;
	}

	public String getSymbol() {
		return "=";
	}
}