package com.appsamurai.storyly.exoplayer2.core.source.mediaparser;

import android.annotation.SuppressLint;
import android.media.MediaParser$SeekableInputReader;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.io.IOException;

@RequiresApi(30)
@SuppressLint({"Override"})
public final class InputReaderAdapterV30 implements MediaParser$SeekableInputReader {
    private long currentPosition;
    @Nullable
    private DataReader dataReader;
    private long lastSeekPosition;
    private long resourceLength;

    public long getAndResetSeekPosition() {
        long j2 = this.lastSeekPosition;
        this.lastSeekPosition = -1;
        return j2;
    }

    public long getLength() {
        return this.resourceLength;
    }

    public long getPosition() {
        return this.currentPosition;
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        int read = ((DataReader) Util.castNonNull(this.dataReader)).read(bArr, i3, i4);
        this.currentPosition += (long) read;
        return read;
    }

    public void seekToPosition(long j2) {
        this.lastSeekPosition = j2;
    }

    public void setCurrentPosition(long j2) {
        this.currentPosition = j2;
    }

    public void setDataReader(DataReader dataReader2, long j2) {
        this.dataReader = dataReader2;
        this.resourceLength = j2;
        this.lastSeekPosition = -1;
    }
}
