package blu3.asteroids.math;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://github.com/gurkenlabs/litiengine/blob/16f486f584551c0dee01d0b689611790f969b5a0/src/de/gurkenlabs/litiengine/util/io/StreamUtilities.java#L13
// tysm for existing

// ...

// the class is bared down to only what i need, of course

public final class StreamUtilities {
    private static final Logger log = Logger.getLogger(StreamUtilities.class.getName());

    private StreamUtilities() {
        throw new UnsupportedOperationException();
    }

    public static void copy(final InputStream in, final OutputStream out) throws IOException {
        final byte[] buffer = new byte[1024];

        if (in.markSupported()) {
            in.mark(Integer.MAX_VALUE);
        }

        while (true) {
            final int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }

        if (in.markSupported()) {
            in.reset();
        }
    }

    public static byte[] getBytes(final InputStream in) {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try {
            StreamUtilities.copy(in, buffer);
            return buffer.toByteArray();
        } catch (final IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

        return new byte[0];
    }
}