package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagr  reason: invalid package */
public final class zzagr {
    private final int zza;
    private List<String> zzb;

    public zzagr() {
        this((List<String>) null);
    }

    public static zzagr zza() {
        return new zzagr((List<String>) null);
    }

    public final List<String> zzb() {
        return this.zzb;
    }

    private zzagr(@Nullable List<String> list) {
        this.zza = 1;
        this.zzb = new ArrayList();
    }

    public zzagr(int i3, List<String> list) {
        this.zza = 1;
        if (list == null || list.isEmpty()) {
            this.zzb = Collections.emptyList();
            return;
        }
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.set(i4, Strings.emptyToNull(list.get(i4)));
        }
        this.zzb = Collections.unmodifiableList(list);
    }
}
