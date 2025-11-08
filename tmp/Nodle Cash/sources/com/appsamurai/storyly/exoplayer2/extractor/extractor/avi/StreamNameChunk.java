package com.appsamurai.storyly.exoplayer2.extractor.extractor.avi;

import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;

final class StreamNameChunk implements AviChunk {
    public final String name;

    private StreamNameChunk(String str) {
        this.name = str;
    }

    public static StreamNameChunk parseFrom(ParsableByteArray parsableByteArray) {
        return new StreamNameChunk(parsableByteArray.readString(parsableByteArray.bytesLeft()));
    }

    public int getType() {
        return AviExtractor.FOURCC_strn;
    }
}
