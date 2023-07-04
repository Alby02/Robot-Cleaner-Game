package test;

import org.junit.Before;
import org.junit.Test;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.WhereIAmController;
import model.Cell;
import model.Eventable;
import model.Map;
import view.WhereIAmView;

public class WhereIAmControllerTest {
    private WhereIAmController controller;
    private MockWhereIAmView view;
    private Map model;

    @Before
    public void setUp() {
        model = new Map(new Cell[10][10], new Eventable[10]);
        view = new MockWhereIAmView();
        controller = new WhereIAmController(model, view);
    }

    @Test
    public void testActionPerformed() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "a");

        controller.actionPerformed(event);

        assertTrue(view.modelMethodCalled);

        assertEquals("position", view.propertyName);
        assertEquals(null, view.oldValue);
        assertEquals(null, view.newValue);
    }

    @Test
    public void testKeyTyped() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setVisible(true);

            KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent e) {
                    if (e.getID() == KeyEvent.KEY_TYPED) {
                        controller.keyTyped(e);
                        return true;
                    }
                    return false;
                }
            };
            frame.addKeyListener((KeyListener) keyEventDispatcher);

            KeyEvent event = new KeyEvent(frame, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'w');
            frame.dispatchEvent(event);

            frame.removeKeyListener((KeyListener) keyEventDispatcher);
            frame.dispose();
        });

        assertTrue(view.modelMethodCalled);
        assertEquals("w", view.actionCommand);
    }

    private static class MockWhereIAmView implements WhereIAmView {
        private boolean modelMethodCalled = false;
        private String actionCommand;
        private String propertyName;
        private Object oldValue;
        private Object newValue;

        @Override
        public void showPosition() {
            // Implementazione vuota per il test
        }

        @Override
        public void communicateError(String message) {
            // Implementazione vuota per il test
        }

        @Override
        public void addController(WhereIAmController whereIAmController) {
             // Implementazione vuota per il test
        }

        @Override
        public void startView() {
            // Implementazione vuota per il test
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyName = evt.getPropertyName();
            oldValue = evt.getOldValue();
            newValue = evt.getNewValue();
        }
    }
}