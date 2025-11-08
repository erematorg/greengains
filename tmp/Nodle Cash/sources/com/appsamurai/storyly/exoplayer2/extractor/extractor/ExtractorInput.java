package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import java.io.IOException;

public interface ExtractorInput extends DataReader {
    void advancePeekPosition(int i3) throws IOException;

    boolean advancePeekPosition(int i3, boolean z2) throws IOException;

    long getLength();

    long getPeekPosition();

    long getPosition();

    int peek(byte[] bArr, int i3, int i4) throws IOException;

    void peekFully(byte[] bArr, int i3, int i4) throws IOException;

    boolean peekFully(byte[] bArr, int i3, int i4, boolean z2) throws IOException;

    int read(byte[] bArr, int i3, int i4) throws IOException;

    void readFully(byte[] bArr, int i3, int i4) throws IOException;

    boolean readFully(byte[] bArr, int i3, int i4, boolean z2) throws IOException;

    void resetPeekPosition();

    <E extends Throwable> void setRetryPosition(long j2, E e3) throws Throwable;

    int skip(int i3) throws IOException;

    void skipFully(int i3) throws IOException;

    boolean skipFully(int i3, boolean z2) throws IOException;
}
