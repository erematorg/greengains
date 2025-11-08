package com.google.android.gms.internal.p002firebaseauthapi;

import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaf  reason: invalid package */
abstract class zzaf extends zzi<String> {
    final CharSequence zza;
    private final zzj zzb;
    private final boolean zzc;
    private int zzd = 0;
    private int zze;

    public zzaf(zzac zzac, CharSequence charSequence) {
        this.zzb = zzac.zza;
        this.zzc = false;
        this.zze = zzac.zzd;
        this.zza = charSequence;
    }

    public abstract int zza(int i3);

    @CheckForNull
    public final /* synthetic */ Object zza() {
        int i3;
        int i4 = this.zzd;
        while (true) {
            int i5 = this.zzd;
            if (i5 != -1) {
                int zzb2 = zzb(i5);
                if (zzb2 == -1) {
                    zzb2 = this.zza.length();
                    this.zzd = -1;
                } else {
                    this.zzd = zza(zzb2);
                }
                int i6 = this.zzd;
                if (i6 == i4) {
                    int i7 = i6 + 1;
                    this.zzd = i7;
                    if (i7 > this.zza.length()) {
                        this.zzd = -1;
                    }
                } else {
                    while (i4 < zzb2 && this.zzb.zza(this.zza.charAt(i4))) {
                        i4++;
                    }
                    while (i3 > i4 && this.zzb.zza(this.zza.charAt(i3 - 1))) {
                        zzb2 = i3 - 1;
                    }
                    int i8 = this.zze;
                    if (i8 == 1) {
                        i3 = this.zza.length();
                        this.zzd = -1;
                        while (i3 > i4 && this.zzb.zza(this.zza.charAt(i3 - 1))) {
                            i3--;
                        }
                    } else {
                        this.zze = i8 - 1;
                    }
                    return this.zza.subSequence(i4, i3).toString();
                }
            } else {
                zzb();
                return null;
            }
        }
    }

    public abstract int zzb(int i3);
}
