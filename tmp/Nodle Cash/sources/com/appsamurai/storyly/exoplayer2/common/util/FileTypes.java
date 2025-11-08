package com.appsamurai.storyly.exoplayer2.common.util;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

public final class FileTypes {
    public static final int AC3 = 0;
    public static final int AC4 = 1;
    public static final int ADTS = 2;
    public static final int AMR = 3;
    public static final int AVI = 16;
    private static final String EXTENSION_AAC = ".aac";
    private static final String EXTENSION_AC3 = ".ac3";
    private static final String EXTENSION_AC4 = ".ac4";
    private static final String EXTENSION_ADTS = ".adts";
    private static final String EXTENSION_AMR = ".amr";
    private static final String EXTENSION_AVI = ".avi";
    private static final String EXTENSION_EC3 = ".ec3";
    private static final String EXTENSION_FLAC = ".flac";
    private static final String EXTENSION_FLV = ".flv";
    private static final String EXTENSION_JPEG = ".jpeg";
    private static final String EXTENSION_JPG = ".jpg";
    private static final String EXTENSION_M2P = ".m2p";
    private static final String EXTENSION_MID = ".mid";
    private static final String EXTENSION_MIDI = ".midi";
    private static final String EXTENSION_MP3 = ".mp3";
    private static final String EXTENSION_MP4 = ".mp4";
    private static final String EXTENSION_MPEG = ".mpeg";
    private static final String EXTENSION_MPG = ".mpg";
    private static final String EXTENSION_OPUS = ".opus";
    private static final String EXTENSION_PREFIX_CMF = ".cmf";
    private static final String EXTENSION_PREFIX_M4 = ".m4";
    private static final String EXTENSION_PREFIX_MK = ".mk";
    private static final String EXTENSION_PREFIX_MP4 = ".mp4";
    private static final String EXTENSION_PREFIX_OG = ".og";
    private static final String EXTENSION_PREFIX_TS = ".ts";
    private static final String EXTENSION_PS = ".ps";
    private static final String EXTENSION_SMF = ".smf";
    private static final String EXTENSION_TS = ".ts";
    private static final String EXTENSION_VTT = ".vtt";
    private static final String EXTENSION_WAV = ".wav";
    private static final String EXTENSION_WAVE = ".wave";
    private static final String EXTENSION_WEBM = ".webm";
    private static final String EXTENSION_WEBVTT = ".webvtt";
    public static final int FLAC = 4;
    public static final int FLV = 5;
    @VisibleForTesting
    static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final int JPEG = 14;
    public static final int MATROSKA = 6;
    public static final int MIDI = 15;
    public static final int MP3 = 7;
    public static final int MP4 = 8;
    public static final int OGG = 9;
    public static final int PS = 10;
    public static final int TS = 11;
    public static final int UNKNOWN = -1;
    public static final int WAV = 12;
    public static final int WEBVTT = 13;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private FileTypes() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int inferFileTypeFromMimeType(@androidx.annotation.Nullable java.lang.String r18) {
        /*
            r0 = 16
            r1 = 15
            r2 = 14
            r3 = 13
            r4 = 12
            r5 = 11
            r7 = 9
            r8 = 8
            r9 = 7
            r10 = 6
            r11 = 5
            r12 = 4
            r13 = 3
            r14 = 1
            r16 = -1
            if (r18 != 0) goto L_0x001b
            return r16
        L_0x001b:
            java.lang.String r15 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.normalizeMimeType(r18)
            r15.getClass()
            int r17 = r15.hashCode()
            switch(r17) {
                case -2123537834: goto L_0x0174;
                case -1662384011: goto L_0x0167;
                case -1662384007: goto L_0x015a;
                case -1662095187: goto L_0x014d;
                case -1606874997: goto L_0x0141;
                case -1487394660: goto L_0x0135;
                case -1248337486: goto L_0x0129;
                case -1079884372: goto L_0x011c;
                case -1004728940: goto L_0x010e;
                case -387023398: goto L_0x0101;
                case -43467528: goto L_0x00f3;
                case 13915911: goto L_0x00e5;
                case 187078296: goto L_0x00d8;
                case 187078297: goto L_0x00cb;
                case 187078669: goto L_0x00be;
                case 187090232: goto L_0x00b1;
                case 187091926: goto L_0x00a4;
                case 187099443: goto L_0x0097;
                case 1331848029: goto L_0x0089;
                case 1503095341: goto L_0x007c;
                case 1504578661: goto L_0x006f;
                case 1504619009: goto L_0x0062;
                case 1504824762: goto L_0x0055;
                case 1504831518: goto L_0x0048;
                case 1505118770: goto L_0x003b;
                case 2039520277: goto L_0x002d;
                default: goto L_0x0029;
            }
        L_0x0029:
            r6 = r16
            goto L_0x017f
        L_0x002d:
            java.lang.String r6 = "video/x-matroska"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0037
            goto L_0x0029
        L_0x0037:
            r6 = 25
            goto L_0x017f
        L_0x003b:
            java.lang.String r6 = "audio/webm"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0044
            goto L_0x0029
        L_0x0044:
            r6 = 24
            goto L_0x017f
        L_0x0048:
            java.lang.String r6 = "audio/mpeg"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0051
            goto L_0x0029
        L_0x0051:
            r6 = 23
            goto L_0x017f
        L_0x0055:
            java.lang.String r6 = "audio/midi"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x005e
            goto L_0x0029
        L_0x005e:
            r6 = 22
            goto L_0x017f
        L_0x0062:
            java.lang.String r6 = "audio/flac"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x006b
            goto L_0x0029
        L_0x006b:
            r6 = 21
            goto L_0x017f
        L_0x006f:
            java.lang.String r6 = "audio/eac3"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0078
            goto L_0x0029
        L_0x0078:
            r6 = 20
            goto L_0x017f
        L_0x007c:
            java.lang.String r6 = "audio/3gpp"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0085
            goto L_0x0029
        L_0x0085:
            r6 = 19
            goto L_0x017f
        L_0x0089:
            java.lang.String r6 = "video/mp4"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0093
            goto L_0x0029
        L_0x0093:
            r6 = 18
            goto L_0x017f
        L_0x0097:
            java.lang.String r6 = "audio/wav"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00a0
            goto L_0x0029
        L_0x00a0:
            r6 = 17
            goto L_0x017f
        L_0x00a4:
            java.lang.String r6 = "audio/ogg"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00ae
            goto L_0x0029
        L_0x00ae:
            r6 = r0
            goto L_0x017f
        L_0x00b1:
            java.lang.String r6 = "audio/mp4"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00bb
            goto L_0x0029
        L_0x00bb:
            r6 = r1
            goto L_0x017f
        L_0x00be:
            java.lang.String r6 = "audio/amr"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00c8
            goto L_0x0029
        L_0x00c8:
            r6 = r2
            goto L_0x017f
        L_0x00cb:
            java.lang.String r6 = "audio/ac4"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00d5
            goto L_0x0029
        L_0x00d5:
            r6 = r3
            goto L_0x017f
        L_0x00d8:
            java.lang.String r6 = "audio/ac3"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00e2
            goto L_0x0029
        L_0x00e2:
            r6 = r4
            goto L_0x017f
        L_0x00e5:
            java.lang.String r6 = "video/x-flv"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00f0
            goto L_0x0029
        L_0x00f0:
            r6 = r5
            goto L_0x017f
        L_0x00f3:
            java.lang.String r6 = "application/webm"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x0029
        L_0x00fd:
            r6 = 10
            goto L_0x017f
        L_0x0101:
            java.lang.String r6 = "audio/x-matroska"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x0029
        L_0x010b:
            r6 = r7
            goto L_0x017f
        L_0x010e:
            java.lang.String r6 = "text/vtt"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x0029
        L_0x0119:
            r6 = r8
            goto L_0x017f
        L_0x011c:
            java.lang.String r6 = "video/x-msvideo"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x0029
        L_0x0127:
            r6 = r9
            goto L_0x017f
        L_0x0129:
            java.lang.String r6 = "application/mp4"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0133
            goto L_0x0029
        L_0x0133:
            r6 = r10
            goto L_0x017f
        L_0x0135:
            java.lang.String r6 = "image/jpeg"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x013f
            goto L_0x0029
        L_0x013f:
            r6 = r11
            goto L_0x017f
        L_0x0141:
            java.lang.String r6 = "audio/amr-wb"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x014b
            goto L_0x0029
        L_0x014b:
            r6 = r12
            goto L_0x017f
        L_0x014d:
            java.lang.String r6 = "video/webm"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0158
            goto L_0x0029
        L_0x0158:
            r6 = r13
            goto L_0x017f
        L_0x015a:
            java.lang.String r6 = "video/mp2t"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0165
            goto L_0x0029
        L_0x0165:
            r6 = 2
            goto L_0x017f
        L_0x0167:
            java.lang.String r6 = "video/mp2p"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x0172
            goto L_0x0029
        L_0x0172:
            r6 = r14
            goto L_0x017f
        L_0x0174:
            java.lang.String r6 = "audio/eac3-joc"
            boolean r6 = r15.equals(r6)
            if (r6 != 0) goto L_0x017e
            goto L_0x0029
        L_0x017e:
            r6 = 0
        L_0x017f:
            switch(r6) {
                case 0: goto L_0x0194;
                case 1: goto L_0x0191;
                case 2: goto L_0x0190;
                case 3: goto L_0x018f;
                case 4: goto L_0x018e;
                case 5: goto L_0x018d;
                case 6: goto L_0x018c;
                case 7: goto L_0x018b;
                case 8: goto L_0x018a;
                case 9: goto L_0x018f;
                case 10: goto L_0x018f;
                case 11: goto L_0x0189;
                case 12: goto L_0x0194;
                case 13: goto L_0x0188;
                case 14: goto L_0x018e;
                case 15: goto L_0x018c;
                case 16: goto L_0x0187;
                case 17: goto L_0x0186;
                case 18: goto L_0x018c;
                case 19: goto L_0x018e;
                case 20: goto L_0x0194;
                case 21: goto L_0x0185;
                case 22: goto L_0x0184;
                case 23: goto L_0x0183;
                case 24: goto L_0x018f;
                case 25: goto L_0x018f;
                default: goto L_0x0182;
            }
        L_0x0182:
            return r16
        L_0x0183:
            return r9
        L_0x0184:
            return r1
        L_0x0185:
            return r12
        L_0x0186:
            return r4
        L_0x0187:
            return r7
        L_0x0188:
            return r14
        L_0x0189:
            return r11
        L_0x018a:
            return r3
        L_0x018b:
            return r0
        L_0x018c:
            return r8
        L_0x018d:
            return r2
        L_0x018e:
            return r13
        L_0x018f:
            return r10
        L_0x0190:
            return r5
        L_0x0191:
            r0 = 10
            return r0
        L_0x0194:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.FileTypes.inferFileTypeFromMimeType(java.lang.String):int");
    }

    public static int inferFileTypeFromResponseHeaders(Map<String, List<String>> map) {
        List list = map.get("Content-Type");
        return inferFileTypeFromMimeType((list == null || list.isEmpty()) ? null : (String) list.get(0));
    }

    public static int inferFileTypeFromUri(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            return -1;
        }
        if (lastPathSegment.endsWith(EXTENSION_AC3) || lastPathSegment.endsWith(EXTENSION_EC3)) {
            return 0;
        }
        if (lastPathSegment.endsWith(EXTENSION_AC4)) {
            return 1;
        }
        if (lastPathSegment.endsWith(EXTENSION_ADTS) || lastPathSegment.endsWith(EXTENSION_AAC)) {
            return 2;
        }
        if (lastPathSegment.endsWith(EXTENSION_AMR)) {
            return 3;
        }
        if (lastPathSegment.endsWith(EXTENSION_FLAC)) {
            return 4;
        }
        if (lastPathSegment.endsWith(EXTENSION_FLV)) {
            return 5;
        }
        if (lastPathSegment.endsWith(EXTENSION_MID) || lastPathSegment.endsWith(EXTENSION_MIDI) || lastPathSegment.endsWith(EXTENSION_SMF)) {
            return 15;
        }
        if (lastPathSegment.startsWith(EXTENSION_PREFIX_MK, lastPathSegment.length() - 4) || lastPathSegment.endsWith(EXTENSION_WEBM)) {
            return 6;
        }
        if (lastPathSegment.endsWith(EXTENSION_MP3)) {
            return 7;
        }
        if (lastPathSegment.endsWith(".mp4") || lastPathSegment.startsWith(EXTENSION_PREFIX_M4, lastPathSegment.length() - 4) || lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5) || lastPathSegment.startsWith(EXTENSION_PREFIX_CMF, lastPathSegment.length() - 5)) {
            return 8;
        }
        if (lastPathSegment.startsWith(EXTENSION_PREFIX_OG, lastPathSegment.length() - 4) || lastPathSegment.endsWith(EXTENSION_OPUS)) {
            return 9;
        }
        if (lastPathSegment.endsWith(EXTENSION_PS) || lastPathSegment.endsWith(EXTENSION_MPEG) || lastPathSegment.endsWith(EXTENSION_MPG) || lastPathSegment.endsWith(EXTENSION_M2P)) {
            return 10;
        }
        if (lastPathSegment.endsWith(".ts") || lastPathSegment.startsWith(".ts", lastPathSegment.length() - 4)) {
            return 11;
        }
        if (lastPathSegment.endsWith(EXTENSION_WAV) || lastPathSegment.endsWith(EXTENSION_WAVE)) {
            return 12;
        }
        if (lastPathSegment.endsWith(EXTENSION_VTT) || lastPathSegment.endsWith(EXTENSION_WEBVTT)) {
            return 13;
        }
        if (lastPathSegment.endsWith(EXTENSION_JPG) || lastPathSegment.endsWith(EXTENSION_JPEG)) {
            return 14;
        }
        return lastPathSegment.endsWith(EXTENSION_AVI) ? 16 : -1;
    }
}
