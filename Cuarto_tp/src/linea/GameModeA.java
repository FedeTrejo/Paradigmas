package linea;

public class GameModeA extends GameMode{
    public boolean fourInARow(Conecta4 game) {
        return game.fourInAHorizontalRow() || game.fourInAVerticalRow();
    }
}
