package model;

import static org.junit.Assert.*;

import java.io.CharConversionException;
import java.io.FileNotFoundException;

import org.junit.Test;

import model.element.*;
import model.exception.FileStructureWrongException;

public class MapBuilderTest {
    @Test
    public void testGenerateFromFile() {
        final Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};
        String path = "C:/Users/Alberto/Desktop/Robot-Cleaner-Game/";//Importante: non funziona il path relativo nei test JUnit non capisco perchè quindi acco quelo assoluto
        String nome = "testmappa.txt";
        try {
            Map m = MapBuilder.generateFromFile(path + nome, element);
            assertNotNull(m);
            assertEquals(3, m.getISize());
            assertEquals(3, m.getJSize());
            assertEquals('w', m.getIDCasella(0, 0));
            assertEquals('0', m.getIDCasella(1, 1));
        } catch (FileNotFoundException | CharConversionException | IllegalArgumentException
                | FileStructureWrongException | ReflectiveOperationException e) {
            e.printStackTrace();
            fail("fallito");
        }
    }

    @Test
    public void testGenerateRandomMap() {
        final Class<?> element[] = {null, Wall.class, Fire.class, Oven.class, Sink.class, Washer.class, Water.class, Cat.class};
        final int[] probability = {100, -25, 10, 5, 5, 5, 25, 5};
        Map m;
        try {
            m = MapBuilder.generateRandomMap(element, probability);
            for (int i = 0; i < m.getISize(); i++) {
            for (int j = 0; j < m.getJSize(); j++) {
                if ((i == 0 || j == 0 || i == m.getISize() - 1 || j == m.getJSize() - 1) && m.getIDCasella(i, j) != 'w') {
                    fail("fallito");
                } 
            }
        }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            fail("fallito ");
        }
    }
}
