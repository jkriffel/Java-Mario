//James Riffel
//10/29/22
//Assignment5
//class Model that describes pipe position
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

class Model
{
    //declaring pipe positions
    ArrayList<Sprite> sprites;
    Mario mario;
    //constructor
    Model()
    {
        //declaring array of type Pipe for sprites
        sprites = new ArrayList<Sprite>();
        mario = new Mario(35, 450);
        sprites.add(mario);
    }
    //method to call the last position mario was in
    public void lastPosition()
    {
        mario.lastPosition();
    }
    //finds when sprites collide, returns false if they do
    static boolean spriteCollide(Sprite Obj1, Sprite Obj2)
    {
        // left
        if (Obj1.x + Obj1.w < Obj2.x)
        {
            return false;
        }
        // right
        if(Obj1.x > Obj2.x + Obj2.w)
        {
            return false;
        }
        // down
        if(Obj1.y + Obj1.h < Obj2.y)
        {
            return false;
        }
        // up
        if(Obj1.y > Obj2.y + Obj2.h)
        {
            return false;
        }
        //returns true if not above
        return true;
    }
    //unmarshal the json objects for array of sprites
    void unmarshal(Json ob)
    {
        //array list of sprites
        sprites = new ArrayList<Sprite>();
        //add mario to sprites list
        sprites.add(mario);
        //create json list
        Json jsonList = ob.get("sprites");
        //create pipe list
        Json pipeList = jsonList.get("pipes");
       //iterate through pipe list
        Json goombaList = jsonList.get("goombas");
        for(int i = 0; i < pipeList.size(); i++)
        {
            //add pipes
            sprites.add(new Pipe(pipeList.get(i),this));
        }
        for(int i = 0; i < goombaList.size(); i++)
        {
            //add pipes
            sprites.add(new Goomba(goombaList.get(i),this));
        }
    }
    //marshal the json object of pipe arrays, and add then to tmpList, then return
    Json marshal()
    {
        //declaring marshaling object
        Json ob = Json.newObject();
        //declaring sprite object 
        Json spriteOb = Json.newObject();
        //temporary list of new json objects
        Json tmpPipe = Json.newList();
        //add new sprite object
        ob.add("sprites", spriteOb);
        //goomba list
        Json tmpGoomba = Json.newList();
        //add new pipe object to temporary list
        spriteOb.add("pipes", tmpPipe);
        spriteOb.add("goombas", tmpGoomba);
        //iterate through sprites
        for(int i = 0; i < sprites.size(); i++) {
            //if sprites is pipe
            if (sprites.get(i).isPipe()) {
                // add pipe to tmp list (marshal)
                Pipe p = (Pipe) sprites.get(i);
                tmpPipe.add(p.marshal());
            }
        }
        for (int i = 0; i < sprites.size(); i++) {
            if(sprites.get(i).isGoomba()) {
                // add pipe to tmp list (marshal)
                Goomba g = (Goomba) sprites.get(i);
                tmpGoomba.add(g.marshal());
            }
        }
        //returns the object
        return ob;
    }

    //adding a new pipe if there isn't one currently under the mouse,
    //if there is a pipe there, delete it
    public void addPipe(int x, int y)
    {
        //found pipe boolean to determine if pipe was clicked on
        boolean foundPipe = false;
        //iterate through sprites until we find a pipe
            for (int i = 0; i < sprites.size(); i++) {
                if(sprites.get(i).isPipe()) {
                    //pipe was found, delete pipe at given coords
                    if (((Pipe) sprites.get(i)).delPipe(x - 20, y) == true) {
                        //remove pipe
                        sprites.remove(i);
                        //pipe was found
                        foundPipe = true;
                        break;
                    }
                }
            }
            //if pipe is not in the spot being clicked
        if (!foundPipe)
        {
            //add new pipe
            sprites.add(new Pipe(x, y, this));
        }
    }

    // adds goomba to screen
    public void addGoomba(int x, int y)
    {
        sprites.add(new Goomba(x, y, this));
    }

    //adds a fireball to screen
    public void addFireball(int x, int y)
    {
        sprites.add(new Fireball(x, y, this));
    }
    
    //update models
    public void update()
    {
        //comparing two sprites
        Sprite obj1, obj2;
        //iterate sprites
    for (int i = 0; i < sprites.size(); i++)
        {
            obj1 = sprites.get(i);
            //if sprite is goomba
            if (obj1.isGoomba())
            {
                ((Goomba)obj1).lastPosition();
            }
            if (sprites.get(i).isMario())
            {
                if (sprites.get(i).update()) {
                    //remove
                    sprites.remove(i);
                }
            }
            if (sprites.get(i).isFireball())
            {
                if (sprites.get(i).update()) {
                    //remove
                    sprites.remove(i);
                }
            }
            //if sprite 1 updates
            if (sprites.get(i).isGoomba()) {
                if (sprites.get(i).update()) {
                    //remove
                    sprites.remove(i);
                }
            }
            if (sprites.get(i).isFireball())
            {
                if (sprites.get(i).x > 1000 + View.scrollPos)
                {
                    sprites.remove(i);
                }
            }

            
            //iterate sprites
            for (int k = 0; k < sprites.size(); k++)
            {
                obj2 = sprites.get(k);
                //if sprite 1 is a mario and sprite 2 is a pipe
                if (obj1.isMario() && obj2.isPipe())
                {
                    //collision handling
                    if (spriteCollide(obj1, obj2))
                    {
                        //collision out pipe
                        ((Mario)obj1).marioCollision(obj2);
                    }
                }
                
                //if object 1 is goomba and object 2 is a pipe
                if (obj1.isGoomba() && obj2.isPipe())
                {
                    //collide
                    if (spriteCollide(obj1, obj2))
                    {
                        //collision out pipe
                        ((Goomba)obj1).goombaCollision(obj2);
                    }
                }
                
                //if fireball is object 1 and object 2 is goomba
                if (obj1.isFireball() && obj2.isGoomba())
                {
                    //collide
                    if (spriteCollide(obj1, obj2))
                    {
                        //draws goomba on fire
                        ((Goomba)obj2).goombaCollision(obj1);
                    }
                }
            }
        }

    }
}