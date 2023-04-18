//James Riffel
//10/29/22
//Assignment5
// Goomba class is a subclass of sprite containing goomba information
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;

public class Goomba extends Sprite
{
    static BufferedImage goombaImage;
    static BufferedImage fireImage;
    Model model;
    double Yvelocity = 1.2;
    double Xvelocity = 2.5;
    int countGoomba;
    boolean deathGoomba = false;
    int onFire;
    int lastX;
    int lastY;
    public Goomba(int x, int y, Model m) {
        //setting goomba coords
        this.x = x;
        this.y = y;
        w = 37;
        h = 45;

        if (goombaImage == null)
        {
            goombaImage = View.loadImage("goomba.png");
        }
        if  (fireImage == null)
        {
            fireImage = View.loadImage("goomba_fire.png");
        }
    }
    
    boolean delGoomba(int mousex, int mousey)
    {
            return false;
    }

    public Goomba(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 37;
        h = 45;
        if (goombaImage == null)
        {
            goombaImage = View.loadImage("goomba.png");
        }
        if (fireImage == null)
        {
            fireImage = View.loadImage("goomba_fire.png");
        }

    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    //tracks last position of goomba for collision
    void lastPosition()
    {
        lastX = x;
        lastY = y;
    }

    //detects which direction collision is occurring and if it is occurring
    void goombaCollision(Sprite p)
    {
        if (p.isPipe()) {

            //from the left to right side x  collision
            if (x + w >= p.x && lastX + w < p.x) {
                x = p.x - w - 1;
                Xvelocity = -Xvelocity;
            }
            //from the right to left side x collision
            else if (x <= p.x + p.w && lastX > p.x + p.w) {
                x = p.x + p.w + 1;
                Xvelocity = -Xvelocity;
            }
            //from the bottom collision in y
            else if (y + h >= p.y && lastY + h < p.y) {
                y = p.y - h - 1;
                Xvelocity = -Xvelocity;
            }
            //from the top collision in y
            else if (y <= p.y + p.h && lastY > p.y + p.h) {
                y = p.y + p.h + 1;
                Xvelocity = -Xvelocity;
                countGoomba = 0;
            }
        }
        else if (p.isFireball())
        {
            deathGoomba = true;
        }
    }
        
    boolean update()
    {
        Yvelocity += 4.0;
        y += Yvelocity;

        countGoomba++;
        //if he is at or below ground legevel
        if(y > 965 - h)
        {
            //remove velocity and set him to ground level
            Yvelocity = 0.0;
            y = 965 - h;
            countGoomba = 0;
        }
        if (countGoomba == 0)
        {
            x += Xvelocity;
        }
        if (deathGoomba)
        {
            onFire++;
        }
        if (onFire > 20)
        {
            return true;
        }
        return false;
    }

    void drawYourself(Graphics g)
    {
        if (!deathGoomba) {
            g.drawImage(goombaImage, x - View.scrollPos + 20, y, null);
        }
        else if (deathGoomba)
        {
            g.drawImage(fireImage, x - View.scrollPos + 20, y, null);
        }
    }

    @Override
    boolean isGoomba()
    {
        return true;
    }
}