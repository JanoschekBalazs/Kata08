package profiler;

class ElapsedTime {
    private static long start;

    static void start() {
        start = System.currentTimeMillis();
    }

    static float get() {
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        return elapsedTimeMillis / 1000F;
    }
}
