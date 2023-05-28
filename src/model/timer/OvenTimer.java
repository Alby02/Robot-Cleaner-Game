package model.timer;

import model.Map;

public class OvenTimer extends EventTimer {
    private Map oven;

    public OvenTimer(long delay, long period, Map oven){
        super(delay, period);
        this.oven = oven;
    }

    @Override
    public void startEvent() {
        oven.event(null);
    }
}
