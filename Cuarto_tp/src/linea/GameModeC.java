package linea;

public class GameModeC extends GameMode {

    public GameModeC(){
        this.gameModeName = 'C';
    }

    boolean validateWinCondition(Conecta4 table, int column) {
        return  table.isDiagonalMatch(column) || table.isHorizontalMatch(column) || table.isVerticalMatch(column);

    }

}
