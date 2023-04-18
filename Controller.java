//James Riffel
//10/29/22
//Assignment5
//class Controller contains information and instructions for inputs into the game
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//Controller class that takes in Actions, Mouse, and Keyboard input
class Controller implements ActionListener, MouseListener, KeyListener
{
    //declaration of keyboard inputs
    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    boolean spaceBar;
    boolean escapeKey;
    boolean control = true;

    View view;
    Model model;
    boolean editorCount;
    boolean spriteCount;
    //constructor
    Controller(Model m)
    {
        model = m;
    }

    //Actions that occur when button is pressed (event e) to delete button
    public void actionPerformed(ActionEvent e) {}

    //method that sets view
    void setView(View v)
    {
        view = v;
    }

    //method that allows mouse to be clicked and turtle to follow destination
    public void mousePressed(MouseEvent e)
    {
        if (editorCount) {
            if (!spriteCount) {
                model.addPipe(e.getX() + model.mario.x, e.getY());
            }
            if (spriteCount)
            {
                model.addGoomba(e.getX() + model.mario.x, e.getY());
            }
        }
    }

    //methods that describe the mouse clicks, release, enter, and exit
    public void mouseReleased(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) {    }

    //keyboard controls assign movement directions for keys
    public void keyPressed(KeyEvent e)
    {
        //defines key pressed on press
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT: keyRight = true; break;
            case KeyEvent.VK_LEFT: keyLeft = true; break;
            case KeyEvent.VK_UP: keyUp = true; break;
            case KeyEvent.VK_DOWN: keyDown = true; break;
            case KeyEvent.VK_ESCAPE: escapeKey = true; break;
            case KeyEvent.VK_SPACE: spaceBar = true; break;
            case KeyEvent.VK_CONTROL: control = true; break;

        }
    }

    //method for key release of keyboard inputs
    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            //defines key presses on release
            case KeyEvent.VK_RIGHT: keyRight = false; break;
            case KeyEvent.VK_LEFT: keyLeft = false; break;
            case KeyEvent.VK_UP: keyUp = false; break;
            case KeyEvent.VK_DOWN: keyDown = false; break;
            case KeyEvent.VK_ESCAPE: escapeKey = false; break;
            case KeyEvent.VK_SPACE: spaceBar = false; break;
            case KeyEvent.VK_CONTROL: control = false; break;
        }
        //defines keyboard character inputs
        char c = Character.toLowerCase(e.getKeyChar());
        //exits with q
        if (c == 'q')
        {
            //quits
            System.exit(0);
        }
        //exits with escape
        if (escapeKey)
        {
            System.exit(0);
        }
        //saves with s
        if (c == 's')
        {
            //saves the marshal
            Json saveObject = model.marshal();
            saveObject.save("map.json");
            System.out.println("File has been saved.");
        }
        //loads with l
        if (c == 'l')
        {
            //loads the marshal
            Json j = Json.load("map.json");
            model.unmarshal(j);
            System.out.println("File is loaded");
        }
        //enters edit mode with e or leave it
        if (c == 'e')
        {
            if (!editorCount)
            {
                System.out.println("Editor Mode");
                editorCount = true;
            }
            else if (editorCount)
            {
                System.out.println("Play Mode");
                editorCount = false;
            }
        }
        if (editorCount)
        {
            if (c == 'g')
            {
                if (!spriteCount)
                {
                    System.out.println("Goomba Mode");
                    spriteCount = true;
                }
                else if (spriteCount)
                {
                    System.out.println("Pipe Mode");
                    spriteCount = false;
                }
            }
        }
    }

    //key type empty method
    public void keyTyped(KeyEvent e) {}

    //updates pipe location based on inputs
    void update()
    {
        model.mario.lastPosition();
        //moves mario is the positive x direction and update the image for animation with right arrow key
        if(keyRight)
        {
            //move view with marios x
            model.mario.x += 11;
            //count mario images
            model.mario.marioImageNumber++;
            //marios image model being reset after the 4th index
            if(model.mario.marioImageNumber > 4)
            {
                model.mario.marioImageNumber = 0;
            }
        }
        //moves mario is the negative x direction and update the image for animation with left arrow key
        if(keyLeft)
        {
            //move view with marios x
            model.mario.x -= 11;
            //count mario images
            model.mario.marioImageNumber++;
            //marios image model being reset after the 4th index
            if(model.mario.marioImageNumber > 4)
            {
                model.mario.marioImageNumber = 0;
            }
        }
        //allows mario to jump if space is pressed
        if(spaceBar)
        {
            model.mario.jump();
        }
        if(!control)
        {
            model.addFireball(model.mario.x, model.mario.y);
            control = true;
        }
    }
}
