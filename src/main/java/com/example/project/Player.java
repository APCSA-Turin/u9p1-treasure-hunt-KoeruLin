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
    // moves player based off of a directional input
    public void move(String direction) 
    {
        switch (direction)
        {
            case "w": // moves player up
                setY(getY() + 1);
                break;
            case "a": // moves player left
                setX(getX() - 1);
                break;
            case "s": // moves player right
                setY(getY() - 1);
                break;
            case "d": // moves player down
                setX(getX() + 1);
                break;
        }
    }

    // interact with an object in the position you are moving to  
    //numTreasures is the total treasures at the beginning of the game
    // interacts with the item in front, left, right, or botton of the player based on directional input
    public void interact(int size, String direction, int numTreasures, Object obj) 
    {
        if (isValid(size, direction)) // checks if the movement is valid first else do nothing
        {
            if (treasureCount >= numTreasures && obj instanceof Trophy) // turns win to true if trophy is interacted with and all treasures are collected
            {
                win = true;
            }
    
            if (obj instanceof Enemy) // removes a player life when the player walks on top of enemy and interacts with it
            {
                numLives--;
            }
    
            if (obj instanceof Treasure && !(obj instanceof Trophy)) // increase treausreCount if the tile is treasure, had to had the second condition since treasure is a child of trophy
            {
                treasureCount++;
            }
        }
    }

    // checks if player movement is valid 
    public boolean isValid(int size, String direction)
    { //check grid boundaries
        switch (direction) 
        {
            case "w": // checks if moving up runs into the upper limit of grid
                return (getY() + 1) < size;
            case "a": // checks if moving left runs into the left limit of grid
                return (getX() - 1) > -1;
            case "s": // checks if moving right runs into the right limit of grid
                return (getY() - 1) > -1;
            case "d": // checks if moving down runs into the lower limit of grid
                return (getX() + 1) < size;
            default: // returns false for any other inputs(invalid)
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
