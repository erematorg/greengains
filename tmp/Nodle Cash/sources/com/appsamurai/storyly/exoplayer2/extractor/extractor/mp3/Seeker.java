package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;

interface Seeker extends SeekMap {

    public static class UnseekableSeeker extends SeekMap.Unseekable implements Seeker {
        public UnseekableSeeker() {
            super(C.TIME_UNSET);
        }

        public long getDataEndPosition() {
            return -1;
        }

        public long getTimeUs(long j2) {
            return 0;
        }
    }

    long getDataEndPosition();

    long getTimeUs(long j2);
}
