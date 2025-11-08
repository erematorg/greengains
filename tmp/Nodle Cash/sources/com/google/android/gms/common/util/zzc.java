package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class zzc {
    private static final Pattern zza = Pattern.compile("\\\\u[0-9a-fA-F]{4}");

    public static String zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = zza.matcher(str);
        StringBuilder sb = null;
        int i3 = 0;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            int start = matcher.start();
            int i4 = start;
            while (i4 >= 0 && str.charAt(i4) == '\\') {
                i4--;
            }
            if ((start - i4) % 2 != 0) {
                int parseInt = Integer.parseInt(matcher.group().substring(2), 16);
                sb.append(str, i3, matcher.start());
                if (parseInt == 92) {
                    sb.append("\\\\");
                } else {
                    sb.append(Character.toChars(parseInt));
                }
                i3 = matcher.end();
            }
        }
        if (sb == null) {
            return str;
        }
        if (i3 < matcher.regionEnd()) {
            sb.append(str, i3, matcher.regionEnd());
        }
        return sb.toString();
    }
}
