package com.appsamurai.storyly.exoplayer2.common.util;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.fasterxml.jackson.core.JsonPointer;

public final class UriUtil {
    private static final int FRAGMENT = 3;
    private static final int INDEX_COUNT = 4;
    private static final int PATH = 1;
    private static final int QUERY = 2;
    private static final int SCHEME_COLON = 0;

    private UriUtil() {
    }

    private static int[] getUriIndices(String str) {
        int i3;
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int length = str.length();
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            length = indexOf;
        }
        int indexOf2 = str.indexOf(63);
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 == -1 || indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(58);
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        int i4 = indexOf4 + 2;
        if (i4 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(i4) == '/') {
            i3 = str.indexOf(47, indexOf4 + 3);
            if (i3 == -1 || i3 > indexOf2) {
                i3 = indexOf2;
            }
        } else {
            i3 = indexOf4 + 1;
        }
        iArr[0] = indexOf4;
        iArr[1] = i3;
        iArr[2] = indexOf2;
        iArr[3] = length;
        return iArr;
    }

    public static boolean isAbsolute(@Nullable String str) {
        return (str == null || getUriIndices(str)[0] == -1) ? false : true;
    }

    private static String removeDotSegments(StringBuilder sb, int i3, int i4) {
        int i5;
        int i6;
        if (i3 >= i4) {
            return sb.toString();
        }
        if (sb.charAt(i3) == '/') {
            i3++;
        }
        int i7 = i3;
        int i8 = i7;
        while (i7 <= i4) {
            if (i7 == i4) {
                i5 = i7;
            } else if (sb.charAt(i7) == '/') {
                i5 = i7 + 1;
            } else {
                i7++;
            }
            int i9 = i8 + 1;
            if (i7 == i9 && sb.charAt(i8) == '.') {
                sb.delete(i8, i5);
                i4 -= i5 - i8;
            } else {
                if (i7 == i8 + 2 && sb.charAt(i8) == '.' && sb.charAt(i9) == '.') {
                    i6 = sb.lastIndexOf("/", i8 - 2) + 1;
                    int i10 = i6 > i3 ? i6 : i3;
                    sb.delete(i10, i5);
                    i4 -= i5 - i10;
                } else {
                    i6 = i7 + 1;
                }
                i8 = i6;
            }
            i7 = i8;
        }
        return sb.toString();
    }

    public static Uri removeQueryParameter(Uri uri, String str) {
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.clearQuery();
        for (String next : uri.getQueryParameterNames()) {
            if (!next.equals(str)) {
                for (String appendQueryParameter : uri.getQueryParameters(next)) {
                    buildUpon.appendQueryParameter(next, appendQueryParameter);
                }
            }
        }
        return buildUpon.build();
    }

    public static String resolve(@Nullable String str, @Nullable String str2) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] uriIndices = getUriIndices(str2);
        if (uriIndices[0] != -1) {
            sb.append(str2);
            removeDotSegments(sb, uriIndices[1], uriIndices[2]);
            return sb.toString();
        }
        int[] uriIndices2 = getUriIndices(str);
        if (uriIndices[3] == 0) {
            sb.append(str, 0, uriIndices2[3]);
            sb.append(str2);
            return sb.toString();
        } else if (uriIndices[2] == 0) {
            sb.append(str, 0, uriIndices2[2]);
            sb.append(str2);
            return sb.toString();
        } else {
            int i3 = uriIndices[1];
            if (i3 != 0) {
                int i4 = uriIndices2[0] + 1;
                sb.append(str, 0, i4);
                sb.append(str2);
                return removeDotSegments(sb, uriIndices[1] + i4, i4 + uriIndices[2]);
            } else if (str2.charAt(i3) == '/') {
                sb.append(str, 0, uriIndices2[1]);
                sb.append(str2);
                int i5 = uriIndices2[1];
                return removeDotSegments(sb, i5, uriIndices[2] + i5);
            } else {
                int i6 = uriIndices2[0] + 2;
                int i7 = uriIndices2[1];
                if (i6 >= i7 || i7 != uriIndices2[2]) {
                    int lastIndexOf = str.lastIndexOf(47, uriIndices2[2] - 1);
                    int i8 = lastIndexOf == -1 ? uriIndices2[1] : lastIndexOf + 1;
                    sb.append(str, 0, i8);
                    sb.append(str2);
                    return removeDotSegments(sb, uriIndices2[1], i8 + uriIndices[2]);
                }
                sb.append(str, 0, i7);
                sb.append(JsonPointer.SEPARATOR);
                sb.append(str2);
                int i9 = uriIndices2[1];
                return removeDotSegments(sb, i9, uriIndices[2] + i9 + 1);
            }
        }
    }

    public static Uri resolveToUri(@Nullable String str, @Nullable String str2) {
        return Uri.parse(resolve(str, str2));
    }
}
