package nemo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SubmarineTest {

    @Test
    public void testSubmarinePosition() {
        SubmarinePosition position = new SubmarinePosition(10, 20);
        assertEquals(10, position.x());
        assertEquals(20, position.y());
    }

    @Test
    public void testSubmarineOrientation() {
        NorthSubmarineOrientation north = new NorthSubmarineOrientation();
        assertTrue(north.turnLeft() instanceof WestSubmarineOrientation);
        assertTrue(north.turnRight() instanceof EastSubmarineOrientation);
    }

    @Test
    public void testSubmarineDepth() {
        TopDepthLevel top = new TopDepthLevel();
        assertEquals(0, top.submarineDepths());

        SafeDepthLevel safe = (SafeDepthLevel) top.descend();
        assertEquals(1, safe.submarineDepths());
    }

    @Test
    public void testSubmarineCommands() {
        SubmarineCommand forward = new SubmarineCommand("f", sub -> {
        });

        assertEquals("f", forward.name());
    }

    @Test
    public void testSubmarineNemo() {
        ArrayList<SubmarineCommand> commands = new ArrayList<>();

        SubmarineNemo nemo = new SubmarineNemo(new SubmarinePosition(0, 0),
                new NorthSubmarineOrientation(),
                commands);

        nemo.runCommands("ffr");

        assertEquals(0, nemo.getPosition().y());
    }

    @Test
    public void testInitialState() {
        SubmarineNemo nemo = new SubmarineNemo(new SubmarinePosition(0, 0),
                new NorthSubmarineOrientation(),
                new ArrayList<>());

        assertEquals(0, nemo.getPosition().x());
        assertEquals(0, nemo.getPosition().y());
        assertEquals(0, nemo.getDepth().submarineDepths());
        assertTrue(nemo.getOrientation() instanceof NorthSubmarineOrientation);
    }

    @Test
    public void testEmptyCommand() {
        SubmarineNemo nemo = new SubmarineNemo(new SubmarinePosition(0, 0),
                new NorthSubmarineOrientation(),
                new ArrayList<>());

        nemo.runCommands("");

    }

    @Test
    public void testDescendCommand() {
        SubmarineNemo nemo = new SubmarineNemo(new SubmarinePosition(0, 0),
                new NorthSubmarineOrientation(),
                new ArrayList<>());

        nemo.runCommands("d");

        assertEquals(0, nemo.getDepth().submarineDepths());
    }

}
