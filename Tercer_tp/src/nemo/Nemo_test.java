package nemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Nemo_Test {
    @Test void isEverythingSetUp() {
        assertEquals(new Nemo().getCoordX(), 0);
        assertEquals(new Nemo().getCoordY(), 0);
        assertEquals(new Nemo().depth, 0);
        assertEquals(new Nemo().direction, "north");
    }

    @Test void doNothingWithAnEmptyCommand() {
        Nemo nemo = new Nemo();
        assertEquals(nemo.sendCommand(""), nemo);
    }
    @Test void descendWithACommand(){
        Nemo nemo = new Nemo();
        assertEquals(nemo.sendCommand("d").getDepth(), -1);
    }

}
