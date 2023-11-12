package linea;

public class GameModeA extends GameMode {
    public GameModeA() {
        this.gameModeName = 'A';
    }

    public boolean validateWinCondition(Conecta4 table, int column) {
        return table.isHorizontalMatch(column) || table.isVerticalMatch(column);
    }



}