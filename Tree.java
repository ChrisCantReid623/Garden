import java.awt.*;
import java.util.*;

/** A Plant subclass. */
class Tree extends Plant {
    private String[][] plot = new String[5][5];
    private ArrayList<Point> cells = new ArrayList<>();

    /** Constructor */
    public Tree(String type, char token, String species) {
        super(type, token, species);
        for (String[] cells : plot) {
            Arrays.fill(cells, ".");
        }
        Point origin = new Point(4, 2);
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
            for (Point cell: cells) {
                int above = (cell.x - 1);
                if (above < 0) {
                    above = 0;
                }
                if (!Objects.equals(plot[above][cell.y], String.valueOf(token))) {
                    plot[cell.x - 1][cell.y] = String.valueOf(token); // Up
                    Point up = new Point(cell.x - 1, cell.y);
                    tempCords.add(up);
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