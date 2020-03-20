package profiler;

import java.util.ArrayList;

public class Stopwatch {
    private long start;
    private boolean isStarted;
    private ArrayList<Long> breakpoints;

    public Stopwatch() {
        breakpoints = new ArrayList<>();
    }

    public void start() {
        if (!isStarted) {
            start = System.currentTimeMillis();
            isStarted = true;
        } else throw new IllegalStateException("Can't start the timer if it's already started.");
    }

    public float restart() {
        float stop = stop();
        start();
        return stop;
    }

    public float savePoint() {
        if (isStarted) {
            long elapsedTimeMillis = System.currentTimeMillis() - start;
            breakpoints.add(elapsedTimeMillis);
            return elapsedTimeMillis / 1000F;
        } else throw new IllegalStateException("Can't save the time if the timer hasn't started yet.");
    }

    public float stop() {
        if (isStarted) {
            long elapsedTimeMillis = System.currentTimeMillis() - start;
            breakpoints.add(elapsedTimeMillis);
            isStarted = false;
            return elapsedTimeMillis / 1000F;
        } else throw new IllegalStateException("Can't stop timer if it hasn't started yet.");
    }

    public float loadPoint(int index) {
        if (!isStarted)
            return breakpoints.get(index) / 1000F;
        else throw new IllegalStateException("Can't load the breakpoint becouse the timer hasn't stopped yet.");
    }
}
