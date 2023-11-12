package linea;

public class BluesPlay extends GameStatus {

    public static final String notRedTurn = "Not red turn!";

    public void playBlueDiskAtColumn(Conecta4 game, int column) {
        game.placeDiskAtColumn(column, 'b');
    }

    public void playRedDiskAtColumn(Conecta4 game, int column) {
        throw new RuntimeException(notRedTurn);
    }

    public GameStatus switchTurn() {
        return new RedsPlay();
    }

    public GameStatus winGame() {
        return new Win("Blue won!");
    }

    public GameStatus drawGame() {
        return new Draw();
    }

    public String displayGameState() {
        return "Blue player's turn";
    }
}
