package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzi implements DynamiteModule.VersionPolicy {
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.localVersion = iVersions.zza(context, str);
        int i3 = 1;
        int zzb = iVersions.zzb(context, str, true);
        selectionResult.remoteVersion = zzb;
        int i4 = selectionResult.localVersion;
        if (i4 == 0) {
            i4 = 0;
            if (zzb == 0) {
                i3 = 0;
                selectionResult.selection = i3;
                return selectionResult;
            }
        }
        if (i4 >= zzb) {
            i3 = -1;
        }
        selectionResult.selection = i3;
        return selectionResult;
    }
}
