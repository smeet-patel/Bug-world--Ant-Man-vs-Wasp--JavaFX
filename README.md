# Ant Man vs Wasp JavaFX (Animated Bug World)

Using a graphical interface, I made an artificial simulation game inspired from by the 2018 Marvel movie called Ant-Man and the Wasp. The two characters in this game were 'Ant-Man' and the 'Wasp' who animate within an island (window of GUI) which would collide with obstacles, border or other bugs and eat food (Sunflower and other plants, fruits and vegetable) to gain back energy to survive. Either Ant-Man or the Wasp would win depending on which bug survives and which bug does not. A selected number of each bug can be selected, however, a random energy level assigned to each bug. At the end of each round, the aggregate energy level of the remaining bugs counted as the overall score.


The mechanics of the game. The bugs are animated on a bug object which is updated at each keyframe.  All bugs have independent speed value which determines how fast they move. I implemented a collision detection method that allows the bugs to rebound of the boundary, obstacles or other moving bugs. As the bugs animate, the bug shrinks in size and losses energy and eventually dies, but when they collide with the food sources, the bugs regain the energy to survive. The objective of this simulation is the find out which bugs type is the best fit over many rounds. 

<img src="antmanvswaspGAME.png" style="border-radius: 15px">


For this game, I learnt about:


- Different layouts in JavaFX such as Border Pane, Grid Pane, HBox and Vbox. 
 - How to animate different shape using the Keyframe call. 
- Create a collision detection method for bugs.
- Creating a class that extends the circle class to allow for collision functionality 
- Using different Event listener for a button mouse click or key press.
- Using regex to only valid certain characters. 
- Using images for background, and converting circles into images such as bugs and food. 