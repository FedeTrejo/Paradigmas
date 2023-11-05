package linea;
public class GameModeB extends GameMode{
    public boolean fourInARow(Conecta4 game){
        return game.fourInADiagonalRow();
    }
}
