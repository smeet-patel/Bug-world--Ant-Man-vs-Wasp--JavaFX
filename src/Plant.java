import javafx.scene.shape.Circle;
/**
 * Having a class but extending to Application (pre-made interface) gives the
 * functionality. This Class is where the scenes and game is initalies
 * 
 * @author patelsmee
 */
public class Plant  extends Circle {
	String Symbol="P";
	public Plant(double centerX, double centerY, double radius, String Symbol){
	
		super (centerX, centerY, radius);
		this.Symbol=Symbol;
	}
	public String getSymbol() {
		return "P";
	}
}