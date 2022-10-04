/** The Parent Plant class. Contains the common fields. */
abstract class Plant {
    protected String type;
    protected char token;
    protected String species;

    /** Constructor */
    public Plant(String type, char token, String species) {
        this.type = type;
        this.token = token;
        this.species = species;
    }
    abstract String[][] getPlot();
    abstract public String getLine(int index);
    abstract void grow(int growth);
    abstract void remove();
}