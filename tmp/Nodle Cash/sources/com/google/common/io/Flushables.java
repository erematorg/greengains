package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Flushable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public final class Flushables {
    private static final Logger logger = Logger.getLogger(Flushables.class.getName());

    private Flushables() {
    }

    public static void flush(Flushable flushable, boolean z2) throws IOException {
        try {
            flushable.flush();
        } catch (IOException e3) {
            if (z2) {
                logger.log(Level.WARNING, "IOException thrown while flushing Flushable.", e3);
                return;
            }
            throw e3;
        }
    }

    @Beta
    public static void flushQuietly(Flushable flushable) {
        try {
            flush(flushable, true);
        } catch (IOException e3) {
            logger.log(Level.SEVERE, "IOException should not have been thrown.", e3);
        }
    }
}
