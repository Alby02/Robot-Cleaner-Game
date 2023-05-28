package model.timer;

import model.Map;

public class WasherTimer extends EventTimer {
    private Map washer;

    public WasherTimer(long delay, long period, Map washer){
        super(delay, period);
        this.washer = washer;
    }

    @Override
    public void startEvent() {
        washer.event(null);
    }
}
