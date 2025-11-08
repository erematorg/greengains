package com.phrase.android.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0000\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0000\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u0001\u001a/\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u0002H\u00100\u0013H\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0015"}, d2 = {"getAppVersion", "", "context", "Landroid/content/Context;", "localeFromString", "Ljava/util/Locale;", "locale", "normalizeLocaleStringForApi", "phraseLog", "", "s", "", "sha512", "fromHtml", "", "use", "R", "Landroid/content/res/TypedArray;", "block", "Lkotlin/Function1;", "(Landroid/content/res/TypedArray;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sdk_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class UtilsKt {
    @NotNull
    public static final CharSequence fromHtml(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Spanned fromHtml = Html.fromHtml(str, 63);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "{\n        Html.fromHtml(…_HTML_MODE_COMPACT)\n    }");
        return fromHtml;
    }

    @Nullable
    public static final String getAppVersion(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "context.packageManager.g…o(context.packageName, 0)");
            String str = packageInfo.versionName;
            if (str != null && !StringsKt.isBlank(str)) {
                return packageInfo.versionName;
            }
            throw new PackageManager.NameNotFoundException();
        } catch (PackageManager.NameNotFoundException unused) {
            phraseLog("Could not read app version");
            return null;
        }
    }

    @Nullable
    public static final Locale localeFromString(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "locale");
        Object[] array = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"_"}, false, 0, 6, (Object) null).toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        if (strArr.length == 1) {
            return new Locale(strArr[0]);
        }
        if (strArr.length == 2 || (strArr.length == 3 && StringsKt__StringsJVMKt.startsWith$default(strArr[2], "#", false, 2, (Object) null))) {
            return new Locale(strArr[0], strArr[1]);
        }
        if (strArr.length >= 3) {
            return new Locale(strArr[0], strArr[1], strArr[2]);
        }
        return null;
    }

    @NotNull
    public static final String normalizeLocaleStringForApi(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "locale");
        if (!StringsKt__StringsKt.contains$default((CharSequence) str, '#', false, 2, (Object) null)) {
            return str;
        }
        Object[] array = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"_"}, false, 0, 6, (Object) null).toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        int length = strArr.length;
        if (length == 1) {
            return strArr[0];
        }
        if (length != 2) {
            return strArr[0] + SignatureVisitor.SUPER + StringsKt.drop(strArr[2], 1) + SignatureVisitor.SUPER + strArr[1];
        }
        return strArr[0] + SignatureVisitor.SUPER + strArr[1];
    }

    public static final void phraseLog(@Nullable Object obj) {
        Log.d("Phrase OTA", String.valueOf(obj));
    }

    @NotNull
    public static final String sha512(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "s");
        MessageDigest instance = MessageDigest.getInstance("SHA-512");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] digest = instance.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "digest");
        StringBuilder sb = new StringBuilder();
        for (byte valueOf : digest) {
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(valueOf)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
            sb.append(format);
            Intrinsics.checkNotNullExpressionValue(sb, "sb.append(\"%02x\".format(byte))");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "digest.fold(StringBuilde…rmat(byte)) }).toString()");
        return sb2;
    }

    public static final <R> R use(@NotNull TypedArray typedArray, @NotNull Function1<? super TypedArray, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        R invoke = function1.invoke(typedArray);
        typedArray.recycle();
        return invoke;
    }
}
