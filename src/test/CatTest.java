package test;

import org.junit.jupiter.api.Test;

import model.Eventable;
import model.Cell;
import model.Map;
import model.element.Cat;
import model.exception.CantGenerateEventException;

import static org.junit.jupiter.api.Assertions.*;

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
