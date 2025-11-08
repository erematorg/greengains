package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzj implements DynamiteModule.VersionPolicy {
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        int i3;
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int zza = iVersions.zza(context, str);
        selectionResult.localVersion = zza;
        int i4 = 1;
        int i5 = 0;
        if (zza != 0) {
            i3 = iVersions.zzb(context, str, false);
            selectionResult.remoteVersion = i3;
        } else {
            i3 = iVersions.zzb(context, str, true);
            selectionResult.remoteVersion = i3;
        }
        int i6 = selectionResult.localVersion;
        if (i6 != 0) {
            i5 = i6;
        } else if (i3 == 0) {
            i4 = 0;
            selectionResult.selection = i4;
            return selectionResult;
        }
        if (i5 >= i3) {
            i4 = -1;
        }
        selectionResult.selection = i4;
        return selectionResult;
    }
}
