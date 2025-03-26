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
                grid[i][j] = new Dot(i, j); // replaces every position in grid with a dot object
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
        // converts carterian plane coordinates into 2d array coordinates and places sprite in that 2d array coordinate

        // replaces the previous position player was in with a dot object
        switch (direction)
        {
            case "w":
                // if player moves up then replace the tile below it with a dot object
                grid[size - s.getY()][s.getX()] = new Dot(s.getX(), s.getY() + 1);
                break;
            case "a":
                // if player moves left then replace the tile to the right with a dot object
                grid[size - 1 - s.getY()][s.getX() + 1] = new Dot(s.getX() + 1, s.getY());
                break;
            case "s":
                // if player moves right then replace the tile to the left with a dot object
                grid[size - 2 - s.getY()][s.getX()] = new Dot(s.getX(), s.getY() - 1);
                break;
            case "d":
                // if player moves down then place the tile above it with a dot object
                grid[size - 1 - s.getY()][s.getX() - 1] = new Dot(s.getX() - 1, s.getY());
                break;
        }
    }

    // print out the current grid to the screen
    // checks the instance on each position of the grid and prints the appropriate sprite for it
    public void display()
    {
        for (Sprite[] row : grid)
        {
            for (Sprite sprite : row)
            {
                if (sprite instanceof Player)
                {
                    System.out.print("👾");
                }
                else if (sprite instanceof Enemy)
                {
                    System.out.print("⚔️");
                }
                else if (sprite instanceof Treasure && !(sprite instanceof Trophy))
                {
                    System.out.print("💎");
                }
                else if (sprite instanceof Trophy)
                {
                    System.out.print("🏆");
                }
                else
                {
                    System.out.print("🀄");
                }
            }    

            System.out.println();
        }
    }

    // use this method to display a loss
    // if player lives becomes 0 then this lose message is printed and game ends
    public void gameover()
    {
        System.out.println(" Y   Y   OOO    U   U      L       OOO    SSSSS   EEEEE");
        System.out.println("  Y Y   O   O   U   U      L      O   O   S        E");
        System.out.println("   Y    O   O   U   U      L      O   O   SSSSS    EEEE");
        System.out.println("   Y    O   O   U   U      L      O   O       S    E");
        System.out.println("   Y     OOO     UUU       L       OOO    SSSSS   EEEEE");
    }

    // if player wins then this win message is printed and game ends
    public void win()
    {
        System.out.println(" Y   Y   OOO   U   U    W   W   III   N   N");
        System.out.println("  Y Y   O   O  U   U    W   W    I    NN  N");
        System.out.println("   Y    O   O  U   U    W W W    I    N N N");
        System.out.println("   Y    O   O  U   U    WW WW    I    N  NN");
        System.out.println("   Y     OOO    UUU     W   W   III   N   N");
    }
}
 