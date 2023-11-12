package linea;

public abstract class GameStatus {

    public static final String gameOverMessage = "Game Over!";

    public abstract String displayGameState();

    public abstract void playBlueDiskAtColumn(Conecta4 game, int column);

    public abstract void playRedDiskAtColumn(Conecta4 game, int column);

    public abstract GameStatus winGame();

    public abstract GameStatus drawGame();

    public abstract GameStatus switchTurn();
}
