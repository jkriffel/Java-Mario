//James Riffel
//10/29/22
//Assignment5
//class Game contains the commands to start the game panel and call the important game related functions
import javax.swing.JFrame;
import java.awt.Toolkit;

//game class contains turtle update and frame description
public class Game extends JFrame
{
	//declaring variables used
	Model model;
	View view;
	Controller controller;

	//Game constructor that sets window settings and adds input listeners
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A5 - Goomba Scroller");
		this.setSize( 1000, 1000);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		Json j = Json.load("map.json");
		model.unmarshal(j);
	}

	//method that runs the game itself
	public static void main(String[] args)
	{
		Game g = new Game();
		System.out.println("Play Mode");
		g.run();
	}

	//method that describes the run, updates at 40 ms
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40 milliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
