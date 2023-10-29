package linea;

import org.junit.Test;

import static org.junit.Assert.*;

public class Conecta4Test {

    @Test
    public void ShowCorrectly() {
        Conecta4 game1 = new Conecta4(4, 4);



        game1.playRedAt(1);

        game1.playBlueAt(2);

        System.out.println( game1.showBoard() );

    }

    @Test
    public void GameFinalizes() {
        Conecta4 game1 = new Conecta4(4, 4);

        game1.playRedAt(0);

        game1.playRedAt(1);
        game1.playBlueAt(1);

        game1.playRedAt(2);
        game1.playBlueAt(2);

        game1.playRedAt(3);
        game1.playBlueAt(3);


        System.out.println( game1.showBoard() );
        assertTrue(game1.finished());

    }




    }