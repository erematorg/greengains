package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class zzfb {
    @NotNull
    public static final zzfb zza = new zzfb();
    @NotNull
    private static final List zzb = zze(CollectionsKt.listOf("www.recaptcha.net", "www.gstatic.com/recaptcha", "www.gstatic.cn/recaptcha"));

    private zzfb() {
    }

    public static final boolean zza(@NotNull Uri uri) {
        return zzd(uri) && zzc(uri.toString());
    }

    public static final boolean zzb(@NotNull Uri uri) {
        return zzd(uri);
    }

    private static final boolean zzc(String str) {
        List<String> list = zzb;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (String e02 : list) {
            if (StringsKt__StringsJVMKt.startsWith$default(str, e02, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean zzd(Uri uri) {
        return !TextUtils.isEmpty(uri.toString()) && Intrinsics.areEqual((Object) Constants.SCHEME, (Object) uri.getScheme()) && !TextUtils.isEmpty(uri.getHost());
    }

    private static final List zze(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add("https://" + ((String) it.next()) + "/");
        }
        return arrayList;
    }
}
