import javafx.scene.paint.Color;
/**
 * Having a class but extending to Application (pre-made interface) gives the
 * functionality. This Class is where the scenes and game is initalies
 * 
 * @author patelsmee
 */
import javafx.scene.shape.Circle;

public class Bugs extends Circle /* implements animal */ {
	private String name;
	private int w = 1100, h = 900;
	private float x = 100, y = 100, dx, dy;
	private float size;
	private int speed;
	private Circle circle;
	private Color color;
	private double energy;
	
	//constructor
	public Bugs(double centerX, double centerY, double radius, float dy,float dx, int energy) {
		super(radius);
		new Circle();
		setTranslateX(centerX);
		setTranslateY(centerY);
		this.dy=dy;
		this.dx=dx;
		this.energy=energy;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double d) {
		this.energy = d;
	}

	public Bugs(String name, int x, int y, int size, int speed) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
	}

	public void step() {
				//make bug move
				setTranslateX(getTranslateX() + dx);
				setTranslateY(getTranslateY() + dy);
				
				//check horizontal borders
				if (getTranslateX() - getRadius() <= 0) {
					setTranslateX(0 + getRadius());
					dx *= -1;
				} else if (getTranslateX() + getRadius() >= w) {
					setTranslateX(w - getRadius());
					dx *= -1;
				}
				
				//check vertical borders
				if (getTranslateY() - getRadius() <= 0) {
					setTranslateY(0 + getRadius());
					dy *= -1;
				} else if (getTranslateY() + getRadius() >= h) {
					setTranslateY(h - getRadius());
					dy *= -1;
				}
		// hitting other balls in another method make collision 
	}
	
	public void collision() {
		//change of X and Y directions when collision is detected
		double x = Math.random() * 4;
		if (x >= 2) {
			dy = -dy;
		} else {
			dx = -dx;
		}
		//animates the balls otherwise nothing happens
		setTranslateX(getTranslateX() + dx);
		setTranslateY(getTranslateY() + dy);
	}
	
	//Getter and setter
	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Bugs [name=" + name + ", w=" + w + ", h=" + h + ", x=" + x + ", y=" + y + ", dx=" + dx + ", dy=" + dy
				+ ", size=" + size + ", speed=" + speed + ", circle=" + circle + ", color=" + color + ", energy="
				+ energy + "]";
	}

//	public double getEnergy() {
//		// TODO Auto-generated method stub
//		return 0;
//	}


//	public void resolveCollision(Bugs bugs) {
//		// TODO Auto-generated method stub
//		dy *= -1;
//		dy *= -1;
//	}
}