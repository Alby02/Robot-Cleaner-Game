package model.element;

import model.*;
import model.exception.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class CatTest {

    @Test
    public void testCatEvent() {
        Cell[][] emptyMap = new Cell[3][3];
        Map map = new Map(emptyMap, new Eventable[0]);

        Cat cat = new Cat(1, 1, 7);
        try {
            Cell updatedCat = cat.Event(map);

            int newI = updatedCat.posI();
            int newJ = updatedCat.posJ();
            assertTrue(Math.abs(newI - 1) <= 1 && Math.abs(newJ - 1) <= 1 && (newI == 1 || newJ == 1));
            assertEquals(updatedCat, map.getStateCasella(newI, newJ));
        } catch (CantGenerateEventException e) {
            fail("Unexpected CantGenerateEventException: " + e.getMessage());
        }
    }
}
