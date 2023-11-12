package linea;

public class RedsPlay extends GameStatus {


    public static final String notBlueTurn = "Not blue turn!";

    public void playBlueDiskAtColumn(Conecta4 game, int column) {
        throw new RuntimeException(notBlueTurn);
    }

    public void playRedDiskAtColumn(Conecta4 game, int column) {
        game.placeDiskAtColumn(column, 'r');
    }

    public GameStatus switchTurn() {
        return new BluesPlay();
    }

    public GameStatus winGame() {
        return new Win("Red won!");
    }

    public GameStatus drawGame() {
        return new Draw();
    }

    public String displayGameState() {
        return "Red player's turn";
    }
}
