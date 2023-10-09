package nemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Submarine_Test {

    @Test void isEverythingSetUp() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.getCoordX(), 0);
        assertEquals(nemo.getCoordY(), 0);
        assertEquals(nemo.getDepth(), 0);
        assertEquals(nemo.getDirection(), "north");
        assertFalse(nemo.capsuleReleased);
    }

    @Test void doNothingWithAnEmptyCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand(""), nemo);
    }

    @Test void descendWithASingleCharacterCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand("d").getDepth(), -1);
    }

    @Test void undescendWithASingleCharacterCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand("d").sendCommand("u").getDepth(), 0);
    }

    @Test void moveLeftWithASingleCharacterCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand("l").getDirection(), "west");
    }

    @Test void moveRightWithASingleCharacterCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand("r").getDirection(), "east");
    }

    @Test void moveForwardWithASingleCharacterCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand("f").getCoordY(), 1);
    }

    @Test void moveAndDescendWithAStringCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand("df").getCoordY(), 1);
        assertEquals(nemo.getDepth(), -1);
    }

    @Test void moveToASpecificPlaceUsingEverythingWithAStringCommand() {
        Submarine nemo = new Submarine();
        assertEquals(nemo.sendCommand("dfrudfflf").getCoordX(), 2);
        assertEquals(nemo.getCoordY(), 2);
        assertEquals(nemo.getDepth(), -1);
    }

    @Test void releaseCapsuleAtACorrectDepth() {
        Submarine nemo = new Submarine();
        assertTrue(nemo.sendCommand("d").sendCommand("m").capsuleReleased);
    }

    @Test void releaseCapsuleAtAnIncorrectDepth() {
        Submarine nemo = new Submarine();
        nemo.sendCommand("d").sendCommand("d");
        assertThrows(Error.class, () -> nemo.sendCommand("m"));
        assertFalse(nemo.capsuleReleased);
    }

}
