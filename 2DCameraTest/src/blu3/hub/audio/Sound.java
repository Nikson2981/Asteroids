package blu3.hub.audio;

import blu3.hub.Logger;
import blu3.hub.Main;
import blu3.hub.math.StreamUtilities;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Sound {
    private final Set<Clip> clips = Collections.synchronizedSet(new HashSet<>());

    private AudioFormat format;
    private byte[] bytes;
    private final String path;
    public boolean loaded = false;

    public Sound(String path) {
        this.path = path;
    }

    public void load() {
        if (loaded) return;
        long ms = System.currentTimeMillis();
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(Main.class.getResourceAsStream(path)));

            this.format = stream.getFormat();
            this.bytes = StreamUtilities.getBytes(stream);

            for (int i = 0; i < 4; i++) {
                this.createNewClip();
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new Error(e);
        }
        Logger.INFO("Loaded sound: " + path + ", took " + (System.currentTimeMillis() - ms) + " millis");
        loaded = true;
    }

    private Clip createNewClip() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(this.format, this.bytes, 0, this.bytes.length);
            clips.add(clip);
            return clip;
        } catch (LineUnavailableException e) {
            throw new Error(e);
        }
    }

    public void play() {
        new Thread(() -> {
            Clip clip = clips.stream()
                    .filter(c ->
                            c.getFramePosition() == 0 ||
                                    c.getFramePosition() == c.getFrameLength())
                    .findFirst()
                    .orElseGet(this::createNewClip);
            clip.setFramePosition(0);
            clip.start();
        }).start();
    }
}
