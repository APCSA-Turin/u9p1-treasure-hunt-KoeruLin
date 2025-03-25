package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Grid
{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) 
    { //initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size];

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = new Dot(i, j);
            }
        }
    }

    public Sprite[][] getGrid()
    {
        return grid;
    }

    // place sprite in a new spot
    public void placeSprite(Sprite s)
    {
        grid[size - 1 - s.getY()][s.getX()] = s;   
    }

    // place sprite in a new spot based on direction
    public void placeSprite(Sprite s, String direction)
    {
        grid[size - 1 - s.getY()][s.getX()] = s;  

        switch (direction)
        {
            case "w":
                grid[size - s.getY()][s.getX()] = new Dot(s.getX(), s.getY() + 1);
                break;
            case "a":
                grid[size - 1 - s.getY()][s.getX() + 1] = new Dot(s.getX() + 1, s.getY());
                break;
            case "s":
                grid[size - 2 - s.getY()][s.getX()] = new Dot(s.getX(), s.getY() - 1);
                break;
            case "d":
                grid[size - 1 - s.getY()][s.getX() - 1] = new Dot(s.getX() - 1, s.getY());
                break;
        }
    }
    // potential issue for one of the test cases, not turning treasure into a dot

    // print out the current grid to the screen
    public void display()
    {
        for (Sprite[] row : grid)
        {
            for (Sprite sprite : row)
            {
                if (sprite instanceof Player)
                {
                    System.out.print("PP");
                }
                else if (sprite instanceof Enemy)
                {
                    System.out.print("EE");
                }
                else if (sprite instanceof Treasure && !(sprite instanceof Trophy))
                {
                    System.out.print("Tt");
                }
                else if (sprite instanceof Trophy)
                {
                    System.out.print("TT");
                }
                else
                {
                    System.out.print("[]");
                }
            }    

            System.out.println();
        }
    }

    // use this method to display a loss
    public void gameover()
    {

    }
}
 