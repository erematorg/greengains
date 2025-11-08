package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzo implements DynamiteModule.VersionPolicy.IVersions {
    private final int zza;

    public zzo(int i3, int i4) {
        this.zza = i3;
    }

    public final int zza(Context context, String str) {
        return this.zza;
    }

    public final int zzb(Context context, String str, boolean z2) {
        return 0;
    }
}
