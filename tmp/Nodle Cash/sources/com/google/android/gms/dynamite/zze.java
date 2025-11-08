package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zze implements DynamiteModule.VersionPolicy.IVersions {
    public final int zza(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str);
    }

    public final int zzb(Context context, String str, boolean z2) throws DynamiteModule.LoadingException {
        return DynamiteModule.zza(context, str, z2);
    }
}
