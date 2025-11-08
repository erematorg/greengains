package com.appsamurai.storyly.exoplayer2.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.work.impl.a;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.BundleableUtil;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.ColorInfo;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class Format implements Bundleable {
    public static final Bundleable.Creator<Format> CREATOR = new a(25);
    private static final Format DEFAULT = new Builder().build();
    private static final int FIELD_ACCESSIBILITY_CHANNEL = 28;
    private static final int FIELD_AVERAGE_BITRATE = 5;
    private static final int FIELD_CHANNEL_COUNT = 23;
    private static final int FIELD_CODECS = 7;
    private static final int FIELD_COLOR_INFO = 22;
    private static final int FIELD_CONTAINER_MIME_TYPE = 9;
    private static final int FIELD_CRYPTO_TYPE = 29;
    private static final int FIELD_DRM_INIT_DATA = 13;
    private static final int FIELD_ENCODER_DELAY = 26;
    private static final int FIELD_ENCODER_PADDING = 27;
    private static final int FIELD_FRAME_RATE = 17;
    private static final int FIELD_HEIGHT = 16;
    private static final int FIELD_ID = 0;
    private static final int FIELD_INITIALIZATION_DATA = 12;
    private static final int FIELD_LABEL = 1;
    private static final int FIELD_LANGUAGE = 2;
    private static final int FIELD_MAX_INPUT_SIZE = 11;
    private static final int FIELD_METADATA = 8;
    private static final int FIELD_PCM_ENCODING = 25;
    private static final int FIELD_PEAK_BITRATE = 6;
    private static final int FIELD_PIXEL_WIDTH_HEIGHT_RATIO = 19;
    private static final int FIELD_PROJECTION_DATA = 20;
    private static final int FIELD_ROLE_FLAGS = 4;
    private static final int FIELD_ROTATION_DEGREES = 18;
    private static final int FIELD_SAMPLE_MIME_TYPE = 10;
    private static final int FIELD_SAMPLE_RATE = 24;
    private static final int FIELD_SELECTION_FLAGS = 3;
    private static final int FIELD_STEREO_MODE = 21;
    private static final int FIELD_SUBSAMPLE_OFFSET_US = 14;
    private static final int FIELD_WIDTH = 15;
    public static final int NO_VALUE = -1;
    public static final long OFFSET_SAMPLE_RELATIVE = Long.MAX_VALUE;
    public final int accessibilityChannel;
    public final int averageBitrate;
    public final int bitrate;
    public final int channelCount;
    @Nullable
    public final String codecs;
    @Nullable
    public final ColorInfo colorInfo;
    @Nullable
    public final String containerMimeType;
    public final int cryptoType;
    @Nullable
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    private int hashCode;
    public final int height;
    @Nullable
    public final String id;
    public final List<byte[]> initializationData;
    @Nullable
    public final String label;
    @Nullable
    public final String language;
    public final int maxInputSize;
    @Nullable
    public final Metadata metadata;
    public final int pcmEncoding;
    public final int peakBitrate;
    public final float pixelWidthHeightRatio;
    @Nullable
    public final byte[] projectionData;
    public final int roleFlags;
    public final int rotationDegrees;
    @Nullable
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int width;

    public static final class Builder {
        /* access modifiers changed from: private */
        public int accessibilityChannel;
        /* access modifiers changed from: private */
        public int averageBitrate;
        /* access modifiers changed from: private */
        public int channelCount;
        /* access modifiers changed from: private */
        @Nullable
        public String codecs;
        /* access modifiers changed from: private */
        @Nullable
        public ColorInfo colorInfo;
        /* access modifiers changed from: private */
        @Nullable
        public String containerMimeType;
        /* access modifiers changed from: private */
        public int cryptoType;
        /* access modifiers changed from: private */
        @Nullable
        public DrmInitData drmInitData;
        /* access modifiers changed from: private */
        public int encoderDelay;
        /* access modifiers changed from: private */
        public int encoderPadding;
        /* access modifiers changed from: private */
        public float frameRate;
        /* access modifiers changed from: private */
        public int height;
        /* access modifiers changed from: private */
        @Nullable
        public String id;
        /* access modifiers changed from: private */
        @Nullable
        public List<byte[]> initializationData;
        /* access modifiers changed from: private */
        @Nullable
        public String label;
        /* access modifiers changed from: private */
        @Nullable
        public String language;
        /* access modifiers changed from: private */
        public int maxInputSize;
        /* access modifiers changed from: private */
        @Nullable
        public Metadata metadata;
        /* access modifiers changed from: private */
        public int pcmEncoding;
        /* access modifiers changed from: private */
        public int peakBitrate;
        /* access modifiers changed from: private */
        public float pixelWidthHeightRatio;
        /* access modifiers changed from: private */
        @Nullable
        public byte[] projectionData;
        /* access modifiers changed from: private */
        public int roleFlags;
        /* access modifiers changed from: private */
        public int rotationDegrees;
        /* access modifiers changed from: private */
        @Nullable
        public String sampleMimeType;
        /* access modifiers changed from: private */
        public int sampleRate;
        /* access modifiers changed from: private */
        public int selectionFlags;
        /* access modifiers changed from: private */
        public int stereoMode;
        /* access modifiers changed from: private */
        public long subsampleOffsetUs;
        /* access modifiers changed from: private */
        public int width;

        public Format build() {
            return new Format(this);
        }

        public Builder setAccessibilityChannel(int i3) {
            this.accessibilityChannel = i3;
            return this;
        }

        public Builder setAverageBitrate(int i3) {
            this.averageBitrate = i3;
            return this;
        }

        public Builder setChannelCount(int i3) {
            this.channelCount = i3;
            return this;
        }

        public Builder setCodecs(@Nullable String str) {
            this.codecs = str;
            return this;
        }

        public Builder setColorInfo(@Nullable ColorInfo colorInfo2) {
            this.colorInfo = colorInfo2;
            return this;
        }

        public Builder setContainerMimeType(@Nullable String str) {
            this.containerMimeType = str;
            return this;
        }

        public Builder setCryptoType(int i3) {
            this.cryptoType = i3;
            return this;
        }

        public Builder setDrmInitData(@Nullable DrmInitData drmInitData2) {
            this.drmInitData = drmInitData2;
            return this;
        }

        public Builder setEncoderDelay(int i3) {
            this.encoderDelay = i3;
            return this;
        }

        public Builder setEncoderPadding(int i3) {
            this.encoderPadding = i3;
            return this;
        }

        public Builder setFrameRate(float f2) {
            this.frameRate = f2;
            return this;
        }

        public Builder setHeight(int i3) {
            this.height = i3;
            return this;
        }

        public Builder setId(@Nullable String str) {
            this.id = str;
            return this;
        }

        public Builder setInitializationData(@Nullable List<byte[]> list) {
            this.initializationData = list;
            return this;
        }

        public Builder setLabel(@Nullable String str) {
            this.label = str;
            return this;
        }

        public Builder setLanguage(@Nullable String str) {
            this.language = str;
            return this;
        }

        public Builder setMaxInputSize(int i3) {
            this.maxInputSize = i3;
            return this;
        }

        public Builder setMetadata(@Nullable Metadata metadata2) {
            this.metadata = metadata2;
            return this;
        }

        public Builder setPcmEncoding(int i3) {
            this.pcmEncoding = i3;
            return this;
        }

        public Builder setPeakBitrate(int i3) {
            this.peakBitrate = i3;
            return this;
        }

        public Builder setPixelWidthHeightRatio(float f2) {
            this.pixelWidthHeightRatio = f2;
            return this;
        }

        public Builder setProjectionData(@Nullable byte[] bArr) {
            this.projectionData = bArr;
            return this;
        }

        public Builder setRoleFlags(int i3) {
            this.roleFlags = i3;
            return this;
        }

        public Builder setRotationDegrees(int i3) {
            this.rotationDegrees = i3;
            return this;
        }

        public Builder setSampleMimeType(@Nullable String str) {
            this.sampleMimeType = str;
            return this;
        }

        public Builder setSampleRate(int i3) {
            this.sampleRate = i3;
            return this;
        }

        public Builder setSelectionFlags(int i3) {
            this.selectionFlags = i3;
            return this;
        }

        public Builder setStereoMode(int i3) {
            this.stereoMode = i3;
            return this;
        }

        public Builder setSubsampleOffsetUs(long j2) {
            this.subsampleOffsetUs = j2;
            return this;
        }

        public Builder setWidth(int i3) {
            this.width = i3;
            return this;
        }

        public Builder() {
            this.averageBitrate = -1;
            this.peakBitrate = -1;
            this.maxInputSize = -1;
            this.subsampleOffsetUs = Long.MAX_VALUE;
            this.width = -1;
            this.height = -1;
            this.frameRate = -1.0f;
            this.pixelWidthHeightRatio = 1.0f;
            this.stereoMode = -1;
            this.channelCount = -1;
            this.sampleRate = -1;
            this.pcmEncoding = -1;
            this.accessibilityChannel = -1;
            this.cryptoType = 0;
        }

        public Builder setId(int i3) {
            this.id = Integer.toString(i3);
            return this;
        }

        private Builder(Format format) {
            this.id = format.id;
            this.label = format.label;
            this.language = format.language;
            this.selectionFlags = format.selectionFlags;
            this.roleFlags = format.roleFlags;
            this.averageBitrate = format.averageBitrate;
            this.peakBitrate = format.peakBitrate;
            this.codecs = format.codecs;
            this.metadata = format.metadata;
            this.containerMimeType = format.containerMimeType;
            this.sampleMimeType = format.sampleMimeType;
            this.maxInputSize = format.maxInputSize;
            this.initializationData = format.initializationData;
            this.drmInitData = format.drmInitData;
            this.subsampleOffsetUs = format.subsampleOffsetUs;
            this.width = format.width;
            this.height = format.height;
            this.frameRate = format.frameRate;
            this.rotationDegrees = format.rotationDegrees;
            this.pixelWidthHeightRatio = format.pixelWidthHeightRatio;
            this.projectionData = format.projectionData;
            this.stereoMode = format.stereoMode;
            this.colorInfo = format.colorInfo;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.pcmEncoding = format.pcmEncoding;
            this.encoderDelay = format.encoderDelay;
            this.encoderPadding = format.encoderPadding;
            this.accessibilityChannel = format.accessibilityChannel;
            this.cryptoType = format.cryptoType;
        }
    }

    @Deprecated
    public static Format createAudioSampleFormat(@Nullable String str, @Nullable String str2, @Nullable String str3, int i3, int i4, int i5, int i6, @Nullable List<byte[]> list, @Nullable DrmInitData drmInitData2, int i7, @Nullable String str4) {
        return new Builder().setId(str).setLanguage(str4).setSelectionFlags(i7).setAverageBitrate(i3).setPeakBitrate(i3).setCodecs(str3).setSampleMimeType(str2).setMaxInputSize(i4).setInitializationData(list).setDrmInitData(drmInitData2).setChannelCount(i5).setSampleRate(i6).build();
    }

    @Deprecated
    public static Format createContainerFormat(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, int i3, int i4, int i5, @Nullable String str6) {
        return new Builder().setId(str).setLabel(str2).setLanguage(str6).setSelectionFlags(i4).setRoleFlags(i5).setAverageBitrate(i3).setPeakBitrate(i3).setCodecs(str5).setContainerMimeType(str3).setSampleMimeType(str4).build();
    }

    @Deprecated
    public static Format createSampleFormat(@Nullable String str, @Nullable String str2) {
        return new Builder().setId(str).setSampleMimeType(str2).build();
    }

    @Deprecated
    public static Format createVideoSampleFormat(@Nullable String str, @Nullable String str2, @Nullable String str3, int i3, int i4, int i5, int i6, float f2, @Nullable List<byte[]> list, @Nullable DrmInitData drmInitData2) {
        return new Builder().setId(str).setAverageBitrate(i3).setPeakBitrate(i3).setCodecs(str3).setSampleMimeType(str2).setMaxInputSize(i4).setInitializationData(list).setDrmInitData(drmInitData2).setWidth(i5).setHeight(i6).setFrameRate(f2).build();
    }

    @Nullable
    private static <T> T defaultIfNull(@Nullable T t2, @Nullable T t3) {
        return t2 != null ? t2 : t3;
    }

    /* access modifiers changed from: private */
    public static Format fromBundle(Bundle bundle) {
        Builder builder = new Builder();
        BundleableUtil.ensureClassLoader(bundle);
        int i3 = 0;
        String string = bundle.getString(keyForField(0));
        Format format = DEFAULT;
        builder.setId((String) defaultIfNull(string, format.id)).setLabel((String) defaultIfNull(bundle.getString(keyForField(1)), format.label)).setLanguage((String) defaultIfNull(bundle.getString(keyForField(2)), format.language)).setSelectionFlags(bundle.getInt(keyForField(3), format.selectionFlags)).setRoleFlags(bundle.getInt(keyForField(4), format.roleFlags)).setAverageBitrate(bundle.getInt(keyForField(5), format.averageBitrate)).setPeakBitrate(bundle.getInt(keyForField(6), format.peakBitrate)).setCodecs((String) defaultIfNull(bundle.getString(keyForField(7)), format.codecs)).setMetadata((Metadata) defaultIfNull((Metadata) bundle.getParcelable(keyForField(8)), format.metadata)).setContainerMimeType((String) defaultIfNull(bundle.getString(keyForField(9)), format.containerMimeType)).setSampleMimeType((String) defaultIfNull(bundle.getString(keyForField(10)), format.sampleMimeType)).setMaxInputSize(bundle.getInt(keyForField(11), format.maxInputSize));
        ArrayList arrayList = new ArrayList();
        while (true) {
            byte[] byteArray = bundle.getByteArray(keyForInitializationData(i3));
            if (byteArray == null) {
                break;
            }
            arrayList.add(byteArray);
            i3++;
        }
        Builder drmInitData2 = builder.setInitializationData(arrayList).setDrmInitData((DrmInitData) bundle.getParcelable(keyForField(13)));
        String keyForField = keyForField(14);
        Format format2 = DEFAULT;
        drmInitData2.setSubsampleOffsetUs(bundle.getLong(keyForField, format2.subsampleOffsetUs)).setWidth(bundle.getInt(keyForField(15), format2.width)).setHeight(bundle.getInt(keyForField(16), format2.height)).setFrameRate(bundle.getFloat(keyForField(17), format2.frameRate)).setRotationDegrees(bundle.getInt(keyForField(18), format2.rotationDegrees)).setPixelWidthHeightRatio(bundle.getFloat(keyForField(19), format2.pixelWidthHeightRatio)).setProjectionData(bundle.getByteArray(keyForField(20))).setStereoMode(bundle.getInt(keyForField(21), format2.stereoMode));
        Bundle bundle2 = bundle.getBundle(keyForField(22));
        if (bundle2 != null) {
            builder.setColorInfo(ColorInfo.CREATOR.fromBundle(bundle2));
        }
        builder.setChannelCount(bundle.getInt(keyForField(23), format2.channelCount)).setSampleRate(bundle.getInt(keyForField(24), format2.sampleRate)).setPcmEncoding(bundle.getInt(keyForField(25), format2.pcmEncoding)).setEncoderDelay(bundle.getInt(keyForField(26), format2.encoderDelay)).setEncoderPadding(bundle.getInt(keyForField(27), format2.encoderPadding)).setAccessibilityChannel(bundle.getInt(keyForField(28), format2.accessibilityChannel)).setCryptoType(bundle.getInt(keyForField(29), format2.cryptoType));
        return builder.build();
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    private static String keyForInitializationData(int i3) {
        return keyForField(12) + "_" + Integer.toString(i3, 36);
    }

    public static String toLogString(@Nullable Format format) {
        if (format == null) {
            return AbstractJsonLexerKt.NULL;
        }
        StringBuilder p2 = A.a.p("id=");
        p2.append(format.id);
        p2.append(", mimeType=");
        p2.append(format.sampleMimeType);
        if (format.bitrate != -1) {
            p2.append(", bitrate=");
            p2.append(format.bitrate);
        }
        if (format.codecs != null) {
            p2.append(", codecs=");
            p2.append(format.codecs);
        }
        if (format.drmInitData != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i3 = 0;
            while (true) {
                DrmInitData drmInitData2 = format.drmInitData;
                if (i3 >= drmInitData2.schemeDataCount) {
                    break;
                }
                UUID uuid = drmInitData2.get(i3).uuid;
                if (uuid.equals(C.COMMON_PSSH_UUID)) {
                    linkedHashSet.add(C.CENC_TYPE_cenc);
                } else if (uuid.equals(C.CLEARKEY_UUID)) {
                    linkedHashSet.add("clearkey");
                } else if (uuid.equals(C.PLAYREADY_UUID)) {
                    linkedHashSet.add("playready");
                } else if (uuid.equals(C.WIDEVINE_UUID)) {
                    linkedHashSet.add("widevine");
                } else if (uuid.equals(C.UUID_NIL)) {
                    linkedHashSet.add("universal");
                } else {
                    linkedHashSet.add("unknown (" + uuid + ")");
                }
                i3++;
            }
            p2.append(", drm=[");
            Joiner.on((char) AbstractJsonLexerKt.COMMA).appendTo(p2, (Iterable<? extends Object>) linkedHashSet);
            p2.append(AbstractJsonLexerKt.END_LIST);
        }
        if (!(format.width == -1 || format.height == -1)) {
            p2.append(", res=");
            p2.append(format.width);
            p2.append("x");
            p2.append(format.height);
        }
        if (format.frameRate != -1.0f) {
            p2.append(", fps=");
            p2.append(format.frameRate);
        }
        if (format.channelCount != -1) {
            p2.append(", channels=");
            p2.append(format.channelCount);
        }
        if (format.sampleRate != -1) {
            p2.append(", sample_rate=");
            p2.append(format.sampleRate);
        }
        if (format.language != null) {
            p2.append(", language=");
            p2.append(format.language);
        }
        if (format.label != null) {
            p2.append(", label=");
            p2.append(format.label);
        }
        if (format.selectionFlags != 0) {
            ArrayList arrayList = new ArrayList();
            if ((format.selectionFlags & 4) != 0) {
                arrayList.add("auto");
            }
            if ((format.selectionFlags & 1) != 0) {
                arrayList.add("default");
            }
            if ((format.selectionFlags & 2) != 0) {
                arrayList.add("forced");
            }
            p2.append(", selectionFlags=[");
            Joiner.on((char) AbstractJsonLexerKt.COMMA).appendTo(p2, (Iterable<? extends Object>) arrayList);
            p2.append("]");
        }
        if (format.roleFlags != 0) {
            ArrayList arrayList2 = new ArrayList();
            if ((format.roleFlags & 1) != 0) {
                arrayList2.add("main");
            }
            if ((format.roleFlags & 2) != 0) {
                arrayList2.add("alt");
            }
            if ((format.roleFlags & 4) != 0) {
                arrayList2.add("supplementary");
            }
            if ((format.roleFlags & 8) != 0) {
                arrayList2.add("commentary");
            }
            if ((format.roleFlags & 16) != 0) {
                arrayList2.add("dub");
            }
            if ((format.roleFlags & 32) != 0) {
                arrayList2.add("emergency");
            }
            if ((format.roleFlags & 64) != 0) {
                arrayList2.add("caption");
            }
            if ((format.roleFlags & 128) != 0) {
                arrayList2.add("subtitle");
            }
            if ((format.roleFlags & 256) != 0) {
                arrayList2.add("sign");
            }
            if ((format.roleFlags & 512) != 0) {
                arrayList2.add("describes-video");
            }
            if ((format.roleFlags & 1024) != 0) {
                arrayList2.add("describes-music");
            }
            if ((format.roleFlags & 2048) != 0) {
                arrayList2.add("enhanced-intelligibility");
            }
            if ((format.roleFlags & 4096) != 0) {
                arrayList2.add("transcribes-dialog");
            }
            if ((format.roleFlags & 8192) != 0) {
                arrayList2.add("easy-read");
            }
            if ((format.roleFlags & 16384) != 0) {
                arrayList2.add("trick-play");
            }
            p2.append(", roleFlags=[");
            Joiner.on((char) AbstractJsonLexerKt.COMMA).appendTo(p2, (Iterable<? extends Object>) arrayList2);
            p2.append("]");
        }
        return p2.toString();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    @Deprecated
    public Format copyWithBitrate(int i3) {
        return buildUpon().setAverageBitrate(i3).setPeakBitrate(i3).build();
    }

    public Format copyWithCryptoType(int i3) {
        return buildUpon().setCryptoType(i3).build();
    }

    @Deprecated
    public Format copyWithDrmInitData(@Nullable DrmInitData drmInitData2) {
        return buildUpon().setDrmInitData(drmInitData2).build();
    }

    @Deprecated
    public Format copyWithFrameRate(float f2) {
        return buildUpon().setFrameRate(f2).build();
    }

    @Deprecated
    public Format copyWithGaplessInfo(int i3, int i4) {
        return buildUpon().setEncoderDelay(i3).setEncoderPadding(i4).build();
    }

    @Deprecated
    public Format copyWithLabel(@Nullable String str) {
        return buildUpon().setLabel(str).build();
    }

    @Deprecated
    public Format copyWithManifestFormatInfo(Format format) {
        return withManifestFormatInfo(format);
    }

    @Deprecated
    public Format copyWithMaxInputSize(int i3) {
        return buildUpon().setMaxInputSize(i3).build();
    }

    @Deprecated
    public Format copyWithMetadata(@Nullable Metadata metadata2) {
        return buildUpon().setMetadata(metadata2).build();
    }

    @Deprecated
    public Format copyWithSubsampleOffsetUs(long j2) {
        return buildUpon().setSubsampleOffsetUs(j2).build();
    }

    @Deprecated
    public Format copyWithVideoSize(int i3, int i4) {
        return buildUpon().setWidth(i3).setHeight(i4).build();
    }

    public boolean equals(@Nullable Object obj) {
        int i3;
        if (this == obj) {
            return true;
        }
        if (obj == null || Format.class != obj.getClass()) {
            return false;
        }
        Format format = (Format) obj;
        int i4 = this.hashCode;
        if (i4 == 0 || (i3 = format.hashCode) == 0 || i4 == i3) {
            return this.selectionFlags == format.selectionFlags && this.roleFlags == format.roleFlags && this.averageBitrate == format.averageBitrate && this.peakBitrate == format.peakBitrate && this.maxInputSize == format.maxInputSize && this.subsampleOffsetUs == format.subsampleOffsetUs && this.width == format.width && this.height == format.height && this.rotationDegrees == format.rotationDegrees && this.stereoMode == format.stereoMode && this.channelCount == format.channelCount && this.sampleRate == format.sampleRate && this.pcmEncoding == format.pcmEncoding && this.encoderDelay == format.encoderDelay && this.encoderPadding == format.encoderPadding && this.accessibilityChannel == format.accessibilityChannel && this.cryptoType == format.cryptoType && Float.compare(this.frameRate, format.frameRate) == 0 && Float.compare(this.pixelWidthHeightRatio, format.pixelWidthHeightRatio) == 0 && Util.areEqual(this.id, format.id) && Util.areEqual(this.label, format.label) && Util.areEqual(this.codecs, format.codecs) && Util.areEqual(this.containerMimeType, format.containerMimeType) && Util.areEqual(this.sampleMimeType, format.sampleMimeType) && Util.areEqual(this.language, format.language) && Arrays.equals(this.projectionData, format.projectionData) && Util.areEqual(this.metadata, format.metadata) && Util.areEqual(this.colorInfo, format.colorInfo) && Util.areEqual(this.drmInitData, format.drmInitData) && initializationDataEquals(format);
        }
        return false;
    }

    public int getPixelCount() {
        int i3;
        int i4 = this.width;
        if (i4 == -1 || (i3 = this.height) == -1) {
            return -1;
        }
        return i4 * i3;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.id;
            int i3 = 0;
            int hashCode2 = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.label;
            int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.language;
            int hashCode4 = (((((((((hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31) + this.averageBitrate) * 31) + this.peakBitrate) * 31;
            String str4 = this.codecs;
            int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Metadata metadata2 = this.metadata;
            int hashCode6 = (hashCode5 + (metadata2 == null ? 0 : metadata2.hashCode())) * 31;
            String str5 = this.containerMimeType;
            int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.sampleMimeType;
            if (str6 != null) {
                i3 = str6.hashCode();
            }
            this.hashCode = ((((((((((((((((Float.floatToIntBits(this.pixelWidthHeightRatio) + ((((Float.floatToIntBits(this.frameRate) + ((((((((((hashCode7 + i3) * 31) + this.maxInputSize) * 31) + ((int) this.subsampleOffsetUs)) * 31) + this.width) * 31) + this.height) * 31)) * 31) + this.rotationDegrees) * 31)) * 31) + this.stereoMode) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31) + this.accessibilityChannel) * 31) + this.cryptoType;
        }
        return this.hashCode;
    }

    public boolean initializationDataEquals(Format format) {
        if (this.initializationData.size() != format.initializationData.size()) {
            return false;
        }
        for (int i3 = 0; i3 < this.initializationData.size(); i3++) {
            if (!Arrays.equals(this.initializationData.get(i3), format.initializationData.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(keyForField(0), this.id);
        bundle.putString(keyForField(1), this.label);
        bundle.putString(keyForField(2), this.language);
        bundle.putInt(keyForField(3), this.selectionFlags);
        bundle.putInt(keyForField(4), this.roleFlags);
        bundle.putInt(keyForField(5), this.averageBitrate);
        bundle.putInt(keyForField(6), this.peakBitrate);
        bundle.putString(keyForField(7), this.codecs);
        bundle.putParcelable(keyForField(8), this.metadata);
        bundle.putString(keyForField(9), this.containerMimeType);
        bundle.putString(keyForField(10), this.sampleMimeType);
        bundle.putInt(keyForField(11), this.maxInputSize);
        for (int i3 = 0; i3 < this.initializationData.size(); i3++) {
            bundle.putByteArray(keyForInitializationData(i3), this.initializationData.get(i3));
        }
        bundle.putParcelable(keyForField(13), this.drmInitData);
        bundle.putLong(keyForField(14), this.subsampleOffsetUs);
        bundle.putInt(keyForField(15), this.width);
        bundle.putInt(keyForField(16), this.height);
        bundle.putFloat(keyForField(17), this.frameRate);
        bundle.putInt(keyForField(18), this.rotationDegrees);
        bundle.putFloat(keyForField(19), this.pixelWidthHeightRatio);
        bundle.putByteArray(keyForField(20), this.projectionData);
        bundle.putInt(keyForField(21), this.stereoMode);
        if (this.colorInfo != null) {
            bundle.putBundle(keyForField(22), this.colorInfo.toBundle());
        }
        bundle.putInt(keyForField(23), this.channelCount);
        bundle.putInt(keyForField(24), this.sampleRate);
        bundle.putInt(keyForField(25), this.pcmEncoding);
        bundle.putInt(keyForField(26), this.encoderDelay);
        bundle.putInt(keyForField(27), this.encoderPadding);
        bundle.putInt(keyForField(28), this.accessibilityChannel);
        bundle.putInt(keyForField(29), this.cryptoType);
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Format(");
        sb.append(this.id);
        sb.append(", ");
        sb.append(this.label);
        sb.append(", ");
        sb.append(this.containerMimeType);
        sb.append(", ");
        sb.append(this.sampleMimeType);
        sb.append(", ");
        sb.append(this.codecs);
        sb.append(", ");
        sb.append(this.bitrate);
        sb.append(", ");
        sb.append(this.language);
        sb.append(", [");
        sb.append(this.width);
        sb.append(", ");
        sb.append(this.height);
        sb.append(", ");
        sb.append(this.frameRate);
        sb.append("], [");
        sb.append(this.channelCount);
        sb.append(", ");
        return A.a.m(sb, "])", this.sampleRate);
    }

    public Format withManifestFormatInfo(Format format) {
        String str;
        if (this == format) {
            return this;
        }
        int trackType = MimeTypes.getTrackType(this.sampleMimeType);
        String str2 = format.id;
        String str3 = format.label;
        if (str3 == null) {
            str3 = this.label;
        }
        String str4 = this.language;
        if ((trackType == 3 || trackType == 1) && (str = format.language) != null) {
            str4 = str;
        }
        int i3 = this.averageBitrate;
        if (i3 == -1) {
            i3 = format.averageBitrate;
        }
        int i4 = this.peakBitrate;
        if (i4 == -1) {
            i4 = format.peakBitrate;
        }
        String str5 = this.codecs;
        if (str5 == null) {
            String codecsOfType = Util.getCodecsOfType(format.codecs, trackType);
            if (Util.splitCodecs(codecsOfType).length == 1) {
                str5 = codecsOfType;
            }
        }
        Metadata metadata2 = this.metadata;
        Metadata copyWithAppendedEntriesFrom = metadata2 == null ? format.metadata : metadata2.copyWithAppendedEntriesFrom(format.metadata);
        float f2 = this.frameRate;
        if (f2 == -1.0f && trackType == 2) {
            f2 = format.frameRate;
        }
        int i5 = this.selectionFlags | format.selectionFlags;
        return buildUpon().setId(str2).setLabel(str3).setLanguage(str4).setSelectionFlags(i5).setRoleFlags(this.roleFlags | format.roleFlags).setAverageBitrate(i3).setPeakBitrate(i4).setCodecs(str5).setMetadata(copyWithAppendedEntriesFrom).setDrmInitData(DrmInitData.createSessionCreationData(format.drmInitData, this.drmInitData)).setFrameRate(f2).build();
    }

    private Format(Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.language = Util.normalizeLanguageCode(builder.language);
        this.selectionFlags = builder.selectionFlags;
        this.roleFlags = builder.roleFlags;
        int access$600 = builder.averageBitrate;
        this.averageBitrate = access$600;
        int access$700 = builder.peakBitrate;
        this.peakBitrate = access$700;
        this.bitrate = access$700 != -1 ? access$700 : access$600;
        this.codecs = builder.codecs;
        this.metadata = builder.metadata;
        this.containerMimeType = builder.containerMimeType;
        this.sampleMimeType = builder.sampleMimeType;
        this.maxInputSize = builder.maxInputSize;
        this.initializationData = builder.initializationData == null ? Collections.emptyList() : builder.initializationData;
        DrmInitData access$1400 = builder.drmInitData;
        this.drmInitData = access$1400;
        this.subsampleOffsetUs = builder.subsampleOffsetUs;
        this.width = builder.width;
        this.height = builder.height;
        this.frameRate = builder.frameRate;
        int i3 = 0;
        this.rotationDegrees = builder.rotationDegrees == -1 ? 0 : builder.rotationDegrees;
        this.pixelWidthHeightRatio = builder.pixelWidthHeightRatio == -1.0f ? 1.0f : builder.pixelWidthHeightRatio;
        this.projectionData = builder.projectionData;
        this.stereoMode = builder.stereoMode;
        this.colorInfo = builder.colorInfo;
        this.channelCount = builder.channelCount;
        this.sampleRate = builder.sampleRate;
        this.pcmEncoding = builder.pcmEncoding;
        this.encoderDelay = builder.encoderDelay == -1 ? 0 : builder.encoderDelay;
        this.encoderPadding = builder.encoderPadding != -1 ? builder.encoderPadding : i3;
        this.accessibilityChannel = builder.accessibilityChannel;
        if (builder.cryptoType != 0 || access$1400 == null) {
            this.cryptoType = builder.cryptoType;
        } else {
            this.cryptoType = 1;
        }
    }

    @Deprecated
    public static Format createVideoSampleFormat(@Nullable String str, @Nullable String str2, @Nullable String str3, int i3, int i4, int i5, int i6, float f2, @Nullable List<byte[]> list, int i7, float f3, @Nullable DrmInitData drmInitData2) {
        return new Builder().setId(str).setAverageBitrate(i3).setPeakBitrate(i3).setCodecs(str3).setSampleMimeType(str2).setMaxInputSize(i4).setInitializationData(list).setDrmInitData(drmInitData2).setWidth(i5).setHeight(i6).setFrameRate(f2).setRotationDegrees(i7).setPixelWidthHeightRatio(f3).build();
    }

    @Deprecated
    public static Format createAudioSampleFormat(@Nullable String str, @Nullable String str2, @Nullable String str3, int i3, int i4, int i5, int i6, int i7, @Nullable List<byte[]> list, @Nullable DrmInitData drmInitData2, int i8, @Nullable String str4) {
        return new Builder().setId(str).setLanguage(str4).setSelectionFlags(i8).setAverageBitrate(i3).setPeakBitrate(i3).setCodecs(str3).setSampleMimeType(str2).setMaxInputSize(i4).setInitializationData(list).setDrmInitData(drmInitData2).setChannelCount(i5).setSampleRate(i6).setPcmEncoding(i7).build();
    }
}
