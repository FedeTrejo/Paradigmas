package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Conecta4 {

    private static final char EMPTY = ' ';


    public static final String outOfBounds = "Out of bounds!";

    public static final String columnFull = "Illegal movement, column is full";
    public static final String MinBoardSize = "Board size must be at least 4x4";

    private final GameMode gameMode;

    private GameStatus gameStatus = new RedsPlay();

    private final ArrayList<ArrayList<Character>> table = new ArrayList<>();

    private final int HEIGHT;
    private final int WIDTH;

    public Conecta4(int rows, int cols, char gameMode) {
        if (rows < 4 || cols < 4) throw new RuntimeException(MinBoardSize);
        this.HEIGHT = rows;
        this.WIDTH = cols;
        this.gameMode = GameMode.getGameModeByChar(gameMode);

        IntStream.range(0, WIDTH)
                .forEach(i -> table.add(new ArrayList<>()));
    }

    public void playBlueAt(int column) {
        gameStatus.playBlueDiskAtColumn(this, column - 1);
    }

    public void playRedAt(int column) {
        gameStatus.playRedDiskAtColumn(this, column - 1);
    }

    public void placeDiskAtColumn(int column, char disk) {

        if (column < 0 || column >= this.WIDTH) {
            throw new RuntimeException(outOfBounds);
        }

        if (this.table.get(column).size() == this.HEIGHT) {
            throw new RuntimeException(columnFull);
        }

        this.table.get(column).add(disk);
        updateGameStateAfterMove(column);
    }

    private void updateGameStateAfterMove(int column) {
        if (gameMode.validateWinCondition(this, column)) {
            gameStatus = gameStatus.winGame();
        } else if (boardIsFull()) {
            gameStatus = gameStatus.drawGame();
        } else {
            gameStatus = gameStatus.switchTurn();
        }
    }

    public String showBoard() {

        StringBuilder decoratedBoard = new StringBuilder();

        decoratedBoard.append("+");
        IntStream.range(0, WIDTH)
                .forEach(i -> decoratedBoard.append("-"));
        decoratedBoard.append("+\n");

        IntStream.range(0, HEIGHT)
                .forEach(i -> {
                    decoratedBoard.append("/");
                    IntStream.range(0, WIDTH)
                            .forEach(j -> decoratedBoard.append(getDiskAtPosition(i, j)));
                    decoratedBoard.append("/\n");
                });

        decoratedBoard.append("+");
        IntStream.range(0, WIDTH)
                .forEach(i -> decoratedBoard.append("-"));
        decoratedBoard.append("+\n");

        // Game status
        decoratedBoard.append("Game Status: ").append(gameStatus.displayGameState());

        return decoratedBoard.toString();
    }



    public boolean boardIsFull() {
        return IntStream.range(0, WIDTH)
                .noneMatch(col -> getDiskAtPosition(0, col) == EMPTY);
    }


    public boolean finished() {
        return gameStatus instanceof Draw || gameStatus instanceof Win;
    }

    private char getDiskAtPosition(int row, int col) {

        int rowIndex = HEIGHT - 1 - row;

        if (col >= 0 && col < WIDTH) {

            ArrayList<Character> column = table.get(col);

            if (rowIndex >= 0 && rowIndex < column.size()) {

                return column.get(rowIndex);

            }

        }

        return EMPTY;

    }

    protected boolean isVerticalMatch(int xAxis) {

        int yAxis = HEIGHT - this.table.get(xAxis).size();

        char disk = this.getDiskAtPosition(yAxis, xAxis);

        return IntStream.range(1, 4).mapToObj(row -> this.getDiskAtPosition(row + yAxis, xAxis)).allMatch(s -> s == disk);
    }

    protected boolean isHorizontalMatch(int xAxis) {

        int yAxis = HEIGHT - this.table.get(xAxis).size();

        return checkWinningLineFromCoord(xAxis,yAxis,1,0);

    }

    protected boolean isDiagonalMatch(int x) {

        int y = HEIGHT - this.table.get(x).size();

        return  checkWinningLineFromCoord(x,y,-1,1) || checkWinningLineFromCoord(x,y,-1,-1); // anda mal diagonal inversa
    }

    private boolean checkWinningLineFromCoord(int xAxis, int yAxis, int stepX, int stepY){

        char disk = this.getDiskAtPosition(yAxis, xAxis);

        return IntStream.range(0,4)
                .mapToObj(index -> IntStream.range(0,4)
                                            .mapToObj(delta -> this.getDiskAtPosition(yAxis + (delta - index)*stepY,xAxis + (delta - index)*stepX))
                                            .allMatch(s -> s == disk ))
                .anyMatch(s -> s);
    }


}