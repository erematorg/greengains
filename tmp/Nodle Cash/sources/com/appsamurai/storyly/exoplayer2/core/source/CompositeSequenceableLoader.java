package com.appsamurai.storyly.exoplayer2.core.source;

public class CompositeSequenceableLoader implements SequenceableLoader {
    protected final SequenceableLoader[] loaders;

    public CompositeSequenceableLoader(SequenceableLoader[] sequenceableLoaderArr) {
        this.loaders = sequenceableLoaderArr;
    }

    public boolean continueLoading(long j2) {
        boolean z2;
        long j3 = j2;
        boolean z3 = false;
        do {
            long nextLoadPositionUs = getNextLoadPositionUs();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                break;
            }
            z2 = false;
            for (SequenceableLoader sequenceableLoader : this.loaders) {
                long nextLoadPositionUs2 = sequenceableLoader.getNextLoadPositionUs();
                boolean z4 = nextLoadPositionUs2 != Long.MIN_VALUE && nextLoadPositionUs2 <= j3;
                if (nextLoadPositionUs2 == nextLoadPositionUs || z4) {
                    z2 |= sequenceableLoader.continueLoading(j3);
                }
            }
            z3 |= z2;
        } while (z2);
        return z3;
    }

    public final long getBufferedPositionUs() {
        long j2 = Long.MAX_VALUE;
        for (SequenceableLoader bufferedPositionUs : this.loaders) {
            long bufferedPositionUs2 = bufferedPositionUs.getBufferedPositionUs();
            if (bufferedPositionUs2 != Long.MIN_VALUE) {
                j2 = Math.min(j2, bufferedPositionUs2);
            }
        }
        if (j2 == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j2;
    }

    public final long getNextLoadPositionUs() {
        long j2 = Long.MAX_VALUE;
        for (SequenceableLoader nextLoadPositionUs : this.loaders) {
            long nextLoadPositionUs2 = nextLoadPositionUs.getNextLoadPositionUs();
            if (nextLoadPositionUs2 != Long.MIN_VALUE) {
                j2 = Math.min(j2, nextLoadPositionUs2);
            }
        }
        if (j2 == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j2;
    }

    public boolean isLoading() {
        for (SequenceableLoader isLoading : this.loaders) {
            if (isLoading.isLoading()) {
                return true;
            }
        }
        return false;
    }

    public final void reevaluateBuffer(long j2) {
        for (SequenceableLoader reevaluateBuffer : this.loaders) {
            reevaluateBuffer.reevaluateBuffer(j2);
        }
    }
}
