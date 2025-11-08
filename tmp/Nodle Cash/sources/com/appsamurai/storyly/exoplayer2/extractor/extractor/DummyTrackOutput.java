package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import java.io.EOFException;
import java.io.IOException;

public final class DummyTrackOutput implements TrackOutput {
    private final byte[] readBuffer = new byte[4096];

    public void format(Format format) {
    }

    public int sampleData(DataReader dataReader, int i3, boolean z2, int i4) throws IOException {
        int read = dataReader.read(this.readBuffer, 0, Math.min(this.readBuffer.length, i3));
        if (read != -1) {
            return read;
        }
        if (z2) {
            return -1;
        }
        throw new EOFException();
    }

    public void sampleMetadata(long j2, int i3, int i4, int i5, @Nullable TrackOutput.CryptoData cryptoData) {
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i3, int i4) {
        parsableByteArray.skipBytes(i3);
    }
}
