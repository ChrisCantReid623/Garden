import java.awt.*;
import java.util.*;

/** A Plant subclass. */
class Flower extends Plant {
    private String[][] plot = new String[5][5];
    private ArrayList<Point> cells = new ArrayList<>();

    /** Constructor */
    public Flower(String type, char token, String species) {
        super(type, token, species);
        for (String[] cells : plot) {
            Arrays.fill(cells, ".");
        }
        Point origin = new Point(2, 2);
        this.plot[origin.x][origin.y] = String.valueOf(token);
        this.cells.add(origin);
    }

    /** Returns the plant's cell plot. */
    public String[][] getPlot() {return plot;}

    /** Returns a specific line of the matrix. */
    public String getLine(int index) {return String.join( "",plot[index]);}

    /** Simulates growth within the plot.
     * @param growth the number of times growth will modify the plot */
    public void grow(int growth) {
        ArrayList<Point> tempCords = new ArrayList<>();
        for (int count = 0; count < growth; count++) {
            for (Point cell : cells) {
                // Cells Above
                int above = (cell.x - 1);
                if (above < 0) {
                    above = 0;
                }
                if (!Objects.equals(plot[above][cell.y], String.valueOf(token))) {
                    plot[cell.x - 1][cell.y] = String.valueOf(token); // Up
                    Point up = new Point(cell.x - 1, cell.y);
                    tempCords.add(up);
                }

                // Cells Below
                int below = (cell.x + 1);
                if (below > 4){
                    below = 4;
                }
                if (!Objects.equals(plot[below][cell.y], String.valueOf(token))) {
                    plot[cell.x + 1][cell.y] = String.valueOf(token); // Down
                    Point down = new Point(cell.x + 1, cell.y);
                    tempCords.add(down);
                }

                // Cells Adjacent Left
                int leftSide = (cell.y - 1);
                if (leftSide < 0){
                    leftSide = 0;
                }
                if (!Objects.equals(plot[cell.x][leftSide], String.valueOf(token))) {
                    plot[cell.x][cell.y - 1] = String.valueOf(token); // Left
                    Point left = new Point(cell.x, cell.y - 1);
                    tempCords.add(left);
                }

                // //Cells Adjacent Right
                int rightSide = (cell.y + 1);
                if (rightSide > 4){
                    rightSide = 4;
                }
                if (!Objects.equals(plot[cell.x][rightSide], String.valueOf(token))) {
                    plot[cell.x][cell.y + 1] = String.valueOf(token);
                    Point right = new Point(cell.x, cell.y + 1); // Right
                    tempCords.add(right);
                }
            }
            cells.addAll(tempCords);
        }
    }

    /** Resets the plot of cells by removing any plant token. */
    public void remove() {
        for (String[] cells : plot) {
            Arrays.fill(cells, ".");
        }
        this.cells = new ArrayList<>();
    }
}
