package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.a;
import org.apache.commons.text.StringSubstitutor;

final class zzce extends zzcm {
    private final String zzc;
    private final boolean zzd;
    private final boolean zze;
    private final zzcc zzf;
    private final zzcb zzg;
    private final zzco zzh;

    public /* synthetic */ zzce(String str, boolean z2, boolean z3, zzcc zzcc, zzcb zzcb, zzco zzco, zzcg zzcg) {
        this(str, false, z3, (zzcc) null, (zzcb) null, zzco);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        r1 = r4.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        r1 = r4.zzg;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.internal.measurement.zzcm
            r2 = 0
            if (r1 == 0) goto L_0x005e
            com.google.android.gms.internal.measurement.zzcm r5 = (com.google.android.gms.internal.measurement.zzcm) r5
            java.lang.String r1 = r4.zzc
            java.lang.String r3 = r5.zzd()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
            boolean r1 = r4.zzd
            boolean r3 = r5.zze()
            if (r1 != r3) goto L_0x005e
            boolean r1 = r4.zze
            boolean r3 = r5.zzf()
            if (r1 != r3) goto L_0x005e
            com.google.android.gms.internal.measurement.zzcc r1 = r4.zzf
            if (r1 != 0) goto L_0x0032
            com.google.android.gms.internal.measurement.zzcc r1 = r5.zza()
            if (r1 != 0) goto L_0x005e
            goto L_0x003c
        L_0x0032:
            com.google.android.gms.internal.measurement.zzcc r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
        L_0x003c:
            com.google.android.gms.internal.measurement.zzcb r1 = r4.zzg
            if (r1 != 0) goto L_0x0047
            com.google.android.gms.internal.measurement.zzcb r1 = r5.zzb()
            if (r1 != 0) goto L_0x005e
            goto L_0x0051
        L_0x0047:
            com.google.android.gms.internal.measurement.zzcb r3 = r5.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
        L_0x0051:
            com.google.android.gms.internal.measurement.zzco r4 = r4.zzh
            com.google.android.gms.internal.measurement.zzco r5 = r5.zzc()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x005e
            return r0
        L_0x005e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzce.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i3 = 1237;
        int hashCode = (((this.zzc.hashCode() ^ 1000003) * 1000003) ^ (this.zzd ? 1231 : 1237)) * 1000003;
        if (this.zze) {
            i3 = 1231;
        }
        int i4 = (hashCode ^ i3) * 1000003;
        zzcc zzcc = this.zzf;
        int i5 = 0;
        int hashCode2 = (i4 ^ (zzcc == null ? 0 : zzcc.hashCode())) * 1000003;
        zzcb zzcb = this.zzg;
        if (zzcb != null) {
            i5 = zzcb.hashCode();
        }
        return this.zzh.hashCode() ^ ((hashCode2 ^ i5) * 1000003);
    }

    public final String toString() {
        String str = this.zzc;
        boolean z2 = this.zzd;
        boolean z3 = this.zze;
        String valueOf = String.valueOf(this.zzf);
        String valueOf2 = String.valueOf(this.zzg);
        String valueOf3 = String.valueOf(this.zzh);
        StringBuilder sb = new StringBuilder("FileComplianceOptions{fileOwner=");
        sb.append(str);
        sb.append(", hasDifferentDmaOwner=");
        sb.append(z2);
        sb.append(", skipChecks=");
        sb.append(z3);
        sb.append(", dataForwardingNotAllowedResolver=");
        sb.append(valueOf);
        sb.append(", multipleProductIdGroupsResolver=");
        return a.r(sb, valueOf2, ", filePurpose=", valueOf3, StringSubstitutor.DEFAULT_VAR_END);
    }

    public final zzcc zza() {
        return this.zzf;
    }

    public final zzcb zzb() {
        return this.zzg;
    }

    public final zzco zzc() {
        return this.zzh;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        return this.zze;
    }

    private zzce(String str, boolean z2, boolean z3, zzcc zzcc, zzcb zzcb, zzco zzco) {
        this.zzc = str;
        this.zzd = z2;
        this.zze = z3;
        this.zzf = null;
        this.zzg = null;
        this.zzh = zzco;
    }
}
