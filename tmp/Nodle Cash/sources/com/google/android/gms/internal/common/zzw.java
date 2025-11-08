package com.google.android.gms.internal.common;

import javax.annotation.CheckForNull;

abstract class zzw extends zzj {
    final CharSequence zzb;
    final zzo zzc;
    final boolean zzd;
    int zze = 0;
    int zzf;

    public zzw(zzx zzx, CharSequence charSequence) {
        this.zzc = zzx.zza;
        this.zzd = zzx.zzb;
        this.zzf = Integer.MAX_VALUE;
        this.zzb = charSequence;
    }

    @CheckForNull
    public final /* bridge */ /* synthetic */ Object zza() {
        int zzd2;
        int i3;
        int i4 = this.zze;
        while (true) {
            int i5 = this.zze;
            if (i5 != -1) {
                zzd2 = zzd(i5);
                if (zzd2 == -1) {
                    zzd2 = this.zzb.length();
                    this.zze = -1;
                    i3 = -1;
                } else {
                    i3 = zzc(zzd2);
                    this.zze = i3;
                }
                if (i3 == i4) {
                    int i6 = i3 + 1;
                    this.zze = i6;
                    if (i6 > this.zzb.length()) {
                        this.zze = -1;
                    }
                } else {
                    if (i4 < zzd2) {
                        this.zzb.charAt(i4);
                    }
                    if (i4 < zzd2) {
                        this.zzb.charAt(zzd2 - 1);
                    }
                    if (!this.zzd || i4 != zzd2) {
                        int i7 = this.zzf;
                    } else {
                        i4 = this.zze;
                    }
                }
            } else {
                zzb();
                return null;
            }
        }
        int i72 = this.zzf;
        if (i72 == 1) {
            zzd2 = this.zzb.length();
            this.zze = -1;
            if (zzd2 > i4) {
                this.zzb.charAt(zzd2 - 1);
            }
        } else {
            this.zzf = i72 - 1;
        }
        return this.zzb.subSequence(i4, zzd2).toString();
    }

    public abstract int zzc(int i3);

    public abstract int zzd(int i3);
}
