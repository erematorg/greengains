package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;

final class zzt extends zzw {
    final /* synthetic */ zzu zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzt(zzu zzu, zzx zzx, CharSequence charSequence) {
        super(zzx, charSequence);
        this.zza = zzu;
    }

    public final int zzc(int i3) {
        return i3 + 1;
    }

    public final int zzd(int i3) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzs.zzb(i3, length, FirebaseAnalytics.Param.INDEX);
        while (i3 < length) {
            zzu zzu = this.zza;
            if (zzu.zza.zza(charSequence.charAt(i3))) {
                return i3;
            }
            i3++;
        }
        return -1;
    }
}
