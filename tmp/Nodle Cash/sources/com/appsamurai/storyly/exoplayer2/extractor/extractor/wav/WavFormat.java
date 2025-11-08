package com.appsamurai.storyly.exoplayer2.extractor.extractor.wav;

final class WavFormat {
    public final int averageBytesPerSecond;
    public final int bitsPerSample;
    public final int blockSize;
    public final byte[] extraData;
    public final int formatType;
    public final int frameRateHz;
    public final int numChannels;

    public WavFormat(int i3, int i4, int i5, int i6, int i7, int i8, byte[] bArr) {
        this.formatType = i3;
        this.numChannels = i4;
        this.frameRateHz = i5;
        this.averageBytesPerSecond = i6;
        this.blockSize = i7;
        this.bitsPerSample = i8;
        this.extraData = bArr;
    }
}
