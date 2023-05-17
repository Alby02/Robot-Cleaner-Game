package model.timer;

import model.element.Oven;
import model.exception.CantGenerateStateEventException;

public class OvenTimer extends EventTimer {
    private Oven oven;

    public OvenTimer(long delay, long period){
        super(delay, period);
        this.oven = oven;
    }

    @Override
    public void startEvent() {
        try {
            oven.Event(null);
        } catch (CantGenerateStateEventException e) {
            e.printStackTrace();
        }
    }
}
