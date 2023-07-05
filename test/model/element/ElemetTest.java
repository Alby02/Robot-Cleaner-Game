package model.element;

import model.*;
import model.exception.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Point;

public class ElemetTest {

    @Test
    public void testOvenInteract() {
        Oven oven = new Oven(1, 1, 1);

        try {
            oven.interact();
            int state = oven.getState();
            assertEquals(0, state);//verifica lo stato se è "base"
        } catch (IllegaInteractnGameException e) {
            fail("Unexpected IllegaInteractnGameException: " + e.getMessage());
        }
    }

    @Test
    public void testSinkInteract() {
        Sink sink = new Sink(1, 1, 1);

        try {
            sink.interact();
            int state = sink.getState();
            assertEquals(0, state);//verifica lo stato se è "base"
        } catch (IllegaInteractnGameException e) {
            fail("Unexpected IllegaInteractnGameException: " + e.getMessage());
        }
    }

    @Test
    public void testWasherInteract() {
        Washer washer = new Washer(1, 1, 1);

        try {
            washer.interact();
            int state = washer.getState();
            assertEquals(0, state);//verifica lo stato se è "base"
        } catch (IllegaInteractnGameException e) {
            fail("Unexpected IllegaInteractnGameException: " + e.getMessage());
        }
    }

    @Test
    public void testOvenEvent() {
        Cell[][] emptyMap = new Cell[3][3];
        java.util.Arrays.fill(emptyMap[0],null);
        java.util.Arrays.fill(emptyMap[1],null);
        java.util.Arrays.fill(emptyMap[2],null);
        Oven oven = new Oven(1, 1, 1);
        emptyMap[1][1] = oven; 
        Map map = new Map(emptyMap, new Point(1, 1));
        
        map.event();
        assertTrue(emptyMap[0][1] instanceof Fire || emptyMap[1][0] instanceof Fire || emptyMap[2][1] instanceof Fire || emptyMap[1][2] instanceof Fire);
    }

    @Test
    public void testSinkEvent() {
        Cell[][] emptyMap = new Cell[3][3];
        java.util.Arrays.fill(emptyMap[0],null);
        java.util.Arrays.fill(emptyMap[1],null);
        java.util.Arrays.fill(emptyMap[2],null);
        Sink sink = new Sink(1, 1, 1);
        emptyMap[1][1] = sink; 
        Map map = new Map(emptyMap, new Point(1, 1));
        
        map.event();
        assertTrue(emptyMap[0][1] instanceof Water || emptyMap[1][0] instanceof Water || emptyMap[2][1] instanceof Water || emptyMap[1][2] instanceof Water);
    }

    @Test
    public void testWasherEvent() {
        Cell[][] emptyMap = new Cell[3][3];
        java.util.Arrays.fill(emptyMap[0],null);
        java.util.Arrays.fill(emptyMap[1],null);
        java.util.Arrays.fill(emptyMap[2],null);
        Washer washer = new Washer(1, 1, 1);
        emptyMap[1][1] = washer; 
        Map map = new Map(emptyMap, new Point(1, 1));
        
        map.event();
        assertTrue(emptyMap[0][1] instanceof Water || emptyMap[1][0] instanceof Water || emptyMap[2][1] instanceof Water || emptyMap[1][2] instanceof Water);
    }

    @Test
    public void testCatEvent() {
        Cell[][] emptyMap = new Cell[3][3];
        java.util.Arrays.fill(emptyMap[0],null);
        java.util.Arrays.fill(emptyMap[1],null);
        java.util.Arrays.fill(emptyMap[2],null);
        Cat cat = new Cat(1, 1);
        emptyMap[1][1] = cat; 
        Map map = new Map(emptyMap, new Point(1, 1));

        map.event();
        assertTrue(emptyMap[0][1] instanceof Cat || emptyMap[1][0] instanceof Cat || emptyMap[2][1] instanceof Cat || emptyMap[1][2] instanceof Cat);
    }
}
