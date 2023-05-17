package model.timer;

import java.util.Timer;
import java.util.TimerTask;

public abstract class EventTimer {
    private Timer timer;
    private long delay;
    private long period;

    public EventTimer(long delay, long period) {
        this.delay = delay;
        this.period = period;
        this.timer = new Timer();
    }

    public abstract void startEvent();

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                startEvent();
            }
        }, delay, period); 
    }

    public void stopTimer() {
        timer.cancel();
    }
}
