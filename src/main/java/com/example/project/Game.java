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
        boolean quit = false;
        Scanner scan = new Scanner(System.in);
        int size = 0;
        int totalTreasures = 0;
        Player player = new Player(0, 0);
        
        // enemies and treasures in easy mode
        Enemy enemy = new Enemy(5, 5);
        Enemy enemy2 = new Enemy(7,8);
        Treasure treasure = new Treasure(2, 2);
        Treasure treasure2 = new Treasure(1,7);
        Trophy trophy = new Trophy(9, 9);

        // enemies and treasures in medium mode
        Enemy enemy3 = new Enemy(6, 2);
        Enemy enemy4 = new Enemy(12, 13);
        Enemy enemy5 = new Enemy(9, 10);
        Treasure treasure3 = new Treasure(4, 6);
        Treasure treasure4 = new Treasure(8, 8);
        Treasure treasure5 = new Treasure(4, 5);
        Trophy trophy2 = new Trophy(15, 15);

        // enemies and treasures in hard mode
        Enemy enemy6 = new Enemy(15, 16);
        Enemy enemy7 = new Enemy(20, 21);
        Enemy enemy8 = new Enemy(17, 19);
        Enemy enemy9 = new Enemy(10, 13);
        Treasure treasure6 = new Treasure(5, 7);
        Treasure treasure7 = new Treasure(9, 9);
        Treasure treasure8 = new Treasure(15, 15);
        Treasure treasure9 = new Treasure(2, 2);
        Trophy trophy3 = new Trophy(18, 19);

        // used to initialize everything at the beginning
        Sprite[] easy = {treasure, treasure2, enemy, enemy2};
        Sprite[] medium = {treasure3, treasure4, treasure5, enemy3, enemy4, enemy5};
        Sprite[] hard = {enemy6, enemy7, enemy8, enemy9, treasure6, treasure7, treasure8, treasure9};

        System.out.print("Select Difficulty(Easy, Medium, Hard): ");
        String difficulty = scan.nextLine().toLowerCase();

        // depending on difficulty size is randomized to be within a certain range and lives is set to a random amount, total treasures is set to current difficulty enemies + previous difficulty enemies
        // as difficulty increases the map size increases and lives decreases
        if (difficulty.equals("easy"))
        {
            size = (int)(Math.random() * 3) + 10;
            player.setLives((int)((Math.random() * 5)) + 4);
            totalTreasures = 2;
        }
        else if (difficulty.equals("medium"))
        {
            size = (int)(Math.random() * 4) + 17;
            player.setLives((int)((Math.random() * 3)) + 2);
            totalTreasures = 5;
        }
        else
        {
            size = (int)(Math.random() * 4) + 22;
            player.setLives((int)((Math.random() * 2)) + 1);
            totalTreasures = 8;
        }

        Grid grid = new Grid(size);

        // places every object depending on difficulty
        if (difficulty.equals("easy"))
        {
            for (Sprite sprite : easy)
            {
                grid.placeSprite(sprite);
            }

            grid.placeSprite(trophy);
        }
        else if (difficulty.equals("medium"))
        {
            for (Sprite sprite : easy)
            {
                grid.placeSprite(sprite);
            }

            for (Sprite sprite : medium)
            {
                grid.placeSprite(sprite);
            }

            grid.placeSprite(trophy2);
        }
        else
        {
            for (Sprite sprite : easy)
            {
                grid.placeSprite(sprite);
            }

            for (Sprite sprite : medium)
            {
                grid.placeSprite(sprite);
            }

            for (Sprite sprite : hard)
            {
                grid.placeSprite(sprite);
            }

            grid.placeSprite(trophy3);
        }
        
        grid.placeSprite(player);
        grid.display();

        // constantly running to update grid
        while (!quit)
        {
            // constantly setting enemy on grid even when player tries to overwrite it so enemy isn't overwritten with dot object
            // trophy is treated the same to enemy
            if (difficulty.equals("hard"))
            {
                grid.placeSprite(enemy);
                grid.placeSprite(enemy2);
                grid.placeSprite(enemy3);
                grid.placeSprite(enemy4);
                grid.placeSprite(enemy5);
                grid.placeSprite(enemy6);
                grid.placeSprite(enemy7);
                grid.placeSprite(enemy8);
                grid.placeSprite(enemy9);
                grid.placeSprite(trophy3);
            }
            else if (difficulty.equals("medium"))
            {
                grid.placeSprite(enemy);
                grid.placeSprite(enemy2);
                grid.placeSprite(enemy3);
                grid.placeSprite(enemy4);
                grid.placeSprite(enemy5);
                grid.placeSprite(trophy2);
            }
            else
            {
                grid.placeSprite(enemy);
                grid.placeSprite(enemy2);
                grid.placeSprite(trophy);
            }

            // input to get direction
            System.out.print("Enter directional movement(WASD): ");
            String direction = scan.nextLine().toLowerCase();

            // checks if directional movement is valid
            if (player.isValid(size, direction))
            {
                // calls interact based on direction, totalTreasures in the map, and the player's supposed previous position if the move function was to be called with that directional input                
                switch (direction)
                {
                    case "w":
                        // if up then player's y position is one below the supposed position
                        player.interact(size, direction, totalTreasures, grid.getGrid()[size - 2 - player.getY()][player.getX()]);
                        break;
                    case "a":
                        // if left then player's x position is one below the supposed position
                        player.interact(size, direction, totalTreasures, grid.getGrid()[size - 1 - player.getY()][player.getX() - 1]);
                        break;
                       
                    case "s":
                        // if right then player's x position is one above the supposed position
                        player.interact(size, direction, totalTreasures, grid.getGrid()[size - player.getY()][player.getX()]);
                        break;
                        
                    case "d":
                        // if down then player's y position is one above the supposed position
                        player.interact(size, direction, totalTreasures, grid.getGrid()[size - 1 - player.getY()][player.getX() + 1]);
                        break;
                }

                // after interact is calculated then move player into that position and places the sprite there
                player.move(direction);
                grid.placeSprite(player, direction);
            }

            // clears screen to avoid a mess in terminal
            clearScreen();
            grid.display();

            // quits the loop and lose message is printed if player lives ever become less than and equal to zero
            if (player.getLives() <= 0)
            {
                quit = true;
                grid.gameover();
            }
            
            // quits loop and win message is printed if player wins the game and gets the trophy
            if (player.getWin())
            {
                quit = true;
                grid.win();
            }
        }

        // stops the program for 3 seconds so users can see the lose/win acsii art
        try 
        {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        // makes a game instance so game can start
        Game game = new Game(10);
    }
}
