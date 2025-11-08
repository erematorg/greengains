package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import java.io.IOException;

public class ForwardingExtractorInput implements ExtractorInput {
    private final ExtractorInput input;

    public ForwardingExtractorInput(ExtractorInput extractorInput) {
        this.input = extractorInput;
    }

    public boolean advancePeekPosition(int i3, boolean z2) throws IOException {
        return this.input.advancePeekPosition(i3, z2);
    }

    public long getLength() {
        return this.input.getLength();
    }

    public long getPeekPosition() {
        return this.input.getPeekPosition();
    }

    public long getPosition() {
        return this.input.getPosition();
    }

    public int peek(byte[] bArr, int i3, int i4) throws IOException {
        return this.input.peek(bArr, i3, i4);
    }

    public boolean peekFully(byte[] bArr, int i3, int i4, boolean z2) throws IOException {
        return this.input.peekFully(bArr, i3, i4, z2);
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        return this.input.read(bArr, i3, i4);
    }

    public boolean readFully(byte[] bArr, int i3, int i4, boolean z2) throws IOException {
        return this.input.readFully(bArr, i3, i4, z2);
    }

    public void resetPeekPosition() {
        this.input.resetPeekPosition();
    }

    public <E extends Throwable> void setRetryPosition(long j2, E e3) throws Throwable {
        this.input.setRetryPosition(j2, e3);
    }

    public int skip(int i3) throws IOException {
        return this.input.skip(i3);
    }

    public boolean skipFully(int i3, boolean z2) throws IOException {
        return this.input.skipFully(i3, z2);
    }

    public void advancePeekPosition(int i3) throws IOException {
        this.input.advancePeekPosition(i3);
    }

    public void peekFully(byte[] bArr, int i3, int i4) throws IOException {
        this.input.peekFully(bArr, i3, i4);
    }

    public void readFully(byte[] bArr, int i3, int i4) throws IOException {
        this.input.readFully(bArr, i3, i4);
    }

    public void skipFully(int i3) throws IOException {
        this.input.skipFully(i3);
    }
}
