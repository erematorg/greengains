package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

final class zziu extends zzir<Boolean> {
    public zziu(zziz zziz, String str, Boolean bool, boolean z2) {
        super(zziz, str, bool);
    }

    @Nullable
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzhq.zzc.matcher(str).matches()) {
                return Boolean.TRUE;
            }
            if (zzhq.zzd.matcher(str).matches()) {
                return Boolean.FALSE;
            }
        }
        String zzb = zzb();
        String valueOf = String.valueOf(obj);
        Log.e("PhenotypeFlag", "Invalid boolean value for " + zzb + ": " + valueOf);
        return null;
    }
}
