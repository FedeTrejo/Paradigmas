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
        game.playRedAt(1);
        game.playBlueAt(2);
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

    @Test public void testOnceRedPlaysIfRedPlaysAgainShouldFail(){
        Conecta4 game = new Conecta4(4, 4, new GameModeC());
        game.playRedAt(1);
        assertThrows(RuntimeException.class, () -> game.playRedAt(1));
    }

    @Test public void testPuttingAPieceInAnInvalidColumnShouldFail(){
        assertThrows(RuntimeException.class, () -> new Conecta4(4, 4, new GameModeC()).playRedAt(6));
    }

    @Test public void testBluePlayngInRedTurnShouldFail(){
        Conecta4 game = new Conecta4(4, 4, new GameModeC());
        assertThrows(RuntimeException.class, () -> game.playBlueAt(1));
    }

    @Test public void testGameFinishesWhenFourInAHorizontalRowAndGamemodeIsA(){
        Conecta4 game = new Conecta4(4, 4, new GameModeA());
        game.playRedAt(0);
        game.playBlueAt(0);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
        assertTrue(game.redWon());
    }

    @Test public void testGameFinishesWhenFourInAVerticalRowAndGamemodeIsA(){
        Conecta4 game = new Conecta4(4, 4, new GameModeA());
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(0);

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
        assertTrue(game.redWon());
    }
    @Test public void testGameDoesNotFinishWhenFourInADiagonalRowAndGamemodeIsA(){
        Conecta4 game = new Conecta4(4, 4, new GameModeA());
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(0);
        game.playBlueAt(3);
        game.playRedAt(3);

        System.out.println( game.showBoard() );
        assertFalse(game.finished());
        assertFalse(game.redWon());
    }

    @Test public void testGameFinishesWhenFourInADiagonalRowAndGamemodeIsB(){
        Conecta4 game = new Conecta4(4, 4, new GameModeB());
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(0);
        game.playBlueAt(3);
        game.playRedAt(3);

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
        assertTrue(game.redWon());
    }

    @Test public void testGameDoesNotFinishWhenFourInAHorizontalRowAndGamemodeIsB(){
        Conecta4 game = new Conecta4(4, 4, new GameModeB());
        game.playRedAt(0);
        game.playBlueAt(0);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);

        System.out.println( game.showBoard() );
        assertFalse(game.finished());
        assertFalse(game.redWon());
    }

    @Test public void testGameDoesNotFinishWhenFourInAVerticalRowAndGamemodeIsB(){
        Conecta4 game = new Conecta4(4, 4, new GameModeB());
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(0);

        System.out.println( game.showBoard() );
        assertFalse(game.finished());
        assertFalse(game.redWon());
    }

    @Test
    public void testGameFinishesAtTieIfNobodyWinsAndTheBoardIsFull() {
        Conecta4 game = new Conecta4(4, 4, new GameModeC());
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(0);
        game.playRedAt(1);
        game.playBlueAt(0);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(2);

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
        assertTrue(game.boardIsFull());
        assertFalse(game.redWon());
    }

    @Test public void testPuttingAPieceInAFullColumnShouldFail(){
        Conecta4 game = new Conecta4(4, 4, new GameModeC());
        game.playRedAt(0);
        game.playBlueAt(0);
        game.playRedAt(0);
        game.playBlueAt(0);

        System.out.println( game.showBoard() );
        assertThrows(RuntimeException.class, () -> game.playRedAt(0));
        assertFalse(game.finished());
    }

    @Test public void testOnceTheGameIsFinishedPlayingAgainShouldFail(){
        Conecta4 game = new Conecta4(4, 4, new GameModeC());
        game.playRedAt(0);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(0);
        game.playBlueAt(3);
        game.playRedAt(3);

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
        assertTrue(game.redWon());
        assertThrows(RuntimeException.class, () -> game.playBlueAt(0));
    }

}