package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.core.content.PermissionChecker;

final class zzd extends zzk {
    public final int zza(Context context, zzj zzj, boolean z2) {
        return (zzj.zza.getAuthority().lastIndexOf(64) < 0 || PermissionChecker.checkSelfPermission(context, "android.permission.INTERACT_ACROSS_USERS") != 0) ? 3 : 2;
    }
}
