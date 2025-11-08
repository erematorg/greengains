package com.appsamurai.storyly.exoplayer2.extractor.extractor.avi;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.google.common.collect.ImmutableList;

final class StreamFormatChunk implements AviChunk {
    private static final String TAG = "StreamFormatChunk";
    public final Format format;

    public StreamFormatChunk(Format format2) {
        this.format = format2;
    }

    @Nullable
    private static String getMimeTypeFromCompression(int i3) {
        switch (i3) {
            case 808802372:
            case 877677894:
            case 1145656883:
            case 1145656920:
            case 1482049860:
            case 1684633208:
            case 2021026148:
                return MimeTypes.VIDEO_MP4V;
            case 826496577:
            case 828601953:
            case 875967048:
                return MimeTypes.VIDEO_H264;
            case 842289229:
                return MimeTypes.VIDEO_MP42;
            case 859066445:
                return MimeTypes.VIDEO_MP43;
            case 1196444237:
            case 1735420525:
                return MimeTypes.VIDEO_MJPEG;
            default:
                return null;
        }
    }

    @Nullable
    private static String getMimeTypeFromTag(int i3) {
        if (i3 == 1) {
            return MimeTypes.AUDIO_RAW;
        }
        if (i3 == 85) {
            return MimeTypes.AUDIO_MPEG;
        }
        if (i3 == 255) {
            return MimeTypes.AUDIO_AAC;
        }
        if (i3 == 8192) {
            return MimeTypes.AUDIO_AC3;
        }
        if (i3 != 8193) {
            return null;
        }
        return MimeTypes.AUDIO_DTS;
    }

    @Nullable
    private static AviChunk parseBitmapInfoHeader(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        String mimeTypeFromCompression = getMimeTypeFromCompression(readLittleEndianInt3);
        if (mimeTypeFromCompression == null) {
            r.a(readLittleEndianInt3, "Ignoring track with unsupported compression ", TAG);
            return null;
        }
        Format.Builder builder = new Format.Builder();
        builder.setWidth(readLittleEndianInt).setHeight(readLittleEndianInt2).setSampleMimeType(mimeTypeFromCompression);
        return new StreamFormatChunk(builder.build());
    }

    @Nullable
    public static AviChunk parseFrom(int i3, ParsableByteArray parsableByteArray) {
        if (i3 == 2) {
            return parseBitmapInfoHeader(parsableByteArray);
        }
        if (i3 == 1) {
            return parseWaveFormatEx(parsableByteArray);
        }
        Log.w(TAG, "Ignoring strf box for unsupported track type: " + Util.getTrackTypeString(i3));
        return null;
    }

    @Nullable
    private static AviChunk parseWaveFormatEx(ParsableByteArray parsableByteArray) {
        int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        String mimeTypeFromTag = getMimeTypeFromTag(readLittleEndianUnsignedShort);
        if (mimeTypeFromTag == null) {
            r.a(readLittleEndianUnsignedShort, "Ignoring track with unsupported format tag ", TAG);
            return null;
        }
        int readLittleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(6);
        int pcmEncoding = Util.getPcmEncoding(parsableByteArray.readUnsignedShort());
        int readLittleEndianUnsignedShort3 = parsableByteArray.readLittleEndianUnsignedShort();
        byte[] bArr = new byte[readLittleEndianUnsignedShort3];
        parsableByteArray.readBytes(bArr, 0, readLittleEndianUnsignedShort3);
        Format.Builder builder = new Format.Builder();
        builder.setSampleMimeType(mimeTypeFromTag).setChannelCount(readLittleEndianUnsignedShort2).setSampleRate(readLittleEndianInt);
        if (MimeTypes.AUDIO_RAW.equals(mimeTypeFromTag) && pcmEncoding != 0) {
            builder.setPcmEncoding(pcmEncoding);
        }
        if (MimeTypes.AUDIO_AAC.equals(mimeTypeFromTag) && readLittleEndianUnsignedShort3 > 0) {
            builder.setInitializationData(ImmutableList.of(bArr));
        }
        return new StreamFormatChunk(builder.build());
    }

    public int getType() {
        return AviExtractor.FOURCC_strf;
    }
}
