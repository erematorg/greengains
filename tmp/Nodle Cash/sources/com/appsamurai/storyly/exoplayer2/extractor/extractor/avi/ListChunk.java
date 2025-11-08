package com.appsamurai.storyly.exoplayer2.extractor.extractor.avi;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

final class ListChunk implements AviChunk {
    public final ImmutableList<AviChunk> children;
    private final int type;

    private ListChunk(int i3, ImmutableList<AviChunk> immutableList) {
        this.type = i3;
        this.children = immutableList;
    }

    @Nullable
    private static AviChunk createBox(int i3, int i4, ParsableByteArray parsableByteArray) {
        switch (i3) {
            case AviExtractor.FOURCC_strf /*1718776947*/:
                return StreamFormatChunk.parseFrom(i4, parsableByteArray);
            case AviExtractor.FOURCC_avih /*1751742049*/:
                return AviMainHeaderChunk.parseFrom(parsableByteArray);
            case AviExtractor.FOURCC_strh /*1752331379*/:
                return AviStreamHeaderChunk.parseFrom(parsableByteArray);
            case AviExtractor.FOURCC_strn /*1852994675*/:
                return StreamNameChunk.parseFrom(parsableByteArray);
            default:
                return null;
        }
    }

    public static ListChunk parseFrom(int i3, ParsableByteArray parsableByteArray) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        int limit = parsableByteArray.limit();
        int i4 = -2;
        while (parsableByteArray.bytesLeft() > 8) {
            int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
            int position = parsableByteArray.getPosition() + parsableByteArray.readLittleEndianInt();
            parsableByteArray.setLimit(position);
            AviChunk parseFrom = readLittleEndianInt == 1414744396 ? parseFrom(parsableByteArray.readLittleEndianInt(), parsableByteArray) : createBox(readLittleEndianInt, i4, parsableByteArray);
            if (parseFrom != null) {
                if (parseFrom.getType() == 1752331379) {
                    i4 = ((AviStreamHeaderChunk) parseFrom).getTrackType();
                }
                builder.add((Object) parseFrom);
            }
            parsableByteArray.setPosition(position);
            parsableByteArray.setLimit(limit);
        }
        return new ListChunk(i3, builder.build());
    }

    @Nullable
    public <T extends AviChunk> T getChild(Class<T> cls) {
        UnmodifiableIterator<AviChunk> it = this.children.iterator();
        while (it.hasNext()) {
            T t2 = (AviChunk) it.next();
            if (t2.getClass() == cls) {
                return t2;
            }
        }
        return null;
    }

    public int getType() {
        return this.type;
    }
}
