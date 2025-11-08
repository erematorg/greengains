package com.appsamurai.storyly.exoplayer2.extractor.audio;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import jnr.ffi.provider.jffi.JNINativeInterface;
import okio.Utf8;
import org.msgpack.core.MessagePack;

public final class Ac3Util {
    public static final int AC3_MAX_RATE_BYTES_PER_SECOND = 80000;
    private static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    private static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, DilithiumEngine.DilithiumPolyT1PackedBytes, KyberEngine.KyberPolyBytes, 448, 512, 576, 640};
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    public static final int E_AC3_MAX_RATE_BYTES_PER_SECOND = 768000;
    private static final int[] SAMPLE_RATE_BY_FSCOD = {OpusUtil.SAMPLE_RATE, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, 104, 121, 139, 174, JNINativeInterface.SetByteArrayRegion, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};
    public static final int TRUEHD_MAX_RATE_BYTES_PER_SECOND = 3062500;
    public static final int TRUEHD_RECHUNK_SAMPLE_COUNT = 16;
    public static final int TRUEHD_SYNCFRAME_PREFIX_LENGTH = 10;

    public static final class SyncFrameInfo {
        public static final int STREAM_TYPE_TYPE0 = 0;
        public static final int STREAM_TYPE_TYPE1 = 1;
        public static final int STREAM_TYPE_TYPE2 = 2;
        public static final int STREAM_TYPE_UNDEFINED = -1;
        public final int channelCount;
        public final int frameSize;
        @Nullable
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        @Documented
        @Target({ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface StreamType {
        }

        private SyncFrameInfo(@Nullable String str, int i3, int i4, int i5, int i6, int i7) {
            this.mimeType = str;
            this.streamType = i3;
            this.channelCount = i4;
            this.sampleRate = i5;
            this.frameSize = i6;
            this.sampleCount = i7;
        }
    }

    private Ac3Util() {
    }

    public static int findTrueHdSyncframeOffset(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - 10;
        for (int i3 = position; i3 <= limit; i3++) {
            if ((Util.getBigEndianInt(byteBuffer, i3 + 4) & -2) == -126718022) {
                return i3 - position;
            }
        }
        return -1;
    }

    private static int getAc3SyncframeSize(int i3, int i4) {
        int i5 = i4 / 2;
        if (i3 < 0) {
            return -1;
        }
        int[] iArr = SAMPLE_RATE_BY_FSCOD;
        if (i3 >= iArr.length || i4 < 0) {
            return -1;
        }
        int[] iArr2 = SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
        if (i5 >= iArr2.length) {
            return -1;
        }
        int i6 = iArr[i3];
        if (i6 == 44100) {
            return ((i4 % 2) + iArr2[i5]) * 2;
        }
        int i7 = BITRATE_BY_HALF_FRMSIZECOD[i5];
        return i6 == 32000 ? i7 * 6 : i7 * 4;
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, @Nullable DrmInitData drmInitData) {
        int i3 = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i4 = CHANNEL_COUNT_BY_ACMOD[(readUnsignedByte & 56) >> 3];
        if ((readUnsignedByte & 4) != 0) {
            i4++;
        }
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.AUDIO_AC3).setChannelCount(i4).setSampleRate(i3).setDrmInitData(drmInitData).setLanguage(str2).build();
    }

    public static int parseAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        int i3 = 3;
        if (((byteBuffer.get(byteBuffer.position() + 5) & 248) >> 3) <= 10) {
            return AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
        }
        if (((byteBuffer.get(byteBuffer.position() + 4) & MessagePack.Code.NIL) >> 6) != 3) {
            i3 = (byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4;
        }
        return BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[i3] * 256;
    }

    public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        int ac3SyncframeSize;
        int i3;
        int i4;
        int i5;
        int i6;
        String str;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int position = parsableBitArray.getPosition();
        parsableBitArray2.skipBits(40);
        boolean z2 = parsableBitArray2.readBits(5) > 10;
        parsableBitArray2.setPosition(position);
        int i12 = -1;
        if (z2) {
            parsableBitArray2.skipBits(16);
            int readBits = parsableBitArray2.readBits(2);
            if (readBits == 0) {
                i12 = 0;
            } else if (readBits == 1) {
                i12 = 1;
            } else if (readBits == 2) {
                i12 = 2;
            }
            parsableBitArray2.skipBits(3);
            ac3SyncframeSize = (parsableBitArray2.readBits(11) + 1) * 2;
            int readBits2 = parsableBitArray2.readBits(2);
            if (readBits2 == 3) {
                i3 = SAMPLE_RATE_BY_FSCOD2[parsableBitArray2.readBits(2)];
                i7 = 6;
                i8 = 3;
            } else {
                i8 = parsableBitArray2.readBits(2);
                i7 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[i8];
                i3 = SAMPLE_RATE_BY_FSCOD[readBits2];
            }
            i5 = i7 * 256;
            int readBits3 = parsableBitArray2.readBits(3);
            boolean readBit = parsableBitArray.readBit();
            i4 = CHANNEL_COUNT_BY_ACMOD[readBits3] + (readBit ? 1 : 0);
            parsableBitArray2.skipBits(10);
            if (parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(8);
            }
            if (readBits3 == 0) {
                parsableBitArray2.skipBits(5);
                if (parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
            }
            if (i12 == 1 && parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(16);
            }
            if (parsableBitArray.readBit()) {
                if (readBits3 > 2) {
                    parsableBitArray2.skipBits(2);
                }
                if ((readBits3 & 1) == 0 || readBits3 <= 2) {
                    i10 = 6;
                } else {
                    i10 = 6;
                    parsableBitArray2.skipBits(6);
                }
                if ((readBits3 & 4) != 0) {
                    parsableBitArray2.skipBits(i10);
                }
                if (readBit && parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(5);
                }
                if (i12 == 0) {
                    if (parsableBitArray.readBit()) {
                        i11 = 6;
                        parsableBitArray2.skipBits(6);
                    } else {
                        i11 = 6;
                    }
                    if (readBits3 == 0 && parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(i11);
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(i11);
                    }
                    int readBits4 = parsableBitArray2.readBits(2);
                    if (readBits4 == 1) {
                        parsableBitArray2.skipBits(5);
                    } else if (readBits4 == 2) {
                        parsableBitArray2.skipBits(12);
                    } else if (readBits4 == 3) {
                        int readBits5 = parsableBitArray2.readBits(5);
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                            }
                        }
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(7);
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(8);
                                }
                            }
                        }
                        parsableBitArray2.skipBits((readBits5 + 2) * 8);
                        parsableBitArray.byteAlign();
                    }
                    if (readBits3 < 2) {
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                        if (readBits3 == 0 && parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        if (i8 == 0) {
                            parsableBitArray2.skipBits(5);
                        } else {
                            for (int i13 = 0; i13 < i7; i13++) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(5);
                                }
                            }
                        }
                    }
                }
            }
            if (parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(5);
                if (readBits3 == 2) {
                    parsableBitArray2.skipBits(4);
                }
                if (readBits3 >= 6) {
                    parsableBitArray2.skipBits(2);
                }
                if (parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                if (readBits3 == 0 && parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                if (readBits2 < 3) {
                    parsableBitArray.skipBit();
                }
            }
            if (i12 == 0 && i8 != 3) {
                parsableBitArray.skipBit();
            }
            if (i12 != 2 || (i8 != 3 && !parsableBitArray.readBit())) {
                i9 = 6;
            } else {
                i9 = 6;
                parsableBitArray2.skipBits(6);
            }
            str = (parsableBitArray.readBit() && parsableBitArray2.readBits(i9) == 1 && parsableBitArray2.readBits(8) == 1) ? MimeTypes.AUDIO_E_AC3_JOC : MimeTypes.AUDIO_E_AC3;
            i6 = i12;
        } else {
            parsableBitArray2.skipBits(32);
            int readBits6 = parsableBitArray2.readBits(2);
            String str2 = readBits6 == 3 ? null : MimeTypes.AUDIO_AC3;
            ac3SyncframeSize = getAc3SyncframeSize(readBits6, parsableBitArray2.readBits(6));
            parsableBitArray2.skipBits(8);
            int readBits7 = parsableBitArray2.readBits(3);
            if (!((readBits7 & 1) == 0 || readBits7 == 1)) {
                parsableBitArray2.skipBits(2);
            }
            if ((readBits7 & 4) != 0) {
                parsableBitArray2.skipBits(2);
            }
            if (readBits7 == 2) {
                parsableBitArray2.skipBits(2);
            }
            int[] iArr = SAMPLE_RATE_BY_FSCOD;
            i3 = readBits6 < iArr.length ? iArr[readBits6] : -1;
            i4 = CHANNEL_COUNT_BY_ACMOD[readBits7] + (parsableBitArray.readBit() ? 1 : 0);
            i5 = AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
            i6 = -1;
            str = str2;
        }
        return new SyncFrameInfo(str, i6, i4, i3, ac3SyncframeSize, i5);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (((bArr[5] & 248) >> 3) > 10) {
            return (((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1) * 2;
        }
        byte b3 = bArr[4];
        return getAc3SyncframeSize((b3 & MessagePack.Code.NIL) >> 6, b3 & Utf8.REPLACEMENT_BYTE);
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, @Nullable DrmInitData drmInitData) {
        parsableByteArray.skipBytes(2);
        int i3 = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i4 = CHANNEL_COUNT_BY_ACMOD[(readUnsignedByte & 14) >> 1];
        if ((readUnsignedByte & 1) != 0) {
            i4++;
        }
        if (((parsableByteArray.readUnsignedByte() & 30) >> 1) > 0 && (2 & parsableByteArray.readUnsignedByte()) != 0) {
            i4 += 2;
        }
        return new Format.Builder().setId(str).setSampleMimeType((parsableByteArray.bytesLeft() <= 0 || (parsableByteArray.readUnsignedByte() & 1) == 0) ? MimeTypes.AUDIO_E_AC3 : MimeTypes.AUDIO_E_AC3_JOC).setChannelCount(i4).setSampleRate(i3).setDrmInitData(drmInitData).setLanguage(str2).build();
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] bArr) {
        boolean z2 = false;
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111) {
            byte b3 = bArr[7];
            if ((b3 & 254) == 186) {
                if ((b3 & 255) == 187) {
                    z2 = true;
                }
                return 40 << ((bArr[z2 ? (char) 9 : 8] >> 4) & 7);
            }
        }
        return 0;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer byteBuffer, int i3) {
        return 40 << ((byteBuffer.get((byteBuffer.position() + i3) + ((byteBuffer.get((byteBuffer.position() + i3) + 7) & 255) == 187 ? 9 : 8)) >> 4) & 7);
    }
}
