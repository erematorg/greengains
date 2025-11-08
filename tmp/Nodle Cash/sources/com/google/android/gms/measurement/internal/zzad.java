package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzfn;

final class zzad extends zzaa {
    private zzfn.zze zzg;
    private final /* synthetic */ zzv zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzad(zzv zzv, String str, int i3, zzfn.zze zze) {
        super(str, i3);
        this.zzh = zzv;
        this.zzg = zze;
    }

    public final int zza() {
        return this.zzg.zza();
    }

    public final boolean zzb() {
        return false;
    }

    public final boolean zzc() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r4v17, types: [java.lang.Integer] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(java.lang.Long r11, java.lang.Long r12, com.google.android.gms.internal.measurement.zzgn.zzo r13, boolean r14) {
        /*
            r10 = this;
            boolean r0 = com.google.android.gms.internal.measurement.zzqb.zza()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x001a
            com.google.android.gms.measurement.internal.zzv r0 = r10.zzh
            com.google.android.gms.measurement.internal.zzah r0 = r0.zze()
            java.lang.String r3 = r10.zza
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbj.zzbm
            boolean r0 = r0.zzf(r3, r4)
            if (r0 == 0) goto L_0x001a
            r0 = r2
            goto L_0x001b
        L_0x001a:
            r0 = r1
        L_0x001b:
            com.google.android.gms.internal.measurement.zzfn$zze r3 = r10.zzg
            boolean r3 = r3.zzf()
            com.google.android.gms.internal.measurement.zzfn$zze r4 = r10.zzg
            boolean r4 = r4.zzg()
            com.google.android.gms.internal.measurement.zzfn$zze r5 = r10.zzg
            boolean r5 = r5.zzh()
            if (r3 != 0) goto L_0x0036
            if (r4 != 0) goto L_0x0036
            if (r5 == 0) goto L_0x0034
            goto L_0x0036
        L_0x0034:
            r3 = r1
            goto L_0x0037
        L_0x0036:
            r3 = r2
        L_0x0037:
            r4 = 0
            if (r14 == 0) goto L_0x0064
            if (r3 != 0) goto L_0x0064
            com.google.android.gms.measurement.internal.zzv r11 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r11 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r11 = r11.zzp()
            int r12 = r10.zzb
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            com.google.android.gms.internal.measurement.zzfn$zze r13 = r10.zzg
            boolean r13 = r13.zzi()
            if (r13 == 0) goto L_0x005e
            com.google.android.gms.internal.measurement.zzfn$zze r10 = r10.zzg
            int r10 = r10.zza()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
        L_0x005e:
            java.lang.String r10 = "Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID"
            r11.zza(r10, r12, r4)
            return r2
        L_0x0064:
            com.google.android.gms.internal.measurement.zzfn$zze r6 = r10.zzg
            com.google.android.gms.internal.measurement.zzfn$zzc r6 = r6.zzb()
            boolean r7 = r6.zzf()
            boolean r8 = r13.zzk()
            if (r8 == 0) goto L_0x00ab
            boolean r8 = r6.zzh()
            if (r8 != 0) goto L_0x0099
            com.google.android.gms.measurement.internal.zzv r6 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzu()
            com.google.android.gms.measurement.internal.zzv r7 = r10.zzh
            com.google.android.gms.measurement.internal.zzgh r7 = r7.zzi()
            java.lang.String r8 = r13.zzg()
            java.lang.String r7 = r7.zzc(r8)
            java.lang.String r8 = "No number filter for long property. property"
            r6.zza(r8, r7)
            goto L_0x0189
        L_0x0099:
            long r8 = r13.zzc()
            com.google.android.gms.internal.measurement.zzfn$zzd r4 = r6.zzc()
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((long) r8, (com.google.android.gms.internal.measurement.zzfn.zzd) r4)
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((java.lang.Boolean) r4, (boolean) r7)
            goto L_0x0189
        L_0x00ab:
            boolean r8 = r13.zzi()
            if (r8 == 0) goto L_0x00e8
            boolean r8 = r6.zzh()
            if (r8 != 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzv r6 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzu()
            com.google.android.gms.measurement.internal.zzv r7 = r10.zzh
            com.google.android.gms.measurement.internal.zzgh r7 = r7.zzi()
            java.lang.String r8 = r13.zzg()
            java.lang.String r7 = r7.zzc(r8)
            java.lang.String r8 = "No number filter for double property. property"
            r6.zza(r8, r7)
            goto L_0x0189
        L_0x00d6:
            double r8 = r13.zza()
            com.google.android.gms.internal.measurement.zzfn$zzd r4 = r6.zzc()
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((double) r8, (com.google.android.gms.internal.measurement.zzfn.zzd) r4)
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((java.lang.Boolean) r4, (boolean) r7)
            goto L_0x0189
        L_0x00e8:
            boolean r8 = r13.zzm()
            if (r8 == 0) goto L_0x016c
            boolean r8 = r6.zzj()
            if (r8 != 0) goto L_0x0155
            boolean r8 = r6.zzh()
            if (r8 != 0) goto L_0x0118
            com.google.android.gms.measurement.internal.zzv r6 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzu()
            com.google.android.gms.measurement.internal.zzv r7 = r10.zzh
            com.google.android.gms.measurement.internal.zzgh r7 = r7.zzi()
            java.lang.String r8 = r13.zzg()
            java.lang.String r7 = r7.zzc(r8)
            java.lang.String r8 = "No string or number filter defined. property"
            r6.zza(r8, r7)
            goto L_0x0189
        L_0x0118:
            java.lang.String r8 = r13.zzh()
            boolean r8 = com.google.android.gms.measurement.internal.zzol.zzb((java.lang.String) r8)
            if (r8 == 0) goto L_0x0133
            java.lang.String r4 = r13.zzh()
            com.google.android.gms.internal.measurement.zzfn$zzd r6 = r6.zzc()
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((java.lang.String) r4, (com.google.android.gms.internal.measurement.zzfn.zzd) r6)
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((java.lang.Boolean) r4, (boolean) r7)
            goto L_0x0189
        L_0x0133:
            com.google.android.gms.measurement.internal.zzv r6 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzu()
            com.google.android.gms.measurement.internal.zzv r7 = r10.zzh
            com.google.android.gms.measurement.internal.zzgh r7 = r7.zzi()
            java.lang.String r8 = r13.zzg()
            java.lang.String r7 = r7.zzc(r8)
            java.lang.String r8 = r13.zzh()
            java.lang.String r9 = "Invalid user property value for Numeric number filter. property, value"
            r6.zza(r9, r7, r8)
            goto L_0x0189
        L_0x0155:
            java.lang.String r4 = r13.zzh()
            com.google.android.gms.internal.measurement.zzfn$zzf r6 = r6.zzd()
            com.google.android.gms.measurement.internal.zzv r8 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r8 = r8.zzj()
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((java.lang.String) r4, (com.google.android.gms.internal.measurement.zzfn.zzf) r6, (com.google.android.gms.measurement.internal.zzgi) r8)
            java.lang.Boolean r4 = com.google.android.gms.measurement.internal.zzaa.zza((java.lang.Boolean) r4, (boolean) r7)
            goto L_0x0189
        L_0x016c:
            com.google.android.gms.measurement.internal.zzv r6 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzu()
            com.google.android.gms.measurement.internal.zzv r7 = r10.zzh
            com.google.android.gms.measurement.internal.zzgh r7 = r7.zzi()
            java.lang.String r8 = r13.zzg()
            java.lang.String r7 = r7.zzc(r8)
            java.lang.String r8 = "User property has no value, property"
            r6.zza(r8, r7)
        L_0x0189:
            com.google.android.gms.measurement.internal.zzv r6 = r10.zzh
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzp()
            if (r4 != 0) goto L_0x0198
            java.lang.String r7 = "null"
            goto L_0x0199
        L_0x0198:
            r7 = r4
        L_0x0199:
            java.lang.String r8 = "Property filter result"
            r6.zza(r8, r7)
            if (r4 != 0) goto L_0x01a1
            return r1
        L_0x01a1:
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            r10.zzc = r1
            if (r5 == 0) goto L_0x01ae
            boolean r1 = r4.booleanValue()
            if (r1 != 0) goto L_0x01ae
            return r2
        L_0x01ae:
            if (r14 == 0) goto L_0x01b8
            com.google.android.gms.internal.measurement.zzfn$zze r14 = r10.zzg
            boolean r14 = r14.zzf()
            if (r14 == 0) goto L_0x01ba
        L_0x01b8:
            r10.zzd = r4
        L_0x01ba:
            boolean r14 = r4.booleanValue()
            if (r14 == 0) goto L_0x01ff
            if (r3 == 0) goto L_0x01ff
            boolean r14 = r13.zzl()
            if (r14 == 0) goto L_0x01ff
            long r13 = r13.zzd()
            if (r11 == 0) goto L_0x01d2
            long r13 = r11.longValue()
        L_0x01d2:
            if (r0 == 0) goto L_0x01ea
            com.google.android.gms.internal.measurement.zzfn$zze r11 = r10.zzg
            boolean r11 = r11.zzf()
            if (r11 == 0) goto L_0x01ea
            com.google.android.gms.internal.measurement.zzfn$zze r11 = r10.zzg
            boolean r11 = r11.zzg()
            if (r11 != 0) goto L_0x01ea
            if (r12 == 0) goto L_0x01ea
            long r13 = r12.longValue()
        L_0x01ea:
            com.google.android.gms.internal.measurement.zzfn$zze r11 = r10.zzg
            boolean r11 = r11.zzg()
            if (r11 == 0) goto L_0x01f9
            java.lang.Long r11 = java.lang.Long.valueOf(r13)
            r10.zzf = r11
            goto L_0x01ff
        L_0x01f9:
            java.lang.Long r11 = java.lang.Long.valueOf(r13)
            r10.zze = r11
        L_0x01ff:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.Long, java.lang.Long, com.google.android.gms.internal.measurement.zzgn$zzo, boolean):boolean");
    }
}
