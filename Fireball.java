//James Riffel
//10/29/22
//Assignment5
// Fireball class is a subclass of sprite containing fireball information
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;

public class Fireball extends Sprite
{
    static BufferedImage image;
    Model model;
    double Yvelocity = 1.2;
    double Xvelocity = 100;
    int lastX, lastY;
    public Fireball(int x, int y, Model m) {
        //setting goomba coords
        this.x = x;
        this.y = y;
        w = 47;
        h = 47;

        if (image == null) {
            image = View.loadImage("fireball.png");
        }
    }

    
    public Fireball(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 47;
        h = 47;
        if (image == null)
        {
            image = View.loadImage("fireball.png");
        }
    }

    //detects which direction collision is occurring and if it is occurring
    boolean fireballCollision(Sprite p)
    {
        return false;
    }

        
        boolean update()
        {
            Yvelocity += 4.0;
            y += Yvelocity;
            Xvelocity += 1.0;
            x += Xvelocity;
            //if he is at or below ground level
            if(y > 955 - h)
            {
                //remove velocity and set him to ground level
                Yvelocity -= 40;
                x += Xvelocity;
            }
            return false;
        }

        //fireball draws itself
        void drawYourself(Graphics g)
        {
            g.drawImage(image, x - View.scrollPos + 20, y, null);
        }
        

        @Override
        boolean isFireball()
        {
            return true;
        }
    }