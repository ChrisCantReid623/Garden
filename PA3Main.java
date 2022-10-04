/** Author: Christopher Reid
 * CSC 210 Fall 2022
 * PA3Main.java: Simulates a garden containing plant themed class objects capable of being modified based on commands
 * read from a txt file. */

import java.io.*;
import java.util.*;

public class PA3Main {
    static Garden garden = new Garden();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner commands = new Scanner(new File(args[0]));

        //Garden Setup
        String rows = commands.nextLine();
        String cols = commands.nextLine();
        String blankLine = commands.nextLine();

        if (gardenSize(cols) > 5) {
            System.out.println("Too many plot columns.");
        } else{
            readCommands(gardenSize(rows), gardenSize(cols), commands);
        }
    }

    /** Reads the garden matrix setup settings from the input text file. Stored as an integer.
     * @param line the row or column line */
    public static int gardenSize(String line) {
        String[] settings = line.split(" ");
        return Integer.parseInt(settings[1]);
    }

    /** Returns the coordinates from a command line as a list of the integers of the locations... i.e [0,0]
     * @param coordinates a string of the coordinates */
    public static int[] getCoordinates(String coordinates) {
        int[] location = new int[2];
        location[0] = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        location[1] = Integer.parseInt(String.valueOf(coordinates.charAt(3)));
        return location;
    }

    /** Reads the specific type of plantSpecies to determine the category based on predetermined lists.
     * @param plantSpecies the string for the specific plantSpecies name */
    public static String getPlantType(String plantSpecies) {
        String[] flowers = {"iris", "lily", "rose", "daisy", "tulip", "sunflower"};
        String[] trees = {"oak", "willow", "banana", "coconut", "pine"};
        String[] veggies = {"garlic", "zucchini", "tomato", "yam", "lettuce"};

        String plantType = null;
        if (Arrays.asList(flowers).contains(plantSpecies.toLowerCase())) {
            plantType = "flower";
        } else if (Arrays.asList(trees).contains(plantSpecies.toLowerCase())) {
            plantType = "tree";
        } else if (Arrays.asList(veggies).contains(plantSpecies.toLowerCase())) {
            plantType = "vegetable";
        }
        return plantType;
    }

    /** Creates new objects of the Plant subclasses and creates the token used to represent the plant within the cells
     * @param plantCommand a growth command from the input text file */
    public static Plant newPlant(String[] plantCommand) {
        String specific = plantCommand[2];
        String type = getPlantType(plantCommand[2]);
        char token = plantCommand[2].toLowerCase().charAt(0);

        return switch (type) {
            case "flower" -> new Flower(type, token, specific);
            case "tree" -> new Tree(type, token, specific);
            case "vegetable" -> new Vegetable(type, token, specific);
            default -> null;
        };
    }

    /** Sets the dimensions of the main garden object. Loops through the remaining lines of the input text file
     * performing actions modifying a Garden object and multiple objects of Plant subclasses.
     * @param rows sets the number of garden rows
     * @param cols sets the number of plant plots within a garden row
     * @param commands a Scanner object containing the remaining lines from the input text file */
    public static void readCommands(int rows, int cols, Scanner commands) {
        garden.setGarden(rows, cols);

        while (commands.hasNext()) {
            String command = commands.nextLine();
            String[] commandLine = command.split(" ");

            if (commandLine[0].equalsIgnoreCase("print")) {
                garden.printGarden();
            }
            else if (commandLine[0].equalsIgnoreCase("plant")) {
                int[] location = getCoordinates(commandLine[1]);
                Plant newPlant = newPlant(commandLine);
                garden.addNewPlant(location, newPlant);
            }
            else if (commandLine[0].equalsIgnoreCase("grow")) {
                System.out.println("> " + command.toUpperCase());
                System.out.println();
                if (commandLine.length == 2) {
                    garden.growEverything(Integer.parseInt(commandLine[1]));
                } else if (commandLine.length == 3) {
                    if (commandLine[2].length() == 5  && getPlantType(commandLine[2]) != null) {
                        garden.growByType(Integer.parseInt(commandLine[1]), commandLine[2], null);
                    } else {
                        garden.growByType(Integer.parseInt(commandLine[1]),null, getCoordinates(commandLine[2]));
                    }
                }
            }
            else if (commandLine[0].equalsIgnoreCase("harvest")) {
                System.out.println("> " + command.toUpperCase());
                System.out.println();
                if (commandLine.length == 1) {
                    garden.harvestVeggies(null, null);
                } else if (commandLine.length == 2) {
                    if (commandLine[1].length() == 5) {
                        garden.harvestVeggies(null, getCoordinates(commandLine[1]));
                    } else {
                        garden.harvestVeggies(commandLine[1], null);
                    }
                }
            }
            else if (commandLine[0].equalsIgnoreCase("pick")) {
                System.out.println("> " + command.toUpperCase());
                System.out.println();
                if (commandLine.length == 1) {
                    garden.pickFlowers(null, null);
                } else if (commandLine.length == 2) {
                    if (commandLine[1].length() == 5) {
                        garden.pickFlowers(null, getCoordinates(commandLine[1]));
                    } else {
                        garden.pickFlowers(commandLine[1], null);
                    }
                }
            }
            else if (commandLine[0].equalsIgnoreCase("cut")) {
                System.out.println("> " + command.toUpperCase());
                System.out.println();
                if (commandLine.length == 1) {
                    garden.cutTrees(null, null);
                } else if (commandLine.length == 2) {
                    if (commandLine[1].length() == 5) {
                        garden.cutTrees(null, getCoordinates(commandLine[1]));
                    } else {
                        garden.cutTrees(commandLine[1], null);
                    }
                }
            }
        }
    }
}