package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import com.appsamurai.storyly.exoplayer2.common.ExoPlayerLibraryInfo;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;

public final class DefaultExtractorInput implements ExtractorInput {
    private static final int PEEK_MAX_FREE_SPACE = 524288;
    private static final int PEEK_MIN_FREE_SPACE_AFTER_RESIZE = 65536;
    private static final int SCRATCH_SPACE_SIZE = 4096;
    private final DataReader dataReader;
    private byte[] peekBuffer = new byte[65536];
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final byte[] scratchSpace = new byte[4096];
    private final long streamLength;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.extractor");
    }

    public DefaultExtractorInput(DataReader dataReader2, long j2, long j3) {
        this.dataReader = dataReader2;
        this.position = j2;
        this.streamLength = j3;
    }

    private void commitBytesRead(int i3) {
        if (i3 != -1) {
            this.position += (long) i3;
        }
    }

    private void ensureSpaceForPeek(int i3) {
        int i4 = this.peekBufferPosition + i3;
        byte[] bArr = this.peekBuffer;
        if (i4 > bArr.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Util.constrainValue(bArr.length * 2, 65536 + i4, i4 + 524288));
        }
    }

    private int readFromPeekBuffer(byte[] bArr, int i3, int i4) {
        int i5 = this.peekBufferLength;
        if (i5 == 0) {
            return 0;
        }
        int min = Math.min(i5, i4);
        System.arraycopy(this.peekBuffer, 0, bArr, i3, min);
        updatePeekBuffer(min);
        return min;
    }

    private int readFromUpstream(byte[] bArr, int i3, int i4, int i5, boolean z2) throws IOException {
        if (!Thread.interrupted()) {
            int read = this.dataReader.read(bArr, i3 + i5, i4 - i5);
            if (read != -1) {
                return i5 + read;
            }
            if (i5 == 0 && z2) {
                return -1;
            }
            throw new EOFException();
        }
        throw new InterruptedIOException();
    }

    private int skipFromPeekBuffer(int i3) {
        int min = Math.min(this.peekBufferLength, i3);
        updatePeekBuffer(min);
        return min;
    }

    private void updatePeekBuffer(int i3) {
        int i4 = this.peekBufferLength - i3;
        this.peekBufferLength = i4;
        this.peekBufferPosition = 0;
        byte[] bArr = this.peekBuffer;
        byte[] bArr2 = i4 < bArr.length - 524288 ? new byte[(65536 + i4)] : bArr;
        System.arraycopy(bArr, i3, bArr2, 0, i4);
        this.peekBuffer = bArr2;
    }

    public boolean advancePeekPosition(int i3, boolean z2) throws IOException {
        ensureSpaceForPeek(i3);
        int i4 = this.peekBufferLength - this.peekBufferPosition;
        while (i4 < i3) {
            i4 = readFromUpstream(this.peekBuffer, this.peekBufferPosition, i3, i4, z2);
            if (i4 == -1) {
                return false;
            }
            this.peekBufferLength = this.peekBufferPosition + i4;
        }
        this.peekBufferPosition += i3;
        return true;
    }

    public long getLength() {
        return this.streamLength;
    }

    public long getPeekPosition() {
        return this.position + ((long) this.peekBufferPosition);
    }

    public long getPosition() {
        return this.position;
    }

    public int peek(byte[] bArr, int i3, int i4) throws IOException {
        int i5;
        ensureSpaceForPeek(i4);
        int i6 = this.peekBufferLength;
        int i7 = this.peekBufferPosition;
        int i8 = i6 - i7;
        if (i8 == 0) {
            i5 = readFromUpstream(this.peekBuffer, i7, i4, 0, true);
            if (i5 == -1) {
                return -1;
            }
            this.peekBufferLength += i5;
        } else {
            i5 = Math.min(i4, i8);
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition, bArr, i3, i5);
        this.peekBufferPosition += i5;
        return i5;
    }

    public boolean peekFully(byte[] bArr, int i3, int i4, boolean z2) throws IOException {
        if (!advancePeekPosition(i4, z2)) {
            return false;
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - i4, bArr, i3, i4);
        return true;
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        int readFromPeekBuffer = readFromPeekBuffer(bArr, i3, i4);
        if (readFromPeekBuffer == 0) {
            readFromPeekBuffer = readFromUpstream(bArr, i3, i4, 0, true);
        }
        commitBytesRead(readFromPeekBuffer);
        return readFromPeekBuffer;
    }

    public boolean readFully(byte[] bArr, int i3, int i4, boolean z2) throws IOException {
        int readFromPeekBuffer = readFromPeekBuffer(bArr, i3, i4);
        while (readFromPeekBuffer < i4 && readFromPeekBuffer != -1) {
            readFromPeekBuffer = readFromUpstream(bArr, i3, i4, readFromPeekBuffer, z2);
        }
        commitBytesRead(readFromPeekBuffer);
        return readFromPeekBuffer != -1;
    }

    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    public <E extends Throwable> void setRetryPosition(long j2, E e3) throws Throwable {
        Assertions.checkArgument(j2 >= 0);
        this.position = j2;
        throw e3;
    }

    public int skip(int i3) throws IOException {
        int skipFromPeekBuffer = skipFromPeekBuffer(i3);
        if (skipFromPeekBuffer == 0) {
            byte[] bArr = this.scratchSpace;
            skipFromPeekBuffer = readFromUpstream(bArr, 0, Math.min(i3, bArr.length), 0, true);
        }
        commitBytesRead(skipFromPeekBuffer);
        return skipFromPeekBuffer;
    }

    public boolean skipFully(int i3, boolean z2) throws IOException {
        int skipFromPeekBuffer = skipFromPeekBuffer(i3);
        while (skipFromPeekBuffer < i3 && skipFromPeekBuffer != -1) {
            skipFromPeekBuffer = readFromUpstream(this.scratchSpace, -skipFromPeekBuffer, Math.min(i3, this.scratchSpace.length + skipFromPeekBuffer), skipFromPeekBuffer, z2);
        }
        commitBytesRead(skipFromPeekBuffer);
        return skipFromPeekBuffer != -1;
    }

    public void peekFully(byte[] bArr, int i3, int i4) throws IOException {
        peekFully(bArr, i3, i4, false);
    }

    public void readFully(byte[] bArr, int i3, int i4) throws IOException {
        readFully(bArr, i3, i4, false);
    }

    public void skipFully(int i3) throws IOException {
        skipFully(i3, false);
    }

    public void advancePeekPosition(int i3) throws IOException {
        advancePeekPosition(i3, false);
    }
}
