package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrientamentoTest {
    @Test
    public void testGet() {
        Orientamento orientamento = new Orientamento(Orientamento.Direction.Alto);
        assertEquals(Orientamento.Direction.Alto, orientamento.get());
    }

    @Test
    public void testRotateDX() {
        Orientamento orientamento = new Orientamento(Orientamento.Direction.Alto);
        orientamento.rotateDX();
        assertEquals(Orientamento.Direction.Destra, orientamento.get());
    }

    @Test
    public void testRotateSX() {
        Orientamento orientamento = new Orientamento(Orientamento.Direction.Alto);
        orientamento.rotateSX();
        assertEquals(Orientamento.Direction.Sinistra, orientamento.get());
    }
}
