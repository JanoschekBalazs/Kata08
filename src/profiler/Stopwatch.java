package profiler;

import javax.swing.*;
import java.util.ArrayList;

public class Stopwatch {
    private Long start;
    private ArrayList<Long> breakpoints;

    public Stopwatch() {
        start = null;
        breakpoints = new ArrayList<>();
    }

    public void start() {
        if (start == null)
            start = System.currentTimeMillis();
        else
            JOptionPane.showMessageDialog(null, "Error", "Timer can't be started becouse it hasn't stopped yet!", JOptionPane.ERROR_MESSAGE);
    }

    public Float restart() {
        Float stop = stop();
        start();
        return stop;
    }

    public Float savePoint() {
        if (start != null) {
            long elapsedTimeMillis = System.currentTimeMillis() - start;
            breakpoints.add(elapsedTimeMillis);
            return elapsedTimeMillis / 1000F;
        }
        JOptionPane.showMessageDialog(null, "Error", "Can't save timepoint becouse the timer hasn't started yet!", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public Float stop() {
        if (start != null) {
            long elapsedTimeMillis = System.currentTimeMillis() - start;
            breakpoints.add(elapsedTimeMillis);
            start = null;
            return elapsedTimeMillis / 1000F;
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Timer can't be stopped becouse it hasn't started yet!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Float loadPoint(int index) {
        if (start == null)
            return breakpoints.get(index) / 1000F;
        else if (breakpoints.size() == 0) {
            JOptionPane.showMessageDialog(null, "Warning", "There were no breakpoints!", JOptionPane.WARNING_MESSAGE);
            return null;
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Can't get breakpoints becouse the timer hasn't stopped yet!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
