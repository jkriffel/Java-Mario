//James Riffel
//10/29/22
//Assignment5
// Sprite class serves as a superclass for both Mario and Pipe

import java.awt.Graphics;

//abstract class sprite serves as superclass for mario, pipes, fireballs, and goombas
abstract class Sprite
{
    //holdes the information about sprite locations here
    int x, y, w, h;
    //contstructor for sprite
    public Sprite()
    {
        //initializes variables at zero
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }
    //method declaration, definition exists in subclasses
    //updates the object
    abstract boolean update();
    //object draws itself
    abstract void drawYourself(Graphics g);
    
    //methods to determine what type of sprite an object is
    boolean isPipe()
    {
        return false;
    }
    boolean isMario()
    {
        return false;
    }
    boolean isGoomba() { return false;}
    boolean isFireball() { return false;}
}
