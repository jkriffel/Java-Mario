//James Riffel
//10/29/22
//Assignment5
//View class creates window and current view for game
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.image.BufferedImage;

//class View that contains the Panel, pipe Graphics, and button remove
class View extends JPanel
{
	//sets up buffered images of pipes
	static BufferedImage pipe_image;
	Model model;
	//determines where the view screen is
	static int scrollPos;
	//View constructor that sets button and pipe image
	View(Controller c, Model m)
	{
		c.setView(this);

		try
		{
			this.pipe_image = ImageIO.read(new File("pipe.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		model = m;
	}

	//method paints and describes graphics for pipe as well as position
	public void paintComponent(Graphics g)
	{
		//sets scroll position to mario x
		scrollPos = model.mario.x;
		//sets background color
		g.setColor(new Color(0, 200, 255));
		//creates rectangle screen
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//floor color
		g.setColor(Color.red);
		//draws floor line
		g.drawLine(0, 965, 4000, 965);
		//for loop iterates across the pixel size of the image
		for (int i = 0; i < model.sprites.size(); i++) {
			model.sprites.get(i).drawYourself(g);
		}
	}

	//load image class loads the images from the file to the arrays
	static BufferedImage loadImage(String filename)
	{
		//sets new image to null for if conditional to be met for objects
		BufferedImage im = null;
		try
		{
			//reads image in
			im = ImageIO.read(new File(filename));
			System.out.println("Successfully loaded " + filename + " image.");
		} catch(Exception e) {
			//catches if error reading
			e.printStackTrace(System.err);
			System.exit(1);
		}
		//returns the buffered image
		return im;
	}
}
