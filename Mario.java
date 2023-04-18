//James Riffel
//10/29/22
//Assignment5
// Mario class is a sprite subclass and contains mario information
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
public class Mario extends Sprite
{
    //array of mario images
    static BufferedImage[] marioImages;
    //tracks current mario image
    int currentImage;
    //tracks index of mario image
    int marioImageNumber;
    //tracks mario's ability to jump
    int countMario;
    //marios last x position
    int lastX;
    //marios last y position
    int lastY;
    //marios base velocity
    double velocity = 1.2;
    public Mario(int x, int y)
    {
        //setting mario coords
        this.x = x;
        this.y = y;
        w = 61;
        h = 95;
        //Intitialze variables
        currentImage = 0;
        marioImageNumber = 0;
        countMario = 0;
        //mario image array
        marioImages = new BufferedImage[5];

        //sets series of images for mario's animation movement
        if(marioImages[0] == null)
            marioImages[0] = View.loadImage("mario1.png");
        if(marioImages[1] == null)
            marioImages[1] = View.loadImage("mario2.png");
        if(marioImages[2] == null)
            marioImages[2] = View.loadImage("mario3.png");
        if(marioImages[3] == null)
            marioImages[3] = View.loadImage("mario4.png");
        if(marioImages[4] == null)
            marioImages[4] = View.loadImage("mario5.png");

    }
    //enables mario to jump
    void jump()
    {
        //conditional for mario jumping, only if he is on the ground or the top of a pipe
        if(countMario == 0)
        {
            //jump
            velocity -= 60;
        }
    }

    boolean update()
    {

        //counts when he is not touching the top of a pipe or the ground
        countMario++;

        //gravity
        velocity += 4;
        y += velocity;

        //if he is at or below ground level
        if(y > 965 - h)
        {
            //remove velocity and set him to ground level
            velocity = 0.0;
            y = 965 - h;
            countMario = 0;
        }
        else if (y < 0)
        {
            y = 0;
        }
        return false;
    }
    
    //tracks last position of mario for collision
    void lastPosition()
    {
        lastX = x;
        lastY = y;
    }

    //detects which direction collision is occurring and if it is occurring
    void marioCollision(Sprite p)
    {
        //from the left to right side x  collision
        if (x + w >= p.x && lastX + w < p.x)
        {
            x = p.x - w - 1;
        }
        //from the right to left side x collision
        else if(x <= p.x + p.w && lastX > p.x + p.w)
        {
            x = p.x + p.w + 1;
        }
        //from the bottom collision in y
        else if (y + h >= p.y && lastY + h < p.y)
        {
            velocity = 0.0;
            countMario = 0;
            y = p.y - h - 1;
        }
        //from the top collision in y
        else if ( y  <= p.y + p.h && lastY > p.y + p.h)
        {
            y = p.y + p.h + 1;
        }
    }
    //method to draw mario
    public void drawYourself(Graphics g)
    {
        //mario draws himself
        g.drawImage(marioImages[marioImageNumber], 25, y, w, h, null);
    }

    @Override
    boolean isMario()
    {
        //returns true if the sprite is a mario
        return true;
    }
}