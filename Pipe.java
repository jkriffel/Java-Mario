//James Riffel
//10/29/22
//Assignment5
// Pipe class is a subclass of sprite and contains pipe information
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
public class Pipe extends Sprite
{
	//int x, y, w, h;
	static BufferedImage image;
	Model model;
	public Pipe(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		this.w = 55;
		this.h = 400;
		if (image == null)
		{
			image = View.loadImage("pipe.png");
		}
	}

	boolean delPipe(int mousex, int mousey)
	{
		if(mousex >= x & mousex <= x + w)
		{
			if (mousey >= y & mousey <= y + h)
			{
				return true;
			}
		}
		return false;
	}

	public Pipe(Json ob, Model m)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 55;
		h = 400;
		if (image == null)
		{
			image = View.loadImage("pipe.png");
		}
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
//		ob.add("w," w);
//		ob.add("h", h);
		return ob;
	}
	//update pipe
	boolean update()
	{
		return false;
	}
	// return true if sprite is a pipe
	@Override
	boolean isPipe()
	{
		return true;
	}

	//pipe draws itself
	public void drawYourself(Graphics g)
	{
		g.drawImage(image, x - View.scrollPos + 20, y, null);
	}
}