package com.appsamurai.storyly.exoplayer2.core.source;

public interface SequenceableLoader {

    public interface Callback<T extends SequenceableLoader> {
        void onContinueLoadingRequested(T t2);
    }

    boolean continueLoading(long j2);

    long getBufferedPositionUs();

    long getNextLoadPositionUs();

    boolean isLoading();

    void reevaluateBuffer(long j2);
}
