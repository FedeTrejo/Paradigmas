package linea;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Conecta4 {
    private static final char notMarked = ' ';

    public static final String columnFull = "Illegal movement, column is full";
    private static final char BLUE = 'b';
    private static final char RED = 'r';

    private final List<List<Character>> TABLE;
    private final int HEIGHT;
    private final int WIDTH;
    private static GameMode GAMEMODE;

    private boolean itsRedTurn;

    public Conecta4(int rows, int cols, GameMode gamemode) {
        if (rows < 4 || cols < 4) throw new RuntimeException("Board size must be at least 4x4");
        this.HEIGHT = rows;
        this.WIDTH = cols;
        this.GAMEMODE = gamemode;

        this.TABLE = new ArrayList<>(rows);
        IntStream.range(0, rows).forEach(i -> {
            List<Character> row = new ArrayList<>(cols);
            IntStream.range(0, cols).forEach(j -> row.add(notMarked));
            this.TABLE.add(row);
        });

        itsRedTurn = true;
    }

    public String showBoard() {
        StringBuilder board = new StringBuilder();

        this.TABLE.stream().forEach(row -> {
            board.append("|");
            row.stream().forEach(cell -> board.append(cell));
            board.append("|\n");
        });

        return board.toString();
    }

    public boolean finished() {
        return GAMEMODE.fourInARow(this) || boardIsFull();
    }

    public boolean boardIsFull() {
        return this.TABLE.stream()
                .flatMap(List::stream)
                .noneMatch(cell -> cell == notMarked);
    }

    public void playBlueAt(int column) {
        if(!itsRedTurn) {
            if (!finished()) {
                int row = checkRowForColumn(column);
                this.TABLE.get(row).set(column, BLUE);
                itsRedTurn = true;
            } else {
                throw new RuntimeException("game finished");
            }
        }else {
            throw new RuntimeException("It's not turn");
        }
    }

    public void playRedAt(int column) {
        if(itsRedTurn) {
            if (!finished()) {
                int row = checkRowForColumn(column);
                this.TABLE.get(row).set(column, RED);
                itsRedTurn = false;
            } else {
                throw new RuntimeException("game finished");
            }
        }else {
            throw new RuntimeException("It's not turn");
        }
    }

    private int checkRowForColumn(int column) {
        int row = IntStream.range(0, this.HEIGHT)
                .filter(i -> this.TABLE.get(i).get(column) == notMarked)
                .reduce((first, second) -> second)
                .orElseThrow(() -> new RuntimeException(columnFull));

        return row;
    }

    boolean fourInADiagonalRow() {
        return IntStream.range(0, this.HEIGHT - 3)
                .anyMatch(i -> IntStream.range(0, this.WIDTH - 3)
                        .anyMatch(j -> isDiagonalMatch(i, j) || isAntiDiagonalMatch(i, j)));
    }

    private boolean isDiagonalMatch(int i, int j) {
        char cell = this.TABLE.get(i).get(j);
        return cell != notMarked &&
                cell == this.TABLE.get(i + 1).get(j + 1) &&
                cell == this.TABLE.get(i + 2).get(j + 2) &&
                cell == this.TABLE.get(i + 3).get(j + 3);
    }

    private boolean isAntiDiagonalMatch(int i, int j) {
        char cell = this.TABLE.get(i).get(j + 3);
        return cell != notMarked &&
                cell == this.TABLE.get(i + 1).get(j + 2) &&
                cell == this.TABLE.get(i + 2).get(j + 1) &&
                cell == this.TABLE.get(i + 3).get(j);
    }


    boolean fourInAVerticalRow() {
        return IntStream.range(0, this.HEIGHT - 3)
                .anyMatch(i -> IntStream.range(0, this.WIDTH)
                        .anyMatch(j -> isVerticalMatch(i, j)));
    }

    private boolean isVerticalMatch(int i, int j) {
        char cell = this.TABLE.get(i).get(j);
        return cell != notMarked &&
                cell == this.TABLE.get(i + 1).get(j) &&
                cell == this.TABLE.get(i + 2).get(j) &&
                cell == this.TABLE.get(i + 3).get(j);
    }

    boolean fourInAHorizontalRow() {
        return IntStream.range(0, this.HEIGHT)
                .anyMatch(i -> IntStream.range(0, this.WIDTH - 3)
                        .anyMatch(j -> isHorizontalMatch(i, j)));
    }

    private boolean isHorizontalMatch(int i, int j) {
        char cell = this.TABLE.get(i).get(j);
        return cell != notMarked &&
                cell == this.TABLE.get(i).get(j + 1) &&
                cell == this.TABLE.get(i).get(j + 2) &&
                cell == this.TABLE.get(i).get(j + 3);
    }

    public boolean isRedTurn() {
        return itsRedTurn;
    }

    public boolean redWon() {
        return GAMEMODE.fourInARow(this) && !itsRedTurn;
    }
    public boolean blueWon() {
        return GAMEMODE.fourInARow(this) && itsRedTurn;
    }
}