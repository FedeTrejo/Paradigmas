package linea;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class Conecta4Tests {
    private Conecta4 game;
    @BeforeEach
    public void setUp() {
        game = new Conecta4(4, 4, 'C');
    }
    @AfterEach
    public void showBoard() {
        System.out.println(game.showBoard());
    }
    @Test
    public void testShowEmptyBoard() {
        assertEquals( emptyBoard(), game.showBoard() );
    }
    @Test void testLineaGameDoesNotFinishAfterANonWinningPlay() {
        game.playRedAt(1);
        assertFalse(game.finished());
    }
    @Test
    public void testCannotInitializeBoardSmallerThan4x4() {
        assertThrowsLike(() -> new Conecta4(3, 3, 'C'), Conecta4.MinBoardSize);
    }
    @Test
    public void testGameCannotBeInitializedWithInvalidStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new Conecta4(4, 4, 'K'));

    }
    @Test public void testRedStartsTheGame(){
        assertThrowsLike(() -> game.playBlueAt(1), RedsPlay.notBlueTurn);
    }


    @Test
    public void testBluePlaysAfterRedTurn() {

        game.playRedAt(1);
        game.playBlueAt(1);
        assertFalse(game.finished());
    }
    @Test public void testOnceRedPlaysIfRedPlaysAgainShouldFail(){
        game.playRedAt(1);
        assertThrowsLike(() -> game.playRedAt(1), BluesPlay.notRedTurn);
    }
    @Test public void testBluePlayngInRedTurnShouldFail(){
        assertThrowsLike(() -> game.playBlueAt(1), RedsPlay.notBlueTurn);
    }

    @Test public void testPuttingAPieceInAnInvalidColumnShouldFail(){
        assertThrowsLike(() -> game.playRedAt(5), Conecta4.outOfBounds);
    }

    @Test
    public void testPuttingAPieceInAFullColumnShouldFail(){
        playAlternatively(game, new int[]{1,1,1,1});
        assertThrowsLike(() -> game.playRedAt(1), Conecta4.columnFull);
        assertFalse(game.finished());
    }

    @Test public void testGameFinishesWhenFourInAHorizontalRowAndGamemodeIsA(){
        game = Conecta4A();
        playAlternatively(game, new int[]{1, 1,2,2,3,3,4});
        assertTrue(game.finished());
    }

    @Test public void testGameFinishesWhenFourInAVerticalRowAndGamemodeIsA(){
        game = Conecta4A();
        playAlternatively(game, new int[]{1,2,1,2,1,2,1});
        assertTrue(game.finished());
    }

    @Test
    public void testGameDoesNotFinishWhenFourInADiagonalRowAndGamemodeIsA(){
        game = Conecta4A();
        playAlternatively(game, new int[]{1,2,2,3,3,4,3,4,1,4,4});
        assertFalse(game.finished());
    }

    @Test
    public void testGameFinishesWhenFourInADiagonalRowAndGamemodeIsB(){
        game = Conecta4B();
        playAlternatively(game, new int[]{1,2,2,3,3,4,3,4,1,4,4});
        assertTrue(game.finished());
    }

    @Test
    public void testGameDoesNotFinishWhenFourInAHorizontalRowAndGamemodeIsB(){
        game = Conecta4B();
        playAlternatively(game, new int[]{1,1,2,2,3,3,4});
        assertFalse(game.finished());
    }

    @Test
    public void testGameDoesNotFinishWhenFourInAVerticalRowAndGamemodeIsB(){
        game = Conecta4B();
        playAlternatively(game, new int[]{1,2,1,2,1,2,1});
        assertFalse(game.finished());
    }

    @Test
    public void testGameFinishesAtTieIfNobodyWinsAndTheBoardIsFull() {
        playAlternatively(game, new int[]{1,2,1,2,2,1,2,1,3,4,3,4,4,3,4,3});
        assertTrue(game.boardIsFull());
        assertTrue(game.finished());
    }

    @Test
    public void testOnceTheGameIsFinishedPlayingAgainShouldFail(){
        playAlternatively(game, new int[]{1,2,2,3,3,4,3,4,1,4,4});
        assertThrowsLike(() -> game.playRedAt(1), GameStatus.gameOverMessage + " " + "Red won!");
    }

    private String emptyBoard() {
        return
                """
                        +----+
                        /    /
                        /    /
                        /    /
                        /    /
                        +----+
                        Game Status: Red player's turn""";


    }
    private static Conecta4 Conecta4A() {
        return new Conecta4(4, 4, 'A');
    }
    private static Conecta4 Conecta4B() {
        return new Conecta4(4, 4, 'B');
    }
    public void playAlternatively(Conecta4 game, int[] moves) {
        for (int i = 0; i < moves.length; i++) {
            if (i % 2 == 0) {
                game.playRedAt(moves[i]);
            } else {
                game.playBlueAt(moves[i]);
            }
        }
    }
    private void assertThrowsLike(Executable executable, String message ) {
        assertEquals( message,
                assertThrows( Exception.class, executable )
                        .getMessage() );
    }

}
