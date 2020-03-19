package profiler;

public class Stopwatch {
    private Long start;

    public Stopwatch() {
        start = null;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public float checkTime() {
        if (start != null) {
            long elapsedTimeMillis = System.currentTimeMillis() - start;
            return elapsedTimeMillis / 1000F;
        }
        System.out.println("Timer hasn't started yet!");
        return 0;
    }

    public float stop() {
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        start = null;
        return elapsedTimeMillis / 1000F;
    }
}
