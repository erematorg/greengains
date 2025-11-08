package com.appsamurai.storyly.exoplayer2.core.upstream;

import java.io.IOException;

public interface LoaderErrorThrower {

    public static final class Dummy implements LoaderErrorThrower {
        public void maybeThrowError() {
        }

        public void maybeThrowError(int i3) {
        }
    }

    void maybeThrowError() throws IOException;

    void maybeThrowError(int i3) throws IOException;
}
