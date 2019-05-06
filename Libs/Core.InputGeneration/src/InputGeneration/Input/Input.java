package InputGeneration.Input;

public class Input {

    private final int row;
    private final int column;

    public Input(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean equals(Object o) {
        if(isNotAnInput(o))
            return false;

        Input i = (Input)o;
        return i.row == row && i.column == column;
    }

    private boolean isNotAnInput(Object o) {
        return !(o instanceof Input);
    }

    public int hashCode() {
        return ("row: " + row + " column: " + column).hashCode();
    }

}
