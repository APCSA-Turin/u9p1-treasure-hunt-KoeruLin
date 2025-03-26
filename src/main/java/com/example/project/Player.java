package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite
{
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) 
    { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }

    public int getTreasureCount()
    {
        return treasureCount;
    }

    public int getLives()
    {
        return numLives;
    }

    public void setLives(int numLives)
    {
        this.numLives = numLives;
    }

    public boolean getWin()
    {
        return win;
    }

    //move method should override parent class, sprite
    public void move(String direction) 
    {
        switch (direction)
        {
            case "w":
                setY(getY() + 1);
                break;
            case "a":
                setX(getX() - 1);
                break;
            case "s":
                setY(getY() - 1);
                break;
            case "d":
                setX(getX() + 1);
                break;
        }
    }

    // interact with an object in the position you are moving to  
    //numTreasures is the total treasures at the beginning of the game
    public void interact(int size, String direction, int numTreasures, Object obj) 
    {
        if (isValid(size, direction))
        {
            if (treasureCount == numTreasures && obj instanceof Trophy)
            {
                win = true;
            }
    
            if (obj instanceof Enemy)
            {
                numLives--;
            }
    
            if (obj instanceof Treasure && !(obj instanceof Trophy))
            {
                treasureCount++;
            }
        }
    }

    public boolean isValid(int size, String direction)
    { //check grid boundaries
        switch (direction) 
        {
            case "w":
                return (getY() + 1) < size;
            case "a":
                return (getX() - 1) > -1;
            case "s":
                return (getY() - 1) > -1;
            case "d":
                return (getX() + 1) < size;
            default:
                return false;
        }
    }

    @Override
    public String getRowCol(int size)
    { //returns the row and column of the sprite -> "[row][col]"
        return "Player:" + "[" + (size - 1 - getY()) + "]" + "[" + getX() + "]";
    }

    @Override
    public String getCoords()
    { //returns the coordinates of the sprite ->"(x,y)"
        return "Player:" + "(" + getX() + "," + getY() + ")";
    }
}
