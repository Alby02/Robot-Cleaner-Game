package model.timer;

import model.element.Sink;
import model.exception.CantGenerateStateEventException;

public class SinkTimer extends EventTimer {
    private Sink sink;

    public SinkTimer(long delay, long period){
        super(delay, period);
        this.sink = sink;
    }

    @Override
    public void startEvent() {
        try {
            sink.Event(null);
        } catch (CantGenerateStateEventException e) {
            e.printStackTrace();
        }
    }
}
