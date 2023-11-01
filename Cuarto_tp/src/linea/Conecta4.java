package linea;
import java.util.List;
import java.util.ArrayList;


public class Conecta4 {

    private static final char notMarked = ' ';

    public static final String columnFull = "Illegal movement, column is full";
    private static final char BLUE = 'b';
    private static final char RED = 'r';

    private final List<List<Character>> TABLE;
    private final int HEIGHT;
    private final int WIDTH;

    private boolean itsRedTurn;

    public Conecta4(int rows, int cols) {

        this.HEIGHT = rows;
        this.WIDTH = cols;

        this.TABLE = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<Character> row = new ArrayList<>(cols);
            for (int j = 0; j < cols; j++) {
                row.add(notMarked);
            }
            this.TABLE.add(row);
        }
        itsRedTurn = true;
    }

    public String showBoard() {
        StringBuilder board = new StringBuilder();

        for (int i = 0; i < this.HEIGHT; i++) {
            board.append("|");
            for (int j = 0; j < this.WIDTH; j++) {
                board.append(this.TABLE.get(i).get(j));
            }
            board.append("|\n");
        }

        return board.toString();
    }


    public boolean finished() {
        return fourInARow() || boardIsFull();
    }

    public boolean fourInARow() {
        return fourInAHorizontalRow() || fourInAVerticalRow() || fourInADiagonalRow();
    }

    public boolean boardIsFull() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                if (this.TABLE.get(i).get(j) == notMarked) {
                    return false;
                }
            }
        }
        return true;
    }

    public void playBlueAt(int column) {
        if(!itsRedTurn && !finished()) {
            int row = checkRowForColumn(column);
            this.TABLE.get(row).set(column, BLUE);
            itsRedTurn = true;
        }else {
            throw new RuntimeException("It's not turn or game finished");
        }
    }

    public void playRedAt(int column) {
        if(itsRedTurn && !finished()) {
            int row = checkRowForColumn(column);
            this.TABLE.get(row).set(column, RED);
            itsRedTurn = false;
        }else {
            throw new RuntimeException("It's not turn or game finished");
        }
    }

    private int checkRowForColumn(int column) {
        for (int row = this.HEIGHT - 1; row >= 0; row--) {
            if (this.TABLE.get(row).get(column) == notMarked) {
                return row;
            }
        }
        throw new RuntimeException(columnFull);
    }

    private boolean fourInADiagonalRow() {
        for (int i = 0; i < this.HEIGHT - 3; i++) {
            for (int j = 0; j < this.WIDTH - 3; j++) {
                if (this.TABLE.get(i).get(j) != notMarked &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 1).get(j + 1) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 2).get(j + 2) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 3).get(j + 3)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < this.HEIGHT - 3; i++) {
            for (int j = 3; j < this.WIDTH; j++) {
                if (this.TABLE.get(i).get(j) != notMarked &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 1).get(j - 1) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 2).get(j - 2) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 3).get(j - 3)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean fourInAVerticalRow() {
        for (int i = 0; i < this.HEIGHT - 3; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                if (this.TABLE.get(i).get(j) != notMarked &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 1).get(j) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 2).get(j) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i + 3).get(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean fourInAHorizontalRow() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH - 3; j++) {
                if (this.TABLE.get(i).get(j) != notMarked &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i).get(j + 1) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i).get(j + 2) &&
                        this.TABLE.get(i).get(j) == this.TABLE.get(i).get(j + 3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRedTurn() {
        return itsRedTurn;
    }

    public boolean redWon() {
        return fourInARow() && !itsRedTurn;
    }
    public boolean blueWon() {
        return fourInARow() && itsRedTurn;
    }
}