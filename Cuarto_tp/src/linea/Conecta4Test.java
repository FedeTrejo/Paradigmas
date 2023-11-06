package linea;

import org.junit.Test;
import static org.junit.Assert.*;

public class Conecta4Test {
    @Test
    public void testCannotInitializeBoardSmallerThan4x4() {
        assertThrows(RuntimeException.class, () -> new Conecta4(3, 3, new GameModeC()));
    }

    @Test
    public void testGameIsShownCorrectly() {
            Conecta4 game = new Conecta4(4, 4, new GameModeC());
            game.playAt(1, new RedPlayer());

            game.playAt(2, new BluePlayer());
            String expectedBoard =
                    "|    |\n" +
                            "|    |\n" +
                            "|    |\n" +
                            "| rb |\n";

            assertEquals(expectedBoard, game.showBoard());
        }

    @Test public void testRedStartsTheGame(){
        assertTrue(new Conecta4(4, 4, new GameModeC()).isRedTurn());
    }
//TODO: CHECK TURN WITHOUT IF
//    @Test public void testOnceRedPlaysIfRedPlaysAgainShouldFail(){
//        Conecta4 game = new Conecta4(4, 4, new GameModeC());
//        Player redPlayer = new RedPlayer();
//        game.playAt(1, redPlayer);
//        assertThrows(RuntimeException.class, () -> game.playAt(1, redPlayer));
//    }
    @Test public void testPuttingAPieceInAnInvalidColumnShouldFail(){
        assertThrows(RuntimeException.class, () -> new Conecta4(4, 4, new GameModeC()).playAt(6, new RedPlayer()));
}

//    @Test public void testBluePlayngInRedTurnShouldFail(){
//        Conecta4 game = new Conecta4(4, 4, new GameModeC());
//        assertThrows(RuntimeException.class, () -> game.playAt(1, new BluePlayer()));
//    }

    @Test public void testGameFinishesWhenFourInAHorizontalRowAndGamemodeIsA(){
        Conecta4 game = new Conecta4(4, 4, new GameModeA());
        game.playAt(0, new RedPlayer());
        game.playAt(0, new BluePlayer());
        game.playAt(1, new RedPlayer());
        game.playAt(1, new BluePlayer());
        game.playAt(2, new RedPlayer());
        game.playAt(2, new BluePlayer());
        game.playAt(3, new RedPlayer());

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
        assertTrue(game.redWon());
    }
    

}