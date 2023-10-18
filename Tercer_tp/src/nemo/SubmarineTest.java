package nemo;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubmarineTest {

    @Test
    public void testSubmarinePosition() {
        SubmarinePosition position = new SubmarinePosition(0, 0);
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void testSubmarineOrientation() {
        North north = new North();
        assertTrue(north.rotateLeft() instanceof West);
        assertTrue(north.rotateRight() instanceof East);
    }

    @Test public void testSubmarineDepth() {
        SurfaceLevel surface = new SurfaceLevel();
        assertEquals(0, surface.submarineDepths());

        SafeDepthLevel safe = new SafeDepthLevel();
        assertEquals(1, safe.submarineDepths());

        UnsafeDepthLevel unsafe = new UnsafeDepthLevel();
        assertEquals(2, unsafe.submarineDepths());
    }

    @Test public void testSubmarineForwardCommand(){
        Command forward = new Command("f", sub -> {
        });
        assertEquals("f", forward.getName());
    }
    @Test public void testSubmarineLeftCommand(){
        Command left = new Command("l", sub -> {
        });
        assertEquals("l", left.getName());
    }
    @Test public void testSubmarineRightCommand(){
        Command right = new Command("r", sub -> {
        });
        assertEquals("r", right.getName());
    }
    @Test public void testSubmarineUpCommand(){
        Command up = new Command("u", sub -> {
        });
        assertEquals("u", up.getName());
    }
    @Test public void testSubmarineDownCommand(){
        Command down = new Command("d", sub -> {
        });
        assertEquals("d", down.getName());
    }
    @Test public void testSubmarineShootCommand(){
        Command shoot = new Command("m", sub -> {
        });
        assertEquals("m", shoot.getName());
    }

    @Test public void testIsEverythingSetUp(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(0, nemo.getSubmarinePosition().getY());
        assertEquals(0, nemo.getSubmarineDepth().submarineDepths());
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
    }

    @Test public void testEmptyCommand(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.runCommands("");
    }

    @Test public void testDescendCommandAtSurface(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.runCommands("d");
        assertEquals(1, nemo.getSubmarineDepth().submarineDepths());
    }

    @Test public void testUndescendCommandAtSurface(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.runCommands("u");
        assertEquals(0, nemo.getSubmarineDepth().submarineDepths());
    }
    @Test public void testMoveLeftCommandAtSurface(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.runCommands("l");
        assertTrue(nemo.getSubmarineOrientation() instanceof West);
    }
    @Test public void testMoveRightCommandAtSurface(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.runCommands("r");
        assertTrue(nemo.getSubmarineOrientation() instanceof East);
    }
    @Test public void testMoveForwardCommandAtSurface(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.runCommands("f");
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(1, nemo.getSubmarinePosition().getY());
    }
    @Test public void testUseAllCommandsAtSurface(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.runCommands("flrudm");
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(1, nemo.getSubmarinePosition().getY());
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
        assertEquals(1, nemo.getSubmarineDepth().submarineDepths());

    }
    @Test public void testDescendCommandUnderWater(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
        nemo.getSubmarineDepth().descend(nemo);
        nemo.runCommands("d");
        assertEquals(2, nemo.getSubmarineDepth().submarineDepths());
    }
    @Test public void testUndescendCommandUnderWater(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.getSubmarineDepth().descend(nemo);
        nemo.runCommands("u");
        assertEquals(0, nemo.getSubmarineDepth().submarineDepths());
    }
    @Test public void testMoveLeftCommandUnderWater(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.getSubmarineDepth().descend(nemo);
        nemo.runCommands("l");
        assertTrue(nemo.getSubmarineOrientation() instanceof West);
    }
    @Test public void testMoveRightCommandUnderWater(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.getSubmarineDepth().descend(nemo);
        nemo.runCommands("r");
        assertTrue(nemo.getSubmarineOrientation() instanceof East);
    }
    @Test public void testMoveForwardCommandUnderWater(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.getSubmarineDepth().descend(nemo);
        nemo.runCommands("f");
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(1, nemo.getSubmarinePosition().getY());
    }
    @Test public void testUseAllCommandsUnderWater(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.getSubmarineDepth().descend(nemo);
        nemo.runCommands("flrudm");
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(1, nemo.getSubmarinePosition().getY());
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
        assertEquals(1, nemo.getSubmarineDepth().submarineDepths());
    }

    @Test public void testShootCapsuleAtSurface(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.runCommands("m");
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(0, nemo.getSubmarinePosition().getY());
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
        assertEquals(0, nemo.getSubmarineDepth().submarineDepths());
    }

    @Test public void testShootCapsuleAtSafeDepthLevel(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.getSubmarineDepth().descend(nemo);
        nemo.runCommands("m");
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(0, nemo.getSubmarinePosition().getY());
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
        assertEquals(1, nemo.getSubmarineDepth().submarineDepths());
    }

    @Test public void testShootCapsuleAtUnsafeDepthLevel(){
        Submarine nemo = new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );

        nemo.getSubmarineDepth().descend(nemo);
        nemo.getSubmarineDepth().descend(nemo);
        assertThrows(Exception.class, () -> {
            nemo.runCommands("m");
        });
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(0, nemo.getSubmarinePosition().getY());
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
        assertEquals(2, nemo.getSubmarineDepth().submarineDepths());
    }
}
