package model;

import java.awt.Point;
import static org.junit.Assert.*;

import org.junit.Test;

import model.exception.IllegalPositionGameException;

public class RobotTest {
    @Test
    public void testForward() {
        Cell[][] cells = new Cell[3][3];
        java.util.Arrays.fill(cells[0],null);
        java.util.Arrays.fill(cells[1],null);
        java.util.Arrays.fill(cells[2],null);
        Map map = new Map(cells, new Point(1, 1));

        try {
            map.robot.forward();
            assertEquals(2, map.robot.getI());
            assertEquals(1, map.robot.getJ());
        } catch (IllegalPositionGameException e) {
            e.printStackTrace();
            fail("fallito");
        }
    }

    @Test
    public void testGetCellFacing() {
        Robot sas = new Robot(null, new Point(1, 1));
        if(sas.getCellFacingI() != 2 || sas.getCellFacingJ() != 1)
            fail("fallito");    
    }

    @Test
    public void testPOS() {
        Robot sas = new Robot(null, new Point(1, 1));
        if(sas.getI() != 1 || sas.getJ() != 1)
            fail("fallito");      
    }
}
