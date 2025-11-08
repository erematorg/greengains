package com.google.android.gms.auth;

import android.support.v4.media.session.a;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;
import org.objectweb.asm.signature.SignatureVisitor;

public final class CookieUtil {
    private CookieUtil() {
    }

    @NonNull
    public static String getCookieUrl(@NonNull String str, @Nullable Boolean bool) {
        Preconditions.checkNotEmpty(str);
        return a.n(true != zza(bool) ? "http" : Constants.SCHEME, "://", str);
    }

    @NonNull
    public static String getCookieValue(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Long l2) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(SignatureVisitor.INSTANCEOF);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(str2);
        }
        if (zza(bool)) {
            sb.append(";HttpOnly");
        }
        if (zza(bool2)) {
            sb.append(";Secure");
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append(";Domain=");
            sb.append(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append(";Path=");
            sb.append(str4);
        }
        if (l2 != null && l2.longValue() > 0) {
            sb.append(";Max-Age=");
            sb.append(l2);
        }
        if (!TextUtils.isEmpty((CharSequence) null)) {
            sb.append(";Priority=null");
        }
        if (!TextUtils.isEmpty((CharSequence) null)) {
            sb.append(";SameSite=null");
        }
        if (zza((Boolean) null)) {
            sb.append(";SameParty");
        }
        return sb.toString();
    }

    private static boolean zza(@Nullable Boolean bool) {
        return bool != null && bool.booleanValue();
    }
}
