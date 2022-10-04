import java.util.ArrayList;
import java.util.Objects;

/** Acts as a container for Plant objects. */
class Garden {
    protected Plant[][] gardenMap;

    /** Constructor */
    public Garden() {}

    /** Sets the dimensions of the gardenMap object.
     * @param rows sets the number of garden rows
     * @param cols sets the number of plant plots within a garden row */
    public void setGarden(int rows, int cols) {this.gardenMap = new Plant[rows][cols];}

    /** Adds a new plant within the garden at a specified location
     * @param location the x,y coordinates of the specified location
     * @param newPlant the new Plant subclass object */
    public void addNewPlant(int[] location, Plant newPlant) {gardenMap[location[0]][location[1]] = newPlant;}

    /** Prints a standard output of the garden object to the console. */
    public void printGarden() {
        System.out.println("> PRINT");
        for (Plant[] mapRow: gardenMap) {
            for (int plotRow = 0; plotRow < 5; plotRow++) {
                StringBuilder printLine = new StringBuilder();
                for (Plant curPlant: mapRow) {
                    String joined = curPlant.getLine(plotRow);
                    printLine.append(joined);
                }
                System.out.println(printLine);
            }
        }
        System.out.println();
    }

    /** Simulates plant growth on every plant object within the Garden.
     * @param reps the number of times growth happens */
    public void growEverything(int reps) {
        for (Plant[] mapRow : gardenMap) {
            for (Plant curPlant : mapRow) {
                curPlant.grow(reps);
            }
        }
    }

    /** Simulates plant growth based on a specific plant type.
     * @param growth the number of times growth will modify the plot
     * @param plantSpecies the specific species of plant
     * @param location the coordinates of the specified plant within the gardenMap*/
    public void growByType(int growth, String plantSpecies, int[] location) {
        if (plantSpecies == null) {
            ArrayList<int[]> gardenPoints = new ArrayList<>();
            for (int row = 0; row < gardenMap.length; row++) {
                for (int col = 0; col < gardenMap[row].length; col++) {
                    int[] cords = {row,col};
                    gardenPoints.add(cords);
                }
            }
            for (int[] cord: gardenPoints){
                if (cord == location) {
                    gardenMap[location[0]][location[1]].grow(growth);
                }
            }
            System.out.println("Can't grow there.");
            System.out.println();
        } else {
            for (Plant[] mapRow : gardenMap) {
                for (Plant curPlant : mapRow) {
                    if (curPlant.type.equalsIgnoreCase(plantSpecies)) {
                        curPlant.grow(growth);
                    }
                }
            }
        }
    }

    /** Removes vegetable objects from the garden with optional conditions.
     * @param local specifies a location to remove a vegetable object
     * @param plantSpecies specifies a certain species of vegetable to remove */
    public void harvestVeggies(String plantSpecies, int[] local) {
        if (plantSpecies == null && local == null) {
            for (Plant[] mapRow : gardenMap) {
                for (Plant curPlant : mapRow) {
                    if (curPlant.type.equals("vegetable")) {
                        curPlant.remove();
                    }
                }
            }
        } else if (local != null) {
            if (!Objects.equals(gardenMap[local[0]][local[1]].type, "vegetable")) {
                System.out.println("Can't harvest there.");
                System.out.println();
            } else {
                if (gardenMap[local[0]][local[1]] == null) {
                    System.out.println("Can't harvest there.");
                } else {
                    gardenMap[local[0]][local[1]].remove();
                }
            }
        } else {
            for (Plant[] mapRow : gardenMap) {
                for (Plant curPlant : mapRow) {
                    if (curPlant.species.equals(plantSpecies)) {
                        curPlant.remove();
                    }
                }
            }
        }
    }

    /** Removes flower objects from the garden with optional conditions.
     * @param local specifies a location to remove a flower object
     * @param plantSpecies specifies a certain species of flower to remove */
    public void pickFlowers(String plantSpecies, int[] local) {
        if (plantSpecies == null && local == null) {
            for (Plant[] mapRow : gardenMap) {
                for (Plant curPlant : mapRow) {
                    if (curPlant.type.equals("flower")) {
                        curPlant.remove();
                    }
                }
            }
        } else if (local != null) {
            if (!Objects.equals(gardenMap[local[0]][local[1]].type, "flower")) {
                System.out.println("Can't pick there.");
                System.out.println();
            } else{
                if (gardenMap[local[0]][local[1]] == null) {
                    System.out.println("Can't pick there");
                } else {
                    gardenMap[local[0]][local[1]].remove();
                }
            }
        } else {
            for (Plant[] mapRow : gardenMap) {
                for (Plant curPlant : mapRow) {
                    if (curPlant.species.equals(plantSpecies)) {
                        curPlant.remove();
                    }
                }
            }
        }
    }

    /** Removes tree objects from the garden with optional conditions.
     * @param local specifies a location to remove a tree object
     * @param plantSpecies specifies a certain species of tree to remove */
    public void cutTrees(String plantSpecies, int[] local) {
        if (plantSpecies == null && local == null) {
            for (Plant[] mapRow : gardenMap) {
                for (Plant curPlant : mapRow) {
                    if (curPlant.type.equals("tree")) {
                        curPlant.remove();
                    }
                }
            }
        } else if (local != null) {
            if (!Objects.equals(gardenMap[local[0]][local[1]].type, "tree")) {
                System.out.println("Can't cut there.");
                System.out.println();
            } else {
                if (gardenMap[local[0]][local[1]] == null) {
                    System.out.println("Cant cut there");
                } else {
                    gardenMap[local[0]][local[1]].remove();
                }
            }
        } else {
            for (Plant[] mapRow : gardenMap) {
                for (Plant curPlant : mapRow) {
                    if (curPlant.species.equals(plantSpecies)) {
                        curPlant.remove();
                    }
                }
            }
        }
    }
}