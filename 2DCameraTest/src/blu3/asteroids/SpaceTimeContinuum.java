package blu3.asteroids;

public class SpaceTimeContinuum { // stupid
    private long time;

    public SpaceTimeContinuum() {
        this.time = -1L;
    }

    public boolean passedS(final double s) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(s * 1000.0);
    }

    public void reset() {
        this.time = System.nanoTime();
    }

    public long getMs(final long time) {
        return time / 1000000L;
    }
}
