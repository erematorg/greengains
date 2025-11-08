package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.annotation.SuppressLint;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.CheckResult;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.compose.animation.core.a;
import com.adjust.sdk.Constants;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.CharUtils;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

@SuppressLint({"InlinedApi"})
public final class MediaCodecUtil {
    private static final String CODEC_ID_AV01 = "av01";
    private static final String CODEC_ID_AVC1 = "avc1";
    private static final String CODEC_ID_AVC2 = "avc2";
    private static final String CODEC_ID_HEV1 = "hev1";
    private static final String CODEC_ID_HVC1 = "hvc1";
    private static final String CODEC_ID_MP4A = "mp4a";
    private static final String CODEC_ID_VP09 = "vp09";
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    private static final String TAG = "MediaCodecUtil";
    @GuardedBy("MediaCodecUtil.class")
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap<>();
    private static int maxH264DecodableFrameSize = -1;

    public static final class CodecKey {
        public final String mimeType;
        public final boolean secure;
        public final boolean tunneling;

        public CodecKey(String str, boolean z2, boolean z3) {
            this.mimeType = str;
            this.secure = z2;
            this.tunneling = z3;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            return TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure && this.tunneling == codecKey.tunneling;
        }

        public int hashCode() {
            int i3 = 1237;
            int i4 = (a.i(this.mimeType, 31, 31) + (this.secure ? 1231 : 1237)) * 31;
            if (this.tunneling) {
                i3 = 1231;
            }
            return i4 + i3;
        }
    }

    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    public interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i3);

        boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    public static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        private MediaCodecListCompatV16() {
        }

        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo getCodecInfoAt(int i3) {
            return MediaCodecList.getCodecInfoAt(i3);
        }

        public boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return false;
        }

        public boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return "secure-playback".equals(str) && MimeTypes.VIDEO_H264.equals(str2);
        }

        public boolean secureDecodersExplicit() {
            return false;
        }
    }

    @RequiresApi(21)
    public static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        @Nullable
        private MediaCodecInfo[] mediaCodecInfos;

        public MediaCodecListCompatV21(boolean z2, boolean z3) {
            this.codecKind = (z2 || z3) ? 1 : 0;
        }

        @EnsuresNonNull({"mediaCodecInfos"})
        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }

        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public MediaCodecInfo getCodecInfoAt(int i3) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i3];
        }

        public boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureRequired(str);
        }

        public boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported(str);
        }

        public boolean secureDecodersExplicit() {
            return true;
        }
    }

    public interface ScoreProvider<T> {
        int getScore(T t2);
    }

    private MediaCodecUtil() {
    }

    private static void applyWorkarounds(String str, List<MediaCodecInfo> list) {
        if (MimeTypes.AUDIO_RAW.equals(str)) {
            if (Util.SDK_INT < 26 && Util.DEVICE.equals("R9") && list.size() == 1 && list.get(0).name.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                list.add(MediaCodecInfo.newInstance("OMX.google.raw.decoder", MimeTypes.AUDIO_RAW, MimeTypes.AUDIO_RAW, (MediaCodecInfo.CodecCapabilities) null, false, true, false, false, false));
            }
            sortByScore(list, new h(0));
        }
        int i3 = Util.SDK_INT;
        if (i3 < 21 && list.size() > 1) {
            String str2 = list.get(0).name;
            if ("OMX.SEC.mp3.dec".equals(str2) || "OMX.SEC.MP3.Decoder".equals(str2) || "OMX.brcm.audio.mp3.decoder".equals(str2)) {
                sortByScore(list, new h(1));
            }
        }
        if (i3 < 32 && list.size() > 1 && "OMX.qti.audio.decoder.flac".equals(list.get(0).name)) {
            list.add(list.remove(0));
        }
    }

    private static int av1LevelNumberToConst(int i3) {
        switch (i3) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            case 11:
                return 2048;
            case 12:
                return 4096;
            case 13:
                return 8192;
            case 14:
                return 16384;
            case 15:
                return 32768;
            case 16:
                return 65536;
            case 17:
                return 131072;
            case 18:
                return 262144;
            case 19:
                return 524288;
            case 20:
                return 1048576;
            case 21:
                return 2097152;
            case 22:
                return 4194304;
            case 23:
                return 8388608;
            default:
                return -1;
        }
    }

    private static int avcLevelNumberToConst(int i3) {
        switch (i3) {
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 8;
            case 13:
                return 16;
            default:
                switch (i3) {
                    case 20:
                        return 32;
                    case 21:
                        return 64;
                    case 22:
                        return 128;
                    default:
                        switch (i3) {
                            case 30:
                                return 256;
                            case 31:
                                return 512;
                            case 32:
                                return 1024;
                            default:
                                switch (i3) {
                                    case 40:
                                        return 2048;
                                    case 41:
                                        return 4096;
                                    case 42:
                                        return 8192;
                                    default:
                                        switch (i3) {
                                            case 50:
                                                return 16384;
                                            case 51:
                                                return 32768;
                                            case 52:
                                                return 65536;
                                            default:
                                                return -1;
                                        }
                                }
                        }
                }
        }
    }

    private static int avcLevelToMaxFrameSize(int i3) {
        if (i3 == 1 || i3 == 2) {
            return 25344;
        }
        switch (i3) {
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
            case 65536:
                return 9437184;
            case 131072:
            case 262144:
            case 524288:
                return 35651584;
            default:
                return -1;
        }
    }

    private static int avcProfileNumberToConst(int i3) {
        if (i3 == 66) {
            return 1;
        }
        if (i3 == 77) {
            return 2;
        }
        if (i3 == 88) {
            return 4;
        }
        if (i3 == 100) {
            return 8;
        }
        if (i3 == 110) {
            return 16;
        }
        if (i3 != 122) {
            return i3 != 244 ? -1 : 64;
        }
        return 32;
    }

    @VisibleForTesting
    public static synchronized void clearDecoderInfoCache() {
        synchronized (MediaCodecUtil.class) {
            decoderInfosCache.clear();
        }
    }

    @Nullable
    private static Integer dolbyVisionStringToLevel(@Nullable String str) {
        if (str == null) {
            return null;
        }
        char c3 = 65535;
        switch (str.hashCode()) {
            case 1537:
                if (str.equals("01")) {
                    c3 = 0;
                    break;
                }
                break;
            case 1538:
                if (str.equals("02")) {
                    c3 = 1;
                    break;
                }
                break;
            case 1539:
                if (str.equals("03")) {
                    c3 = 2;
                    break;
                }
                break;
            case 1540:
                if (str.equals("04")) {
                    c3 = 3;
                    break;
                }
                break;
            case 1541:
                if (str.equals("05")) {
                    c3 = 4;
                    break;
                }
                break;
            case 1542:
                if (str.equals("06")) {
                    c3 = 5;
                    break;
                }
                break;
            case 1543:
                if (str.equals("07")) {
                    c3 = 6;
                    break;
                }
                break;
            case 1544:
                if (str.equals("08")) {
                    c3 = 7;
                    break;
                }
                break;
            case 1545:
                if (str.equals("09")) {
                    c3 = 8;
                    break;
                }
                break;
            case 1567:
                if (str.equals("10")) {
                    c3 = 9;
                    break;
                }
                break;
            case 1568:
                if (str.equals("11")) {
                    c3 = 10;
                    break;
                }
                break;
            case 1569:
                if (str.equals("12")) {
                    c3 = 11;
                    break;
                }
                break;
            case 1570:
                if (str.equals("13")) {
                    c3 = 12;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            case 11:
                return 2048;
            case 12:
                return 4096;
            default:
                return null;
        }
    }

    @Nullable
    private static Integer dolbyVisionStringToProfile(@Nullable String str) {
        if (str == null) {
            return null;
        }
        char c3 = 65535;
        switch (str.hashCode()) {
            case 1536:
                if (str.equals("00")) {
                    c3 = 0;
                    break;
                }
                break;
            case 1537:
                if (str.equals("01")) {
                    c3 = 1;
                    break;
                }
                break;
            case 1538:
                if (str.equals("02")) {
                    c3 = 2;
                    break;
                }
                break;
            case 1539:
                if (str.equals("03")) {
                    c3 = 3;
                    break;
                }
                break;
            case 1540:
                if (str.equals("04")) {
                    c3 = 4;
                    break;
                }
                break;
            case 1541:
                if (str.equals("05")) {
                    c3 = 5;
                    break;
                }
                break;
            case 1542:
                if (str.equals("06")) {
                    c3 = 6;
                    break;
                }
                break;
            case 1543:
                if (str.equals("07")) {
                    c3 = 7;
                    break;
                }
                break;
            case 1544:
                if (str.equals("08")) {
                    c3 = 8;
                    break;
                }
                break;
            case 1545:
                if (str.equals("09")) {
                    c3 = 9;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            default:
                return null;
        }
    }

    @Nullable
    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String str, String[] strArr) {
        int mp4aAudioObjectTypeToProfile;
        if (strArr.length != 3) {
            h.a("Ignoring malformed MP4A codec string: ", str, TAG);
            return null;
        }
        try {
            if (MimeTypes.AUDIO_AAC.equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(strArr[1], 16))) && (mp4aAudioObjectTypeToProfile = mp4aAudioObjectTypeToProfile(Integer.parseInt(strArr[2]))) != -1) {
                return new Pair<>(Integer.valueOf(mp4aAudioObjectTypeToProfile), 0);
            }
        } catch (NumberFormatException unused) {
            h.a("Ignoring malformed MP4A codec string: ", str, TAG);
        }
        return null;
    }

    @Nullable
    public static String getAlternativeCodecMimeType(Format format) {
        Pair<Integer, Integer> codecProfileAndLevel;
        if (MimeTypes.AUDIO_E_AC3_JOC.equals(format.sampleMimeType)) {
            return MimeTypes.AUDIO_E_AC3;
        }
        if (!MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) || (codecProfileAndLevel = getCodecProfileAndLevel(format)) == null) {
            return null;
        }
        int intValue = ((Integer) codecProfileAndLevel.first).intValue();
        if (intValue == 16 || intValue == 256) {
            return MimeTypes.VIDEO_H265;
        }
        if (intValue == 512) {
            return MimeTypes.VIDEO_H264;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r8 = r10.colorTransfer;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair<java.lang.Integer, java.lang.Integer> getAv1ProfileAndLevel(java.lang.String r8, java.lang.String[] r9, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.common.video.ColorInfo r10) {
        /*
            int r0 = r9.length
            r1 = 4
            java.lang.String r2 = "Ignoring malformed AV1 codec string: "
            r3 = 0
            java.lang.String r4 = "MediaCodecUtil"
            if (r0 >= r1) goto L_0x000d
            com.appsamurai.storyly.exoplayer2.core.mediacodec.h.a(r2, r8, r4)
            return r3
        L_0x000d:
            r0 = 1
            r1 = r9[r0]     // Catch:{ NumberFormatException -> 0x006d }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x006d }
            r5 = 2
            r6 = r9[r5]     // Catch:{ NumberFormatException -> 0x006d }
            r7 = 0
            java.lang.String r6 = r6.substring(r7, r5)     // Catch:{ NumberFormatException -> 0x006d }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x006d }
            r7 = 3
            r9 = r9[r7]     // Catch:{ NumberFormatException -> 0x006d }
            int r8 = java.lang.Integer.parseInt(r9)     // Catch:{ NumberFormatException -> 0x006d }
            if (r1 == 0) goto L_0x002f
            java.lang.String r8 = "Unknown AV1 profile: "
            com.appsamurai.storyly.exoplayer2.core.r.a(r1, r8, r4)
            return r3
        L_0x002f:
            r9 = 8
            if (r8 == r9) goto L_0x003d
            r1 = 10
            if (r8 == r1) goto L_0x003d
            java.lang.String r9 = "Unknown AV1 bit depth: "
            com.appsamurai.storyly.exoplayer2.core.r.a(r8, r9, r4)
            return r3
        L_0x003d:
            if (r8 != r9) goto L_0x0040
            goto L_0x0052
        L_0x0040:
            if (r10 == 0) goto L_0x0051
            byte[] r8 = r10.hdrStaticInfo
            if (r8 != 0) goto L_0x004e
            int r8 = r10.colorTransfer
            r9 = 7
            if (r8 == r9) goto L_0x004e
            r9 = 6
            if (r8 != r9) goto L_0x0051
        L_0x004e:
            r0 = 4096(0x1000, float:5.74E-42)
            goto L_0x0052
        L_0x0051:
            r0 = r5
        L_0x0052:
            int r8 = av1LevelNumberToConst(r6)
            r9 = -1
            if (r8 != r9) goto L_0x005f
            java.lang.String r8 = "Unknown AV1 level: "
            com.appsamurai.storyly.exoplayer2.core.r.a(r6, r8, r4)
            return r3
        L_0x005f:
            android.util.Pair r9 = new android.util.Pair
            java.lang.Integer r10 = java.lang.Integer.valueOf(r0)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9.<init>(r10, r8)
            return r9
        L_0x006d:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.h.a(r2, r8, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil.getAv1ProfileAndLevel(java.lang.String, java.lang.String[], com.appsamurai.storyly.exoplayer2.common.video.ColorInfo):android.util.Pair");
    }

    @Nullable
    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        int i3;
        int i4;
        if (strArr.length < 2) {
            h.a("Ignoring malformed AVC codec string: ", str, TAG);
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                i4 = Integer.parseInt(strArr[1].substring(0, 2), 16);
                i3 = Integer.parseInt(strArr[1].substring(4), 16);
            } else if (strArr.length >= 3) {
                int parseInt = Integer.parseInt(strArr[1]);
                i3 = Integer.parseInt(strArr[2]);
                i4 = parseInt;
            } else {
                Log.w(TAG, "Ignoring malformed AVC codec string: " + str);
                return null;
            }
            int avcProfileNumberToConst = avcProfileNumberToConst(i4);
            if (avcProfileNumberToConst == -1) {
                r.a(i4, "Unknown AVC profile: ", TAG);
                return null;
            }
            int avcLevelNumberToConst = avcLevelNumberToConst(i3);
            if (avcLevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(avcProfileNumberToConst), Integer.valueOf(avcLevelNumberToConst));
            }
            r.a(i3, "Unknown AVC level: ", TAG);
            return null;
        } catch (NumberFormatException unused) {
            h.a("Ignoring malformed AVC codec string: ", str, TAG);
            return null;
        }
    }

    @Nullable
    private static String getCodecMimeType(MediaCodecInfo mediaCodecInfo, String str, String str2) {
        for (String str3 : mediaCodecInfo.getSupportedTypes()) {
            if (str3.equalsIgnoreCase(str2)) {
                return str3;
            }
        }
        if (str2.equals(MimeTypes.VIDEO_DOLBY_VISION)) {
            if ("OMX.MS.HEVCDV.Decoder".equals(str)) {
                return "video/hevcdv";
            }
            if ("OMX.RTK.video.decoder".equals(str) || "OMX.realtek.video.decoder.tunneled".equals(str)) {
                return "video/dv_hevc";
            }
            return null;
        } else if (str2.equals(MimeTypes.AUDIO_ALAC) && "OMX.lge.alac.decoder".equals(str)) {
            return "audio/x-lg-alac";
        } else {
            if (str2.equals(MimeTypes.AUDIO_FLAC) && "OMX.lge.flac.decoder".equals(str)) {
                return "audio/x-lg-flac";
            }
            if (!str2.equals(MimeTypes.AUDIO_AC3) || !"OMX.lge.ac3.decoder".equals(str)) {
                return null;
            }
            return "audio/lg-ac3";
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0077, code lost:
        if (r3.equals(CODEC_ID_AV01) == false) goto L_0x002c;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(com.appsamurai.storyly.exoplayer2.common.Format r6) {
        /*
            r0 = 0
            java.lang.String r1 = r6.codecs
            r2 = 0
            if (r1 != 0) goto L_0x0007
            return r2
        L_0x0007:
            java.lang.String r3 = "\\."
            java.lang.String[] r1 = r1.split(r3)
            java.lang.String r3 = "video/dolby-vision"
            java.lang.String r4 = r6.sampleMimeType
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x001f
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getDolbyVisionProfileAndLevel(r6, r1)
            return r6
        L_0x001f:
            r3 = r1[r0]
            r3.getClass()
            r4 = -1
            int r5 = r3.hashCode()
            switch(r5) {
                case 3004662: goto L_0x0071;
                case 3006243: goto L_0x0066;
                case 3006244: goto L_0x005b;
                case 3199032: goto L_0x0050;
                case 3214780: goto L_0x0045;
                case 3356560: goto L_0x003a;
                case 3624515: goto L_0x002e;
                default: goto L_0x002c;
            }
        L_0x002c:
            r0 = r4
            goto L_0x007a
        L_0x002e:
            java.lang.String r0 = "vp09"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0038
            goto L_0x002c
        L_0x0038:
            r0 = 6
            goto L_0x007a
        L_0x003a:
            java.lang.String r0 = "mp4a"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0043
            goto L_0x002c
        L_0x0043:
            r0 = 5
            goto L_0x007a
        L_0x0045:
            java.lang.String r0 = "hvc1"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x004e
            goto L_0x002c
        L_0x004e:
            r0 = 4
            goto L_0x007a
        L_0x0050:
            java.lang.String r0 = "hev1"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0059
            goto L_0x002c
        L_0x0059:
            r0 = 3
            goto L_0x007a
        L_0x005b:
            java.lang.String r0 = "avc2"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0064
            goto L_0x002c
        L_0x0064:
            r0 = 2
            goto L_0x007a
        L_0x0066:
            java.lang.String r0 = "avc1"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x006f
            goto L_0x002c
        L_0x006f:
            r0 = 1
            goto L_0x007a
        L_0x0071:
            java.lang.String r5 = "av01"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L_0x007a
            goto L_0x002c
        L_0x007a:
            switch(r0) {
                case 0: goto L_0x009a;
                case 1: goto L_0x0093;
                case 2: goto L_0x0093;
                case 3: goto L_0x008c;
                case 4: goto L_0x008c;
                case 5: goto L_0x0085;
                case 6: goto L_0x007e;
                default: goto L_0x007d;
            }
        L_0x007d:
            return r2
        L_0x007e:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getVp9ProfileAndLevel(r6, r1)
            return r6
        L_0x0085:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getAacCodecProfileAndLevel(r6, r1)
            return r6
        L_0x008c:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getHevcProfileAndLevel(r6, r1)
            return r6
        L_0x0093:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getAvcProfileAndLevel(r6, r1)
            return r6
        L_0x009a:
            java.lang.String r0 = r6.codecs
            com.appsamurai.storyly.exoplayer2.common.video.ColorInfo r6 = r6.colorInfo
            android.util.Pair r6 = getAv1ProfileAndLevel(r0, r1, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(com.appsamurai.storyly.exoplayer2.common.Format):android.util.Pair");
    }

    @Nullable
    public static MediaCodecInfo getDecoderInfo(String str, boolean z2, boolean z3) throws DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(str, z2, z3);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String str, boolean z2, boolean z3) throws DecoderQueryException {
        synchronized (MediaCodecUtil.class) {
            try {
                CodecKey codecKey = new CodecKey(str, z2, z3);
                HashMap<CodecKey, List<MediaCodecInfo>> hashMap = decoderInfosCache;
                List<MediaCodecInfo> list = hashMap.get(codecKey);
                if (list != null) {
                    return list;
                }
                int i3 = Util.SDK_INT;
                ArrayList<MediaCodecInfo> decoderInfosInternal = getDecoderInfosInternal(codecKey, i3 >= 21 ? new MediaCodecListCompatV21(z2, z3) : new MediaCodecListCompatV16());
                if (z2 && decoderInfosInternal.isEmpty() && 21 <= i3 && i3 <= 23) {
                    decoderInfosInternal = getDecoderInfosInternal(codecKey, new MediaCodecListCompatV16());
                    if (!decoderInfosInternal.isEmpty()) {
                        Log.w(TAG, "MediaCodecList API didn't list secure decoder for: " + str + ". Assuming: " + decoderInfosInternal.get(0).name);
                    }
                }
                applyWorkarounds(str, decoderInfosInternal);
                ImmutableList<MediaCodecInfo> copyOf = ImmutableList.copyOf(decoderInfosInternal);
                hashMap.put(codecKey, copyOf);
                return copyOf;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        if (r1.secure == false) goto L_0x008f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0106 A[SYNTHETIC, Splitter:B:60:0x0106] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0131 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo> getDecoderInfosInternal(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil.CodecKey r24, com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil.MediaCodecListCompat r25) throws com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            r1 = r24
            r2 = r25
            java.lang.String r3 = "secure-playback"
            java.lang.String r4 = "tunneled-playback"
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x012f }
            r5.<init>()     // Catch:{ Exception -> 0x012f }
            java.lang.String r15 = r1.mimeType     // Catch:{ Exception -> 0x012f }
            int r14 = r25.getCodecCount()     // Catch:{ Exception -> 0x012f }
            boolean r13 = r25.secureDecodersExplicit()     // Catch:{ Exception -> 0x012f }
            r0 = 0
            r12 = r0
        L_0x001a:
            if (r12 >= r14) goto L_0x0155
            android.media.MediaCodecInfo r0 = r2.getCodecInfoAt(r12)     // Catch:{ Exception -> 0x012f }
            boolean r6 = isAlias(r0)     // Catch:{ Exception -> 0x012f }
            if (r6 == 0) goto L_0x002e
        L_0x0026:
            r22 = r12
            r23 = r13
            r18 = r14
            goto L_0x0125
        L_0x002e:
            java.lang.String r11 = r0.getName()     // Catch:{ Exception -> 0x012f }
            boolean r6 = isCodecUsableDecoder(r0, r11, r13, r15)     // Catch:{ Exception -> 0x012f }
            if (r6 != 0) goto L_0x0039
            goto L_0x0026
        L_0x0039:
            java.lang.String r10 = getCodecMimeType(r0, r11, r15)     // Catch:{ Exception -> 0x012f }
            if (r10 != 0) goto L_0x0040
            goto L_0x0026
        L_0x0040:
            android.media.MediaCodecInfo$CodecCapabilities r9 = r0.getCapabilitiesForType(r10)     // Catch:{ Exception -> 0x007d }
            boolean r6 = r2.isFeatureSupported(r4, r10, r9)     // Catch:{ Exception -> 0x007d }
            boolean r7 = r2.isFeatureRequired(r4, r10, r9)     // Catch:{ Exception -> 0x007d }
            boolean r8 = r1.tunneling     // Catch:{ Exception -> 0x007d }
            if (r8 != 0) goto L_0x0052
            if (r7 != 0) goto L_0x0026
        L_0x0052:
            if (r8 == 0) goto L_0x0057
            if (r6 != 0) goto L_0x0057
            goto L_0x0026
        L_0x0057:
            boolean r6 = r2.isFeatureSupported(r3, r10, r9)     // Catch:{ Exception -> 0x007d }
            boolean r7 = r2.isFeatureRequired(r3, r10, r9)     // Catch:{ Exception -> 0x007d }
            boolean r8 = r1.secure     // Catch:{ Exception -> 0x007d }
            if (r8 != 0) goto L_0x0065
            if (r7 != 0) goto L_0x0026
        L_0x0065:
            if (r8 == 0) goto L_0x006a
            if (r6 != 0) goto L_0x006a
            goto L_0x0026
        L_0x006a:
            boolean r16 = isHardwareAccelerated(r0, r15)     // Catch:{ Exception -> 0x007d }
            boolean r17 = isSoftwareOnly(r0, r15)     // Catch:{ Exception -> 0x007d }
            boolean r0 = isVendor(r0)     // Catch:{ Exception -> 0x007d }
            if (r13 == 0) goto L_0x0089
            boolean r7 = r1.secure     // Catch:{ Exception -> 0x007d }
            if (r7 == r6) goto L_0x008f
            goto L_0x0089
        L_0x007d:
            r0 = move-exception
            r20 = r10
            r1 = r11
            r22 = r12
            r23 = r13
            r18 = r14
            goto L_0x00fe
        L_0x0089:
            if (r13 != 0) goto L_0x00b6
            boolean r7 = r1.secure     // Catch:{ Exception -> 0x00c1 }
            if (r7 != 0) goto L_0x00b6
        L_0x008f:
            r18 = 0
            r19 = 0
            r6 = r11
            r7 = r15
            r8 = r10
            r20 = r10
            r10 = r16
            r21 = r11
            r11 = r17
            r22 = r12
            r12 = r0
            r23 = r13
            r13 = r18
            r18 = r14
            r14 = r19
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo r0 = com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo.newInstance(r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x00b2 }
            r5.add(r0)     // Catch:{ Exception -> 0x00b2 }
            goto L_0x0125
        L_0x00b2:
            r0 = move-exception
        L_0x00b3:
            r1 = r21
            goto L_0x00fe
        L_0x00b6:
            r20 = r10
            r21 = r11
            r22 = r12
            r23 = r13
            r18 = r14
            goto L_0x00cd
        L_0x00c1:
            r0 = move-exception
            r20 = r10
            r21 = r11
            r22 = r12
            r23 = r13
            r18 = r14
            goto L_0x00b3
        L_0x00cd:
            if (r23 != 0) goto L_0x0125
            if (r6 == 0) goto L_0x0125
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b2 }
            r6.<init>()     // Catch:{ Exception -> 0x00b2 }
            r14 = r21
            r6.append(r14)     // Catch:{ Exception -> 0x00fc }
            java.lang.String r7 = ".secure"
            r6.append(r7)     // Catch:{ Exception -> 0x00fc }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00fc }
            r13 = 0
            r19 = 1
            r7 = r15
            r8 = r20
            r10 = r16
            r11 = r17
            r12 = r0
            r1 = r14
            r14 = r19
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo r0 = com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo.newInstance(r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x00fa }
            r5.add(r0)     // Catch:{ Exception -> 0x00fa }
            return r5
        L_0x00fa:
            r0 = move-exception
            goto L_0x00fe
        L_0x00fc:
            r0 = move-exception
            r1 = r14
        L_0x00fe:
            int r6 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT     // Catch:{ Exception -> 0x012f }
            r7 = 23
            java.lang.String r8 = "MediaCodecUtil"
            if (r6 > r7) goto L_0x0131
            boolean r6 = r5.isEmpty()     // Catch:{ Exception -> 0x012f }
            if (r6 != 0) goto L_0x0131
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012f }
            r0.<init>()     // Catch:{ Exception -> 0x012f }
            java.lang.String r6 = "Skipping codec "
            r0.append(r6)     // Catch:{ Exception -> 0x012f }
            r0.append(r1)     // Catch:{ Exception -> 0x012f }
            java.lang.String r1 = " (failed to query capabilities)"
            r0.append(r1)     // Catch:{ Exception -> 0x012f }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x012f }
            com.appsamurai.storyly.exoplayer2.common.util.Log.e(r8, r0)     // Catch:{ Exception -> 0x012f }
        L_0x0125:
            int r12 = r22 + 1
            r1 = r24
            r14 = r18
            r13 = r23
            goto L_0x001a
        L_0x012f:
            r0 = move-exception
            goto L_0x0156
        L_0x0131:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012f }
            r2.<init>()     // Catch:{ Exception -> 0x012f }
            java.lang.String r3 = "Failed to query codec "
            r2.append(r3)     // Catch:{ Exception -> 0x012f }
            r2.append(r1)     // Catch:{ Exception -> 0x012f }
            java.lang.String r1 = " ("
            r2.append(r1)     // Catch:{ Exception -> 0x012f }
            r1 = r20
            r2.append(r1)     // Catch:{ Exception -> 0x012f }
            java.lang.String r1 = ")"
            r2.append(r1)     // Catch:{ Exception -> 0x012f }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x012f }
            com.appsamurai.storyly.exoplayer2.common.util.Log.e(r8, r1)     // Catch:{ Exception -> 0x012f }
            throw r0     // Catch:{ Exception -> 0x012f }
        L_0x0155:
            return r5
        L_0x0156:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil$DecoderQueryException r1 = new com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil$DecoderQueryException
            r2 = 0
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil.getDecoderInfosInternal(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil$CodecKey, com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil$MediaCodecListCompat):java.util.ArrayList");
    }

    @CheckResult
    public static List<MediaCodecInfo> getDecoderInfosSortedByFormatSupport(List<MediaCodecInfo> list, Format format) {
        ArrayList arrayList = new ArrayList(list);
        sortByScore(arrayList, new f(format));
        return arrayList;
    }

    @Nullable
    public static MediaCodecInfo getDecryptOnlyDecoderInfo() throws DecoderQueryException {
        return getDecoderInfo(MimeTypes.AUDIO_RAW, false, false);
    }

    @Nullable
    private static Pair<Integer, Integer> getDolbyVisionProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            h.a("Ignoring malformed Dolby Vision codec string: ", str, TAG);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            h.a("Ignoring malformed Dolby Vision codec string: ", str, TAG);
            return null;
        }
        String group = matcher.group(1);
        Integer dolbyVisionStringToProfile = dolbyVisionStringToProfile(group);
        if (dolbyVisionStringToProfile == null) {
            h.a("Unknown Dolby Vision profile string: ", group, TAG);
            return null;
        }
        String str2 = strArr[2];
        Integer dolbyVisionStringToLevel = dolbyVisionStringToLevel(str2);
        if (dolbyVisionStringToLevel != null) {
            return new Pair<>(dolbyVisionStringToProfile, dolbyVisionStringToLevel);
        }
        h.a("Unknown Dolby Vision level string: ", str2, TAG);
        return null;
    }

    @Nullable
    private static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 4) {
            h.a("Ignoring malformed HEVC codec string: ", str, TAG);
            return null;
        }
        int i3 = 1;
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            h.a("Ignoring malformed HEVC codec string: ", str, TAG);
            return null;
        }
        String group = matcher.group(1);
        if (!"1".equals(group)) {
            if ("2".equals(group)) {
                i3 = 2;
            } else {
                h.a("Unknown HEVC profile string: ", group, TAG);
                return null;
            }
        }
        String str2 = strArr[3];
        Integer hevcCodecStringToProfileLevel = hevcCodecStringToProfileLevel(str2);
        if (hevcCodecStringToProfileLevel != null) {
            return new Pair<>(Integer.valueOf(i3), hevcCodecStringToProfileLevel);
        }
        h.a("Unknown HEVC level string: ", str2, TAG);
        return null;
    }

    @Nullable
    private static Pair<Integer, Integer> getVp9ProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            h.a("Ignoring malformed VP9 codec string: ", str, TAG);
            return null;
        }
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2]);
            int vp9ProfileNumberToConst = vp9ProfileNumberToConst(parseInt);
            if (vp9ProfileNumberToConst == -1) {
                r.a(parseInt, "Unknown VP9 profile: ", TAG);
                return null;
            }
            int vp9LevelNumberToConst = vp9LevelNumberToConst(parseInt2);
            if (vp9LevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(vp9ProfileNumberToConst), Integer.valueOf(vp9LevelNumberToConst));
            }
            r.a(parseInt2, "Unknown VP9 level: ", TAG);
            return null;
        } catch (NumberFormatException unused) {
            h.a("Ignoring malformed VP9 codec string: ", str, TAG);
            return null;
        }
    }

    @Nullable
    private static Integer hevcCodecStringToProfileLevel(@Nullable String str) {
        if (str == null) {
            return null;
        }
        char c3 = 65535;
        switch (str.hashCode()) {
            case 70821:
                if (str.equals("H30")) {
                    c3 = 0;
                    break;
                }
                break;
            case 70914:
                if (str.equals("H60")) {
                    c3 = 1;
                    break;
                }
                break;
            case 70917:
                if (str.equals("H63")) {
                    c3 = 2;
                    break;
                }
                break;
            case 71007:
                if (str.equals("H90")) {
                    c3 = 3;
                    break;
                }
                break;
            case 71010:
                if (str.equals("H93")) {
                    c3 = 4;
                    break;
                }
                break;
            case 74665:
                if (str.equals("L30")) {
                    c3 = 5;
                    break;
                }
                break;
            case 74758:
                if (str.equals("L60")) {
                    c3 = 6;
                    break;
                }
                break;
            case 74761:
                if (str.equals("L63")) {
                    c3 = 7;
                    break;
                }
                break;
            case 74851:
                if (str.equals("L90")) {
                    c3 = 8;
                    break;
                }
                break;
            case 74854:
                if (str.equals("L93")) {
                    c3 = 9;
                    break;
                }
                break;
            case 2193639:
                if (str.equals("H120")) {
                    c3 = 10;
                    break;
                }
                break;
            case 2193642:
                if (str.equals("H123")) {
                    c3 = 11;
                    break;
                }
                break;
            case 2193732:
                if (str.equals("H150")) {
                    c3 = 12;
                    break;
                }
                break;
            case 2193735:
                if (str.equals("H153")) {
                    c3 = CharUtils.CR;
                    break;
                }
                break;
            case 2193738:
                if (str.equals("H156")) {
                    c3 = 14;
                    break;
                }
                break;
            case 2193825:
                if (str.equals("H180")) {
                    c3 = 15;
                    break;
                }
                break;
            case 2193828:
                if (str.equals("H183")) {
                    c3 = 16;
                    break;
                }
                break;
            case 2193831:
                if (str.equals("H186")) {
                    c3 = 17;
                    break;
                }
                break;
            case 2312803:
                if (str.equals("L120")) {
                    c3 = 18;
                    break;
                }
                break;
            case 2312806:
                if (str.equals("L123")) {
                    c3 = 19;
                    break;
                }
                break;
            case 2312896:
                if (str.equals("L150")) {
                    c3 = 20;
                    break;
                }
                break;
            case 2312899:
                if (str.equals("L153")) {
                    c3 = 21;
                    break;
                }
                break;
            case 2312902:
                if (str.equals("L156")) {
                    c3 = 22;
                    break;
                }
                break;
            case 2312989:
                if (str.equals("L180")) {
                    c3 = 23;
                    break;
                }
                break;
            case 2312992:
                if (str.equals("L183")) {
                    c3 = 24;
                    break;
                }
                break;
            case 2312995:
                if (str.equals("L186")) {
                    c3 = 25;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return 2;
            case 1:
                return 8;
            case 2:
                return 32;
            case 3:
                return 128;
            case 4:
                return 512;
            case 5:
                return 1;
            case 6:
                return 4;
            case 7:
                return 16;
            case 8:
                return 64;
            case 9:
                return 256;
            case 10:
                return 2048;
            case 11:
                return 8192;
            case 12:
                return 32768;
            case 13:
                return 131072;
            case 14:
                return 524288;
            case 15:
                return 2097152;
            case 16:
                return 8388608;
            case 17:
                return 33554432;
            case 18:
                return 1024;
            case 19:
                return 4096;
            case 20:
                return 16384;
            case 21:
                return 65536;
            case 22:
                return 262144;
            case 23:
                return 1048576;
            case 24:
                return 4194304;
            case 25:
                return 16777216;
            default:
                return null;
        }
    }

    private static boolean isAlias(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 29 && isAliasV29(mediaCodecInfo);
    }

    @RequiresApi(29)
    private static boolean isAliasV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isAlias();
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z2, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z2 && str.endsWith(".secure"))) {
            return false;
        }
        int i3 = Util.SDK_INT;
        if (i3 < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (i3 < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str)) {
            String str3 = Util.DEVICE;
            if ("a70".equals(str3) || ("Xiaomi".equals(Util.MANUFACTURER) && str3.startsWith("HM"))) {
                return false;
            }
        }
        if (i3 == 16 && "OMX.qcom.audio.decoder.mp3".equals(str)) {
            String str4 = Util.DEVICE;
            if ("dlxu".equals(str4) || "protou".equals(str4) || "ville".equals(str4) || "villeplus".equals(str4) || "villec2".equals(str4) || str4.startsWith("gee") || "C6602".equals(str4) || "C6603".equals(str4) || "C6606".equals(str4) || "C6616".equals(str4) || "L36h".equals(str4) || "SO-02E".equals(str4)) {
                return false;
            }
        }
        if (i3 == 16 && "OMX.qcom.audio.decoder.aac".equals(str)) {
            String str5 = Util.DEVICE;
            if ("C1504".equals(str5) || "C1505".equals(str5) || "C1604".equals(str5) || "C1605".equals(str5)) {
                return false;
            }
        }
        if (i3 < 24 && (("OMX.SEC.aac.dec".equals(str) || "OMX.Exynos.AAC.Decoder".equals(str)) && Constants.REFERRER_API_SAMSUNG.equals(Util.MANUFACTURER))) {
            String str6 = Util.DEVICE;
            if (str6.startsWith("zeroflte") || str6.startsWith("zerolte") || str6.startsWith("zenlte") || "SC-05G".equals(str6) || "marinelteatt".equals(str6) || "404SC".equals(str6) || "SC-04G".equals(str6) || "SCV31".equals(str6)) {
                return false;
            }
        }
        if (i3 <= 19 && "OMX.SEC.vp8.dec".equals(str) && Constants.REFERRER_API_SAMSUNG.equals(Util.MANUFACTURER)) {
            String str7 = Util.DEVICE;
            if (str7.startsWith("d2") || str7.startsWith("serrano") || str7.startsWith("jflte") || str7.startsWith("santos") || str7.startsWith("t0")) {
                return false;
            }
        }
        if (i3 > 19 || !Util.DEVICE.startsWith("jflte") || !"OMX.qcom.video.decoder.vp8".equals(str)) {
            return i3 > 23 || !MimeTypes.AUDIO_E_AC3_JOC.equals(str2) || !"OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str);
        }
        return false;
    }

    private static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo, String str) {
        return Util.SDK_INT >= 29 ? isHardwareAcceleratedV29(mediaCodecInfo) : !isSoftwareOnly(mediaCodecInfo, str);
    }

    @RequiresApi(29)
    private static boolean isHardwareAcceleratedV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isHardwareAccelerated();
    }

    private static boolean isSoftwareOnly(MediaCodecInfo mediaCodecInfo, String str) {
        if (Util.SDK_INT >= 29) {
            return isSoftwareOnlyV29(mediaCodecInfo);
        }
        if (MimeTypes.isAudio(str)) {
            return true;
        }
        String lowerCase = Ascii.toLowerCase(mediaCodecInfo.getName());
        if (lowerCase.startsWith("arc.")) {
            return false;
        }
        if (lowerCase.startsWith("omx.google.") || lowerCase.startsWith("omx.ffmpeg.")) {
            return true;
        }
        if ((!lowerCase.startsWith("omx.sec.") || !lowerCase.contains(".sw.")) && !lowerCase.equals("omx.qcom.video.decoder.hevcswvdec") && !lowerCase.startsWith("c2.android.") && !lowerCase.startsWith("c2.google.")) {
            return !lowerCase.startsWith("omx.") && !lowerCase.startsWith("c2.");
        }
        return true;
    }

    @RequiresApi(29)
    private static boolean isSoftwareOnlyV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isSoftwareOnly();
    }

    private static boolean isVendor(MediaCodecInfo mediaCodecInfo) {
        if (Util.SDK_INT >= 29) {
            return isVendorV29(mediaCodecInfo);
        }
        String lowerCase = Ascii.toLowerCase(mediaCodecInfo.getName());
        return !lowerCase.startsWith("omx.google.") && !lowerCase.startsWith("c2.android.") && !lowerCase.startsWith("c2.google.");
    }

    @RequiresApi(29)
    private static boolean isVendorV29(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isVendor();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$applyWorkarounds$1(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.name;
        if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
            return 1;
        }
        return (Util.SDK_INT >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : -1;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$applyWorkarounds$2(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.name.startsWith("OMX.google") ? 1 : 0;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$getDecoderInfosSortedByFormatSupport$0(Format format, MediaCodecInfo mediaCodecInfo) {
        try {
            return mediaCodecInfo.isFormatSupported(format) ? 1 : 0;
        } catch (DecoderQueryException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortByScore$3(ScoreProvider scoreProvider, Object obj, Object obj2) {
        return scoreProvider.getScore(obj2) - scoreProvider.getScore(obj);
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int i3 = 0;
            MediaCodecInfo decoderInfo = getDecoderInfo(MimeTypes.VIDEO_H264, false, false);
            if (decoderInfo != null) {
                MediaCodecInfo.CodecProfileLevel[] profileLevels = decoderInfo.getProfileLevels();
                int length = profileLevels.length;
                int i4 = 0;
                while (i3 < length) {
                    i4 = Math.max(avcLevelToMaxFrameSize(profileLevels[i3].level), i4);
                    i3++;
                }
                i3 = Math.max(i4, Util.SDK_INT >= 21 ? 345600 : 172800);
            }
            maxH264DecodableFrameSize = i3;
        }
        return maxH264DecodableFrameSize;
    }

    private static int mp4aAudioObjectTypeToProfile(int i3) {
        int i4 = 17;
        if (i3 != 17) {
            i4 = 20;
            if (i3 != 20) {
                i4 = 23;
                if (i3 != 23) {
                    i4 = 29;
                    if (i3 != 29) {
                        i4 = 39;
                        if (i3 != 39) {
                            i4 = 42;
                            if (i3 != 42) {
                                switch (i3) {
                                    case 1:
                                        return 1;
                                    case 2:
                                        return 2;
                                    case 3:
                                        return 3;
                                    case 4:
                                        return 4;
                                    case 5:
                                        return 5;
                                    case 6:
                                        return 6;
                                    default:
                                        return -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return i4;
    }

    private static <T> void sortByScore(List<T> list, ScoreProvider<T> scoreProvider) {
        Collections.sort(list, new g(scoreProvider));
    }

    private static int vp9LevelNumberToConst(int i3) {
        if (i3 == 10) {
            return 1;
        }
        if (i3 == 11) {
            return 2;
        }
        if (i3 == 20) {
            return 4;
        }
        if (i3 == 21) {
            return 8;
        }
        if (i3 == 30) {
            return 16;
        }
        if (i3 == 31) {
            return 32;
        }
        if (i3 == 40) {
            return 64;
        }
        if (i3 == 41) {
            return 128;
        }
        if (i3 == 50) {
            return 256;
        }
        if (i3 == 51) {
            return 512;
        }
        switch (i3) {
            case 60:
                return 2048;
            case 61:
                return 4096;
            case 62:
                return 8192;
            default:
                return -1;
        }
    }

    private static int vp9ProfileNumberToConst(int i3) {
        if (i3 == 0) {
            return 1;
        }
        if (i3 == 1) {
            return 2;
        }
        if (i3 != 2) {
            return i3 != 3 ? -1 : 8;
        }
        return 4;
    }

    public static void warmDecoderInfoCache(String str, boolean z2, boolean z3) {
        try {
            getDecoderInfos(str, z2, z3);
        } catch (DecoderQueryException e3) {
            Log.e(TAG, "Codec warming failed", e3);
        }
    }
}
