package com.appsamurai.storyly.exoplayer2.common.util;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.SystemClock;
import android.security.NetworkSecurityPolicy;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseLongArray;
import android.view.Display;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.os.EnvironmentCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.work.impl.a;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import jnr.ffi.provider.jffi.JNINativeInterface;
import org.apache.commons.lang3.time.TimeZones;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.objectweb.asm.signature.SignatureVisitor;

public final class Util {
    private static final int[] CRC32_BYTES_MSBF = {0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
    private static final int[] CRC8_BYTES_MSBF = {0, 7, 14, 9, 28, 27, 18, 21, 56, 63, 54, 49, 36, 35, 42, 45, 112, 119, 126, 121, 108, 107, 98, 101, 72, 79, 70, 65, 84, 83, 90, 93, 224, JNINativeInterface.GetDirectBufferCapacity, 238, 233, 252, 251, 242, 245, JNINativeInterface.UnregisterNatives, JNINativeInterface.ReleasePrimitiveArrayCritical, JNINativeInterface.SetDoubleArrayRegion, JNINativeInterface.SetCharArrayRegion, 196, 195, 202, 205, 144, 151, 158, 153, 140, 139, 130, 133, 168, 175, 166, 161, 180, 179, 186, 189, 199, 192, 201, 206, JNINativeInterface.GetJavaVM, JNINativeInterface.GetStringRegion, JNINativeInterface.SetFloatArrayRegion, JNINativeInterface.SetShortArrayRegion, 255, 248, 241, 246, JNINativeInterface.DeleteWeakGlobalRef, JNINativeInterface.ExceptionCheck, 237, 234, 183, 176, 185, 190, 171, 172, 165, 162, 143, 136, 129, 134, 147, 148, 157, 154, 39, 32, 41, 46, 59, 60, 53, 50, 31, 24, 17, 22, 3, 4, 13, 10, 87, 80, 89, 94, 75, 76, 69, 66, 111, 104, 97, 102, 115, 116, 125, 122, 137, 142, 135, 128, 149, 146, 155, 156, 177, 182, 191, 184, 173, 170, 163, 164, 249, 254, 247, 240, JNINativeInterface.NewDirectByteBuffer, JNINativeInterface.NewWeakGlobalRef, 235, 236, 193, 198, 207, 200, JNINativeInterface.GetStringUTFRegion, JNINativeInterface.MonitorExit, 211, JNINativeInterface.SetLongArrayRegion, 105, 110, 103, 96, 117, 114, 123, 124, 81, 86, 95, 88, 77, 74, 67, 68, 25, 30, 23, 16, 5, 2, 11, 12, 33, 38, 47, 40, 61, 58, 51, 52, 78, 73, 64, 71, 82, 85, 92, 91, 118, 113, 120, 127, 106, 109, 100, 99, 62, 57, 48, 55, 34, 37, 44, 43, 6, 1, 8, 15, 26, 29, 20, 19, 174, 169, 160, 167, 178, 181, 188, 187, 150, 145, 152, 159, 138, 141, 132, 131, JNINativeInterface.GetPrimitiveArrayCritical, JNINativeInterface.MonitorEnter, JNINativeInterface.SetByteArrayRegion, JNINativeInterface.RegisterNatives, 194, 197, 204, 203, JNINativeInterface.GetDirectBufferAddress, JNINativeInterface.ReleaseStringCritical, JNINativeInterface.GetObjectRefType, 239, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 253, 244, 243};
    public static final String DEVICE;
    public static final String DEVICE_DEBUG_INFO;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final Pattern ESCAPED_CHARACTER_PATTERN = Pattern.compile("%([A-Fa-f0-9]{2})");
    private static final String ISM_DASH_FORMAT_EXTENSION = "format=mpd-time-csf";
    private static final String ISM_HLS_FORMAT_EXTENSION = "format=m3u8-aapl";
    private static final Pattern ISM_PATH_PATTERN = Pattern.compile("(?:.*\\.)?isml?(?:/(manifest(.*))?)?", 2);
    public static final String MANUFACTURER;
    public static final String MODEL;
    public static final int SDK_INT;
    private static final String TAG = "Util";
    private static final Pattern XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
    private static final Pattern XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
    private static final String[] additionalIsoLanguageReplacements = {"alb", "sq", "arm", "hy", "baq", "eu", "bur", "my", "tib", "bo", "chi", "zh", "cze", "cs", "dut", "nl", "ger", "de", "gre", "el", "fre", "fr", "geo", "ka", "ice", "is", "mac", "mk", "mao", "mi", "may", "ms", "per", "fa", "rum", "ro", "scc", "hbs-srp", "slo", "sk", "wel", "cy", TtmlNode.ATTR_ID, "ms-ind", "iw", "he", "heb", "he", "ji", "yi", "arb", "ar-arb", "in", "ms-ind", "ind", "ms-ind", "nb", "no-nob", "nob", "no-nob", "nn", "no-nno", "nno", "no-nno", "tw", "ak-twi", "twi", "ak-twi", "bs", "hbs-bos", "bos", "hbs-bos", "hr", "hbs-hrv", "hrv", "hbs-hrv", "sr", "hbs-srp", "srp", "hbs-srp", "cmn", "zh-cmn", "hak", "zh-hak", "nan", "zh-nan", "hsn", "zh-hsn"};
    private static final String[] isoLegacyTagReplacements = {"i-lux", "lb", "i-hak", "zh-hak", "i-navajo", "nv", "no-bok", "no-nob", "no-nyn", "no-nno", "zh-guoyu", "zh-cmn", "zh-hakka", "zh-hak", "zh-min-nan", "zh-nan", "zh-xiang", "zh-hsn"};
    @Nullable
    private static HashMap<String, String> languageTagReplacementMap;

    static {
        int i3 = Build.VERSION.SDK_INT;
        SDK_INT = i3;
        String str = Build.DEVICE;
        DEVICE = str;
        String str2 = Build.MANUFACTURER;
        MANUFACTURER = str2;
        String str3 = Build.MODEL;
        MODEL = str3;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(", ");
        sb.append(str3);
        sb.append(", ");
        sb.append(str2);
        DEVICE_DEBUG_INFO = a.u(sb, ", ", i3);
    }

    private Util() {
    }

    public static long addWithOverflowDefault(long j2, long j3, long j4) {
        long j5 = j2 + j3;
        return ((j2 ^ j5) & (j3 ^ j5)) < 0 ? j4 : j5;
    }

    public static boolean areEqual(@Nullable Object obj, @Nullable Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int binarySearchCeil(int[] r3, int r4, boolean r5, boolean r6) {
        /*
            int r0 = java.util.Arrays.binarySearch(r3, r4)
            if (r0 >= 0) goto L_0x0008
            int r4 = ~r0
            goto L_0x0018
        L_0x0008:
            int r1 = r0 + 1
            int r2 = r3.length
            if (r1 >= r2) goto L_0x0013
            r2 = r3[r1]
            if (r2 != r4) goto L_0x0013
            r0 = r1
            goto L_0x0008
        L_0x0013:
            if (r5 == 0) goto L_0x0017
            r4 = r0
            goto L_0x0018
        L_0x0017:
            r4 = r1
        L_0x0018:
            if (r6 == 0) goto L_0x0021
            int r3 = r3.length
            int r3 = r3 + -1
            int r4 = java.lang.Math.min(r3, r4)
        L_0x0021:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchCeil(int[], int, boolean, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0018  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int binarySearchFloor(int[] r3, int r4, boolean r5, boolean r6) {
        /*
            int r0 = java.util.Arrays.binarySearch(r3, r4)
            if (r0 >= 0) goto L_0x000a
            int r0 = r0 + 2
            int r3 = -r0
            goto L_0x0019
        L_0x000a:
            int r1 = r0 + -1
            if (r1 < 0) goto L_0x0014
            r2 = r3[r1]
            if (r2 != r4) goto L_0x0014
            r0 = r1
            goto L_0x000a
        L_0x0014:
            if (r5 == 0) goto L_0x0018
            r3 = r0
            goto L_0x0019
        L_0x0018:
            r3 = r1
        L_0x0019:
            if (r6 == 0) goto L_0x0020
            r4 = 0
            int r3 = java.lang.Math.max(r4, r3)
        L_0x0020:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchFloor(int[], int, boolean, boolean):int");
    }

    @EnsuresNonNull({"#1"})
    public static <T> T castNonNull(@Nullable T t2) {
        return t2;
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[] castNonNullTypeArray(T[] tArr) {
        return tArr;
    }

    public static int ceilDivide(int i3, int i4) {
        return com.appsamurai.storyly.exoplayer2.common.a.a(i3, i4, 1, i4);
    }

    public static boolean checkCleartextTrafficPermitted(MediaItem... mediaItemArr) {
        if (SDK_INT < 24) {
            return true;
        }
        for (MediaItem mediaItem : mediaItemArr) {
            MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
            if (localConfiguration != null) {
                if (isTrafficRestricted(localConfiguration.uri)) {
                    return false;
                }
                for (int i3 = 0; i3 < mediaItem.localConfiguration.subtitleConfigurations.size(); i3++) {
                    if (isTrafficRestricted(mediaItem.localConfiguration.subtitleConfigurations.get(i3).uri)) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int compareLong(long j2, long j3) {
        int i3 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i3 < 0) {
            return -1;
        }
        return i3 == 0 ? 0 : 1;
    }

    public static int constrainValue(int i3, int i4, int i5) {
        return Math.max(i4, Math.min(i3, i5));
    }

    public static boolean contains(Object[] objArr, @Nullable Object obj) {
        for (Object areEqual : objArr) {
            if (areEqual(areEqual, obj)) {
                return true;
            }
        }
        return false;
    }

    public static int crc32(byte[] bArr, int i3, int i4, int i5) {
        while (i3 < i4) {
            i5 = CRC32_BYTES_MSBF[((i5 >>> 24) ^ (bArr[i3] & 255)) & 255] ^ (i5 << 8);
            i3++;
        }
        return i5;
    }

    public static int crc8(byte[] bArr, int i3, int i4, int i5) {
        while (i3 < i4) {
            i5 = CRC8_BYTES_MSBF[i5 ^ (bArr[i3] & 255)];
            i3++;
        }
        return i5;
    }

    public static Handler createHandler(Looper looper, @Nullable Handler.Callback callback) {
        return new Handler(looper, callback);
    }

    public static Handler createHandlerForCurrentLooper() {
        return createHandlerForCurrentLooper((Handler.Callback) null);
    }

    public static Handler createHandlerForCurrentOrMainLooper() {
        return createHandlerForCurrentOrMainLooper((Handler.Callback) null);
    }

    private static HashMap<String, String> createIsoLanguageReplacementMap() {
        String[] iSOLanguages = Locale.getISOLanguages();
        HashMap<String, String> hashMap = new HashMap<>(iSOLanguages.length + additionalIsoLanguageReplacements.length);
        int i3 = 0;
        for (String str : iSOLanguages) {
            try {
                String iSO3Language = new Locale(str).getISO3Language();
                if (!TextUtils.isEmpty(iSO3Language)) {
                    hashMap.put(iSO3Language, str);
                }
            } catch (MissingResourceException unused) {
            }
        }
        while (true) {
            String[] strArr = additionalIsoLanguageReplacements;
            if (i3 >= strArr.length) {
                return hashMap;
            }
            hashMap.put(strArr[i3], strArr[i3 + 1]);
            i3 += 2;
        }
    }

    public static File createTempDirectory(Context context, String str) throws IOException {
        File createTempFile = createTempFile(context, str);
        createTempFile.delete();
        createTempFile.mkdir();
        return createTempFile;
    }

    public static File createTempFile(Context context, String str) throws IOException {
        return File.createTempFile(str, (String) null, (File) Assertions.checkNotNull(context.getCacheDir()));
    }

    public static String escapeFileName(String str) {
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (shouldEscapeCharacter(str.charAt(i5))) {
                i4++;
            }
        }
        if (i4 == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder((i4 * 2) + length);
        while (i4 > 0) {
            int i6 = i3 + 1;
            char charAt = str.charAt(i3);
            if (shouldEscapeCharacter(charAt)) {
                sb.append('%');
                sb.append(Integer.toHexString(charAt));
                i4--;
            } else {
                sb.append(charAt);
            }
            i3 = i6;
        }
        if (i3 < length) {
            sb.append(str, i3, length);
        }
        return sb.toString();
    }

    public static Uri fixSmoothStreamingIsmManifestUri(Uri uri) {
        String path = uri.getPath();
        if (path == null) {
            return uri;
        }
        Matcher matcher = ISM_PATH_PATTERN.matcher(path);
        return (!matcher.matches() || matcher.group(1) != null) ? uri : Uri.withAppendedPath(uri, "Manifest");
    }

    public static String formatInvariant(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String fromUtf8Bytes(byte[] bArr) {
        return new String(bArr, Charsets.UTF_8);
    }

    @RequiresApi(21)
    public static int generateAudioSessionIdV21(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            return -1;
        }
        return audioManager.generateAudioSessionId();
    }

    @Nullable
    public static String getAdaptiveMimeTypeForContentType(int i3) {
        if (i3 == 0) {
            return MimeTypes.APPLICATION_MPD;
        }
        if (i3 == 1) {
            return MimeTypes.APPLICATION_SS;
        }
        if (i3 != 2) {
            return null;
        }
        return MimeTypes.APPLICATION_M3U8;
    }

    public static int getAudioContentTypeForStreamType(int i3) {
        if (i3 != 0) {
            return (i3 == 1 || i3 == 2 || i3 == 4 || i3 == 5 || i3 == 8) ? 4 : 2;
        }
        return 1;
    }

    public static int getAudioTrackChannelConfig(int i3) {
        if (i3 == 12) {
            return SDK_INT >= 32 ? 743676 : 0;
        }
        switch (i3) {
            case 1:
                return 4;
            case 2:
                return 12;
            case 3:
                return 28;
            case 4:
                return 204;
            case 5:
                return JNINativeInterface.GetStringRegion;
            case 6:
                return 252;
            case 7:
                return 1276;
            case 8:
                int i4 = SDK_INT;
                return (i4 < 23 && i4 < 21) ? 0 : 6396;
            default:
                return 0;
        }
    }

    public static int getAudioUsageForStreamType(int i3) {
        if (i3 == 0) {
            return 2;
        }
        if (i3 == 1) {
            return 13;
        }
        if (i3 == 2) {
            return 6;
        }
        int i4 = 4;
        if (i3 != 4) {
            i4 = 5;
            if (i3 != 5) {
                return i3 != 8 ? 1 : 3;
            }
        }
        return i4;
    }

    public static Player.Commands getAvailableCommands(Player player, Player.Commands commands) {
        boolean isPlayingAd = player.isPlayingAd();
        boolean isCurrentMediaItemSeekable = player.isCurrentMediaItemSeekable();
        boolean hasPreviousMediaItem = player.hasPreviousMediaItem();
        boolean hasNextMediaItem = player.hasNextMediaItem();
        boolean isCurrentMediaItemLive = player.isCurrentMediaItemLive();
        boolean isCurrentMediaItemDynamic = player.isCurrentMediaItemDynamic();
        boolean isEmpty = player.getCurrentTimeline().isEmpty();
        boolean z2 = false;
        Player.Commands.Builder addIf = new Player.Commands.Builder().addAll(commands).addIf(4, !isPlayingAd).addIf(5, isCurrentMediaItemSeekable && !isPlayingAd).addIf(6, hasPreviousMediaItem && !isPlayingAd).addIf(7, !isEmpty && (hasPreviousMediaItem || !isCurrentMediaItemLive || isCurrentMediaItemSeekable) && !isPlayingAd).addIf(8, hasNextMediaItem && !isPlayingAd).addIf(9, !isEmpty && (hasNextMediaItem || (isCurrentMediaItemLive && isCurrentMediaItemDynamic)) && !isPlayingAd).addIf(10, !isPlayingAd).addIf(11, isCurrentMediaItemSeekable && !isPlayingAd);
        if (isCurrentMediaItemSeekable && !isPlayingAd) {
            z2 = true;
        }
        return addIf.addIf(12, z2).build();
    }

    public static int getBigEndianInt(ByteBuffer byteBuffer, int i3) {
        int i4 = byteBuffer.getInt(i3);
        return byteBuffer.order() == ByteOrder.BIG_ENDIAN ? i4 : Integer.reverseBytes(i4);
    }

    public static byte[] getBytesFromHexString(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * 2;
            bArr[i3] = (byte) (Character.digit(str.charAt(i4 + 1), 16) + (Character.digit(str.charAt(i4), 16) << 4));
        }
        return bArr;
    }

    public static int getCodecCountOfType(@Nullable String str, int i3) {
        int i4 = 0;
        for (String trackTypeOfCodec : splitCodecs(str)) {
            if (i3 == MimeTypes.getTrackTypeOfCodec(trackTypeOfCodec)) {
                i4++;
            }
        }
        return i4;
    }

    @Nullable
    public static String getCodecsOfType(@Nullable String str, int i3) {
        String[] splitCodecs = splitCodecs(str);
        if (splitCodecs.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : splitCodecs) {
            if (i3 == MimeTypes.getTrackTypeOfCodec(str2)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str2);
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    public static String getCommaDelimitedSimpleClassNames(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < objArr.length; i3++) {
            sb.append(objArr[i3].getClass().getSimpleName());
            if (i3 < objArr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static String getCountryCode(@Nullable Context context) {
        TelephonyManager telephonyManager;
        if (!(context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null)) {
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (!TextUtils.isEmpty(networkCountryIso)) {
                return Ascii.toUpperCase(networkCountryIso);
            }
        }
        return Ascii.toUpperCase(Locale.getDefault().getCountry());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = (android.hardware.display.DisplayManager) r2.getSystemService(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Point getCurrentDisplayModeSize(android.content.Context r2) {
        /*
            int r0 = SDK_INT
            r1 = 17
            if (r0 < r1) goto L_0x0016
            java.lang.String r0 = "display"
            java.lang.Object r0 = r2.getSystemService(r0)
            android.hardware.display.DisplayManager r0 = (android.hardware.display.DisplayManager) r0
            if (r0 == 0) goto L_0x0016
            r1 = 0
            android.view.Display r0 = r0.getDisplay(r1)
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            if (r0 != 0) goto L_0x002c
            java.lang.String r0 = "window"
            java.lang.Object r0 = r2.getSystemService(r0)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            java.lang.Object r0 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r0)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
        L_0x002c:
            android.graphics.Point r2 = getCurrentDisplayModeSize(r2, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.Util.getCurrentDisplayModeSize(android.content.Context):android.graphics.Point");
    }

    public static Looper getCurrentOrMainLooper() {
        Looper myLooper = Looper.myLooper();
        return myLooper != null ? myLooper : Looper.getMainLooper();
    }

    public static Uri getDataUriForString(String str, String str2) {
        StringBuilder w2 = android.support.v4.media.session.a.w("data:", str, ";base64,");
        w2.append(Base64.encodeToString(str2.getBytes(), 2));
        return Uri.parse(w2.toString());
    }

    public static Locale getDefaultDisplayLocale() {
        return SDK_INT >= 24 ? Locale.getDefault(Locale.Category.DISPLAY) : Locale.getDefault();
    }

    private static void getDisplaySizeV16(Display display, Point point) {
        display.getSize(point);
    }

    @RequiresApi(17)
    private static void getDisplaySizeV17(Display display, Point point) {
        display.getRealSize(point);
    }

    @RequiresApi(23)
    private static void getDisplaySizeV23(Display display, Point point) {
        Display.Mode mode = display.getMode();
        point.x = mode.getPhysicalWidth();
        point.y = mode.getPhysicalHeight();
    }

    @Nullable
    public static UUID getDrmUuid(String str) {
        String lowerCase = Ascii.toLowerCase(str);
        lowerCase.getClass();
        char c3 = 65535;
        switch (lowerCase.hashCode()) {
            case -1860423953:
                if (lowerCase.equals("playready")) {
                    c3 = 0;
                    break;
                }
                break;
            case -1400551171:
                if (lowerCase.equals("widevine")) {
                    c3 = 1;
                    break;
                }
                break;
            case 790309106:
                if (lowerCase.equals("clearkey")) {
                    c3 = 2;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return C.PLAYREADY_UUID;
            case 1:
                return C.WIDEVINE_UUID;
            case 2:
                return C.CLEARKEY_UUID;
            default:
                try {
                    return UUID.fromString(str);
                } catch (RuntimeException unused) {
                    return null;
                }
        }
    }

    public static int getErrorCodeForMediaDrmErrorCode(int i3) {
        if (i3 == 2 || i3 == 4) {
            return PlaybackException.ERROR_CODE_DRM_DISALLOWED_OPERATION;
        }
        if (i3 == 10) {
            return PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED;
        }
        if (i3 == 7) {
            return PlaybackException.ERROR_CODE_DRM_DISALLOWED_OPERATION;
        }
        if (i3 == 8) {
            return PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR;
        }
        switch (i3) {
            case 15:
                return PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR;
            case 16:
            case 18:
                return PlaybackException.ERROR_CODE_DRM_DISALLOWED_OPERATION;
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
                return PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED;
            default:
                switch (i3) {
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                        return PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED;
                    default:
                        return PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR;
                }
        }
    }

    public static int getErrorCodeFromPlatformDiagnosticsInfo(@Nullable String str) {
        String[] split;
        int length;
        if (str == null || (length = split.length) < 2) {
            return 0;
        }
        String str2 = (split = split(str, "_"))[length - 1];
        boolean z2 = length >= 3 && "neg".equals(split[length - 2]);
        try {
            int parseInt = Integer.parseInt((String) Assertions.checkNotNull(str2));
            return z2 ? -parseInt : parseInt;
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static String getFormatSupportString(int i3) {
        if (i3 == 0) {
            return "NO";
        }
        if (i3 == 1) {
            return "NO_UNSUPPORTED_TYPE";
        }
        if (i3 == 2) {
            return "NO_UNSUPPORTED_DRM";
        }
        if (i3 == 3) {
            return "NO_EXCEEDS_CAPABILITIES";
        }
        if (i3 == 4) {
            return "YES";
        }
        throw new IllegalStateException();
    }

    public static int getIntegerCodeForString(String str) {
        int length = str.length();
        Assertions.checkArgument(length <= 4);
        char c3 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            c3 = (c3 << 8) | str.charAt(i3);
        }
        return c3;
    }

    public static String getLocaleLanguageTag(Locale locale) {
        return SDK_INT >= 21 ? getLocaleLanguageTagV21(locale) : locale.toString();
    }

    @RequiresApi(21)
    private static String getLocaleLanguageTagV21(Locale locale) {
        return locale.toLanguageTag();
    }

    public static long getMediaDurationForPlayoutDuration(long j2, float f2) {
        return f2 == 1.0f ? j2 : Math.round(((double) j2) * ((double) f2));
    }

    public static long getNowUnixTimeMs(long j2) {
        return j2 == C.TIME_UNSET ? System.currentTimeMillis() : j2 + SystemClock.elapsedRealtime();
    }

    public static int getPcmEncoding(int i3) {
        if (i3 == 8) {
            return 3;
        }
        if (i3 == 16) {
            return 2;
        }
        if (i3 == 24) {
            return 536870912;
        }
        if (i3 != 32) {
            return 0;
        }
        return C.ENCODING_PCM_32BIT;
    }

    public static Format getPcmFormat(int i3, int i4, int i5) {
        return new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_RAW).setChannelCount(i4).setSampleRate(i5).setPcmEncoding(i3).build();
    }

    public static int getPcmFrameSize(int i3, int i4) {
        if (i3 != 2) {
            if (i3 == 3) {
                return i4;
            }
            if (i3 != 4) {
                if (i3 != 268435456) {
                    if (i3 == 536870912) {
                        return i4 * 3;
                    }
                    if (i3 != 805306368) {
                        throw new IllegalArgumentException();
                    }
                }
            }
            return i4 * 4;
        }
        return i4 * 2;
    }

    public static long getPlayoutDurationForMediaDuration(long j2, float f2) {
        return f2 == 1.0f ? j2 : Math.round(((double) j2) / ((double) f2));
    }

    public static int getStreamTypeForAudioUsage(int i3) {
        if (i3 == 13) {
            return 1;
        }
        switch (i3) {
            case 2:
                return 0;
            case 3:
                return 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    public static String getStringForTime(StringBuilder sb, Formatter formatter, long j2) {
        if (j2 == C.TIME_UNSET) {
            j2 = 0;
        }
        String str = j2 < 0 ? "-" : "";
        long abs = (Math.abs(j2) + 500) / 1000;
        long j3 = abs % 60;
        long j4 = (abs / 60) % 60;
        long j5 = abs / 3600;
        sb.setLength(0);
        return j5 > 0 ? formatter.format("%s%d:%02d:%02d", new Object[]{str, Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)}).toString() : formatter.format("%s%02d:%02d", new Object[]{str, Long.valueOf(j4), Long.valueOf(j3)}).toString();
    }

    public static String[] getSystemLanguageCodes() {
        String[] systemLocales = getSystemLocales();
        for (int i3 = 0; i3 < systemLocales.length; i3++) {
            systemLocales[i3] = normalizeLanguageCode(systemLocales[i3]);
        }
        return systemLocales;
    }

    private static String[] getSystemLocales() {
        Configuration configuration = Resources.getSystem().getConfiguration();
        if (SDK_INT >= 24) {
            return getSystemLocalesV24(configuration);
        }
        return new String[]{getLocaleLanguageTag(configuration.locale)};
    }

    @RequiresApi(24)
    private static String[] getSystemLocalesV24(Configuration configuration) {
        return split(configuration.getLocales().toLanguageTags(), ",");
    }

    @Nullable
    private static String getSystemProperty(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (Exception e3) {
            Log.e(TAG, "Failed to read system property " + str, e3);
            return null;
        }
    }

    public static String getTrackTypeString(int i3) {
        switch (i3) {
            case -2:
                return "none";
            case -1:
                return EnvironmentCompat.MEDIA_UNKNOWN;
            case 0:
                return "default";
            case 1:
                return MimeTypes.BASE_TYPE_AUDIO;
            case 2:
                return MimeTypes.BASE_TYPE_VIDEO;
            case 3:
                return "text";
            case 4:
                return "image";
            case 5:
                return TtmlNode.TAG_METADATA;
            case 6:
                return "camera motion";
            default:
                return i3 >= 10000 ? C0118y.c(i3, "custom (", ")") : "?";
        }
    }

    public static String getUserAgent(Context context, String str) {
        String str2;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str2 = "?";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append(" (Linux;Android ");
        return A.a.n(sb, Build.VERSION.RELEASE, ") ExoPlayerLib/2.18.0");
    }

    public static byte[] getUtf8Bytes(String str) {
        return str.getBytes(Charsets.UTF_8);
    }

    public static byte[] gzip(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e3) {
            throw new IllegalStateException(e3);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    @Deprecated
    public static int inferContentType(Uri uri, @Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return inferContentType(uri);
        }
        return inferContentTypeForExtension(str);
    }

    public static int inferContentTypeForExtension(String str) {
        String lowerCase = Ascii.toLowerCase(str);
        lowerCase.getClass();
        char c3 = 65535;
        switch (lowerCase.hashCode()) {
            case 104579:
                if (lowerCase.equals("ism")) {
                    c3 = 0;
                    break;
                }
                break;
            case 108321:
                if (lowerCase.equals("mpd")) {
                    c3 = 1;
                    break;
                }
                break;
            case 3242057:
                if (lowerCase.equals("isml")) {
                    c3 = 2;
                    break;
                }
                break;
            case 3299913:
                if (lowerCase.equals("m3u8")) {
                    c3 = 3;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
            case 2:
                return 1;
            case 1:
                return 0;
            case 3:
                return 2;
            default:
                return 4;
        }
    }

    public static int inferContentTypeForUriAndMimeType(Uri uri, @Nullable String str) {
        if (str == null) {
            return inferContentType(uri);
        }
        char c3 = 65535;
        switch (str.hashCode()) {
            case -979127466:
                if (str.equals(MimeTypes.APPLICATION_M3U8)) {
                    c3 = 0;
                    break;
                }
                break;
            case -156749520:
                if (str.equals(MimeTypes.APPLICATION_SS)) {
                    c3 = 1;
                    break;
                }
                break;
            case 64194685:
                if (str.equals(MimeTypes.APPLICATION_MPD)) {
                    c3 = 2;
                    break;
                }
                break;
            case 1154777587:
                if (str.equals(MimeTypes.APPLICATION_RTSP)) {
                    c3 = 3;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return 2;
            case 1:
                return 1;
            case 2:
                return 0;
            case 3:
                return 3;
            default:
                return 4;
        }
    }

    public static boolean inflate(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, @Nullable Inflater inflater) {
        if (parsableByteArray.bytesLeft() <= 0) {
            return false;
        }
        if (parsableByteArray2.capacity() < parsableByteArray.bytesLeft()) {
            parsableByteArray2.ensureCapacity(parsableByteArray.bytesLeft() * 2);
        }
        if (inflater == null) {
            inflater = new Inflater();
        }
        inflater.setInput(parsableByteArray.getData(), parsableByteArray.getPosition(), parsableByteArray.bytesLeft());
        int i3 = 0;
        while (true) {
            try {
                i3 += inflater.inflate(parsableByteArray2.getData(), i3, parsableByteArray2.capacity() - i3);
                if (inflater.finished()) {
                    parsableByteArray2.setLimit(i3);
                    inflater.reset();
                    return true;
                } else if (inflater.needsDictionary()) {
                    break;
                } else if (inflater.needsInput()) {
                    break;
                } else if (i3 == parsableByteArray2.capacity()) {
                    parsableByteArray2.ensureCapacity(parsableByteArray2.capacity() * 2);
                }
            } catch (DataFormatException unused) {
                return false;
            } finally {
                inflater.reset();
            }
        }
        return false;
    }

    public static boolean isAutomotive(Context context) {
        return SDK_INT >= 23 && context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    public static boolean isEncodingHighResolutionPcm(int i3) {
        return i3 == 536870912 || i3 == 805306368 || i3 == 4;
    }

    public static boolean isEncodingLinearPcm(int i3) {
        return i3 == 3 || i3 == 2 || i3 == 268435456 || i3 == 536870912 || i3 == 805306368 || i3 == 4;
    }

    public static boolean isLinebreak(int i3) {
        return i3 == 10 || i3 == 13;
    }

    public static boolean isLocalFileUri(Uri uri) {
        String scheme = uri.getScheme();
        return TextUtils.isEmpty(scheme) || StringLookupFactory.KEY_FILE.equals(scheme);
    }

    @RequiresApi(api = 24)
    private static boolean isTrafficRestricted(Uri uri) {
        return "http".equals(uri.getScheme()) && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted((String) Assertions.checkNotNull(uri.getHost()));
    }

    public static boolean isTv(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getApplicationContext().getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$newSingleThreadExecutor$0(String str, Runnable runnable) {
        return new Thread(runnable, str);
    }

    public static int linearSearch(int[] iArr, int i3) {
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (iArr[i4] == i3) {
                return i4;
            }
        }
        return -1;
    }

    @RequiresApi(18)
    public static long maxValue(SparseLongArray sparseLongArray) {
        if (sparseLongArray.size() != 0) {
            long j2 = Long.MIN_VALUE;
            for (int i3 = 0; i3 < sparseLongArray.size(); i3++) {
                j2 = Math.max(j2, sparseLongArray.valueAt(i3));
            }
            return j2;
        }
        throw new NoSuchElementException();
    }

    private static String maybeReplaceLegacyLanguageTags(String str) {
        int i3 = 0;
        while (true) {
            String[] strArr = isoLegacyTagReplacements;
            if (i3 >= strArr.length) {
                return str;
            }
            if (str.startsWith(strArr[i3])) {
                return strArr[i3 + 1] + str.substring(strArr[i3].length());
            }
            i3 += 2;
        }
    }

    public static boolean maybeRequestReadExternalStoragePermission(Activity activity, Uri... uriArr) {
        if (SDK_INT < 23) {
            return false;
        }
        for (Uri isLocalFileUri : uriArr) {
            if (isLocalFileUri(isLocalFileUri)) {
                return requestExternalStoragePermission(activity);
            }
        }
        return false;
    }

    @RequiresApi(18)
    public static long minValue(SparseLongArray sparseLongArray) {
        if (sparseLongArray.size() != 0) {
            long j2 = Long.MAX_VALUE;
            for (int i3 = 0; i3 < sparseLongArray.size(); i3++) {
                j2 = Math.min(j2, sparseLongArray.valueAt(i3));
            }
            return j2;
        }
        throw new NoSuchElementException();
    }

    public static <T> void moveItems(List<T> list, int i3, int i4, int i5) {
        ArrayDeque arrayDeque = new ArrayDeque();
        for (int i6 = (i4 - i3) - 1; i6 >= 0; i6--) {
            arrayDeque.addFirst(list.remove(i3 + i6));
        }
        list.addAll(Math.min(i5, list.size()), arrayDeque);
    }

    public static long msToUs(long j2) {
        return (j2 == C.TIME_UNSET || j2 == Long.MIN_VALUE) ? j2 : j2 * 1000;
    }

    public static ExecutorService newSingleThreadExecutor(String str) {
        return Executors.newSingleThreadExecutor(new a(str));
    }

    public static String normalizeLanguageCode(String str) {
        if (str == null) {
            return null;
        }
        String replace = str.replace('_', SignatureVisitor.SUPER);
        if (!replace.isEmpty() && !replace.equals(C.LANGUAGE_UNDETERMINED)) {
            str = replace;
        }
        String lowerCase = Ascii.toLowerCase(str);
        String str2 = splitAtFirst(lowerCase, "-")[0];
        if (languageTagReplacementMap == null) {
            languageTagReplacementMap = createIsoLanguageReplacementMap();
        }
        String str3 = languageTagReplacementMap.get(str2);
        if (str3 != null) {
            StringBuilder p2 = A.a.p(str3);
            p2.append(lowerCase.substring(str2.length()));
            lowerCase = p2.toString();
            str2 = str3;
        }
        return ("no".equals(str2) || "i".equals(str2) || "zh".equals(str2)) ? maybeReplaceLegacyLanguageTags(lowerCase) : lowerCase;
    }

    public static <T> T[] nullSafeArrayAppend(T[] tArr, T t2) {
        Object[] copyOf = Arrays.copyOf(tArr, tArr.length + 1);
        copyOf[tArr.length] = t2;
        return castNonNullTypeArray(copyOf);
    }

    public static <T> T[] nullSafeArrayConcatenation(T[] tArr, T[] tArr2) {
        T[] copyOf = Arrays.copyOf(tArr, tArr.length + tArr2.length);
        System.arraycopy(tArr2, 0, copyOf, tArr.length, tArr2.length);
        return copyOf;
    }

    public static <T> T[] nullSafeArrayCopy(T[] tArr, int i3) {
        Assertions.checkArgument(i3 <= tArr.length);
        return Arrays.copyOf(tArr, i3);
    }

    public static <T> T[] nullSafeArrayCopyOfRange(T[] tArr, int i3, int i4) {
        boolean z2 = false;
        Assertions.checkArgument(i3 >= 0);
        if (i4 <= tArr.length) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        return Arrays.copyOfRange(tArr, i3, i4);
    }

    public static <T> void nullSafeListToArray(List<T> list, T[] tArr) {
        Assertions.checkState(list.size() == tArr.length);
        list.toArray(tArr);
    }

    public static long parseXsDateTime(String str) throws ParserException {
        Matcher matcher = XS_DATE_TIME_PATTERN.matcher(str);
        if (matcher.matches()) {
            int i3 = 0;
            if (matcher.group(9) != null && !matcher.group(9).equalsIgnoreCase("Z")) {
                i3 = Integer.parseInt(matcher.group(13)) + (Integer.parseInt(matcher.group(12)) * 60);
                if ("-".equals(matcher.group(11))) {
                    i3 *= -1;
                }
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone(TimeZones.GMT_ID));
            gregorianCalendar.clear();
            gregorianCalendar.set(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
            if (!TextUtils.isEmpty(matcher.group(8))) {
                gregorianCalendar.set(14, new BigDecimal("0." + matcher.group(8)).movePointRight(3).intValue());
            }
            long timeInMillis = gregorianCalendar.getTimeInMillis();
            return i3 != 0 ? timeInMillis - (((long) i3) * 60000) : timeInMillis;
        }
        throw ParserException.createForMalformedContainer("Invalid date/time format: " + str, (Throwable) null);
    }

    public static long parseXsDuration(String str) {
        Matcher matcher = XS_DURATION_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return (long) (Double.parseDouble(str) * 3600.0d * 1000.0d);
        }
        boolean isEmpty = TextUtils.isEmpty(matcher.group(1));
        String group = matcher.group(3);
        double d2 = 0.0d;
        double parseDouble = group != null ? Double.parseDouble(group) * 3.1556908E7d : 0.0d;
        String group2 = matcher.group(5);
        double parseDouble2 = parseDouble + (group2 != null ? Double.parseDouble(group2) * 2629739.0d : 0.0d);
        String group3 = matcher.group(7);
        double parseDouble3 = parseDouble2 + (group3 != null ? Double.parseDouble(group3) * 86400.0d : 0.0d);
        String group4 = matcher.group(10);
        double parseDouble4 = parseDouble3 + (group4 != null ? Double.parseDouble(group4) * 3600.0d : 0.0d);
        String group5 = matcher.group(12);
        double parseDouble5 = parseDouble4 + (group5 != null ? Double.parseDouble(group5) * 60.0d : 0.0d);
        String group6 = matcher.group(14);
        if (group6 != null) {
            d2 = Double.parseDouble(group6);
        }
        long j2 = (long) ((parseDouble5 + d2) * 1000.0d);
        return !isEmpty ? -j2 : j2;
    }

    public static boolean postOrRun(Handler handler, Runnable runnable) {
        if (!handler.getLooper().getThread().isAlive()) {
            return false;
        }
        if (handler.getLooper() != Looper.myLooper()) {
            return handler.post(runnable);
        }
        runnable.run();
        return true;
    }

    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void recursiveDelete(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File recursiveDelete : listFiles) {
                recursiveDelete(recursiveDelete);
            }
        }
        file.delete();
    }

    public static <T> void removeRange(List<T> list, int i3, int i4) {
        if (i3 < 0 || i4 > list.size() || i3 > i4) {
            throw new IllegalArgumentException();
        } else if (i3 != i4) {
            list.subList(i3, i4).clear();
        }
    }

    @RequiresApi(api = 23)
    private static boolean requestExternalStoragePermission(Activity activity) {
        if (activity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
            return false;
        }
        activity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
        return true;
    }

    public static long scaleLargeTimestamp(long j2, long j3, long j4) {
        int i3 = (j4 > j3 ? 1 : (j4 == j3 ? 0 : -1));
        if (i3 >= 0 && j4 % j3 == 0) {
            return j2 / (j4 / j3);
        }
        if (i3 < 0 && j3 % j4 == 0) {
            return (j3 / j4) * j2;
        }
        return (long) (((double) j2) * (((double) j3) / ((double) j4)));
    }

    public static long[] scaleLargeTimestamps(List<Long> list, long j2, long j3) {
        int size = list.size();
        long[] jArr = new long[size];
        int i3 = (j3 > j2 ? 1 : (j3 == j2 ? 0 : -1));
        int i4 = 0;
        if (i3 >= 0 && j3 % j2 == 0) {
            long j4 = j3 / j2;
            while (i4 < size) {
                jArr[i4] = list.get(i4).longValue() / j4;
                i4++;
            }
        } else if (i3 >= 0 || j2 % j3 != 0) {
            double d2 = ((double) j2) / ((double) j3);
            while (i4 < size) {
                jArr[i4] = (long) (((double) list.get(i4).longValue()) * d2);
                i4++;
            }
        } else {
            long j5 = j2 / j3;
            while (i4 < size) {
                jArr[i4] = list.get(i4).longValue() * j5;
                i4++;
            }
        }
        return jArr;
    }

    public static void scaleLargeTimestampsInPlace(long[] jArr, long j2, long j3) {
        int i3 = (j3 > j2 ? 1 : (j3 == j2 ? 0 : -1));
        int i4 = 0;
        if (i3 >= 0 && j3 % j2 == 0) {
            long j4 = j3 / j2;
            while (i4 < jArr.length) {
                jArr[i4] = jArr[i4] / j4;
                i4++;
            }
        } else if (i3 >= 0 || j2 % j3 != 0) {
            double d2 = ((double) j2) / ((double) j3);
            while (i4 < jArr.length) {
                jArr[i4] = (long) (((double) jArr[i4]) * d2);
                i4++;
            }
        } else {
            long j5 = j2 / j3;
            while (i4 < jArr.length) {
                jArr[i4] = jArr[i4] * j5;
                i4++;
            }
        }
    }

    private static boolean shouldEscapeCharacter(char c3) {
        return c3 == '\"' || c3 == '%' || c3 == '*' || c3 == '/' || c3 == ':' || c3 == '<' || c3 == '\\' || c3 == '|' || c3 == '>' || c3 == '?';
    }

    public static void sneakyThrow(Throwable th) {
        sneakyThrowInternal(th);
    }

    private static <T extends Throwable> void sneakyThrowInternal(Throwable th) throws Throwable {
        throw th;
    }

    public static String[] split(String str, String str2) {
        return str.split(str2, -1);
    }

    public static String[] splitAtFirst(String str, String str2) {
        return str.split(str2, 2);
    }

    public static String[] splitCodecs(@Nullable String str) {
        return TextUtils.isEmpty(str) ? new String[0] : split(str.trim(), "(\\s*,\\s*)");
    }

    @Nullable
    public static ComponentName startForegroundService(Context context, Intent intent) {
        return SDK_INT >= 26 ? context.startForegroundService(intent) : context.startService(intent);
    }

    public static long subtractWithOverflowDefault(long j2, long j3, long j4) {
        long j5 = j2 - j3;
        return ((j2 ^ j5) & (j3 ^ j2)) < 0 ? j4 : j5;
    }

    public static long sum(long... jArr) {
        long j2 = 0;
        for (long j3 : jArr) {
            j2 += j3;
        }
        return j2;
    }

    public static boolean tableExists(SQLiteDatabase sQLiteDatabase, String str) {
        return DatabaseUtils.queryNumEntries(sQLiteDatabase, "sqlite_master", "tbl_name = ?", new String[]{str}) > 0;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i3 = 0; i3 < bArr.length; i3++) {
            sb.append(Character.forDigit((bArr[i3] >> 4) & 15, 16));
            sb.append(Character.forDigit(bArr[i3] & Ascii.SI, 16));
        }
        return sb.toString();
    }

    public static long toLong(int i3, int i4) {
        return toUnsignedLong(i4) | (toUnsignedLong(i3) << 32);
    }

    public static long toUnsignedLong(int i3) {
        return ((long) i3) & 4294967295L;
    }

    public static CharSequence truncateAscii(CharSequence charSequence, int i3) {
        return charSequence.length() <= i3 ? charSequence : charSequence.subSequence(0, i3);
    }

    @Nullable
    public static String unescapeFileName(String str) {
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (str.charAt(i5) == '%') {
                i4++;
            }
        }
        if (i4 == 0) {
            return str;
        }
        int i6 = length - (i4 * 2);
        StringBuilder sb = new StringBuilder(i6);
        Matcher matcher = ESCAPED_CHARACTER_PATTERN.matcher(str);
        while (i4 > 0 && matcher.find()) {
            sb.append(str, i3, matcher.start());
            sb.append((char) Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)), 16));
            i3 = matcher.end();
            i4--;
        }
        if (i3 < length) {
            sb.append(str, i3, length);
        }
        if (sb.length() != i6) {
            return null;
        }
        return sb.toString();
    }

    public static long usToMs(long j2) {
        return (j2 == C.TIME_UNSET || j2 == Long.MIN_VALUE) ? j2 : j2 / 1000;
    }

    public static void writeBoolean(Parcel parcel, boolean z2) {
        parcel.writeInt(z2 ? 1 : 0);
    }

    public static long constrainValue(long j2, long j3, long j4) {
        return Math.max(j3, Math.min(j2, j4));
    }

    public static Handler createHandlerForCurrentLooper(@Nullable Handler.Callback callback) {
        return createHandler((Looper) Assertions.checkStateNotNull(Looper.myLooper()), callback);
    }

    public static Handler createHandlerForCurrentOrMainLooper(@Nullable Handler.Callback callback) {
        return createHandler(getCurrentOrMainLooper(), callback);
    }

    public static String fromUtf8Bytes(byte[] bArr, int i3, int i4) {
        return new String(bArr, i3, i4, Charsets.UTF_8);
    }

    public static long ceilDivide(long j2, long j3) {
        return ((j2 + j3) - 1) / j3;
    }

    public static float constrainValue(float f2, float f3, float f4) {
        return Math.max(f3, Math.min(f2, f4));
    }

    public static int linearSearch(long[] jArr, long j2) {
        for (int i3 = 0; i3 < jArr.length; i3++) {
            if (jArr[i3] == j2) {
                return i3;
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int binarySearchCeil(long[] r4, long r5, boolean r7, boolean r8) {
        /*
            int r0 = java.util.Arrays.binarySearch(r4, r5)
            if (r0 >= 0) goto L_0x0008
            int r5 = ~r0
            goto L_0x001a
        L_0x0008:
            int r1 = r0 + 1
            int r2 = r4.length
            if (r1 >= r2) goto L_0x0015
            r2 = r4[r1]
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0015
            r0 = r1
            goto L_0x0008
        L_0x0015:
            if (r7 == 0) goto L_0x0019
            r5 = r0
            goto L_0x001a
        L_0x0019:
            r5 = r1
        L_0x001a:
            if (r8 == 0) goto L_0x0023
            int r4 = r4.length
            int r4 = r4 + -1
            int r5 = java.lang.Math.min(r4, r5)
        L_0x0023:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchCeil(long[], long, boolean, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int binarySearchFloor(long[] r4, long r5, boolean r7, boolean r8) {
        /*
            int r0 = java.util.Arrays.binarySearch(r4, r5)
            if (r0 >= 0) goto L_0x000a
            int r0 = r0 + 2
            int r4 = -r0
            goto L_0x001b
        L_0x000a:
            int r1 = r0 + -1
            if (r1 < 0) goto L_0x0016
            r2 = r4[r1]
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0016
            r0 = r1
            goto L_0x000a
        L_0x0016:
            if (r7 == 0) goto L_0x001a
            r4 = r0
            goto L_0x001b
        L_0x001a:
            r4 = r1
        L_0x001b:
            if (r8 == 0) goto L_0x0022
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
        L_0x0022:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchFloor(long[], long, boolean, boolean):int");
    }

    public static int inferContentType(Uri uri) {
        int inferContentTypeForExtension;
        String scheme = uri.getScheme();
        if (scheme != null && Ascii.equalsIgnoreCase("rtsp", scheme)) {
            return 3;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            return 4;
        }
        int lastIndexOf = lastPathSegment.lastIndexOf(46);
        if (lastIndexOf >= 0 && (inferContentTypeForExtension = inferContentTypeForExtension(lastPathSegment.substring(lastIndexOf + 1))) != 4) {
            return inferContentTypeForExtension;
        }
        Matcher matcher = ISM_PATH_PATTERN.matcher((CharSequence) Assertions.checkNotNull(uri.getPath()));
        if (!matcher.matches()) {
            return 4;
        }
        String group = matcher.group(2);
        if (group != null) {
            if (group.contains(ISM_DASH_FORMAT_EXTENSION)) {
                return 0;
            }
            if (group.contains(ISM_HLS_FORMAT_EXTENSION)) {
                return 2;
            }
        }
        return 1;
    }

    public static boolean maybeRequestReadExternalStoragePermission(Activity activity, MediaItem... mediaItemArr) {
        if (SDK_INT < 23) {
            return false;
        }
        for (MediaItem mediaItem : mediaItemArr) {
            MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
            if (localConfiguration != null) {
                if (isLocalFileUri(localConfiguration.uri)) {
                    return requestExternalStoragePermission(activity);
                }
                for (int i3 = 0; i3 < mediaItem.localConfiguration.subtitleConfigurations.size(); i3++) {
                    if (isLocalFileUri(mediaItem.localConfiguration.subtitleConfigurations.get(i3).uri)) {
                        return requestExternalStoragePermission(activity);
                    }
                }
                continue;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends java.lang.Comparable<? super T>> int binarySearchCeil(java.util.List<? extends java.lang.Comparable<? super T>> r4, T r5, boolean r6, boolean r7) {
        /*
            int r0 = java.util.Collections.binarySearch(r4, r5)
            if (r0 >= 0) goto L_0x0008
            int r5 = ~r0
            goto L_0x0023
        L_0x0008:
            int r1 = r4.size()
        L_0x000c:
            int r2 = r0 + 1
            if (r2 >= r1) goto L_0x001e
            java.lang.Object r3 = r4.get(r2)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r3 = r3.compareTo(r5)
            if (r3 != 0) goto L_0x001e
            r0 = r2
            goto L_0x000c
        L_0x001e:
            if (r6 == 0) goto L_0x0022
            r5 = r0
            goto L_0x0023
        L_0x0022:
            r5 = r2
        L_0x0023:
            if (r7 == 0) goto L_0x002f
            int r4 = r4.size()
            int r4 = r4 + -1
            int r5 = java.lang.Math.min(r4, r5)
        L_0x002f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchCeil(java.util.List, java.lang.Comparable, boolean, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends java.lang.Comparable<? super T>> int binarySearchFloor(java.util.List<? extends java.lang.Comparable<? super T>> r3, T r4, boolean r5, boolean r6) {
        /*
            int r0 = java.util.Collections.binarySearch(r3, r4)
            if (r0 >= 0) goto L_0x000a
            int r0 = r0 + 2
            int r3 = -r0
            goto L_0x0021
        L_0x000a:
            int r1 = r0 + -1
            if (r1 < 0) goto L_0x001c
            java.lang.Object r2 = r3.get(r1)
            java.lang.Comparable r2 = (java.lang.Comparable) r2
            int r2 = r2.compareTo(r4)
            if (r2 != 0) goto L_0x001c
            r0 = r1
            goto L_0x000a
        L_0x001c:
            if (r5 == 0) goto L_0x0020
            r3 = r0
            goto L_0x0021
        L_0x0020:
            r3 = r1
        L_0x0021:
            if (r6 == 0) goto L_0x0028
            r4 = 0
            int r3 = java.lang.Math.max(r4, r3)
        L_0x0028:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchFloor(java.util.List, java.lang.Comparable, boolean, boolean):int");
    }

    public static Point getCurrentDisplayModeSize(Context context, Display display) {
        String str;
        if (display.getDisplayId() == 0 && isTv(context)) {
            if (SDK_INT < 28) {
                str = getSystemProperty("sys.display-size");
            } else {
                str = getSystemProperty("vendor.display-size");
            }
            if (!TextUtils.isEmpty(str)) {
                try {
                    String[] split = split(str.trim(), "x");
                    if (split.length == 2) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        if (parseInt > 0 && parseInt2 > 0) {
                            return new Point(parseInt, parseInt2);
                        }
                    }
                } catch (NumberFormatException unused) {
                }
                Log.e(TAG, "Invalid display size: " + str);
            }
            if ("Sony".equals(MANUFACTURER) && MODEL.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd")) {
                return new Point(3840, 2160);
            }
        }
        Point point = new Point();
        int i3 = SDK_INT;
        if (i3 >= 23) {
            getDisplaySizeV23(display, point);
        } else if (i3 >= 17) {
            getDisplaySizeV17(display, point);
        } else {
            getDisplaySizeV16(display, point);
        }
        return point;
    }

    public static int binarySearchFloor(LongArray longArray, long j2, boolean z2, boolean z3) {
        int i3;
        int size = longArray.size() - 1;
        int i4 = 0;
        while (i4 <= size) {
            int i5 = (i4 + size) >>> 1;
            if (longArray.get(i5) < j2) {
                i4 = i5 + 1;
            } else {
                size = i5 - 1;
            }
        }
        if (z2 && (i3 = size + 1) < longArray.size() && longArray.get(i3) == j2) {
            return i3;
        }
        if (!z3 || size != -1) {
            return size;
        }
        return 0;
    }

    @Deprecated
    public static int inferContentType(String str) {
        return inferContentType(Uri.parse("file:///" + str));
    }
}
