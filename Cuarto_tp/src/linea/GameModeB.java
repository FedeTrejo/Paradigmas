package linea;

public class GameModeB extends GameMode {


    public GameModeB(){
        this.gameModeName = 'B';
    }

    boolean validateWinCondition(Conecta4 table, int column) {
        return table.isDiagonalMatch(column);
    }





}
