package linea;

public class Draw extends GameStatus {

    public static final String tieGameMessage = "Draw!";


    public void playBlueDiskAtColumn(Conecta4 game, int column) {
        throw new RuntimeException(gameOverMessage + " " + tieGameMessage);
    }

    public void playRedDiskAtColumn(Conecta4 game, int column) {
        throw new RuntimeException(gameOverMessage + " " + tieGameMessage);
    }

    public GameStatus switchTurn() {
        throw new RuntimeException(gameOverMessage + " " + tieGameMessage);
    }

    public GameStatus winGame() {
        throw new RuntimeException(gameOverMessage + " " + tieGameMessage);
    }

    public GameStatus drawGame() {
        throw new RuntimeException(gameOverMessage + " " + tieGameMessage);
    }

    public String displayGameState() {
        return tieGameMessage;
    }
}
