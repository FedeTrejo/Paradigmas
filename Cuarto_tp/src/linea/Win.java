package linea;

public class Win extends GameStatus {
    private final String message;
    public Win(String message) {
        this.message = message;
    }

    public void playBlueDiskAtColumn(Conecta4 game, int column) {
        throw new RuntimeException(gameOverMessage + " " + message);
    }

    public void playRedDiskAtColumn(Conecta4 game, int column) {
        throw new RuntimeException(GameStatus.gameOverMessage + " " + message);
    }

    public GameStatus switchTurn() {
        throw new RuntimeException(GameStatus.gameOverMessage + " " + message);
    }

    public GameStatus winGame() {
        throw new RuntimeException(GameStatus.gameOverMessage + " " + message);
    }

    public GameStatus drawGame() {
        throw new RuntimeException(GameStatus.gameOverMessage + " " + message);
    }

    public String displayGameState() {
        return message;
    }
}
