package model.element;

import model.*;
import model.exception.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class OvenSinkWasherTest {

    @Test
    public void testOvenInteract() {
        Oven oven = new Oven(1, 1, 3);

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
        Sink sink = new Sink(1, 1, 4);

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
        Washer washer = new Washer(1, 1, 5);

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
        Map map = new Map(emptyMap, new Eventable[0]);
        Oven oven = new Oven(1, 1, 3);

        try {
            oven.setState(1);//set broken
            Cell generatedCell = oven.Event(map);
            assertNotNull(generatedCell);
            assertTrue(generatedCell instanceof Fire);
        } catch (CantGenerateEventException e) {
            fail("Unexpected CantGenerateEventException: " + e.getMessage());
        }
    }

    @Test
    public void testSinkEvent() {
        Cell[][] emptyMap = new Cell[3][3];
        Map map = new Map(emptyMap, new Eventable[0]);
        Sink sink = new Sink(1, 1, 4);

        try {
            sink.setState(1);//set broken
            Cell generatedCell = sink.Event(map);
            assertNotNull(generatedCell);
            assertTrue(generatedCell instanceof Water);
        } catch (CantGenerateEventException e) {
            fail("Unexpected CantGenerateEventException: " + e.getMessage());
        }
    }

    @Test
    public void testWasherEvent() {
        Cell[][] emptyMap = new Cell[3][3];
        Map map = new Map(emptyMap, new Eventable[0]);
        Washer washer = new Washer(1, 1, 5);

        try {
            washer.setState(1);//set broken
            Cell generatedCell = washer.Event(map);
            assertNotNull(generatedCell);
            assertTrue(generatedCell instanceof Water);
        } catch (CantGenerateEventException e) {
            fail("Unexpected CantGenerateEventException: " + e.getMessage());
        }
    }
}
