package blu3.asteroids.audio;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sounds {

    public static final Sound EXPLODE1 =   new Sound(soundLocation("explode1"));
    public static final Sound EXPLODE2 =   new Sound(soundLocation("explode2"));
    public static final Sound EXPLODE3 =   new Sound(soundLocation("explode3"));
    public static final Sound SHOOT1 =     new Sound(soundLocation("hks1"));
    public static final Sound SHOOT2 =     new Sound(soundLocation("hks2"));
    public static final Sound SHOOT3 =     new Sound(soundLocation("hks3"));
    public static final Sound BEEP =       new Sound(soundLocation("blip1"));
    public static final Sound NEW_WAVE =   new Sound(soundLocation("ammopickup2"));
    public static final Sound CLOSE_GAME = new Sound(soundLocation("die3"));

    public static String soundLocation(String fileName) {
        return "/sounds/" + fileName + ".wav";
    } // .wav only

    public static final List<Sound> sounds = Arrays.asList(NEW_WAVE, EXPLODE1, EXPLODE2, EXPLODE3, SHOOT1, SHOOT2, SHOOT3, BEEP, CLOSE_GAME); // add new sounds to the list

    public static void loadSound(int i) {
        if (sounds.get(i) != null) {
            sounds.get(i).load();
        }
    }

    public static void playRandomExplosionSound() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0: {
                EXPLODE1.play();
                break;
            }
            case 1: {
                EXPLODE2.play();
                break;
            }
            case 2: {
                EXPLODE3.play();
                break;
            }
        }
    }

    public static void playRandomShootSound() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0: {
                SHOOT1.play();
                break;
            }
            case 1: {
                SHOOT2.play();
                break;
            }
            case 2: {
                SHOOT3.play();
                break;
            }
        }
    }

}
