package com.example.project;

import java.util.Scanner;

public class Game
{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size)
    { //the constructor should call initialize() and play()
        initialize();
        play();
    }

    public static void clearScreen() 
    { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) 
            {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } 
            else 
            {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void play()
    { //write your game logic here
        Scanner scanner = new Scanner(System.in);


        while(true)
        {
            try 
            {
                Thread.sleep(100); // Wait for 1/10 seconds
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            }
    }

    public void initialize()
    {
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        int size = 10;
        Grid grid = new Grid(size);
        Player player = new Player(0, 0);
        Enemy enemy = new Enemy(5, 5);
        Enemy enemy2 = new Enemy(7,8);
        Treasure treasure = new Treasure(2, 2);
        Treasure treasure2 = new Treasure(1,7);
        Trophy trophy = new Trophy(9, 9);
        Scanner scan = new Scanner(System.in);

        while (player.getLives() > 0 || player.getWin())
        {
            grid.placeSprite(enemy);
            grid.placeSprite(enemy2);
            grid.placeSprite(treasure);
            grid.placeSprite(treasure2);
            grid.placeSprite(player);
            // player is not getting deleted from previous position
            grid.placeSprite(trophy);
            grid.display();

            System.out.print("Enter directional movement(WASD): ");
            String direction = scan.nextLine().toLowerCase();

            if (player.isValid(size, direction))
            {
                player.interact(size, direction, 2, grid.getGrid()[size - 1 - player.getY()][player.getX()]);
                player.move(direction);
                grid.placeSprite(player, direction);
            }

            grid.display();
        }
    }

    public static void main(String[] args)
    {
        Game game = new Game(10);
    }
}