package com.appsamurai.storyly.exoplayer2.core.offline;

import androidx.annotation.Nullable;
import java.io.IOException;

public interface Downloader {

    public interface ProgressListener {
        void onProgress(long j2, long j3, float f2);
    }

    void cancel();

    void download(@Nullable ProgressListener progressListener) throws IOException, InterruptedException;

    void remove();
}
