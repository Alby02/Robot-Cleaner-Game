package model;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;

import model.element.Sink;
import model.element.Wall;
import model.element.Water;

public class MapTest {
    
    @Test
    public void testGetIDCasella() {
        Cell[][] cells = new Cell[3][3];
        java.util.Arrays.fill(cells[0],null);
        java.util.Arrays.fill(cells[1],null);
        java.util.Arrays.fill(cells[2],null);
        cells[0][0] = new Wall(0, 0);
        cells[2][2] = new Sink(2, 2, 1);

        Map map = new Map(cells, new Point(1, 1));

        assertEquals('w', map.getIDCasella(0, 0));
        assertEquals('S', map.getIDCasella(2, 2));
    }

    @Test
    public void testGetISize() {
        Cell[][] cells1 = new Cell[3][3];
        Cell[][] cells2 = new Cell[2][2];

        Map map1 = new Map(cells1, new Point(0, 0));
        Map map2 = new Map(cells2, new Point(0, 0));

        assertEquals(3, map1.getISize());
        assertEquals(2, map2.getISize());
    }

    @Test
    public void testGetJSize() {
        Cell[][] cells1 = new Cell[3][3];
        Cell[][] cells2 = new Cell[2][2];

        Map map1 = new Map(cells1, new Point(0, 0));
        Map map2 = new Map(cells2, new Point(0, 0));

        assertEquals(3, map1.getJSize());
        assertEquals(2, map2.getJSize());
    }

    @Test
    public void testGetStateCasella() {
        Cell[][] cells = new Cell[3][3];
        java.util.Arrays.fill(cells[0],null);
        java.util.Arrays.fill(cells[1],null);
        java.util.Arrays.fill(cells[2],null);
        cells[1][1] = new Sink(1, 1, 0);

        Map map = new Map(cells, new Point(1, 1));

        assertEquals(0, map.getStateCasella(1, 1));

        java.util.Arrays.fill(cells[0],null);
        java.util.Arrays.fill(cells[1],null);
        java.util.Arrays.fill(cells[2],null);
        cells[1][1] = new Water(1, 1);

        map = new Map(cells, new Point(1, 1));

        assertEquals(-1, map.getStateCasella(1, 1));
    }

    @Test
    public void testIsCasellaEmpty() {
        Cell[][] cells = new Cell[3][3];
        java.util.Arrays.fill(cells[0],null);
        java.util.Arrays.fill(cells[1],null);
        java.util.Arrays.fill(cells[2],null);
        cells[0][0] = new Wall(0, 0);

        Map map = new Map(cells, new Point(1, 1));

        assertTrue(map.isCasellaEmpty(2, 2));
        assertFalse(map.isCasellaEmpty(0, 0));
    }
}
