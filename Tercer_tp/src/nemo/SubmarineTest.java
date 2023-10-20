package nemo;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

public class SubmarineTest {

    @Test
    public void testSubmarinePosition() {
        assertEquals(0, new SubmarinePosition(0, 0).getX());
        assertEquals(0, new SubmarinePosition(0, 0).getY());
    }

    @Test
    public void testSubmarineNorthOrientation() {
        assertTrue(new North().rotateLeft() instanceof West);
        assertTrue(new North().rotateRight() instanceof East);
    }
    @Test
    public void testSubmarineSouthOrientation() {
        assertTrue(new South().rotateLeft() instanceof East);
        assertTrue(new South().rotateRight() instanceof West);
    }
    @Test
    public void testSubmarineEastOrientation() {
        assertTrue(new East().rotateLeft() instanceof North);
        assertTrue(new East().rotateRight() instanceof South);
    }
    @Test
    public void testSubmarineWestOrientation() {
        assertTrue(new West().rotateLeft() instanceof South);
        assertTrue(new West().rotateRight() instanceof North);
    }

    @Test public void testSubmarineDepths() {
        assertEquals(0, new SurfaceLevel().submarineDepths());
        assertEquals(1, new SafeDepthLevel().submarineDepths());
        assertEquals(2, new UnsafeDepthLevel().submarineDepths());
    }

    @Test public void testSubmarineForwardCommand(){
        assertEquals("f", new Command("f", sub -> {
        }).getName());
    }
    @Test public void testSubmarineLeftCommand(){
        assertEquals("l", new Command("l", sub -> {
        }).getName());
    }
    @Test public void testSubmarineRightCommand(){
        assertEquals("r", new Command("r", sub -> {
        }).getName());
    }
    @Test public void testSubmarineUpCommand(){
        assertEquals("u", new Command("u", sub -> {
        }).getName());
    }
    @Test public void testSubmarineDownCommand(){
        assertEquals("d", new Command("d", sub -> {
        }).getName());
    }
    @Test public void testSubmarineShootCommand(){
        assertEquals("m", new Command("m", sub -> {
        }).getName());
    }

    @Test public void testIsEverythingSetUp(){
        IsEverythingAsStart(Nemo());
    }

    @Test public void testEmptyCommand(){
        IsEverythingAsStart(NemoWithCommand(""));
    }

    @Test public void testDescendCommandAtSurface(){
        assertEquals(1, NemoWithCommand("d").getSubmarineDepth().submarineDepths());
    }

    @Test public void testUndescendCommandAtSurface(){
        assertEquals(0, NemoWithCommand("u").getSubmarineDepth().submarineDepths());
    }
    @Test public void testMoveLeftCommandAtSurface(){
        assertTrue(NemoWithCommand("l").getSubmarineOrientation() instanceof West);
    }
    @Test public void testMoveRightCommandAtSurface(){
        assertTrue(NemoWithCommand("r").getSubmarineOrientation() instanceof East);
    }
    @Test public void testMoveForwardCommandAtSurface(){
        WentForward(NemoWithCommand("f"));
    }

    @Test public void testUseAllCommandsAtSurface(){
        UsedAllCommands(NemoWithCommand("flrudm"));
    }
    @Test public void testDescendCommandUnderWater(){
        assertEquals(2, NemoWithCommand("dd").getSubmarineDepth().submarineDepths());
    }
    @Test public void testUndescendCommandUnderWater(){
        assertEquals(0, NemoWithCommand("du").getSubmarineDepth().submarineDepths());
    }
    @Test public void testMoveLeftCommandUnderWater(){
        assertTrue(NemoWithCommand("ul").getSubmarineOrientation() instanceof West);
    }
    @Test public void testMoveRightCommandUnderWater(){
        assertTrue(NemoWithCommand("ur").getSubmarineOrientation() instanceof East);
    }
    @Test public void testMoveForwardCommandUnderWater(){
        WentForward(NemoWithCommand("uf"));
    }
    @Test public void testUseAllCommandsUnderWater(){
        UsedAllCommands(NemoWithCommand("dflrudm"));
    }

    @Test public void testShootCapsuleAtSurface(){
        IsEverythingAsStart(NemoWithCommand("m"));
    }

    @Test public void testShootCapsuleAtSafeDepthLevel(){
        Submarine nemo = NemoWithCommand("dm");
        ArePositionAndOrientationAtTheirInitialStates(nemo);
        assertEquals(1, nemo.getSubmarineDepth().submarineDepths());
    }

    @Test public void testShootCapsuleAtUnsafeDepthLevel(){
        assertThrowsLike(()-> NemoWithCommand("dd").runCommands("m"), UnsafeDepthLevel.LAUNCH_ERROR_MESSAGE);
    }

    private static Submarine Nemo() {
        return new Submarine(
                new SubmarinePosition(0, 0),
                new North()
        );
    }
    private static Submarine NemoWithCommand(String s) {
        Submarine nemo = Nemo();
        nemo.runCommands(s);
        return nemo;
    }

    private static void WentForward(Submarine nemo) {
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(1, nemo.getSubmarinePosition().getY());
    }

    private static void UsedAllCommands(Submarine nemo) {
        WentForward(nemo);
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
        assertEquals(1, nemo.getSubmarineDepth().submarineDepths());
    }
    private static void ArePositionAndOrientationAtTheirInitialStates(Submarine nemo) {
        assertEquals(0, nemo.getSubmarinePosition().getX());
        assertEquals(0, nemo.getSubmarinePosition().getY());
        assertTrue(nemo.getSubmarineOrientation() instanceof North);
    }
    private static void IsEverythingAsStart(Submarine nemo) {
        ArePositionAndOrientationAtTheirInitialStates(nemo);
        assertEquals(0, nemo.getSubmarineDepth().submarineDepths());
    }
    private void assertThrowsLike(Executable executable, String message ) {
        assertEquals( message,
                assertThrows( Exception.class, executable )
                        .getMessage() );
    }
}
