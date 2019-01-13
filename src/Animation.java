import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * 
 * @author smeet
 *
 */
public class Animation extends Application {
	private int w = 1100, h = 900;
	private ArrayList<Bugs> bug = new ArrayList<Bugs>();
	private ArrayList<Bugs> dead = new ArrayList<Bugs>();
	private ArrayList<Circle> obstacle = new ArrayList<Circle>();
	private ArrayList<Circle> plant = new ArrayList<Circle>();
	private ArrayList<Bugs> antman = new ArrayList<Bugs>();
	private ArrayList<Bugs> wasp = new ArrayList<Bugs>();
	private ArrayList<HighScore> highScore = new ArrayList<HighScore>();// Stores the current games guess score of play
	private Scene scene;
	private int radius;
	private int countPlay = 0;
	private double totalScoreWASP = 0;
	private double totalScoreAnt = 0;

	// initializing the UI field elements
	private ColumnConstraints col1 = new ColumnConstraints();// 50% col size
	private ColumnConstraints col2 = new ColumnConstraints();// 50% col size
	private Text title = new Text();// title of the game
	private final Text bugNumberText = new Text();// amount of bugs
	private final Text bugNumberTextTwo = new Text();// amount of ant
	private final Text bugNumberTextthree = new Text(); // amount of wasp
	private TextField bugNumbertwo = new TextField(); // amount of ant field
	private TextField BugNumber = new TextField();// amount of of bee field
	private TextField BugNumberThree = new TextField();// amount of wspa field
	private final Text sunflowerText = new Text(); // the number of sunflower
	private TextField sunflower = new TextField();// amount of sunflower field
	private Button makeGame = new Button("Create Bug World");
	private Button btn = new Button("Play / Pause");
	private TextFlow textFlow = new TextFlow();// text flow to join 2 text with different properties.
	private Text AntT = new Text();// combine text ant
	private Text gameTitle = new Text();
	private Text WapsT = new Text();// combine text wasp
	private Text score = new Text();// score text
	private Text lives = new Text();// live for ant text
	private Text scoreTwo = new Text();// score for wasp
	private Text twolives = new Text();// lives for wasp
	private TextFlow textFlowB = new TextFlow();// text flow to join 2 text with different properties.
	private Text AntText = new Text();// combine text ant
	private Text gameTitleText = new Text();// combine text title
	private Text gameT = new Text();// game title combine
	private Text WapsText = new Text();
	private Text t = new Text(10, 50, "This is a test");
	private GridPane grid = new GridPane();// new grid pane
	private Pane pane = new Pane();
	private VBox vbox = new VBox();
	private Button btnstart = new Button("Start");
	private BorderPane boardP = new BorderPane();

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Bug World Animated");
		primaryStage.setFullScreen(true); // make java stage into full screen

		scorelist();// initializing the score data

		gridPaneMenu();// grid pane setup

		topUI();// game title and number of items fields

		startPauseBnt();// stop start buttons

		antmanWaspScoreCount();// the title and score count for wsap vs ANTMAN

		antmanWaspscore();// title antman vs wsap Score board title

		// layout using boarderPane with a combination of vbox and pane
		vbox.getChildren().addAll(btnstart);

		// inside BorderPane the grip is on the left like the menu
		boardP.setLeft(grid);
		// inside BorderPane the grip is on the center like the game
		boardP.setCenter(pane);
		pane.setStyle("-fx-background-image: url('2.jpg');");
		boardP.setLeft(grid);// boarder pane set grid left
		boardP.setCenter(pane);// pane to center of window

		// initazing the scene with the board pane and width and height
		scene = new Scene(boardP, w, h);

		KeyFrame frame = new KeyFrame(Duration.millis(15), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				scoreWasp();// updating the score
				for (int i = 0; i < dead.size(); i++) {
					pane.getChildren().remove(dead.get(i).getCircle());
				}
				updateText();// updates the text

				// bugs and bug collaiding change x and y
				for (Bugs a : bug) {
					for (Bugs b : bug) {
						if (a.getBoundsInParent().intersects(b.getBoundsInParent()) && (b != a)) {
							a.collision();
						}
					}
				}

				// just the bug
				for (int i = 0; i < bug.size(); i++) {
					bug.get(i).setEnergy(bug.get(i).getEnergy() - 1);
					bug.get(i).step();
				}
				try{
				for (Bugs b : bug) {
					if (b.getEnergy() <= 0) {
						dead.add(b);
						// removing the bug
						b.setRadius(0);
						bug.remove(b);
					}
				}
				}catch(Exception e) {
					System.out.println("please fix your life");
				}
				// wasp to remove lives when they died
				for (Bugs w : wasp) {
					if (w.getEnergy() <= 0) {
						w.setRadius(0);
						wasp.remove(w);
					}
				}

				// antman to remove lives when they died
				for (Bugs a : antman) {
					if (a.getEnergy() <= 0) {
						a.setRadius(0);
						antman.remove(a);
					}
				}
				// obs and bug colliding change x and y
				for (Bugs a : bug) {
					for (Circle b : obstacle) {
						if (a.getBoundsInParent().intersects(b.getBoundsInParent())) {
							a.collision();
						}
					}
				}

				// every frame plant gets smaller
				for (Circle p : plant) {
					while (p.getRadius() < 30) {
						// plant growing over time
						p.setRadius(p.getRadius() + 0.0001);
					}
				}

				for (Bugs b : bug) {
					for (Circle p : plant) {
						b.getBoundsInParent().intersects(p.getBoundsInParent());
						while (p.getRadius() < 40) {
							p.setRadius(p.getRadius() * 1.01);
						}
						if (b.getBoundsInParent().intersects(p.getBoundsInParent())) {
							b.setEnergy(b.getEnergy() + 1);
							p.setRadius(p.getRadius() - 0.1);
						}
					}
				}
			}

			private void updateText() {
				scoreTwo.setText("Score WASP: " + scoreWasp());
				score.setText("Score ANTMAN: " + scoreAnt());
				twolives.setText("WASP: " + WaspLives());
				lives.setText("ANTMAN: " + AntLives());
			}
		});

		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);

		BugNumber.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// https://stackoverflow.com/questions/33053815/what-is-d-how-replaceall-d-is-working
				if (!newValue.matches("\\d*")) {// if it is an digit \\ is letting a type escape
					BugNumber.setText(newValue.replaceAll("[^\\d]", ""));// setting the value
				}
			}
		});

		makeGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Arg0) {
//				for (int i=0;i <=plant.size();i++) {
//				plant.get(i).setRadius(0);
//				}
//				for (int i=0;i <=obstacle.size();i++) {
//					obstacle.get(i).setRadius(0);
//				2	222	}
				wasp.clear();
				antman.clear();
				twolives.setText("WASP: " + 0);// set zero
				lives.setText("ANTMAN: " + 0);// set zero
				countPlay++;// allows the play pause button work
				pane.getChildren().clear();
				timeline.play();// play timeline
				createBugs();// make bugs
				createFoodObs();
				addingChildToPane();
			}
		});
		BugNumberThree.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// https://stackoverflow.com/questions/33053815/what-is-d-how-replaceall-d-is-working
				if (!newValue.matches("\\d*")) {// if it is an digit \\ is letting a type escape
					BugNumberThree.setText(newValue.replaceAll("[^\\d]", ""));// setting the value
				}
			}
		});
		bugNumbertwo.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {// if it is an digit \\ is letting a type escape
					bugNumbertwo.setText(newValue.replaceAll("[^\\d]", ""));// setting the value
				}
			}
		});
		BugNumber.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {// if it is an digit \\ is letting a type escape
					BugNumber.setText(newValue.replaceAll("[^\\d]", ""));// setting the value
				}
			}
		});
		sunflower.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {// if it is an digit \\ is letting a type escape
					sunflower.setText(newValue.replaceAll("[^\\d]", ""));// setting the value
				}
			}
		});

		for (int i = 0; i < bug.size(); i++) {
			Bugs an = bug.get(i);
			pane.getChildren().add(an);
		}

		primaryStage.setScene(scene);
		primaryStage.show();

		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Arg0) {
				countPlay++;
				if (countPlay % 2 == 0) {
					timeline.pause();
				} else {
					timeline.play();
				}
			}
		});
	}

	//adding the bug, obs, plant to pane
	public void addingChildToPane() {
		pane.getChildren().addAll(bug);
		pane.getChildren().addAll(obstacle);
		pane.getChildren().addAll(plant);
	}

	public void createFoodObs() {
		// sunflower
		if (sunflower.getText() == null || sunflower.getText().trim().isEmpty()) {
			sunflower.setText("0");
		}
		Image imgPlant = new Image(getClass().getResourceAsStream("SunF.png"));
		for (int i = 0; i <= Integer.valueOf(sunflower.getText()); i++) {
			Circle p = new Circle(xRand(), yRand(), obSize());
			p.setFill(new ImagePattern(imgPlant));
			plant.add(p);
		}
		
		// make plant
		Image imgPlantF = new Image(getClass().getResourceAsStream("food.png"));
		for (int i = 0; i <= Integer.valueOf(sunflower.getText()); i++) {
			Circle p = new Circle(xRand(), yRand(), 20);
			p.setFill(new ImagePattern(imgPlantF));
			plant.add(p);
		}
		int totalItems = (Integer.valueOf(sunflower.getText()) + Integer.valueOf(BugNumber.getText())
				+ Integer.valueOf(BugNumberThree.getText()) + Integer.valueOf(bugNumbertwo.getText()));
		
		//make obstacle
		Image imgObs = new Image(getClass().getResourceAsStream("obsr.png"));
		for (int i = 0; i <= totalItems; i++) {
			Circle o = new Circle(xRand(), yRand(), obSize());
			o.setFill(new ImagePattern(imgObs));
			obstacle.add(o);
		}
	}

	public void createBugs() {
		/** making bees */
		Image imgAnt = new Image(getClass().getResourceAsStream("ant1.png"));
		if (bugNumbertwo.getText() == null || bugNumbertwo.getText().trim().isEmpty()) {
			bugNumbertwo.setText("0");
		}
		// making ant man
		for (int i = 0; i < Integer.valueOf(bugNumbertwo.getText()); i++) {
			Bugs addbug = new Bugs(xRand(), yRand(), radiusBug(), speedChange(), speedChange(), energy());
			addbug.setFill(new ImagePattern(imgAnt));
			bug.add(addbug);
			antman.add(addbug);
		}
		// empty field
		if (BugNumberThree.getText() == null || BugNumberThree.getText().trim().isEmpty()) {
			BugNumberThree.setText("0");
		}
		// making wasp
		Image imgWasp = new Image(getClass().getResourceAsStream("wasp.png"));
		for (int i = 0; i < Integer.valueOf(BugNumberThree.getText()); i++) {
			Bugs addbug = new Bugs(xRand(), yRand(), radiusBug(), speedChange(), speedChange(), energy());
			addbug.setFill(new ImagePattern(imgWasp));
			bug.add(addbug);
			wasp.add(addbug);
		}

		if (BugNumber.getText() == null || BugNumber.getText().trim().isEmpty()) {
			BugNumber.setText("0");
		}
		// making bee
		Image img = new Image(getClass().getResourceAsStream("bee.png"));
		for (int i = 0; i < Integer.valueOf(BugNumber.getText()); i++) {
			Bugs addbug = new Bugs(xRand(), yRand(), radiusBug(), speedChange(), speedChange(), energy());
			addbug.setFill(new ImagePattern(img));
			bug.add(addbug);
		}
	}

	private void antmanWaspscore() {
		// title antman vs wsap Score board title
		AntText.setText("ANTMAN");// setting the text
		AntText.setFill(Color.RED);
		AntText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));// font styling
		gameTitleText.setText(" Vs ");// setting the text
		gameTitleText.setFill(Color.WHITE);
		gameTitleText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));// font styling
		WapsText.setText("WASP");// setting the text
		WapsText.setFill(Color.YELLOW);
		WapsText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));// font styling
		gameT.setText(" Score Board ");// setting the text
		gameT.setFill(Color.WHITE);
		gameT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));// font styling
		textFlowB.getChildren().addAll(AntText, gameTitleText, WapsText, gameT);// adding the to children t1 and t2
		GridPane.setHalignment(textFlow, HPos.CENTER);
		grid.add(textFlowB, 0, 11, 2, 1); // colspan so two rows wide

		/* Score list print */
		String str = highScore.toString();
		str = str.replace(",", "");
		str = str.replace("[", "");
		str = str.replace("]", "");
		t.setText(str);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("verdana", FontPosture.REGULAR, 16));
		grid.add(t, 0, 12, 2, 1);
	}

	private void antmanWaspScoreCount() {
		// title antman vs wsap
		AntT.setText("\t" + "ANTMAN");// setting the text
		AntT.setFill(Color.RED);
		AntT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22.2));// font styling
		gameTitle.setText(" Vs ");// setting the text
		gameTitle.setFill(Color.WHITE);
		gameTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22.2));// font styling
		WapsT.setText("WASP");// setting the text
		WapsT.setFill(Color.YELLOW);
		WapsT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22.2));// font styling
		textFlow.getChildren().addAll(AntT, gameTitle, WapsT);// adding the to children t1 and t2
		GridPane.setHalignment(textFlow, HPos.CENTER);
		grid.add(textFlow, 0, 8, 2, 1); // colspan so two rows wide

		/* AntMan score */
		score.setText("Score ANTMAN: " + totalScoreAnt);
		score.setFill(Color.RED);
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(score, 0, 9);

		/* AntMan lives */
		lives.setText("ANTMAN: " + totalScoreAnt);
		lives.setFill(Color.RED);
		lives.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(lives, 1, 9, 1, 1);

		/* WASP score */
		scoreTwo.setText("Score WASP: " + totalScoreWASP);
		scoreTwo.setFill(Color.YELLOW);
		scoreTwo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(scoreTwo, 0, 10);

		/* WASP Lives */
		twolives.setText("WASP: " + totalScoreWASP);
		twolives.setFill(Color.YELLOW);
		twolives.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(twolives, 1, 10, 1, 1);
	}

	private void startPauseBnt() {
		/* Button the check the guess >> look at event handler */
		makeGame.setTextFill(Color.WHITE);
		makeGame.setStyle("-fx-background-color : #1182CF;\r\n; -fx-font-weight: 900;");// styiling the button
		GridPane.setHalignment(makeGame, HPos.CENTER);
		grid.add(makeGame, 0, 6, 2, 1);

		/* Button the check the guess >> look at event handler */
		btn.setTextFill(Color.WHITE);
		btn.setStyle("-fx-background-color : #1182CF;\r\n; -fx-font-weight: 900;");// styiling the button
		GridPane.setHalignment(btn, HPos.CENTER);
		grid.add(btn, 0, 7, 2, 1);
	}

	private void topUI() {
		/* Game Title >>set title text and styling and then adding to the pane */
		title.setWrappingWidth(300);
		title.setText("Bug World Game");// setting the text
		title.setFill(Color.WHITE);
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22.2));// font styling
		title.setTextAlignment(TextAlignment.CENTER);
		grid.add(title, 0, 0, 2, 1); // colspan so two rows wide

		/* Number of bees > Left side */
		bugNumberText.setText("Number of Bees");
		bugNumberText.setFill(Color.ORANGE);
		bugNumberText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(bugNumberText, 0, 1);
		/* text field */
		BugNumber.setPromptText("Number of Bees");// placeholder
		grid.add(BugNumber, 1, 1, 1, 1);

		/* Number of ant > Left side */
		bugNumberTextTwo.setText("Number of Ant-Mans");
		bugNumberTextTwo.setFill(Color.RED);
		bugNumberTextTwo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(bugNumberTextTwo, 0, 2);
		/* Number of ants */
		bugNumbertwo.setPromptText("Number of Ant-Mans");// placeholder
		grid.add(bugNumbertwo, 1, 2, 1, 1);

		/* Number of wasp > Left side */
		bugNumberTextthree.setText("Number of WASP");
		bugNumberTextthree.setFill(Color.YELLOW);
		bugNumberTextthree.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(bugNumberTextthree, 0, 3);
		/* Number of wasp */
		BugNumberThree.setPromptText("Number of WASP");// placeholder
		grid.add(BugNumberThree, 1, 3, 1, 1);

		/* Number of sunflower > Left side */
		sunflowerText.setText("Number of Food \n" + "Sunflowers & Food");
		sunflowerText.setFill(Color.PURPLE);
		sunflowerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		grid.add(sunflowerText, 0, 4);
		/* Number of bees */
		sunflower.setPromptText("Number of sunflowers");// placeholder
		grid.add(sunflower, 1, 4, 1, 1);
	}

	private void gridPaneMenu() {
		grid.setPadding(new Insets(25, 25, 25, 30));// adding padding to each side, can be changed
		grid.setHgap(10);// the gap between cols
		grid.setVgap(15);// the gap between rows
		grid.setAlignment(Pos.CENTER);// aligning the pane to the center
		// grid.setGridLinesVisible(true);//to show the grip line, easy to align
		grid.setStyle("-fx-background-image: url('Background3.jpg');");

		// * both columns are set to 50% of the width*/
		col1.setPercentWidth(50);
		col2.setPercentWidth(50);
	}

	public double scoreWasp() {
		double ScoreAnt = 0;
		for (Bugs b : wasp) {
			ScoreAnt = ScoreAnt + b.getEnergy();
		}
		// System.out.println(ScoreAnt);
		return ScoreAnt;
	}

	public double WaspLives() {
		return wasp.size();
	}

	public double AntLives() {
		return antman.size();
	}

	public double scoreAnt() {
		double ScoreAnt = 0;
		for (Bugs a : antman) {
			ScoreAnt = ScoreAnt + a.getEnergy();
		}
		// System.out.println(ScoreAnt);
		return ScoreAnt;
	}

	// randnum
	public int energy() {
		return (int) (400 + Math.random() * 600);
	}

	private double obSize() {
		return (int) (3 + Math.random() * 22);
	}

	public int r() {
		return (int) (Math.random() * 225);
	}

	public int xRand() {
		return (int) (radius * 2 + (Math.random() * w - radius * 3));
	}

	public int yRand() {
		return (int) (radius * 1.5 + (Math.random() * h - radius * 3));
	}

	private float speedChange() {
		return (int) (1 + Math.random() * 4);
	}

	private double radiusBug() {
		return (int) (5 + Math.random() * 10);
	}

	private void scorelist() {
		// preset score to list
		HighScore round1 = new HighScore(1, "WASP", 12354);
		HighScore round2 = new HighScore(2, "ANTMAN", 549);
		HighScore round3 = new HighScore(3, "ANTMAN", 2312);
		addHs(round1);
		addHs(round2);
		addHs(round3);
	}

	private void addHs(HighScore h) {
		highScore.add(h);
	}

	public static void main(String[] args) {
		launch(args);
	}

}