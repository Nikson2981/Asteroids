package blu3.cameratest.world;

public class World {
    private final FastNoise noiseGen;

    public World() {
        noiseGen = new FastNoise((int)System.currentTimeMillis() / 200);

    }

}
