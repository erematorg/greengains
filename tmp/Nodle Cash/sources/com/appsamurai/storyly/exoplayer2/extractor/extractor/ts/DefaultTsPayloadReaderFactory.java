package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.CodecSpecificDataUtil;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.SignedBytes;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_ENABLE_HDMV_DTS_AUDIO_STREAMS = 64;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    private final List<Format> closedCaptionFormats;
    private final int flags;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    private SeiReader buildSeiReader(TsPayloadReader.EsInfo esInfo) {
        return new SeiReader(getClosedCaptionFormats(esInfo));
    }

    private UserDataReader buildUserDataReader(TsPayloadReader.EsInfo esInfo) {
        return new UserDataReader(getClosedCaptionFormats(esInfo));
    }

    private List<Format> getClosedCaptionFormats(TsPayloadReader.EsInfo esInfo) {
        ArrayList arrayList;
        String str;
        int i3;
        List<byte[]> list;
        if (isSet(32)) {
            return this.closedCaptionFormats;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        List<Format> list2 = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
            if (readUnsignedByte == 134) {
                ArrayList arrayList2 = new ArrayList();
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i4 = 0; i4 < readUnsignedByte2; i4++) {
                    String readString = parsableByteArray.readString(3);
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    boolean z2 = true;
                    boolean z3 = (readUnsignedByte3 & 128) != 0;
                    if (z3) {
                        i3 = readUnsignedByte3 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        str = MimeTypes.APPLICATION_CEA608;
                        i3 = 1;
                    }
                    byte readUnsignedByte4 = (byte) parsableByteArray.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    if (z3) {
                        if ((readUnsignedByte4 & SignedBytes.MAX_POWER_OF_TWO) == 0) {
                            z2 = false;
                        }
                        list = CodecSpecificDataUtil.buildCea708InitializationData(z2);
                    } else {
                        list = null;
                    }
                    arrayList2.add(new Format.Builder().setSampleMimeType(str).setLanguage(readString).setAccessibilityChannel(i3).setInitializationData(list).build());
                }
                arrayList = arrayList2;
            } else {
                arrayList = list2;
            }
            parsableByteArray.setPosition(position);
            list2 = arrayList;
        }
        return list2;
    }

    private boolean isSet(int i3) {
        return (this.flags & i3) != 0;
    }

    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    @Nullable
    public TsPayloadReader createPayloadReader(int i3, TsPayloadReader.EsInfo esInfo) {
        if (i3 != 2) {
            if (i3 == 3 || i3 == 4) {
                return new PesReader(new MpegAudioReader(esInfo.language));
            }
            if (i3 == 21) {
                return new PesReader(new Id3Reader());
            }
            if (i3 != 27) {
                if (i3 == 36) {
                    return new PesReader(new H265Reader(buildSeiReader(esInfo)));
                }
                if (i3 == 89) {
                    return new PesReader(new DvbSubtitleReader(esInfo.dvbSubtitleInfos));
                }
                if (i3 != 138) {
                    if (i3 == 172) {
                        return new PesReader(new Ac4Reader(esInfo.language));
                    }
                    if (i3 == 257) {
                        return new SectionReader(new PassthroughSectionPayloadReader(MimeTypes.APPLICATION_AIT));
                    }
                    if (i3 != 134) {
                        if (i3 != 135) {
                            switch (i3) {
                                case 15:
                                    if (isSet(2)) {
                                        return null;
                                    }
                                    return new PesReader(new AdtsReader(false, esInfo.language));
                                case 16:
                                    return new PesReader(new H263Reader(buildUserDataReader(esInfo)));
                                case 17:
                                    if (isSet(2)) {
                                        return null;
                                    }
                                    return new PesReader(new LatmReader(esInfo.language));
                                default:
                                    switch (i3) {
                                        case 128:
                                            break;
                                        case 129:
                                            break;
                                        case 130:
                                            if (!isSet(64)) {
                                                return null;
                                            }
                                            break;
                                        default:
                                            return null;
                                    }
                            }
                        }
                        return new PesReader(new Ac3Reader(esInfo.language));
                    } else if (isSet(16)) {
                        return null;
                    } else {
                        return new SectionReader(new PassthroughSectionPayloadReader(MimeTypes.APPLICATION_SCTE35));
                    }
                }
                return new PesReader(new DtsReader(esInfo.language));
            } else if (isSet(4)) {
                return null;
            } else {
                return new PesReader(new H264Reader(buildSeiReader(esInfo), isSet(1), isSet(8)));
            }
        }
        return new PesReader(new H262Reader(buildUserDataReader(esInfo)));
    }

    public DefaultTsPayloadReaderFactory(int i3) {
        this(i3, ImmutableList.of());
    }

    public DefaultTsPayloadReaderFactory(int i3, List<Format> list) {
        this.flags = i3;
        this.closedCaptionFormats = list;
    }
}
