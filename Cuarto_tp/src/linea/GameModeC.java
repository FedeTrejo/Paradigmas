package linea;

public class GameModeC extends GameMode{
    public boolean fourInARow(Conecta4 game){
        return game.fourInAHorizontalRow() || game.fourInAVerticalRow() || game.fourInADiagonalRow();
    }
}
