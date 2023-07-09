package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.element.Cat;
import model.element.Fire;
import model.element.Oven;
import model.element.Sink;
import model.element.Wall;
import model.element.Washer;
import model.element.Water;

import java.io.CharConversionException;

public class CellBuilderTest {
    @Test
    public void testCharParser() {
        int result1;
		try {
			result1 = CellBuilder.charParser('a');
            assertEquals(10, result1);
		} catch (CharConversionException e) {
			e.printStackTrace();
            fail("fallito");
		}
        int result2;
		try {
			result2 = CellBuilder.charParser('Z');
            assertEquals(35, result2);
		} catch (CharConversionException e) {
			e.printStackTrace();
            fail("fallito");
		}
        int result3;
		try {
			result3 = CellBuilder.charParser('5');
            assertEquals(5, result3);
		} catch (CharConversionException e) {
			e.printStackTrace();
            fail("fallito");
		}
    }

    static class TestCell extends Cell
    {
        public static final int ID = 0;

        public TestCell(int i, int j) {
            super(i, j);
        }

        @Override
        public char getID() {
            return ID;
        }
        
    }

    @Test
    public void testCreateCell() {
        Cell cell;
        try {
            cell = CellBuilder.createCell(TestCell.class, 1, 2);
            assertNotNull(cell);
            assertTrue(cell instanceof TestCell);
            assertEquals(1, cell.posI());
            assertEquals(2, cell.posJ());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            fail("fallito");
        }
        
    }

    static class TestCellState extends CellState
    {
        public static final int ID = 0;

        public TestCellState(int i, int j, int state) {
            super(i, j, state);
        }

        @Override
        public char getID() {
            return ID;
        }
    }

    @Test
    public void testCreateCellState() {
        
        CellState cell;
        try {
            cell = CellBuilder.createCellState(TestCellState.class, 1, 2, 0);
            assertNotNull(cell);
            assertTrue(cell instanceof TestCellState);
            assertEquals(1, cell.posI());
            assertEquals(2, cell.posJ());
            assertEquals(0, cell.getState());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            fail("fallito");
        }
        
    }

    @Test
    public void testGetClassByID() {
        Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};
        try {
            CellBuilder.IdClassTable tab = new CellBuilder.IdClassTable(element);
            assertEquals(Wall.class, tab.getClassByID('W'));
        } catch (CharConversionException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
                | SecurityException e) {
            e.printStackTrace();
            fail("fallito");
        }
    }
}
