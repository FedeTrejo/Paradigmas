package linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Conecta4Tests {
    @Test
    public void testCannotInitializeBoardSmallerThan4x4() {
        assertThrows(RuntimeException.class, () -> new Conecta4(3, 3, 'A'));
    }

    @Test
    public void testGameIsShownCorrectly() {
        Conecta4 game = new Conecta4(4, 4, 'C');
        game.playRedAt(1);

        game.playBlueAt(2);
        String expectedBoard =
                """
                        +----+
                        /    /
                        /    /
                        /    /
                        /rb  /
                        +----+
                        Game Status: Red player's turn""";
        assertEquals(expectedBoard, game.showBoard());
    }

    @Test public void testRedStartsTheGame(){
        Conecta4 game = new Conecta4(4, 4, 'C');
        assertThrows(RuntimeException.class,() -> game.playBlueAt(2));
    }

    @Test public void testOnceRedPlaysIfRedPlaysAgainShouldFail(){
        Conecta4 game = new Conecta4(4, 4, 'C');
        game.playRedAt(1);
        assertThrows(RuntimeException.class, () -> game.playRedAt(1));
    }

    @Test public void testPuttingAPieceInAnInvalidColumnShouldFail(){
        assertThrows(RuntimeException.class, () -> new Conecta4(4, 4, 'C').playRedAt(6));
    }

        @Test public void testBluePlayngInRedTurnShouldFail(){
        Conecta4 game = new Conecta4(4, 4, 'C');
        assertThrows(RuntimeException.class, () -> game.playBlueAt(3));
    }

    @Test public void testGameFinishesWhenFourInAHorizontalRowAndGamemodeIsA(){
        Conecta4 game = new Conecta4(4, 4, 'A');
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
    }
    @Test public void testGameFinishesWhenFourInAVerticalRowAndGamemodeIsA(){
        Conecta4 game = new Conecta4(4, 4, 'A');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);

        System.out.println( game.showBoard() );
        assertTrue(game.finished());
    }

    @Test
    public void testGameDoesNotFinishWhenFourInADiagonalRowAndGamemodeIsA(){
        Conecta4 game = new Conecta4(4, 4, 'A');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(4);
        game.playRedAt(4);

        System.out.println( game.showBoard() );
        assertFalse(game.finished());
    }

    @Test
    public void testGameFinishesWhenFourInADiagonalRowAndGamemodeIsB(){
        Conecta4 game = new Conecta4(4, 4, 'B');

        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(4);
        game.playRedAt(4);

        System.out.println(game.showBoard());

        assertTrue(game.finished());
    }

    @Test
    public void testGameDoesNotFinishWhenFourInAHorizontalRowAndGamemodeIsB(){
        Conecta4 game = new Conecta4(4, 4, 'B');

        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);

        System.out.println(game.showBoard());

        assertFalse(game.finished());
    }

    @Test
    public void testGameDoesNotFinishWhenFourInAVerticalRowAndGamemodeIsB(){
        Conecta4 game = new Conecta4(4, 4, 'B');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);

        System.out.println(game.showBoard());
        assertFalse(game.finished());
    }

    @Test
    public void testGameFinishesAtTieIfNobodyWinsAndTheBoardIsFull() {
        Conecta4 game = new Conecta4(4, 4, 'C');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(4);
        game.playBlueAt(3);
        game.playRedAt(4);
        game.playBlueAt(3);

        System.out.println(game.showBoard());
        assertTrue(game.boardIsFull());
        assertTrue(game.finished());

    }

    @Test
    public void testPuttingAPieceInAFullColumnShouldFail(){
        Conecta4 game = new Conecta4(4, 4, 'C');
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(1);

        System.out.println(game.showBoard());
        assertThrows(RuntimeException.class, () -> game.playRedAt(1));
        assertFalse(game.finished());
    }

    @Test
    public void testOnceTheGameIsFinishedPlayingAgainShouldFail(){
        Conecta4 game = new Conecta4(4, 4, 'C');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(4);
        game.playRedAt(4);

        assertThrows(RuntimeException.class, () -> game.playRedAt(1));
    }



}
