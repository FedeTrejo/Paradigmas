package linea;

import org.junit.Test;

import static org.junit.Assert.*;

public class Conecta4Test {

    @Test
    public void testGameIsShownCorrectly() {
        Conecta4 game1 = new Conecta4(4, 4);

        game1.playRedAt(1);
        game1.playBlueAt(2);

        System.out.println( game1.showBoard() );

    }

    @Test public void testRedStartsTheGame(){
        Conecta4 game1 = new Conecta4(4, 4);
        assertTrue(game1.isRedTurn());
    }

    @Test public void testOnceRedPlaysIfRedPlaysAgainShouldFail(){
        Conecta4 game1 = new Conecta4(4, 4);

        game1.playRedAt(1);
        assertThrows(RuntimeException.class, () -> game1.playRedAt(1));
    }

    @Test public void testGameFinishesWhenFourInAHorizontalRow(){
        Conecta4 game1 = new Conecta4(4, 4);

        game1.playRedAt(0);
        game1.playBlueAt(0);
        game1.playRedAt(1);
        game1.playBlueAt(1);
        game1.playRedAt(2);
        game1.playBlueAt(2);
        game1.playRedAt(3);

        System.out.println( game1.showBoard() );
        assertTrue(game1.finished());
        assertTrue(game1.fourInARow());
    }

    @Test public void testGameFinishesWhenFourInAVerticalRow(){
        Conecta4 game1 = new Conecta4(4, 4);

        game1.playRedAt(0);
        game1.playBlueAt(1);
        game1.playRedAt(0);
        game1.playBlueAt(1);
        game1.playRedAt(0);
        game1.playBlueAt(1);
        game1.playRedAt(0);

        System.out.println( game1.showBoard() );
        assertTrue(game1.finished());
        assertTrue(game1.fourInARow());
    }

    @Test public void testGameFinishesWhenFourInADiagonalRow(){
        Conecta4 game1 = new Conecta4(4, 4);

        game1.playRedAt(0);
        game1.playBlueAt(1);
        game1.playRedAt(1);
        game1.playBlueAt(2);
        game1.playRedAt(2);
        game1.playBlueAt(3);
        game1.playRedAt(2);
        game1.playBlueAt(3);
        game1.playRedAt(0);
        game1.playBlueAt(3);
        game1.playRedAt(3);

        System.out.println( game1.showBoard() );
        assertTrue(game1.finished());
        assertTrue(game1.fourInARow());
    }

    @Test
    public void testGameFinishesAtTieIfNobodyWinsAndTheBoardIsFull() {
        Conecta4 game = new Conecta4(4, 4);

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
        assertFalse(game.fourInARow());
        assertTrue(game.boardIsFull());
    }

    @Test public void testOnceTheGameIsFinishedPlayingAgainShouldFail(){
        Conecta4 game1 = new Conecta4(4, 4);

        game1.playRedAt(0);
        game1.playBlueAt(1);
        game1.playRedAt(1);
        game1.playBlueAt(2);
        game1.playRedAt(2);
        game1.playBlueAt(3);
        game1.playRedAt(2);
        game1.playBlueAt(3);
        game1.playRedAt(0);
        game1.playBlueAt(3);
        game1.playRedAt(3);

        System.out.println( game1.showBoard() );
        assertTrue(game1.finished());
        assertThrows(RuntimeException.class, () -> game1.playBlueAt(0));
    }
}