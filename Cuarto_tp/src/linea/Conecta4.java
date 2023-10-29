package linea;

public class Conecta4 {

    private static final char notMarked = ' ';

    public static final String columnFull = "Illegal movement, column is full";
    private static final char BLUE = 'b';
    private static final char RED = 'r';

    private final char[][] TABLE;
    private final int HEIGHT;
    private final int WIDTH;

    public Conecta4(int rows, int cols) {

        this.HEIGHT = rows;
        this.WIDTH = cols;

        this.TABLE = new char[rows][cols];


        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                this.TABLE[i][j] = notMarked;
            }
        }

    }

    public String showBoard() {

        StringBuilder board = new StringBuilder();

        for (int i = 0; i < this.HEIGHT; i++) {
            board.append("|");
            for (int j = 0; j < this.WIDTH; j++) {
                board.append(this.TABLE[i][j]);
            }
            board.append("|\n");
        }

        return board.toString();

    }


    public boolean finished() {
        return fourInARow();
    }

    public void playBlueAt(int column) {

        int row = checkRowForColumn(column);

        this.TABLE[row][column] = BLUE;


    }


    public void playRedAt(int column) {
        int row = checkRowForColumn(column);

        this.TABLE[row][column] = RED;

    }

    private int checkRowForColumn(int column) {

        for (int row = this.HEIGHT - 1; row >= 0; row--) {
            if (this.TABLE[row][column] == notMarked) {
                return row;
            }
        }

        throw new RuntimeException(columnFull);
    }



    public boolean fourInARow() {
        return fourInAHorizontalRow() || fourInAVerticalRow() || fourInADiagonalRow();
    }

    private boolean fourInADiagonalRow() {

        for (int i = 0; i < this.HEIGHT - 3; i++) {
            for (int j = 0; j < this.WIDTH - 3; j++) {
                if (this.TABLE[i][j] != notMarked && this.TABLE[i][j] == this.TABLE[i + 1][j + 1] && this.TABLE[i][j] == this.TABLE[i + 2][j + 2] && this.TABLE[i][j] == this.TABLE[i + 3][j + 3]) {
                    return true;
                }
            }
        }

        for (int i = 0; i < this.HEIGHT - 3; i++) {
            for (int j = 3; j < this.WIDTH; j++) {
                if (this.TABLE[i][j] != notMarked && this.TABLE[i][j] == this.TABLE[i + 1][j - 1] && this.TABLE[i][j] == this.TABLE[i + 2][j - 2] && this.TABLE[i][j] == this.TABLE[i + 3][j - 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean fourInAVerticalRow() {
        for (int i = 0; i < this.HEIGHT - 3; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                if (this.TABLE[i][j] != notMarked && this.TABLE[i][j] == this.TABLE[i + 1][j] && this.TABLE[i][j] == this.TABLE[i + 2][j] && this.TABLE[i][j] == this.TABLE[i + 3][j]) {
                    return true;
                }
            }
        }
        return false;
    }



    private boolean fourInAHorizontalRow() {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH - 3; j++) {
                if (this.TABLE[i][j] != notMarked && this.TABLE[i][j] == this.TABLE[i][j + 1] && this.TABLE[i][j] == this.TABLE[i][j + 2] && this.TABLE[i][j] == this.TABLE[i][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }


}