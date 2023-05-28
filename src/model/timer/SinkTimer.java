package model.timer;

import model.Map;

public class SinkTimer extends EventTimer {
    private Map sink;

    public SinkTimer(long delay, long period, Map sink){
        super(delay, period);
        this.sink = sink;
    }

    @Override
    public void startEvent() {
        sink.event(null);
    }
}

