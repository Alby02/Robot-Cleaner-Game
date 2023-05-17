package model.timer;

import model.element.Washer;
import model.exception.CantGenerateStateEventException;

public class WasherTimer extends EventTimer {
    private Washer washer;

    public WasherTimer(long delay, long period){
        super(delay, period);
        this.washer = washer;
    }

    @Override
    public void startEvent() {
        try {
            washer.Event(null);
        } catch (CantGenerateStateEventException e) {
            e.printStackTrace();
        }
    }
}
