package linea;

public class BluePlayer extends Player {
    @Override
    public void playAt(int column, Conecta4 game) {
        int row = game.checkRowForColumn(column);
        game.TABLE.get(row).set(column, Conecta4.BLUE);
        game.itsRedTurn = true;
    }
}
